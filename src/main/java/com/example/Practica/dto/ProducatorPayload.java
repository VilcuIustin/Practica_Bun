package com.example.Practica.dto;

public class ProducatorPayload {
    private String denumire;
    private String email;
    private String password;
    private String adresa;

    public ProducatorPayload(String denumire, String email, String password, String adresa) {
        this.denumire = denumire;
        this.email = email;
        this.password = password;
        this.adresa = adresa;
    }

    public ProducatorPayload() {
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
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
}
