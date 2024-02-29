package com.example.meucontrolefinanceiro.utils.exceptions;

import com.example.meucontrolefinanceiro.controller.responses.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotDeletedException.class)
    public ResponseEntity<Object> handleAccountNotDeletedException(AccountNotDeletedException ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<Object> handleAccountNotFound(AccountNotFound ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleInvalidEmail(InvalidEmailException ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ExistentAccountException.class)
    public ResponseEntity<Object> handleExistentAccount(ExistentAccountException ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFound(CategoryNotFoundException ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ExistentCategoryException.class)
    public ResponseEntity<Object> handleCategoryNotFound(ExistentCategoryException ex) {
        return buildResponseEntity(new ApiResponse(HttpStatus.CONFLICT, ex.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}