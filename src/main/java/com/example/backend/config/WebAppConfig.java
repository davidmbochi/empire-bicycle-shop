package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path bikeImageDir = Paths.get("./upload");
        String bikeImageUploadPath = bikeImageDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/"+bikeImageUploadPath+"/");

        Path spareImageDir = Paths.get("./upload-spare");
        String sparePhotoPath = spareImageDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/upload-spare/**")
                .addResourceLocations("file:/"+sparePhotoPath+"/");
    }
}
