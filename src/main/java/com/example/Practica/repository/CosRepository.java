package com.example.Practica.repository;

import com.example.Practica.model.Cos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosRepository extends JpaRepository<Cos,Long> {


}
