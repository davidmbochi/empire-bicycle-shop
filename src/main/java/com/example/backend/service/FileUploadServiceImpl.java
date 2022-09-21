package com.example.backend.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Configuration
public class FileUploadServiceImpl implements FileUploadService{

    private final Cloudinary cloudinary;

    @Autowired
    public FileUploadServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            return cloudinary.uploader()
                    .upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
