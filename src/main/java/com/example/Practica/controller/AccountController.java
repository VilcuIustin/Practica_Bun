package com.example.Practica.controller;


import com.example.Practica.dto.CartDto;
import com.example.Practica.dto.RestaurantDto;
import com.example.Practica.dto.UserDto;
import com.example.Practica.service.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AccountController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
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
    public ResponseEntity registerProducator(@RequestBody RestaurantDto restaurantDto) {
        return userService.addProducator(restaurantDto);
    }

    @GetMapping("/retrivePersonalData")
    public ResponseEntity retrivePersonalData(){
        return userService.retriveData();
    }

    @PostMapping("/buy")
    public ResponseEntity buy(@RequestBody CartDto cartDto){
        return userService.buy(cartDto);
    }

}
