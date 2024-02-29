package com.example.meucontrolefinanceiro.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Table(name = "TB_USERS")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String amazonId;

    private float budget;

    @OneToMany
    private List<Transaction> transactions;

    @OneToMany
    private List<Category> categories;

}
