package com.example.meucontrolefinanceiro.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String amazonId;
    private float budget;

    public UserRegistrationDTO(String amazonId) {
        this.amazonId = amazonId;
        this.budget = 0;
        //this.expenses = new ArrayList<>();
    }
}
