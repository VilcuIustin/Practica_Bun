package com.example.Practica.exception;

public class CustomEntityNotFoundException extends Exception {
    private final String entity;

    public CustomEntityNotFoundException(Class<?> cause) {
        super(cause.getSimpleName());
        this.entity = cause.getSimpleName();
    }

    public String getErrorMessage() {
        return entity.toUpperCase() + "_NOT_FOUND";
    }
}
