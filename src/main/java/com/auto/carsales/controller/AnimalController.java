package com.auto.carsales.controller;

import com.auto.carsales.model.Animal;
import com.auto.carsales.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AnimalController {

   private final AnimalService service;


    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        List<Animal> list=service.getAllAnimals();
        if(list!=null){
            return new ResponseEntity<>(service.getAllAnimals(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id){
        Optional<Animal> animal = service.getAnimalById(id);
        return animal
                .map(a -> ResponseEntity.ok(a))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/animals")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal){
        Animal savedAnimal=service.addAnimal(animal);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }
    @DeleteMapping("animals/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id){
        service.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
