package com.pets.petcare.Controller;

import com.pets.petcare.DTO.UserProfileResponseDTO;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.ServiceI.RegistrationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private RegistrationServiceI registrationService;

    // Fetch all registered users
    @GetMapping("/users")
    public ResponseEntity<List<RegistrationEntity>> getAllUsers() {
        List<RegistrationEntity> users = registrationService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user profile by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfileByIdadmin(@PathVariable Long id) {
        UserProfileResponseDTO userProfile = registrationService.getUserProfileById(id);
        return ResponseEntity.ok(userProfile);
    }

    // Verify a user
    @PostMapping("/user/verify/{id}")
    public ResponseEntity<Void> verifyUser(@PathVariable Long id) {
        registrationService.verifyUser(id);
        return ResponseEntity.ok().build(); // Returns 200 OK if the verification is successful
    }

    // Delete a user
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        registrationService.deleteUser(id);
        return ResponseEntity.ok().build(); // Returns 200 OK if deletion is successful
    }
}
