package com.example.Practica.dto;

import com.example.Practica.model.Produs;

import java.util.List;

public class CartDto {
    private List<Long> produse;

    public CartDto(List<Long> produse) {
        this.produse = produse;
    }

    public List<Long> getProduse() {
        return produse;
    }

    public void setProduse(List<Long> produse) {
        this.produse = produse;
    }


    public CartDto() {
    }
}
