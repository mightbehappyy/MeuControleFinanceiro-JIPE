package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.CategoryUpdateBudgetDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserCategoryDTO;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.utils.exceptions.CategoryNotFoundException;

import java.util.List;
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
    categoryModel.setBudget(0);
    return categoryRepository.save(categoryModel);
  }

  public List<Category> findAllCategoriesByNameAndUserId(String name, Long userId) {
    return categoryRepository
        .findAllByNameAndUserId(name, userId);
        //.orElseThrow(() -> new CategoryNotFoundException("Categoria não foi encontrada"));
  }

  public Category findCategoryByNameAndUserId(String name, Long userId) {
    return categoryRepository.findByNameAndUserId(name, userId);
  }

  public Category findCategoryByNameAndAmazonId(String name, String amazonId) {
    return categoryRepository.findByNameAndUserEmail(name, amazonId);
  }

  public List<Category> findAllCategoriesByEmail(String email) {
    userService.findUserByEmail(email);
    return categoryRepository.findAllByUserEmail(email);
  }

  public Category updateCategoryBudget(CategoryUpdateBudgetDTO categoryUpdateBudgetDTO) {
    String amazonId = categoryUpdateBudgetDTO.getAmazonId();
    String categoryName = categoryUpdateBudgetDTO.getCategoryName();
    int newBudget = categoryUpdateBudgetDTO.getNewCategoryBudget();
    long userId = userService.findUserByEmail(amazonId).getId();

    Category category = findCategoryByNameAndUserId(categoryName, userId);
    category.setBudget(newBudget);
    return category;
  }

  public Category findCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Categoria não foi encontrada"));
  }
}
