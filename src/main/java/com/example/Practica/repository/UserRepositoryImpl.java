package com.example.Practica.repository;


import com.example.Practica.dto.AuthDto;
import com.example.Practica.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    public void testQuery(String email) {

        String query = "select new com.example.Practica.dto.AuthDto" +
                " (d.email, d.password, d.role.role, d.id) " +
                " from User d " +
                " where d.email = :email ";
        User authPayload =
                entityManager.createQuery(query, User.class)
                        .setParameter("email", email)
                        .getSingleResult();


        System.out.println(authPayload);
    }


    @Override
    public Optional<AuthDto> getByEmailWithPasswordAndRole(String email) {
        String query = "select " +
                " new com.example.Practica.dto.AuthDto (d.email, d.password , d.role.role, d.id) " +
                " from User d " +
                " where d.email = :email";
        AuthDto authDto =
                entityManager.createQuery(query, AuthDto.class)
                        .setParameter("email", email)
                        .getSingleResult();
        return Optional.of(authDto);
    }


}
