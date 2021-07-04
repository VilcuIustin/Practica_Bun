package com.example.Practica.model;

import com.example.Practica.dto.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denumire;
    private String descriere;
    private float pret;
    private long cantitate;
    private int reducere;
    private String path;

    public Produs() {
    }

    public Produs(ProductDto productDto) {
        this.denumire = productDto.getDenumire();
        this.descriere = productDto.getDescriere();
        this.pret = productDto.getPret();
        this.cantitate = productDto.getCantitate();
        this.reducere = productDto.getReducere();
    }


    public Produs(String denumire, String descriere, float pret, long cantitate, int reducere) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.cantitate = cantitate;
        this.reducere = reducere;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", reducere=" + reducere +
                ", path='" + path + '\'' +
                '}';
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

    public void setId(long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public long getCantitate() {
        return cantitate;
    }

    public void setCantitate(long cantitate) {
        this.cantitate = cantitate;
    }

    public int getReducere() {
        return reducere;
    }

    public void setReducere(int reducere) {
        this.reducere = reducere;
    }
}
