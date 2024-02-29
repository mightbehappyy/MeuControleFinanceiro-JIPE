package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.controller.responses.TransactionCreationResponse;
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

  public TransactionCreationResponse createTransaction(UserTransactionDTO userTransactionDTO) {
    User user = userService.findUserByAmazonId(userTransactionDTO.getAmazonId());
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
    Transaction transaction = transactionRepository.save(transactionModel);

    return new TransactionCreationResponse(
            transaction,
            category,
            findMonthTransactions(transaction.getDate(), user.getAmazonId()),
            findCategoryTransactions(transaction.getDate(), user.getAmazonId(), category.getName()),
            user
            );
  }

    public List<Transaction> findByUserId(Long user_id) {
        userService.findUserById(user_id);

        return transactionRepository.findByUserId(user_id);
    }

    public List<Transaction> findByUserAmazonId(String amazonId) {
        userService.findUserByAmazonId(amazonId);
        return transactionRepository.findAllByUserAmazonId(amazonId);
    }

    public List<Transaction> findByType(TransactionEnum type) {

        return transactionRepository.findByType(type);
    }

    public TransactionsInfoResponse findMonthTransactions(Date currentDate, String amazonId) {

        List<Transaction> transactionList = transactionRepository
            .findAllByDateBetweenAndUserAmazonId(
                        getDateMonthRange(currentDate).get("firstDay"),
                        getDateMonthRange(currentDate).get("lastDay"),
                        amazonId);

        Map<String, Float> transactionInfo = getFilteredTransactionsInfo(transactionList);

        return new TransactionsInfoResponse(
            transactionInfo.get("total"),
            transactionInfo.get("income"),
            transactionInfo.get("expense"),
            amazonId,
            userService.findUserByAmazonId(amazonId).getBudget()
        );
    }

    public TransactionsInfoResponse findCategoryTransactions(
        Date currentDate,
        String userAmazonId,
        String category
    ) {
        Category userCategory = categoryService
            .findCategoryByNameAndAmazonId(category, userAmazonId);
        Map<String, Date> monthRange = getDateMonthRange(currentDate);

        List<Transaction> transactionList = transactionRepository
            .findAllByDateBetweenAndUserAmazonIdAndCategory(
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
    public List<Transaction> findByDateRange(Date dateStart, Date dateEnd, String amazonId) {
        User user = userService.findUserByAmazonId(amazonId);

        return transactionRepository.findAllByDateBetweenAndUserId(
                dateStart,
                dateEnd,
                user.getId()
        );
    }

    public List<Transaction> findByDateRangeAndType(
            Date dateStart,
            Date dateEnd,
            String amazonId,
            TransactionEnum type
    ) {
        User user = userService.findUserByAmazonId(amazonId);

        return transactionRepository.findAllByDateBetweenAndUserIdAndType(
                dateStart,
                dateEnd,
                user.getId(),
                type
        );
    }
}
