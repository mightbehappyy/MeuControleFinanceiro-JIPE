package com.example.meucontrolefinanceiro.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryDTO {
  private String name;
  private String amazonId;
}
