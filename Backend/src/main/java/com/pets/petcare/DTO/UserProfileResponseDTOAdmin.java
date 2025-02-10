package com.pets.petcare.DTO;

public class UserProfileResponseDTOAdmin {

    private String name;
    private String email;
    private String bio;
    private String phoneNumber;
    private Boolean verified;

    public UserProfileResponseDTOAdmin(String name, String email, String bio, String phoneNumber, Boolean verified) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.verified = verified;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
}
