package com.example.meucontrolefinanceiro.model.dtos;

import lombok.Data;

@Data
public class UserUpdateAmazonIdDTO {

    private final String oldAmazonId;
    private final String newAmazonId;

    public UserUpdateAmazonIdDTO(String oldAmazonId, String newAmazonId) {
       this.newAmazonId = newAmazonId;
       this.oldAmazonId = oldAmazonId;
    }
}
