package com.example.Practica.repository;

import com.example.Practica.dto.AuthPayload;
import com.example.Practica.model.Producator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProducatorRepositoryImpl implements ProducatorRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Boolean findByEmail(String email) {
        String query = "select " +
                " d.denumire" +
                " from Producator d " +
                " where d.email = :email";
        List rezultat = entityManager.createQuery(query, String.class)
                .setParameter("email", email)
                .getResultList();
        return rezultat.size() == 0;
    }

    @Override
    public Optional<Producator> getProducatorById(Long id) {
        String query = "select d" +
                " from Producator d" +
                " where d.id = :id";
        Producator producator = entityManager.createQuery(query, Producator.class)
                .setParameter("id", id.longValue())
                .getSingleResult();
        System.out.println(producator);
        return Optional.of(producator);
    }

    @Override
    public Optional<List<Producator>> getProducatoriPaginated(int page, int size) {

        String query = "select " +
                " d from Producator d ";
        List<Producator> producator = entityManager.createQuery(query, Producator.class)
                .setMaxResults(size)
                .setFirstResult((page - 1) * size)
                .getResultList();
        producator.stream().forEach(prod -> prod.setPassword(null));

        return Optional.of(producator);
    }
}
