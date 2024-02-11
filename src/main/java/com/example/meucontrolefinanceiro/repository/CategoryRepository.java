package com.example.meucontrolefinanceiro.repository;

import com.example.meucontrolefinanceiro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
