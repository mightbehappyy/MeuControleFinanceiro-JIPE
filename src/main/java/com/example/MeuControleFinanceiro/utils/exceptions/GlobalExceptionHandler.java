package com.example.MeuControleFinanceiro.utils.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotDeletedException.class)
    public ResponseEntity<Object> handleAccountNotDeletedException(AccountNotDeletedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}