package com.example.meucontrolefinanceiro.model.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterDateDTO {
  private Date dateStart;
  private Date dateEnd;
  private String amazonId;
}
