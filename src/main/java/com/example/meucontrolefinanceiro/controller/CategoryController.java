package com.example.meucontrolefinanceiro.controller;

import com.example.meucontrolefinanceiro.model.dtos.CategoryUpdateBudgetDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserCategoryDTO;
import com.example.meucontrolefinanceiro.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @Operation(
      summary = "Criar categoria",
      description = "Este endpoint permite que o usuário crie uma categoria com um orçamento"
          + "definido."
  )
  @PostMapping
  public ResponseEntity<?> createCategory(@RequestBody UserCategoryDTO userCategoryDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(userCategoryDTO));
  }

  @Operation(
      summary = "Listar categorias de um usuário pelo nome e ID",
      description = "Este endpoint permite que seja listado a categoria com um nome e usuário "
          + "específico."
  )
  @GetMapping("/{name}/{user_id}")
  public ResponseEntity<?> findCategoryByNameAndUserId(
      @PathVariable("user_id") Long user_id,
      @PathVariable("name") String name
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
        categoryService.findCategoryByNameAndUserId(name, user_id)
    );
  }

  @Operation(
      summary = "Listar categorias de um usuário específico com AmazonID",
      description = "Este endpoint permite que seja listado todas as categorias cadastradas de um "
          + "usuário específico através do seu AmazonID."
  )
  @GetMapping("user/{amazonId}")
  public ResponseEntity<?> findAllCategoriesByName(@PathVariable("amazonId") String amazonId) {
    return ResponseEntity.ok(categoryService.findAllCategoriesByAmazonId(amazonId));
  }

  @Operation(
      summary = "Listar uma categoria específica com ID",
      description = "Este endpoint permite que seja listado uma categoria específica através do ID."
  )
  @GetMapping("/{id}")
  public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryById(id));
  }

  @Operation(
      summary = "Atualizar o orçamento de uma categoria",
      description = "Este endpoint permite que seja atualizado o orçamento de uma categoria "
          + "específica."
  )
  @PatchMapping
  public ResponseEntity<?> updateCategoryBudget(
      @RequestBody CategoryUpdateBudgetDTO categoryUpdateBudgetDTO
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
        categoryService.updateCategoryBudget(categoryUpdateBudgetDTO)
    );
  }

}
