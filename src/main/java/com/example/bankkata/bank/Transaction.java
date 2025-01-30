package com.example.bankkata.bank;

/**
 * @author $ {USER}
 **/
public class Transaction {
    private String date;
    private int amount;
    private int balance;

    public Transaction(String date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%s | %-6d | %-6d", date, amount, balance);
    }

}
