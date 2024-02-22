package com.example.meucontrolefinanceiro.repository;

import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByUserId(Long user_id);
  List<Transaction> findByType(TransactionEnum type);
  List<Transaction> findAllByDateBetweenAndUserId(Date start, Date end, Long user_id);
  List<Transaction> findAllByDateBetweenAndUserIdAndType(
      Date start,
      Date end,
      Long user_id,
      TransactionEnum type
  );
  List<Transaction> findAllByUserEmail(String email);
  List<Transaction> findAllByDateBetweenAndUserEmail(Date start, Date end, String email);
}

