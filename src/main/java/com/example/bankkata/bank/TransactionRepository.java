package com.example.bankkata.bank;

/**
 * @author $ {USER}
 **/
import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
