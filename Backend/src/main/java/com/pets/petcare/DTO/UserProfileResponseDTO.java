package com.pets.petcare.DTO;

public class UserProfileResponseDTO {
    private String name;
    private String bio;
    private String email;
    private String gender;
    private String phoneNmbr;
    private String profilePic;
    private String nmbrofPost;
    private String numberoffollower;
    private String numberoffollowing;

    public UserProfileResponseDTO(String name, String bio, String email, String gender, String phoneNmbr, String profilePic, String nmbrofPost, String numberoffollower, String numberoffollowing) {
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.gender = gender;
        this.phoneNmbr = phoneNmbr;
        this.profilePic = profilePic;
        this.nmbrofPost = nmbrofPost;
        this.numberoffollower = numberoffollower;
        this.numberoffollowing = numberoffollowing;
    }

    public UserProfileResponseDTO() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getNmbrofPost() {
        return nmbrofPost;
    }

    public void setNmbrofPost(String nmbrofPost) {
        this.nmbrofPost = nmbrofPost;
    }

    public String getNumberoffollower() {
        return numberoffollower;
    }

    public void setNumberoffollower(String numberoffollower) {
        this.numberoffollower = numberoffollower;
    }

    public String getNumberoffollowing() {
        return numberoffollowing;
    }

    public void setNumberoffollowing(String numberoffollowing) {
        this.numberoffollowing = numberoffollowing;
    }
}
