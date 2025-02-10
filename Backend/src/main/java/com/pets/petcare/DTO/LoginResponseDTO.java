package com.pets.petcare.DTO;

public class LoginResponseDTO {
    private String message;
    private Long userId;
    private int statusCode;

    public LoginResponseDTO(String message, Long userId, int statusCode) {
        this.message = message;
        this.userId = userId;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
