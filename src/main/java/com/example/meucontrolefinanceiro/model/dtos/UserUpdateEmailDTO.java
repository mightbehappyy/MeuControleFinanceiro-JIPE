package com.example.meucontrolefinanceiro.model.dtos;

import lombok.Data;

@Data
public class UserUpdateEmailDTO {

    private final String oldEmail;
    private final String newEmail;

    public UserUpdateEmailDTO(String oldEmail, String newEmail) {
       this.newEmail = newEmail;
       this.oldEmail = oldEmail;
    }
}
