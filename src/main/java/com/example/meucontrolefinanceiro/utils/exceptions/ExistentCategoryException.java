package com.example.meucontrolefinanceiro.utils.exceptions;

public class ExistentCategoryException extends RuntimeException{

    public ExistentCategoryException(String message) {
        super("Error: " + message);
    }
}
