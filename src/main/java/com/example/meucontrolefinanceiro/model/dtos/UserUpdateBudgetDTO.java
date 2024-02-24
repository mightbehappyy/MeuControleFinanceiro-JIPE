package com.example.meucontrolefinanceiro.model.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateBudgetDTO {

    private final String email;
    private final float newBudget;

    public UserUpdateBudgetDTO (String email, float budget) {
        this.newBudget = budget;
        this.email = email;
    }
}
