package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.controller.responses.ApiResponse;
import com.example.meucontrolefinanceiro.controller.responses.UserResponse;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserUpdateBudgetDTO;
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

/*    @PatchMapping("/amazonId")
    public ResponseEntity<?> updateUserAmazonId(@RequestBody
    UserUpdateAmazonIdDTO userUpdateAmazonIdDTO) {
        userService.updateUserAmazonId(
            userUpdateAmazonIdDTO.getOldAmazonId(),
            userUpdateAmazonIdDTO.getNewAmazonId()
        );
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Usuário atualizado"));
    }
 */

    @PatchMapping("/budget")
    public ResponseEntity<?> updateUserBudget(
        @RequestBody UserUpdateBudgetDTO userUpdateBudgetDTO
    ) {
        userService.updateUserBudget(userUpdateBudgetDTO);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Orçamento atualizado"));
    }

    @GetMapping("/{amazonId}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("amazonId") String amazonId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserResponse(userService.findUserByAmazonId(amazonId)));
    }

    @DeleteMapping("/{amazonId}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("amazonId") String amazonId) {
        userService.deleteUserByAmazonId(amazonId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Conta deletada"));
    }
}
