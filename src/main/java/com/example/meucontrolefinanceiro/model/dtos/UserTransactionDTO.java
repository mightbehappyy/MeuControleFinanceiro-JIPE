package com.example.meucontrolefinanceiro.model.dtos;

import com.example.meucontrolefinanceiro.model.Transaction;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDTO {
  private List<Transaction> transactions;

  public UserTransactionDTO(Transaction transaction) {

  }
}