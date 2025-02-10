package com.pets.petcare.DTO;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProfileDTO {
    private String name;
    private String bio;
    private String gender;
    private String phoneNmbr;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNmbr() {
        return phoneNmbr;
    }

    public void setPhoneNmbr(String phoneNmbr) {
        this.phoneNmbr = phoneNmbr;
    }
}
