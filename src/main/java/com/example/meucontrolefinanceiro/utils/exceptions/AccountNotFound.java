package com.example.meucontrolefinanceiro.utils.exceptions;

public class AccountNotFound extends RuntimeException{
  public AccountNotFound(String message) {
    super("Error: " + message);
  }
}
