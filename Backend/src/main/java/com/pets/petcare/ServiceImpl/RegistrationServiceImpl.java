package com.pets.petcare.ServiceImpl;

import com.pets.petcare.DTO.*;
import com.pets.petcare.Entity.FileDataEntity;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.Entity.UserFollowEntity;
import com.pets.petcare.ServiceI.RegistrationServiceI;
import com.pets.petcare.Utility.EmailService;
import com.pets.petcare.Utility.ImageUtils;
import com.pets.petcare.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RegistrationServiceImpl implements RegistrationServiceI {


    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    FileDataRepository fileDataRepository;

    @Autowired
    RecipePostRepository recipePostRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    public String verifyOtp(String email, String otp) {
        Optional<RegistrationEntity> userOptional = registrationRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            RegistrationEntity user = userOptional.get();
            // Check if OTP is valid and not expired
            if (user.getOtp().equals(otp) && user.getOtpExpiryTime().isAfter(LocalDateTime.now())) {
                user.setOtp(null); // Clear OTP after successful verification
                user.setOtpExpiryTime(null); // Clear OTP expiration time
                user.setVarified(true);
                registrationRepository.save(user); // Update user entity in the database
                return "OTP verified. Registration complete!";
            } else {
                return "Invalid or expired OTP.";
            }
        } else {
            return "User not found.";
        }
    }

    public String registerUser(RegistrationDTO dto) {
        // Check for empty or null fields
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) return "Please enter field: email";
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) return "Please enter field: password";
        if (dto.getName() == null || dto.getName().isEmpty()) return "Please enter field: name";
        if (dto.getPhoneNmbr() == null || dto.getPhoneNmbr().isEmpty()) return "Please enter field: phone number";

        // Check if email is already registered
        Optional<RegistrationEntity> existingUser = registrationRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            return "Email is already registered";
        }

        // Generate OTP and expiry time
        String otp = generateOTP();
        LocalDateTime otpExpiryTime = LocalDateTime.now().plusMinutes(10); // OTP valid for 10 minutes

        // Create a new registration entity
        RegistrationEntity newUser = new RegistrationEntity();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        newUser.setName(dto.getName());
        newUser.setProfilepic("8044518e-1e1d-48aa-8881-cb1d3e179c58.png");
        newUser.setBio("New User");
        newUser.setOtp(otp);
        newUser.setOtpExpiryTime(otpExpiryTime);
        newUser.setVarified(false);

        // Save user to the database
        registrationRepository.save(newUser);

        // Send OTP email
        emailService.sendEmail(dto.getEmail(), "Your OTP", "Your OTP is: " + otp);

        return "Registration successful! Please check your email for OTP.";
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
        return String.valueOf(otp);
    }


    @Override

    public LoginResponseDTO loginUser(LoginDTO loginDTO) throws NoSuchAlgorithmException {

        Optional<RegistrationEntity> userOpt = registrationRepository.findByEmailAndVarifiedTrue(loginDTO.getEmail());

        // Check if the email exists
        if (userOpt.isEmpty()) {
            return new LoginResponseDTO("User not found. Please register.", null, HttpStatus.NOT_FOUND.value());
        }

        RegistrationEntity user = userOpt.get();

        // Check if the password matches
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return new LoginResponseDTO("Wrong password. Please try again.", null, HttpStatus.UNAUTHORIZED.value());
        }

        // If both email and password are correct
        return new LoginResponseDTO("Login successful!", user.getRegId(), HttpStatus.OK.value());
    }

    @Override

    public UserProfileResponseDTO getUserProfileById(Long id) {
        Optional<RegistrationEntity> userOpt = registrationRepository.findById(id);

        // Check if the user exists
        if (userOpt.isEmpty()) {
            return null;  // User not found
        }

        List<RecipePostEntity> allpost = recipePostRepository.findAllByUserId(id);
        List<UserFollowEntity> follower = userFollowRepository.findAllByFollowingId(id);
        List<UserFollowEntity> following = userFollowRepository.findAllByFollowerId(id);
        UserProfileResponseDTO responseDTO = new UserProfileResponseDTO();

        responseDTO.setProfilePic(userOpt.get().getProfilepic());
        responseDTO.setName(userOpt.get().getName());
        responseDTO.setBio(userOpt.get().getBio());
        responseDTO.setNmbrofPost(String.valueOf(allpost.size()));
        responseDTO.setNumberoffollower(String.valueOf(follower.size()));
        responseDTO.setNumberoffollowing(String.valueOf(following.size()));
        return responseDTO;




        // Create and return UserProfileResponseDTO

    }

    @Override
    public UserProfileResponseDTO updateUserProfile(Long id, UpdateProfileDTO updateProfileDTO) {
        Optional<RegistrationEntity> userOpt = registrationRepository.findById(id);

        // Check if the user exists
        if (userOpt.isEmpty()) {
            return null;  // User not found
        }

        RegistrationEntity user = userOpt.get();

        // Update user details
        if (updateProfileDTO.getName() != null) {
            user.setName(updateProfileDTO.getName());
        }
        if (updateProfileDTO.getBio() != null) {
            user.setBio(updateProfileDTO.getBio());
        }
        if (updateProfileDTO.getGender() != null) {
            user.setGender(updateProfileDTO.getGender());
        }
        if (updateProfileDTO.getPhoneNmbr() != null) {
            user.setPhoneNmbr(updateProfileDTO.getPhoneNmbr());
        }
        // Save updated user to the database
        registrationRepository.save(user);
        RegistrationEntity responceUser = user;
        UserProfileResponseDTO responseDTO = new UserProfileResponseDTO();
        responseDTO.setBio(responceUser.getBio());
        responseDTO.setEmail(responceUser.getEmail());
        responseDTO.setName(responceUser.getName());
        responseDTO.setGender( responceUser.getGender());
        responseDTO.setPhoneNmbr(responceUser.getPhoneNmbr());

       return responseDTO;

    }

    @Override
    public FileDataEntity uploadImage(MultipartFile file , Long userId) throws IOException {

        // Generate a random UUID for the file name
        String randomID = UUID.randomUUID().toString();

        // Get the original file extension
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Create a unique file name using the random UUID and the original file extension
        String uniqueFileName = randomID + fileExtension;

        // Save the file with the unique name in the database
        FileDataEntity imageData = fileDataRepository.save(FileDataEntity.builder()
                .name(uniqueFileName)  // Save the unique file name in the database
                .type(file.getContentType()) // Store the MIME type
                .imageData(ImageUtils.compressImage(file.getBytes()))  // Save compressed image
                .build());

        if (imageData != null) {
            Optional<RegistrationEntity> userProfilr = registrationRepository.findById(userId);
            if (userProfilr.isPresent()){
                userProfilr.get().setProfilepic(imageData.getName());
                registrationRepository.save(userProfilr.get());
            }
            return imageData;
        }
        return null;
    }


    // Get all registered users
    public List<RegistrationEntity> getAllUsers() {
        return registrationRepository.findAll();
    }

    // Get user profile by ID
    public UserProfileResponseDTOAdmin getUserProfileByIdadmin(Long id) {
        Optional<RegistrationEntity> userOpt = registrationRepository.findById(id);
        if (userOpt.isPresent()) {
            RegistrationEntity user = userOpt.get();
            // Map entity data to UserProfileResponseDTO
            return new UserProfileResponseDTOAdmin(user.getName(), user.getEmail(), user.getBio(), user.getPhoneNmbr(), user.getVarified());
        }
        throw new RuntimeException("User not found"); // Handle user not found error
    }

    // Verify user
    public void verifyUser(Long userId) {
        Optional<RegistrationEntity> userOpt = registrationRepository.findById(userId);
        if (userOpt.isPresent()) {
            RegistrationEntity user = userOpt.get();
            user.setVarified(true);
            registrationRepository.save(user);
        }
    }

    // Delete user by ID
    public void deleteUser(Long userId) {
        registrationRepository.deleteById(userId);
    }


}
