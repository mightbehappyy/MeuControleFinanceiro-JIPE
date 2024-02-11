package com.example.meucontrolefinanceiro.controller.responses;

import com.example.meucontrolefinanceiro.model.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private final String email;
    private final float budget;
    public UserResponse(User user) {
        this.email = user.getEmail();
        this.budget = user.getBudget();
    }
}
