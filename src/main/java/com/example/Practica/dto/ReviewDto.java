package com.example.Practica.dto;

public class ReviewDto {

    private String message;
    private String response;
    private long stars;
    private long restaurantId;

    public ReviewDto() {
    }

    public ReviewDto(String message, String response, long stars, long restaurantId) {
        this.message = message;
        this.response = response;
        this.stars = stars;
        this.restaurantId = restaurantId;
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

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
