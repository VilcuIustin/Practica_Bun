package com.example.Practica.model;

import com.example.Practica.dto.ProducatorPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Producator")
public class Producator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String denumire;
    private String email;
    @JsonIgnore
    private String password;
    private String adresa;
    private String poza;
    private long noReviews=0l;
    private float stars =0.0f;
    @ManyToMany
    private List<Review> reviews;
    @ManyToMany
    private List<Category> category;
    @ManyToMany
    private Collection<Produs> produse;


    public Producator() {
    }

    public Producator(ProducatorPayload producatorPayload, List<Category> category) {
        this.email = producatorPayload.getEmail();
        this.denumire = producatorPayload.getDenumire();
        this.adresa = producatorPayload.getAdresa();
        this.category = new ArrayList<>();
        this.category.addAll(category);

    }

    public Producator(long id, String denumire, String email, String adresa, String poza, List<Category> category) {
        this.id = id;
        this.denumire = denumire;
        this.email = email;
        this.adresa = adresa;
        this.poza = poza;
        this.category = category;

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

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public long getNoReviews() {
        return noReviews;
    }

    public void setNoReviews(long noReviews) {
        this.noReviews = noReviews;
    }
    public void addNewReviewWithStars(Review review){

        noReviews++;
        stars=(stars+review.getStars())/noReviews;
        reviews.add(review);
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Producator{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", email='" + email + '\'' +
                ", adresa='" + adresa + '\'' +
                ", poza='" + poza + '\'' +
                ", noReviews=" + noReviews +
                ", stars=" + stars +
                ", reviews=" + reviews +
                ", category=" + category +
                ", produse=" + produse +
                '}';
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
