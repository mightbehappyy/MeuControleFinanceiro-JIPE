package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.controller.responses.UserMonthlySpendingResponse;
import com.example.meucontrolefinanceiro.model.dtos.TransactionFilterDateDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserMonthlySpendingDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;
  @PostMapping
  public ResponseEntity<?> createTransaction(@RequestBody UserTransactionDTO userTransactionDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.createTransaction(userTransactionDTO));
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<?> findTransactionByUserId(@PathVariable("user_id") Long user_id) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByUserId(user_id));
  }

  @GetMapping("/user/{userEmail}")
  public ResponseEntity<?> findTransactionByUserEmail(@PathVariable("userEmail") String userEmail) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByUserEmail(userEmail));
  }

  @GetMapping
  public ResponseEntity<?> getMonthExpending(@RequestBody UserMonthlySpendingDTO userMonthlySpendingDTO) {
    return ResponseEntity.status(HttpStatus.OK).body((transactionService.findMonthTransactions(userMonthlySpendingDTO)));
  }

  @GetMapping("/type/{type}")
  public ResponseEntity<?> findTransactionByType(@PathVariable("type") TransactionEnum type) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByType(type));
  }

  @GetMapping("/daterange")
  public ResponseEntity<?> findTransactionByDateRange(
      @RequestBody TransactionFilterDateDTO transactionFilterDateDTO
  ) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(transactionService.findByDateRange(transactionFilterDateDTO));
  }

  @GetMapping("/daterange/{type}")
  public ResponseEntity<?> findTransactionByDateRangeAndType(
      @RequestBody TransactionFilterDateDTO transactionFilterDateDTO,
      @PathVariable("type") TransactionEnum type
  ) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(transactionService.findByDateRangeAndType(transactionFilterDateDTO, type));
  }
}
