package com.auto.carsales.service;

import com.auto.carsales.model.Animal;
import com.auto.carsales.model.AnimalImage;
import com.auto.carsales.repo.AnimalImageRepo;
import com.auto.carsales.repo.AnimalRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
public class AnimalImageService {

    private final AnimalImageRepo animalImageRepo;
    private final AnimalRepo animalRepo;
    private final S3Service s3Service;

    public AnimalImageService(AnimalImageRepo animalImageRepo, AnimalRepo animalRepo, S3Service s3Service) {
        this.animalImageRepo = animalImageRepo;
        this.animalRepo = animalRepo;
        this.s3Service = s3Service;
    }


    public AnimalImage uploadImage(Long animalId, MultipartFile file, boolean isPrimary) throws IOException {
        Animal animal=animalRepo.findById(animalId).orElseThrow(() -> new EntityNotFoundException("Animal not found!!!"));
        String s3Url=s3Service.uploadFIle(file, "animals/" + animalId);
        String s3Key=extractKeyFromUrl(s3Url);
        if(isPrimary){
            animal.getImages().forEach( img ->img.setPrimary(false));
        }
        AnimalImage animalImage = new AnimalImage();
        animalImage.setS3key(s3Key);
        animalImage.setS3Url(s3Url);
        animalImage.setFileName(file.getOriginalFilename());
        animalImage.setContentType(file.getContentType());
        animalImage.setFileSIze(file.getSize());
        animalImage.setPrimary(isPrimary);
        animal.addImage(animalImage);
        return animalImageRepo.save(animalImage);
    }
    public void deleteImage(Long imageId) {
        AnimalImage image = animalImageRepo.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // Delete from S3
        s3Service.deleteFile(image.getS3key());

        // Delete from database
        image.getAnimal().removeImage(image);
        animalImageRepo.delete(image);
    }
    private String extractKeyFromUrl(String url) {
        // Extract key from full S3 URL
        return url.substring(url.indexOf(".com/") + 5);
    }
    public List<AnimalImage> getAnimalImages(Long animalId) {
        // First verify the animal exists
        if (!animalRepo.existsById(animalId)) {
            throw new RuntimeException("Animal not found with id: " + animalId);
        }

        // Return images ordered by primary first, then by upload date (newest first)
        return animalImageRepo.findByAnimalIdOrderByIsPrimaryDesc(animalId);
    }

    public Optional<AnimalImage> getPrimaryImage(Long animalId) {
        return animalImageRepo.findByAnimalIdAndIsPrimaryTrue(animalId);
    }
}
