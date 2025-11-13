package com.auto.carsales.service;

import com.auto.carsales.model.Animal;
import com.auto.carsales.repo.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

   private final AnimalRepo repo;
    @Autowired
    public AnimalService(AnimalRepo repo) {
        this.repo = repo;
    }

    public List<Animal> getAllAnimals(){
      return repo.findAll();
    }

    public Optional<Animal> getAnimalById(Long id){
       return repo.findById(id);
    }

    public Animal addAnimal(Animal animal){
      return  repo.save(animal);
    }


}
