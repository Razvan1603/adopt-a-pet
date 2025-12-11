package com.auto.carsales.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Animal {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String description;
    private boolean isAvailable;
    @OneToMany (mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AnimalImage> images = new ArrayList<>();



    public Animal() {
    }

    public Animal(Long id, Type type, int age, Gender gender, String description, boolean isAvailable, List<AnimalImage> images) {
        this.id = id;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.isAvailable = isAvailable;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnimalImage> getImages() {
        return images;
    }

    public void setImages(List<AnimalImage> images) {
        this.images = images;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
public void addImage(AnimalImage image){
        images.add(image);
        image.setAnimal(this);
}
public void removeImage(AnimalImage image){
        images.remove(image);
        image.setAnimal(null);
}


}
