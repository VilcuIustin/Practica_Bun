package com.example.Practica.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

    private String denumire;
    private String descriere;
    private float pret;
    private long cantitate;
    private int reducere;
    private Long restaurantId;

    public ProductDto() {
    }

    public ProductDto(String denumire, String descriere, float pret, long cantitate, int reducere, Long restaurantId) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.cantitate = cantitate;
        this.reducere = reducere;
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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
