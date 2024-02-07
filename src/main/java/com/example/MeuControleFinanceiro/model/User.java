package com.example.MeuControleFinanceiro.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "TB_USERS")
@Entity
public class User implements Serializable {

    @Id
    private String email;
    private String password;
    private float budget;
    @OneToMany
    private List<Expense> expenses;
    @OneToMany
    private List<Category> categories;

}