package com.example.meucontrolefinanceiro.controller.responses;

import com.example.meucontrolefinanceiro.model.Category;
import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TransactionCreationResponse {
    private String title;
    private float cost;
    private TransactionEnum transactionEnum;
    private Date date;
    private String categoryName;
    private float categoryBudget;
    private float categoryTotalExpenses;
    private float userBudget;
    private float userTotalExpenses;
    private String amazonId;


    public TransactionCreationResponse(
            Transaction transaction,
            Category category,
            TransactionsInfoResponse userTransactionsInfo,
            TransactionsInfoResponse categoryTransactionsInfo,
            User user) {
        this.title = transaction.getTitle();
        this.cost = transaction.getCost();
        this.transactionEnum = transaction.getType();
        this.date = transaction.getDate();
        this.categoryName = category.getName();
        this.categoryBudget = categoryTransactionsInfo.getBudget();
        this.categoryTotalExpenses = Math.abs(categoryTransactionsInfo.getOutcoming());
        this.userBudget = userTransactionsInfo.getBudget();
        this.userTotalExpenses = Math.abs(userTransactionsInfo.getOutcoming());
        this.amazonId = user.getAmazonId();


    }
}
