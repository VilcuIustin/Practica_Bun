package com.example.Practica.controller;


import com.example.Practica.dto.AuthPayload;
import com.example.Practica.dto.ProducatorPayload;
import com.example.Practica.dto.UserPayload;
import com.example.Practica.repository.UserRepositoryImpl;
import com.example.Practica.service.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AccountController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody UserPayload userPayload) {
        return userService.addUser(userPayload);
    }

    @PostMapping("/forgot")
    public ResponseEntity forgot(@RequestBody String email) {
        throw new NotYetImplementedException();
    }

    @PostMapping("/changeimage")
    public ResponseEntity changeImage(@RequestParam MultipartFile image) {
        return userService.changeImage(image);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registerproducator")
    public ResponseEntity registerProducator(@RequestBody ProducatorPayload producatorPayload) {
        return userService.addProducator(producatorPayload);
    }


}
