package com.example.meucontrolefinanceiro.model.dtos;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private final String oldEmail;
    private final String newEmail;

    public UserUpdateDTO(String oldEmail, String newEmail) {
       this.newEmail = newEmail;
       this.oldEmail = oldEmail;
    }
}
