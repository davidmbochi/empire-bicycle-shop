package com.example.backend.controller;

import com.example.backend.model.SparePart;
import com.example.backend.service.FileUploadService;
import com.example.backend.service.SparePartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("spare")
public class SparePartController {
    private final SparePartService sparePartService;
    private final FileUploadService fileUploadService;

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
        sparePart.setSparePartImage(fileUploadService.uploadFile(multipartFile));
        sparePartService.addSparePart(sparePart);

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
