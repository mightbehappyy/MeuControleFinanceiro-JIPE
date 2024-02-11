package com.example.meucontrolefinanceiro.utils.exceptions;

public class AccountNotDeletedException extends RuntimeException{

    public AccountNotDeletedException(String message) {
        super("Error: " + message);
    }
}
