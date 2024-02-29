package com.example.meucontrolefinanceiro.utils.exceptions;

public class CategoryBudgetBiggerThanBudget extends RuntimeException{

    public CategoryBudgetBiggerThanBudget(String message) {
        super("Error: " + message);
    }
}
