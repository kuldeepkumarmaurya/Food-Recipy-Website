package com.pets.petcare.ServiceImpl;

import com.pets.petcare.Entity.FileDataEntity;
import com.pets.petcare.ServiceI.FileDataServiceI;
import com.pets.petcare.Utility.ImageUtils;
import com.pets.petcare.repository.FileDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDataServiceImpl implements FileDataServiceI {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
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
            return imageData.getName();
        }
        return null;
    }

    @Transactional // Ensure this method is transactional
    public FileDataEntity downloadImage(String fileName) {
        // Retrieve the file from the database using the fileName
        Optional<FileDataEntity> dbImageData = fileDataRepository.findByName(fileName);

        if (dbImageData.isPresent()) {
            return dbImageData.get();
        } else {
            return null;  // Handle the case where the file is not found
        }
    }

    // Method to get dynamic content type
    public String getMediaType(String fileName) {
        Optional<FileDataEntity> dbImageData = fileDataRepository.findByName(fileName);
        if (dbImageData.isPresent()) {
            return dbImageData.get().getType(); // Return the content type (e.g., "image/png")
        }
        return MediaType.APPLICATION_OCTET_STREAM_VALUE; // Default to binary if type is unknown
    }
}
