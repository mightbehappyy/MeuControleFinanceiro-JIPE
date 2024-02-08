package com.example.MeuControleFinanceiro.controller.responses;

import com.example.MeuControleFinanceiro.model.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;
    private float budget;
    public UserResponse(User user) {
        this.email = user.getEmail();
        this.budget = user.getBudget();
    }
}
