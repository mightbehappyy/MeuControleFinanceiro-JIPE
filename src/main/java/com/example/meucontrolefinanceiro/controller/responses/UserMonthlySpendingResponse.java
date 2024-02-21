package com.example.meucontrolefinanceiro.controller.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMonthlySpendingResponse {
    private final float spending;
    private final String userEmail;
}
