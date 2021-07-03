package com.example.Practica.controller;


import com.example.Practica.dto.ProductDto;
import com.example.Practica.service.MediaService;
import com.example.Practica.service.ProducatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProducatorController {
    @Autowired
    ProducatorService producatorService;



    @GetMapping("/getproducatoripaginated")
    public ResponseEntity getProducatori(@RequestParam int page, @RequestParam int size) {
        return producatorService.getProducatorPaginated(page,size);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/uploadphoto")
    public ResponseEntity updatePhoto(@RequestParam MultipartFile image, @RequestParam Long companyId) {
        return producatorService.addBackgroundtoCompany(image, companyId);

    }


}
