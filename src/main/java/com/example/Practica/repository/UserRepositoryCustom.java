package com.example.Practica.repository;


import com.example.Practica.dto.AuthDto;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);
}