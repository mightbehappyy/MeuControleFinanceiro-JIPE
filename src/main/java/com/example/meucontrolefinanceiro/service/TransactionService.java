package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.controller.responses.UserMonthlySpendingResponse;
import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.TransactionFilterDateDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserMonthlySpendingDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserTransactionDTO;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import com.example.meucontrolefinanceiro.repository.TransactionRepository;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public Transaction createTransaction(UserTransactionDTO userTransactionDTO) {
        User user = userService.findUserByEmail(userTransactionDTO.getEmail());
        Category category = categoryService
                .findCategoryByNameAndUserId(userTransactionDTO.getCategory(), user.getId());

        Transaction transactionModel = new Transaction();
        BeanUtils.copyProperties(userTransactionDTO, transactionModel);
        transactionModel.setUser(user);
        System.out.println("CHEGOU NO PONTO 1");
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

    public UserMonthlySpendingResponse findMonthTransactions(UserMonthlySpendingDTO userMonthlySpendingDTO) {
        userService.findUserByEmail(userMonthlySpendingDTO.getUserEmail());
        Calendar cal = Calendar.getInstance();
        cal.setTime(userMonthlySpendingDTO.getCurrentDate());

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int lastDayValue = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date firstDay = new GregorianCalendar(year, month, 1).getTime();
        Date lastDay = new GregorianCalendar(year, month, lastDayValue).getTime();

        List<Transaction> transactionList = transactionRepository
                .findAllByDateBetweenAndUserEmail(firstDay, lastDay, userMonthlySpendingDTO.getUserEmail());

        float expense = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionEnum.EXPENSE))
                .map(Transaction::getCost).reduce(0.0f, Float::sum);

        float income = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionEnum.INCOME))
                .map(Transaction::getCost).reduce(0.0f, Float::sum);

        float total = income - expense;

        return new UserMonthlySpendingResponse(total, userMonthlySpendingDTO.getUserEmail());
    }

    public List<Transaction> findByDateRange(TransactionFilterDateDTO transactionFilterDateDTO) {
        User user = userService.findUserByEmail(transactionFilterDateDTO.getEmail());

        return transactionRepository.findAllByDateBetweenAndUserId(
                transactionFilterDateDTO.getDateStart(),
                transactionFilterDateDTO.getDateEnd(),
                user.getId()
        );
    }

    public List<Transaction> findByDateRangeAndType(
            TransactionFilterDateDTO transactionFilterDateDTO,
            TransactionEnum type
    ) {
        User user = userService.findUserByEmail(transactionFilterDateDTO.getEmail());

        return transactionRepository.findAllByDateBetweenAndUserIdAndType(
                transactionFilterDateDTO.getDateStart(),
                transactionFilterDateDTO.getDateEnd(),
                user.getId(),
                type
        );
    }
}
