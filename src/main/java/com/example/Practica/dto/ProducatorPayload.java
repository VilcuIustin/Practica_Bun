package com.example.Practica.dto;

import java.util.Collection;
import java.util.List;

public class ProducatorPayload {
    private String denumire;
    private String email;
    private String password;
    private String adresa;
    private List<String> category;

    public ProducatorPayload(String denumire, String email, String password, String adresa, List<String> category) {
        this.denumire = denumire;
        this.email = email;
        this.password = password;
        this.adresa = adresa;
        this.category = category;
    }

    public ProducatorPayload() {
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "ProducatorPayload{" +
                "denumire='" + denumire + '\'' +
                ", email='" + email + '\'' +
                ", adresa='" + adresa + '\'' +
                ", category=" + category +
                '}';
    }
}
