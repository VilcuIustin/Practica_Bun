package com.example.Practica.model;


import com.example.Practica.dto.UserPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nume;
    private String prenume;
    private Date data_nastere;
    private char sex;
    @JsonIgnore
    private String password;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
    @ManyToMany
    private Collection<Produs> lastPurchases;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public User(UserPayload userPayload) {
        this.email = userPayload.getEmail();
        this.nume = userPayload.getNume();
        this.prenume = userPayload.getPrenume();
        this.data_nastere = userPayload.getData_nastere();
        this.sex = userPayload.getSex();
        this.password = userPayload.getPassword();

    }


    public User(String email, String nume, String prenume, Date data_nastere, char sex, String password, Role role) {
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.sex = sex;
        this.password = password;
        this.role = role;
    }

    public Collection<Produs> getLastPurchases() {
        return lastPurchases;
    }

    public void setLastPurchases(Collection<Produs> lastPurchases) {
        this.lastPurchases = lastPurchases;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getData_nastere() {
        return data_nastere;
    }

    public void setData_nastere(Date data_nastere) {
        this.data_nastere = data_nastere;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", data_nastere=" + data_nastere +
                ", sex=" + sex +
                ", path='" + path + '\'' +
                ", role=" + role +
                '}';
    }
}
