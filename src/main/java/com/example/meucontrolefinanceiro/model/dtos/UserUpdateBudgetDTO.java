package com.example.meucontrolefinanceiro.model.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateBudgetDTO {

    private final String amazonId;
    private final float newBudget;

    public UserUpdateBudgetDTO (String amazonId, float budget) {
        this.newBudget = budget;
        this.amazonId = amazonId;
    }
}
