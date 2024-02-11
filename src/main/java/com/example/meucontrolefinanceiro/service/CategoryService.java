package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.UserCategoryDTO;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotFound;
import com.example.meucontrolefinanceiro.utils.exceptions.CategoryNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final UserService userService;

  public Category createCategory(UserCategoryDTO userCategoryDTO) {
    User user = userService.findUserByEmail(userCategoryDTO.getEmail());

    Category categoryModel = new Category();
    BeanUtils.copyProperties(userCategoryDTO, categoryModel);
    categoryModel.setUser(user);
    return categoryRepository.save(categoryModel);
  }

  public Category findCategoryByNameAndUserId(String name, Long user_id) {
    return categoryRepository
        .findByNameAndUserId(name, user_id)
        .orElseThrow(() -> new CategoryNotFoundException("Categoria não foi encontrada"));
  }

  public Category findCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Categoria não foi encontrada"));
  }
}
