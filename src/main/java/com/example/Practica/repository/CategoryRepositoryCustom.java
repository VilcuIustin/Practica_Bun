package com.example.Practica.repository;

import com.example.Practica.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    Optional<List<Category>> findByName(List<String> category);
}
