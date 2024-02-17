package com.example.meucontrolefinanceiro.utils.exceptions;

public class ExistentAccountException extends RuntimeException {
    public ExistentAccountException(String message) {
        super("Error: " + message);
    }
}
