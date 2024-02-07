package com.example.MeuControleFinanceiro.repository;

import com.example.MeuControleFinanceiro.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Expense, Long> {
}
