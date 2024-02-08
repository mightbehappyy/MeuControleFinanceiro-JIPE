package com.example.MeuControleFinanceiro.repository;

import com.example.MeuControleFinanceiro.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

