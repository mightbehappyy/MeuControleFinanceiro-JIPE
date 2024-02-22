package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.TransactionFilterDateDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final UserService userService;
  private final CategoryService categoryService;
  private final CategoryRepository categoryRepository;

  public Transaction createTransaction(UserTransactionDTO userTransactionDTO) {
    User user = userService.findUserByEmail(userTransactionDTO.getEmail());
    List<Category> categories = categoryService
        .findCategoryByNameAndUserId(userTransactionDTO.getCategory(), user.getId());
    Category category;

    if (categories.isEmpty()) {
      Category categoryModel = new Category();
      categoryModel.setName(userTransactionDTO.getCategory());
      categoryModel.setUser(user);
      categoryRepository.save(categoryModel);
      category = categoryModel;
    } else {
      category = categories.get(0);
    }

    Transaction transactionModel = new Transaction();
    BeanUtils.copyProperties(userTransactionDTO, transactionModel);
    transactionModel.setUser(user);
    transactionModel.setCategory(category);

    return transactionRepository.save(transactionModel);
  }

  public List<Transaction> findByUserId(Long user_id) {
    userService.findUserById(user_id);

    return transactionRepository.findByUserId(user_id);
  }

  public List<Transaction> findByType(TransactionEnum type) {

    return transactionRepository.findByType(type);
  }

  public List<Transaction> findByDateRange(TransactionFilterDateDTO transactionFilterDateDTO) {

    return transactionRepository.findAllByDateBetweenAndUserId(
        transactionFilterDateDTO.getDateStart(),
        transactionFilterDateDTO.getDateEnd(),
        transactionFilterDateDTO.getUser_id()
    );
  }

  public List<Transaction> findByDateRangeAndType(
      TransactionFilterDateDTO transactionFilterDateDTO,
      TransactionEnum type
  ) {

    return transactionRepository.findAllByDateBetweenAndUserIdAndType(
        transactionFilterDateDTO.getDateStart(),
        transactionFilterDateDTO.getDateEnd(),
        transactionFilterDateDTO.getUser_id(),
        type
    );
  }
}
