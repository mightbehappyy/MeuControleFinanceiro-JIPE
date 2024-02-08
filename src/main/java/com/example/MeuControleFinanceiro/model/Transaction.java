package com.example.MeuControleFinanceiro.model;

import com.example.MeuControleFinanceiro.model.enums.TransactionEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_TRANSACTION")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  private float cost;

  private Date date;

  private TransactionEnum type;

  @ManyToOne
  private User user;

  @ManyToOne
  private Category category;
}
