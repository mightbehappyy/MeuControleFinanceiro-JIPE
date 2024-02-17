package com.example.meucontrolefinanceiro.repository;

import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByNameAndUserId(String name, Long user_id);
  List<Category> findAllByUserEmail(String email);
  void deleteAllByUserEmail(String email);
}
