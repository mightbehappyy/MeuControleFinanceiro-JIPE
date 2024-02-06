package com.example.MeuControleFinanceiro.repository;

import com.example.MeuControleFinanceiro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
