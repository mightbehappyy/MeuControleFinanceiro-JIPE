package com.example.MeuControleFinanceiro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
@Entity
@Table(name = "TB_SPENDING")
public class Spending {

    private String title;
    private float cost;
    private Date date;
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

}

