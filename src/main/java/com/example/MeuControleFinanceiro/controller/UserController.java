package com.example.MeuControleFinanceiro.controller;

import com.example.MeuControleFinanceiro.controller.responses.UserResponse;
import com.example.MeuControleFinanceiro.model.dtos.UserRegistrationDTO;
import com.example.MeuControleFinanceiro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deletar-usuario")
    public ResponseEntity<?> deleteUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userRegistrationDTO));
    }

}
