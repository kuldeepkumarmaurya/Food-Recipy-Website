package com.pets.petcare.ServiceI;

import com.pets.petcare.Entity.FileDataEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileDataServiceI {
    String uploadImage(MultipartFile file) throws IOException;

    FileDataEntity downloadImage(String fileName);

    String getMediaType(String fileName);
}
