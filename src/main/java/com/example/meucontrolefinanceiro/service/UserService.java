package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.repository.UserRepository;
import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotDeletedException;
import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotFound;
import com.example.meucontrolefinanceiro.utils.exceptions.ExistentAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        checkForExistentAccountByEmail(userRegistrationDTO.getEmail());
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
        checkForExistentAccountByEmail(newEmail);
        User userModel = findUserByEmail(oldEmail);
        userModel.setEmail(newEmail);
        userRepository.save(userModel);
    }

    public void deleteUserByEmail(String email) {
        User userModel = findUserByEmail(email);
        categoryRepository.deleteAllByUserEmail(email);
        userRepository
                .deleteUserByEmail(userModel.getEmail())
                .orElseThrow(() -> new AccountNotDeletedException("Não possível deletar essa conta"));
    }

    private void checkForExistentAccountByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ExistentAccountException("Conta existente");
        }
    }
}