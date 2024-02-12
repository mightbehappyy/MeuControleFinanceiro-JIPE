package com.example.meucontrolefinanceiro.model.dtos;

import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDTO {
  private String title;
  private float cost;
  private Date date;
  private TransactionEnum type;
  private String category;
  private String email;
}
