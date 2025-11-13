package com.auto.carsales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animal {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private Type type;
    private int age;
    private Gender gender;
    private String description;
    private boolean isAvailable;


    public Animal() {
    }

    public Animal(long id, Type type, int age, Gender gender, String description, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public long getId() {
        return id;
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
}
