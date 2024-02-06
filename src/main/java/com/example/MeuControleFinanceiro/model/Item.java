package com.example.MeuControleFinanceiro.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Item {
    private String name;
    private Date spendingDate;
    private float cost;
}
