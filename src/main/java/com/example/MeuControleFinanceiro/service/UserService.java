package com.example.MeuControleFinanceiro.service;


import com.example.MeuControleFinanceiro.model.User;
import com.example.MeuControleFinanceiro.model.dtos.UserRegistrationDTO;
import com.example.MeuControleFinanceiro.repository.UserRepository;
import com.example.MeuControleFinanceiro.utils.exceptions.AccountNotDeletedException;
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

        BeanUtils.copyProperties(userRegistrationDTO, userModel);
        return userRepository.save(userModel);
    }

    public ResponseEntity<String> deleteUser(UserRegistrationDTO userRegistrationDTO) {
        User userOptional = userRepository
                .findById(userRegistrationDTO.getEmail())
                .orElseThrow(() -> new AccountNotDeletedException("Account deletion gone wrong"));

        if(!userOptional.getPassword().equals(userRegistrationDTO.getPassword())) {
            throw new AccountNotDeletedException("Account deletion gone wrong");
        }
        userRepository.deleteById(userRegistrationDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted");
    }


}
