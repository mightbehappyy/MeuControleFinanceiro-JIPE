package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @Operation(
      summary = "Criar transação",
      description = "Este endpoint permite que o usuário crie uma transação."
  )
  @PostMapping
  public ResponseEntity<?> createTransaction(@RequestBody UserTransactionDTO userTransactionDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.createTransaction(userTransactionDTO));
  }

  @Operation(
      summary = "Listar transação com ID",
      description = "Este endpoint permite listar todas as transações de um usuário através do seu "
          + "ID."
  )
  @GetMapping("/{user_id}")
  public ResponseEntity<?> findTransactionByUserId(@PathVariable("user_id") Long user_id) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByUserId(user_id));
  }

  @Operation(
      summary = "Listar transação com AmazonID",
      description = "Este endpoint permite listar todas as transações de um usuário através do seu "
          + "AmazonID."
  )
  @GetMapping("/user/{userAmazonId}")
  public ResponseEntity<?> findTransactionByUserAmazonId(
      @PathVariable("userAmazonId") String userAmazonId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
        transactionService.findByUserAmazonId(userAmazonId)
    );
  }

  @Operation(
      summary = "Listar resumo mensal",
      description = "Este endpoint permite que seja listado um resumo mensal do usuário (O resumo "
          + "contém: entradas, saídas e um balanço de entradas e saídas)."
  )
  @GetMapping
  public ResponseEntity<?> getMonthExpending(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime currentDate,
      @RequestParam String amazonId
  ) {
    Date formattedCurrentDate = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());

    return ResponseEntity.status(HttpStatus.OK).body((
        transactionService.findMonthTransactions(formattedCurrentDate, amazonId)
    ));
  }

  @Operation(
      summary = "Listar resumo mensal de uma categoria específica",
      description = "Este endpoint permite que seja listado um resumo mensal do usuário de uma "
          + "categoria específica (O resumo contém: entradas, saídas e um "
          + "balanço de entradas e saídas)."
  )
  @GetMapping("/by-category")
  public ResponseEntity<?> getCategoryMonthExpending(
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime currentDate,
          @RequestParam String amazonId,
          @RequestParam String category
  ) {
    Date formattedCurrentDate = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());

    return ResponseEntity.status(HttpStatus.OK).body((
            transactionService.findCategoryTransactions(formattedCurrentDate, amazonId, category)
    ));
  }

  @Operation(
      summary = "Listar transações por tipo",
      description = "Este endpoint permite que seja listado as transações de um tipo específico, "
          + "podendo ser 'INCOME' ou 'EXPENSE'."
  )
  @GetMapping("/type/{type}")
  public ResponseEntity<?> findTransactionByType(@PathVariable("type") TransactionEnum type) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByType(type));
  }

  @Operation(
      summary = "Listar transações de um período",
      description = "Este endpoint permite que seja listado as transações do usuário de um período "
          + "específico."
  )
  @GetMapping("/daterange")
  public ResponseEntity<?> findTransactionByDateRange(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd,
      @RequestParam String amazonId
  ) {

    Date formattedDateStart = Date.from(dateStart.atZone(ZoneId.systemDefault()).toInstant());
    Date formattedDateEnd = Date.from(dateEnd.atZone(ZoneId.systemDefault()).toInstant());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(transactionService.findByDateRange(formattedDateStart, formattedDateEnd, amazonId));
  }

  @GetMapping("/daterange/{type}")
  @Operation(
      summary = "Listar transações por período e tipo",
      description = "Este endpoint permite que seja listado as transações do usuário de um período "
          + "e um tipo específico (Tipo pode ser 'INCOME' ou 'EXPENSE')."
  )
  public ResponseEntity<?> findTransactionByDateRangeAndType(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd,
      @RequestParam String amazonId,
      @PathVariable("type") TransactionEnum type
  ) {
    Date formattedDateStart = Date.from(dateStart.atZone(ZoneId.systemDefault()).toInstant());
    Date formattedDateEnd = Date.from(dateEnd.atZone(ZoneId.systemDefault()).toInstant());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(transactionService.findByDateRangeAndType(
            formattedDateStart,
            formattedDateEnd,
            amazonId,
            type
        ));
  }
}
