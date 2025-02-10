package com.pets.petcare.Controller;

import com.pets.petcare.DTO.UserProfileResponseDTO;
import com.pets.petcare.Entity.FileDataEntity;
import com.pets.petcare.ServiceI.FileDataServiceI;
import com.pets.petcare.Utility.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    FileDataServiceI fileDataServiceI;

    @PostMapping("/save-image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = fileDataServiceI.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/get-image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        FileDataEntity imageData = fileDataServiceI.downloadImage(fileName);

        if (imageData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Determine the file type (MIME type)
        String contentType = imageData.getType();

        // Create response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        // Stream the image data back as the response
        return ResponseEntity.ok()
                .headers(headers)
                .body(ImageUtils.decompressImage(imageData.getImageData()));  // Decompress image before returning
    }



}
