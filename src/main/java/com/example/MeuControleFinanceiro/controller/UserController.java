package com.example.MeuControleFinanceiro.controller;


import com.example.MeuControleFinanceiro.controller.responses.UserResponse;
import com.example.MeuControleFinanceiro.model.User;
import com.example.MeuControleFinanceiro.model.dtos.UserRegistrationDTO;
import com.example.MeuControleFinanceiro.repository.UserRepository;
import com.example.MeuControleFinanceiro.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {



    private final UserService userService;
    @PostMapping("/criar-usuario")
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(userService.createUser(userRegistrationDTO)));

    }
}
