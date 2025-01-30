package com.example.bankkata.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        int balance = calculateBalance() + amount;
        transactionRepository.addTransaction(new Transaction(getCurrentDate(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        int balance = calculateBalance() - amount;
        if (balance < 0) {
            throw new IllegalArgumentException("Insufficient Balance");
        }
        transactionRepository.addTransaction(new Transaction(getCurrentDate(), -amount, balance));
    }

    @Override
    public void printStatement() {
        statementPrinter.printLine("Date       | Amount | Balance");
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            statementPrinter.printLine(transaction.toString());
        }
    }

    private int calculateBalance() {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        if (transactions.isEmpty()) {
            return 0;
        }
        return transactions.get(transactions.size() - 1).getBalance();
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
