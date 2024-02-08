package com.example.MeuControleFinanceiro.utils.exceptions;

public class AccountNotFound extends RuntimeException{
  public AccountNotFound(String message) {
    super("Error: " + message);
  }
}
