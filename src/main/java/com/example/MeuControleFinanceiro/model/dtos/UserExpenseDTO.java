package com.example.MeuControleFinanceiro.model.dtos;

import com.example.MeuControleFinanceiro.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserExpenseDTO {
    private List<Expense> expenses;

    public UserExpenseDTO(Expense expense) {

    }
}
