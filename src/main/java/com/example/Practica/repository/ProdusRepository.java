package com.example.Practica.repository;

import com.example.Practica.model.Produs;
import com.example.Practica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdusRepository extends JpaRepository<Produs, Long> {
}


