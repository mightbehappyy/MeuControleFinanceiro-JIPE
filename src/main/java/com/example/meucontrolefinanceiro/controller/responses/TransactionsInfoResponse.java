package com.example.meucontrolefinanceiro.controller.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionsInfoResponse {
    private final float outcoming;
    private final float incomes;
    private final float expenses;
    private final String userEmail;
    private final float budget;

}
