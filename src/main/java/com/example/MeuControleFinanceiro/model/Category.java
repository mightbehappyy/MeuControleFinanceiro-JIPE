package com.example.MeuControleFinanceiro.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_CATEGORY")
public class Category {
    private String name;
    @Id
    private Long id;
    @ManyToOne
    private User user;
}