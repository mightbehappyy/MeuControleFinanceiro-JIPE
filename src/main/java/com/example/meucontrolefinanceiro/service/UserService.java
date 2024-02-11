package com.example.meucontrolefinanceiro.service;


import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.repository.UserRepository;

import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotFound;
import com.example.meucontrolefinanceiro.utils.exceptions.ExistentAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new ExistentAccountException("Account already exists");
        }

        User userModel = new User();
        BeanUtils.copyProperties(userRegistrationDTO, userModel);
        return userRepository.save(userModel);
    }

    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
    }

    public User findUserById(Long id) {
        return userRepository
            .findById(id)
            .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
    }

    public void updateUserEmail(String oldEmail, String newEmail) {
        User userModel = findUserByEmail(oldEmail);
        userModel.setEmail(newEmail);
        userRepository.save(userModel);
    }

    public void deleteUserByEmail(String email) {
        User userModel = findUserByEmail(email);
        userRepository.deleteUserByEmail(userModel.getEmail());
    }

}
