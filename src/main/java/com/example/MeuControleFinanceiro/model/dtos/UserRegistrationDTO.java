package com.example.MeuControleFinanceiro.model.dtos;

import com.example.MeuControleFinanceiro.model.Spending;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class UserRegistrationDTO {

    private String email;
    private String userName;
    private String password;
    private float budget;
    private List<Spending> spendings;

    public UserRegistrationDTO(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.budget = 0;
        this.spendings = new ArrayList<>();
    }
}
