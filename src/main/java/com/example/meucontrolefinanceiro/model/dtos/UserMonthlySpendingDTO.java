package com.example.meucontrolefinanceiro.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMonthlySpendingDTO {
    private Date currentDate;
    private String userEmail;
}
