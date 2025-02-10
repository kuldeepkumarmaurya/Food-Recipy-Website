package com.pets.petcare.ServiceI;

import com.pets.petcare.DTO.*;
import com.pets.petcare.Entity.FileDataEntity;
import com.pets.petcare.Entity.RegistrationEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface RegistrationServiceI {
    String registerUser(RegistrationDTO registrationDTO);

    String verifyOtp(String email, String otp);

    LoginResponseDTO loginUser(LoginDTO loginDTO) throws NoSuchAlgorithmException;

    UserProfileResponseDTO getUserProfileById(Long id);

    UserProfileResponseDTO updateUserProfile(Long id, UpdateProfileDTO updateProfileDTO);

    FileDataEntity uploadImage(MultipartFile file , Long userId) throws IOException;

    void deleteUser(Long id);

    List<RegistrationEntity> getAllUsers();

    void verifyUser(Long id);
}
