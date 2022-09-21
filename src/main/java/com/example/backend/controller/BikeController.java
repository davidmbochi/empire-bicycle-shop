package com.example.backend.controller;

import com.example.backend.model.Bike;
import com.example.backend.service.BikeService;
import com.example.backend.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("bike")
@RequiredArgsConstructor
public class BikeController {
    private final BikeService bikeService;
    private final FileUploadService fileUploadService;

    @GetMapping("/all")
    public String findAllBikes(Model model){
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

        bike.setBikeImage(fileUploadService.uploadFile(multipartFile));
        bikeService.addBike(bike);

        return "redirect:/bike/all";
    }

    @GetMapping("/delete/{id}")
    String deleteBike(@PathVariable("id") Long id) throws IOException {
        Bike bike = bikeService.findBikeById(id);
        bikeService.deleteBike(bike);
      return "redirect:/bike/all";
    }

}
