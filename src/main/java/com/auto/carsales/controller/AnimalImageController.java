package com.auto.carsales.controller;

import com.auto.carsales.model.AnimalImage;
import com.auto.carsales.service.AnimalImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalImageController {

    private final AnimalImageService service;

    public AnimalImageController(AnimalImageService service) {
        this.service = service;
    }
    @PostMapping("/{id}/images")
    public ResponseEntity<AnimalImage> uploadImage(@PathVariable Long id, @RequestParam MultipartFile file, @RequestParam(value = "isPrimary", defaultValue = "false") boolean isPrimary){
        try {
            AnimalImage image = service.uploadImage(id, file, isPrimary);
            return new ResponseEntity<>( image, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    @GetMapping("/{animalId}/images")
    public ResponseEntity<List<AnimalImage>> getAnimalImages(@PathVariable Long animalId) {
        try {
            List<AnimalImage> images = service.getAnimalImages(animalId);
            return ResponseEntity.ok(images);
        } catch (RuntimeException e) {
            // Animal not found
            return ResponseEntity.notFound().build();
        }
    }


}
