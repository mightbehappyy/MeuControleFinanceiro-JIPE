package com.example.MeuControleFinanceiro.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String userName;
    private String password;
    private float budget;
    @OneToMany(mappedBy = "user")
    private List<Item> itemList;
}