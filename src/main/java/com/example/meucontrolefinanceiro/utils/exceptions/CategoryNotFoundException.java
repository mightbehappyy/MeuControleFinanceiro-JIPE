package com.example.meucontrolefinanceiro.utils.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super("Error: " + message);
    }
}
