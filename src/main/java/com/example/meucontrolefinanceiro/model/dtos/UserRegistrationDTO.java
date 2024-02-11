package com.example.meucontrolefinanceiro.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String email;
    private float budget;

    public UserRegistrationDTO(String email) {
        this.email = email;
        this.budget = 0;
        //this.expenses = new ArrayList<>();
    }
}
