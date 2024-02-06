package com.example.MeuControleFinanceiro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Item {
    private String name;
    private Date spendingDate;
    private float cost;
    @Id
    private Long id;
    @ManyToOne
    private User user;
}
