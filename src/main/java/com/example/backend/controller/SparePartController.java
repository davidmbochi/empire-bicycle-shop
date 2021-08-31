package com.example.backend.controller;

import com.example.backend.model.SparePart;
import com.example.backend.service.SparePartService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
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
@RequiredArgsConstructor
@RequestMapping("spare")
public class SparePartController {
    private final SparePartService sparePartService;

    @GetMapping("/all")
    public String findAllSpareParts(Model model){
        model.addAttribute("spares",sparePartService.findAllSpareParts());
        return "spares/spares";
    }

    @GetMapping("/spare-form")
    public String getSpareForm(Model model){
        model.addAttribute("spare",new SparePart());
        return "spares/spareform";
    }

    @PostMapping("/save")
    public String saveSparePart(@ModelAttribute("spare") SparePart sparePart,
                                @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(Objects.requireNonNull(multipartFile
                .getOriginalFilename()).replace(" ","_")));
        sparePart.setSparePartPhoto(fileName);
        SparePart savedSparePart = sparePartService.addSparePart(sparePart);
        String uploadDirectory = "./upload-spare/"+savedSparePart.getId();
        Path uploadSparePath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadSparePath)){
            Files.createDirectories(uploadSparePath);
        }
        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadSparePath.resolve(fileName);
        Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/spare/all";
    }

    @GetMapping("/edit/{id}")
    public String editSparePart(@PathVariable("id") Long id, Model model){
        SparePart sparePart = sparePartService.findSparePartById(id);
        model.addAttribute("spare",sparePart);
        return "spares/editsparesform";
    }

    @PostMapping("/save-edit")
    public String saveEdit(@ModelAttribute("spare") SparePart sparePart){
        sparePartService.addSparePart(sparePart);
        return "redirect:/spare/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSparePart(@PathVariable("id") Long id){
        sparePartService.deleteSparePart(id);
        return "redirect:/spare/all";
    }
}
