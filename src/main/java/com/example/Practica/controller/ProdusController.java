package com.example.Practica.controller;

import com.example.Practica.dto.ProductDto;
import com.example.Practica.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProdusController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addAliment")
    //trebuie adaugat si id ul producatorului
    public ResponseEntity addAliment(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addImageProduct")
    public ResponseEntity addImagetoProduct(@RequestParam MultipartFile image, @RequestParam Long productId) {
        return productService.addImageToProduct(image, productId);
    }

    @GetMapping("/getProductPaginated")
    public ResponseEntity getProductPaginated(@RequestParam Long companyId, @RequestParam int page, @RequestParam int size) {
        return productService.getProductPaginated(companyId, page, size);
    }


}
