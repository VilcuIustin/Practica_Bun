package com.example.Practica.repository;


import com.example.Practica.dto.AuthPayload;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<AuthPayload> getByEmailWithPasswordAndRole(String email);
}