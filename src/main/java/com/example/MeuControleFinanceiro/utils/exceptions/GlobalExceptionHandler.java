package com.example.MeuControleFinanceiro.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotDeletedException.class)
    public ResponseEntity<Object> handleAccountNotDeletedException(AccountNotDeletedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<Object> handleAccountNotFound(AccountNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
