package com.example.Practica.dto;

import com.example.Practica.model.Produs;

import java.util.List;

public class CosDto {
    private List<Long> produse;

    public CosDto(List<Long> produse) {
        this.produse = produse;
    }

    public List<Long> getProduse() {
        return produse;
    }

    public void setProduse(List<Long> produse) {
        this.produse = produse;
    }

    public CosDto() {
    }
}
