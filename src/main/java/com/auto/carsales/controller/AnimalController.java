package com.auto.carsales.controller;

import com.auto.carsales.model.Animal;
import com.auto.carsales.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimalController {

   private final AnimalService service;


    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping("animals")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        List<Animal> list=service.getAllAnimals();
        if(list!=null){
            return new ResponseEntity<>(service.getAllAnimals(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
