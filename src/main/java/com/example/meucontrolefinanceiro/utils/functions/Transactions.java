package com.example.meucontrolefinanceiro.utils.functions;

import com.example.meucontrolefinanceiro.model.Transaction;
import com.example.meucontrolefinanceiro.model.enums.TransactionEnum;

import java.util.*;

public class Transactions {

    public static Map<String, Float> getFilteredTransactionsInfo(List<Transaction> transactionList) {

        float expense = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionEnum.EXPENSE))
                .map(Transaction::getCost).reduce(0.0f, Float::sum);

        float income = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionEnum.INCOME))
                .map(Transaction::getCost).reduce(0.0f, Float::sum);

        float total = income - expense;

        return Map.of(
          "expense", expense,
          "income", income,
          "total", total
        );
    }

    public static Map<String, Date> getDateMonthRange(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int lastDayValue = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return Map.of(
          "firstDay", new GregorianCalendar(year, month, 1).getTime(),
          "lastDay", new GregorianCalendar(year, month, lastDayValue).getTime()
        );
    }
}
