package com.example.Practica.repository;


import com.example.Practica.dto.AuthPayload;
import com.example.Practica.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    public void testQuery(String email){
//        String query = "select new User(" +
//                " d.email, d.password, d.role.role )" +
        String query = "select new com.example.Practica.dto.AuthPayload" +
                " (d.email, d.password, d.role.role) " +
                " from User d " +
                " where d.email = :email ";
        User authPayload =
                entityManager.createQuery(query, User.class)
                        .setParameter("email",email)
                        .getSingleResult();


        System.out.println(authPayload);
    }




    @Override
    public Optional<AuthPayload> getByEmailWithPasswordAndRole(String email) {
        System.out.println(email);
        String query = "select " +
                " new com.example.Practica.dto.AuthPayload (d.email, d.password , d.role.role) " +
                " from User d " +
                " where d.email = :email";
        AuthPayload authPayload =
                entityManager.createQuery(query, AuthPayload.class)
                        .setParameter("email",email)
                       .getSingleResult();





        return Optional.of(authPayload);
    }



//    @Override
//    public List<User> getPaginatedDoctors(int firstResults, int maxResults) {
//
//        String query = "select d from Doctor d ";
//
//        List<User> doctors = entityManager.createQuery(query, User.class)
//                .setFirstResult(firstResults)
//                .setMaxResults(maxResults)
//                .getResultList();
//
//        return doctors;
//    }

//    @Override
//    public long getTotalDoctors() {
//
//        String query = "select count(d) from Doctor d ";
//
//        long totalDoctors = entityManager.createQuery(query, Long.class)
//                .getSingleResult();
//        return totalDoctors;
//    }
}
