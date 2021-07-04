package com.example.Practica.model;

import com.example.Practica.dto.ReviewDto;

import javax.persistence.*;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String response;
    private long stars;
    @ManyToOne
    private User client;

    public Review() {
    }

    public Review(String message, Long stars) {
        this.message = message;
        this.stars = stars;
    }
    public Review(ReviewDto reviewDto, User user) {
        this.message = reviewDto.getMessage();
        this.stars = reviewDto.getStars();
        this.client = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public long getStars() {
        return stars;
    }
}
