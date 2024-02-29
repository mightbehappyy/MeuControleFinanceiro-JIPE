package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.service.TransactionService;
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
  @PostMapping
  public ResponseEntity<?> createTransaction(@RequestBody UserTransactionDTO userTransactionDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.createTransaction(userTransactionDTO));
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<?> findTransactionByUserId(@PathVariable("user_id") Long user_id) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByUserId(user_id));
  }

  @GetMapping("/user/{userAmazonId}")
  public ResponseEntity<?> findTransactionByUserAmazonId(
      @PathVariable("userAmazonId") String userAmazonId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
        transactionService.findByUserAmazonId(userAmazonId)
    );
  }

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

  @GetMapping("/type/{type}")
  public ResponseEntity<?> findTransactionByType(@PathVariable("type") TransactionEnum type) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionService.findByType(type));
  }

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
