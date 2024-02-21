package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.controller.responses.ApiResponse;
import com.example.meucontrolefinanceiro.controller.responses.UserResponse;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserUpdateDTO;
import com.example.meucontrolefinanceiro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(userService.createUser(userRegistrationDTO)));
    }

    @PatchMapping
    public ResponseEntity<?> updateUserEmail(@RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUserEmail(userUpdateDTO.getOldEmail(), userUpdateDTO.getNewEmail());
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Usuário atualizado"));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserResponse(userService.findUserByEmail(email)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Conta deletada"));
    }
}