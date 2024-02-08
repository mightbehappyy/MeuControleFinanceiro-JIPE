package com.example.MeuControleFinanceiro.service;


import com.example.MeuControleFinanceiro.model.User;
import com.example.MeuControleFinanceiro.model.dtos.UserRegistrationDTO;
import com.example.MeuControleFinanceiro.repository.UserRepository;
import com.example.MeuControleFinanceiro.utils.exceptions.AccountNotDeletedException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User userModel = new User();
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isEmpty()) {
            BeanUtils.copyProperties(userRegistrationDTO, userModel);
            return userRepository.save(userModel);
        } else {
            throw new AccountNotDeletedException("Usuário já cadastrado");
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotDeletedException("Essa conta não existe"));

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada");
    }


}
