package com.example.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class FileUploadConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config =
                Map.of("cloud_name","javawhizz",
                        "api_key","452296839227936",
                        "api_secret","BOJS5hEOpfw6pUZ0IUSlXtb03ZA");
        return new Cloudinary(config);
    }
}
