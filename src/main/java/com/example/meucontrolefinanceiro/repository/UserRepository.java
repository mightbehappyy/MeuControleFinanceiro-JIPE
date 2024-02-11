package com.example.meucontrolefinanceiro.repository;


import com.example.meucontrolefinanceiro.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  void deleteUserByEmail(String email);

}
