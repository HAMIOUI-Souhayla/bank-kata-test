package com.example.bankkata.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $ {USER}
 **/
public class TransactionRepositoryImp implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}
