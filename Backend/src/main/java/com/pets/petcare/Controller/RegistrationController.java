package com.pets.petcare.Controller;


import com.pets.petcare.DTO.*;
import com.pets.petcare.Entity.FileDataEntity;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.ServiceI.FileDataServiceI;
import com.pets.petcare.ServiceI.RegistrationServiceI;
import com.pets.petcare.ServiceImpl.RegistrationServiceImpl;
import com.pets.petcare.Utility.ImageUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Allow this origin
@RequestMapping("/api/user")
public class RegistrationController {

    @Autowired
    RegistrationServiceI registrationServiceI;

    @Autowired
    FileDataServiceI fileDataServiceI;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            // Call the service to register the user
            String result = registrationServiceI.registerUser(registrationDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            String result = registrationServiceI.verifyOtp(email, otp);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("OTP verification failed ");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        LoginResponseDTO loginResponse = registrationServiceI.loginUser(loginDTO);

        return new ResponseEntity<>(loginResponse, HttpStatus.valueOf(loginResponse.getStatusCode()));
    }

    @GetMapping("/get-profile-by-id/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable Long id) {
        UserProfileResponseDTO userProfile = registrationServiceI.getUserProfileById(id);

        if (userProfile == null) {
            return new ResponseEntity<>("Data not found for the given ID.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PutMapping("/update-profile-by-id/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody UpdateProfileDTO updateProfileDTO) {
        UserProfileResponseDTO updateResponse = registrationServiceI.updateUserProfile(id, updateProfileDTO);

        if (updateResponse == null) {
            return new ResponseEntity<>("User not found for the given ID.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }

    @PostMapping("/save-profile-pic")
    public ResponseEntity<byte[]> uploadImage(@RequestParam("image") MultipartFile file , @RequestParam Long userId) throws IOException {
        FileDataEntity uploadImage = registrationServiceI.uploadImage(file , userId);

        if (uploadImage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String contentType = uploadImage.getType();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .body(ImageUtils.decompressImage(uploadImage.getImageData()));  // Decompress image before returning
    }



}
