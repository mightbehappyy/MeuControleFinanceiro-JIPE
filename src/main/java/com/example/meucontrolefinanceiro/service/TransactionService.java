package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.controller.responses.TransactionsInfoResponse;
import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.repository.TransactionRepository;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.example.meucontrolefinanceiro.utils.functions.Transactions.getDateMonthRange;
import static com.example.meucontrolefinanceiro.utils.functions.Transactions.getFilteredTransactionsInfo;

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
        .findAllCategoriesByNameAndUserId(userTransactionDTO.getCategory(), user.getId());
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

    public List<Transaction> findByUserEmail(String userEmail) {
        userService.findUserByEmail(userEmail);
        return transactionRepository.findAllByUserEmail(userEmail);
    }

    public List<Transaction> findByType(TransactionEnum type) {

        return transactionRepository.findByType(type);
    }

    public TransactionsInfoResponse findMonthTransactions(Date currentDate, String email) {

        List<Transaction> transactionList = transactionRepository.findAllByDateBetweenAndUserEmail(
                        getDateMonthRange(currentDate).get("firstDay"),
                        getDateMonthRange(currentDate).get("lastDay"),
                        email);

        Map<String, Float> transactionInfo = getFilteredTransactionsInfo(transactionList);

        return new TransactionsInfoResponse(
            transactionInfo.get("total"),
            transactionInfo.get("income"),
            transactionInfo.get("expense"),
            email,
            userService.findUserByEmail(email).getBudget()
        );
    }

    public TransactionsInfoResponse findCategoryTransactions(Date currentDate, String userAmazonId, String category) {
        Category userCategory = categoryService.findCategoryByNameAndAmazonId(category, userAmazonId);
        Map<String, Date> monthRange = getDateMonthRange(currentDate);

        List<Transaction> transactionList = transactionRepository.findAllByDateBetweenAndUserEmailAndCategory(
                monthRange.get("firstDay"),
                monthRange.get("lastDay"),
                userAmazonId,
                userCategory
        );

        Map<String, Float> transactionInfo = getFilteredTransactionsInfo(transactionList);

        return new TransactionsInfoResponse(
          transactionInfo.get("total"),
          transactionInfo.get("income"),
          transactionInfo.get("expense"),
          userAmazonId,
          userCategory.getBudget()
        );
    }
    public List<Transaction> findByDateRange(Date dateStart, Date dateEnd, String email) {
        User user = userService.findUserByEmail(email);

        return transactionRepository.findAllByDateBetweenAndUserId(
                dateStart,
                dateEnd,
                user.getId()
        );
    }

    public List<Transaction> findByDateRangeAndType(
            Date dateStart,
            Date dateEnd,
            String email,
            TransactionEnum type
    ) {
        User user = userService.findUserByEmail(email);

        return transactionRepository.findAllByDateBetweenAndUserIdAndType(
                dateStart,
                dateEnd,
                user.getId(),
                type
        );
    }
}
