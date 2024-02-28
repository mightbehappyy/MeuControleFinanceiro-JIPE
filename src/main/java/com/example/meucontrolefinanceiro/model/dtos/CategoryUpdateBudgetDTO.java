package com.example.meucontrolefinanceiro.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdateBudget {

    private final String amazonId;
    private final String categoryName;
    private final int newCategoryBudget;
}
