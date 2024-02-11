package com.example.meucontrolefinanceiro.utils.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message) {
        super("Error: " + message);
    }
}
