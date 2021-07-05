package com.example.Practica.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cos")
public class Cos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    @ManyToMany
    private List<Produs> products;
    private Date date;

    public Cos() {
    }

    public Cos(float price, List<Produs> products, Date date) {
        this.price = price;
        this.products = products;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cos{" +
                "id=" + id +
                ", price=" + price +
                ", products=" + products +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Produs> getProducts() {
        return products;
    }

    public void setProducts(List<Produs> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
