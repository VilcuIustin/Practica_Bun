package com.example.Practica.repository;


import com.example.Practica.model.Category;
import com.example.Practica.model.Producator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
