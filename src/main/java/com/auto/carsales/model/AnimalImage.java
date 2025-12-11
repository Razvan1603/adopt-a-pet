package com.auto.carsales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Entity
@Table(name="animal_images")
public class AnimalImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType. LAZY)
    @JoinColumn (name="animal_id", nullable = false)
    @JsonBackReference
    private Animal animal;

    private String s3key;

    private String s3Url;

    private String fileName;

    private String contentType;
    private  Long fileSIze;
    @Column(name = "is_primary")
    private boolean isPrimary=false;

    public AnimalImage() {
    }

    public AnimalImage(Long id, Animal animal, String s3key, String s3Url, String fileName, String contentType, Long fileSIze, boolean isPrimary) {
        this.id = id;
        this.animal = animal;
        this.s3key = s3key;
        this.s3Url = s3Url;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSIze = fileSIze;
        this.isPrimary = isPrimary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getS3key() {
        return s3key;
    }

    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSIze() {
        return fileSIze;
    }

    public void setFileSIze(Long fileSIze) {
        this.fileSIze = fileSIze;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
