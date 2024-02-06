package com.example.MeuControleFinanceiro.controller.responses;

import com.example.MeuControleFinanceiro.model.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private String userName;
    private float budget;
    public UserResponse(User user) {
        this.userName = user.getUserName();
        this.budget = user.getBudget();
    }
}
