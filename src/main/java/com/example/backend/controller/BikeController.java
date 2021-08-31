package com.example.backend.controller;

import com.example.backend.model.Bike;
import com.example.backend.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping("bike")
@RequiredArgsConstructor
public class BikeController {
    private final BikeService bikeService;

    @GetMapping("/all")
    public String findAllBikes(Model model){
        if (bikeService.findAllBikes().equals(null)){
            return "bikes/bikes";
        }
        model.addAttribute("bikes", bikeService.findAllBikes());
        return "bikes/bikes";
    }

    @GetMapping("/edit/{id}")
    String editBike(@PathVariable("id") Long id, Model model) throws IOException {
        Bike bike = bikeService.findBikeById(id);
        model.addAttribute("bike",bike);
        return "bikes/editbikesform";
    }

    @PostMapping("/save-edit")
    public String saveEdit(@ModelAttribute("bike") Bike bike){
        bikeService.addBike(bike);
        return "redirect:/bike/all";
    }

    @GetMapping("/show-bike-form")
    public String showBikeForm(Model model){
        model.addAttribute("bike", new Bike());
        return "bikes/bikeform";
    }

    @PostMapping(value = "/save")
    public String addBike(@ModelAttribute("bike") Bike bike,
                          @RequestParam("image")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.
                cleanPath(Objects.
                        requireNonNull(Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ","_")));
        bike.setPhoto(fileName);
        Bike theBike = bikeService.addBike(bike);
        String uploadDir = "./upload/"+theBike.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/bike/all";
    }

    @GetMapping("/delete/{id}")
    String deleteBike(@PathVariable("id") Long id) throws IOException {
        Bike bike = bikeService.findBikeById(id);
        Path bikeImagePath = Path.of("./upload/" + bike.getId());
        FileUtils.deleteDirectory(bikeImagePath.toFile());
        bikeService.deleteBike(bike);
      return "redirect:/bike/all";
    }

}
