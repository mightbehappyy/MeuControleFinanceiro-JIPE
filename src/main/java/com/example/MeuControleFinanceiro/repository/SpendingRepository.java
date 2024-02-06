package com.example.MeuControleFinanceiro.repository;

import com.example.MeuControleFinanceiro.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
}
