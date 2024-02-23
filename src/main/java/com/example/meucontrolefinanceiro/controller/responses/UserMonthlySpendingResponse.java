package com.example.meucontrolefinanceiro.controller.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMonthlySpendingResponse {
    private final float spending;
    private final float incomes;
    private final float expenses;
    private final String userEmail;
    private final float userBudget;

}
