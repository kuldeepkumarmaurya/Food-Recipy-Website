package com.pets.petcare.Entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.Builder;

import java.net.ProtocolFamily;

@Entity
@Builder
public class FileDataEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

    public FileDataEntity(Long imageId, String name, String type, byte[] imageData) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public FileDataEntity() {
    }


    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
