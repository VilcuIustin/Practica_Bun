package com.example.Practica.dto;

import com.example.Practica.model.Role;

public class AuthDto {
    private String email;
    private String password;
    private String role;
    private Long id;

    public AuthDto() {
    }

    public AuthDto(String email,
                   String password,
                   String role, Long id) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}