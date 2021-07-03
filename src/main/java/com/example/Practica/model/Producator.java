package com.example.Practica.model;

import com.example.Practica.dto.ProducatorPayload;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Producator")
public class Producator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String denumire;
    private String email;
    private String password;
    private String adresa;
    private String poza;
    @ManyToMany
    private Collection<Produs> produse;


    public Producator(){ }

    public Producator(ProducatorPayload producatorPayload){
        this.email = producatorPayload.getEmail();
        this.denumire = producatorPayload.getDenumire();
        this.password = producatorPayload.getPassword();
        this.adresa= producatorPayload.getAdresa();
    }

    public Producator(long id, String denumire, String email, String adresa) {
        this.id = id;
        this.denumire = denumire;
        this.email = email;
        this.adresa = adresa;

    }

    @Override
    public String toString() {
        return "Producator{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adresa='" + adresa + '\'' +
                ", poza='" + poza + '\'' +
                ", produse=" + produse +
                '}';
    }

    public Producator(long id, String denumire, String email, String password, String adresa, Collection<Produs> produse) {
        this.id = id;
        this.denumire = denumire;
        this.email = email;
        this.password = password;
        this.adresa = adresa;
        this.produse = produse;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Collection<Produs> getProduse() {
        return produse;
    }

    public void setProduse(Collection<Produs> produse) {
        this.produse = produse;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

}
