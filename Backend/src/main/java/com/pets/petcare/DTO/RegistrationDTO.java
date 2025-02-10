package com.pets.petcare.DTO;

public class RegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String phoneNmbr;

    // Getters and Setters
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

    public String getPhoneNmbr() {
        return phoneNmbr;
    }

    public void setPhoneNmbr(String phoneNmbr) {
        this.phoneNmbr = phoneNmbr;
    }
}
