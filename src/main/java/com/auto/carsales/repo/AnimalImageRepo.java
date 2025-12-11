package com.auto.carsales.repo;

import com.auto.carsales.model.AnimalImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalImageRepo extends JpaRepository<AnimalImage,Long> {
    List<AnimalImage> findByAnimalId(Long animalId);
    Optional<AnimalImage> findByAnimalIdAndIsPrimaryTrue(Long animalId);

    List<AnimalImage> findByAnimalIdOrderByIsPrimaryDesc(Long animalId);
}
