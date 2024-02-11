package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.controller.responses.UserResponse;
import com.example.meucontrolefinanceiro.model.dtos.UserCategoryDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<?> createCategory(@RequestBody UserCategoryDTO userCategoryDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(userCategoryDTO));
  }

  @GetMapping("/{name}/{user_id}")
  public ResponseEntity<?> findCategoryByNameAndUserId(@PathVariable("user_id") Long user_id, @PathVariable("name") String name) {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryByNameAndUserId(name, user_id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryById(id));
  }

}
