package com.example.meucontrolefinanceiro.controller.responses;

import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final long id;
    private final String email;
    private final float budget;
    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.budget = user.getBudget();
    }
}
