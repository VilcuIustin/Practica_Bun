package com.example.Practica.dto;

import java.util.Date;

public class UserPayload {
    private String email;
    private String nume;
    private String prenume;
    private Date data_nastere;
    private char sex;
    private String password;


    public UserPayload(){}

    public UserPayload(String email, String nume, String prenume, Date data_nastere, char sex, String password) {
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.sex = sex;
        this.password = password;
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
}
