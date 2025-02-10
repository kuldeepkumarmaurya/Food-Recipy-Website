package com.pets.petcare.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String profilepic;
    private String gender;
    private String phoneNmbr;

    private String otp;
    private LocalDateTime otpExpiryTime;
    private Boolean varified;

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiryTime() {
        return otpExpiryTime;
    }

    public void setOtpExpiryTime(LocalDateTime otpExpiryTime) {
        this.otpExpiryTime = otpExpiryTime;
    }

    public Boolean getVarified() {
        return varified;
    }

    public void setVarified(Boolean varified) {
        this.varified = varified;
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

