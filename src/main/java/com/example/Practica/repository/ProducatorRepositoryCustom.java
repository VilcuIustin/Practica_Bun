package com.example.Practica.repository;

import com.example.Practica.model.Producator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProducatorRepositoryCustom {
    Boolean findByEmail(String email);

    Optional<Producator> getProducatorById(Long id);

    Optional<List<Producator>> getProducatoriPaginated(int page, int size);

}
