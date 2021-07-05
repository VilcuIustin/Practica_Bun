package com.example.Practica.repository;

import com.example.Practica.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<List<Category>> findByName(List<String> category) {
        String query = "SELECT c FROM Category c " + "WHERE c.category IN (:lista)";

        return Optional.of(entityManager.createQuery(query, Category.class)
                .setParameter("lista", category)
                .getResultList());

    }
}
