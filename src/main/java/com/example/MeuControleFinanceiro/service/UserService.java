package com.example.MeuControleFinanceiro.service;


import com.example.MeuControleFinanceiro.model.User;
import com.example.MeuControleFinanceiro.model.dtos.UserRegistrationDTO;
import com.example.MeuControleFinanceiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
}
