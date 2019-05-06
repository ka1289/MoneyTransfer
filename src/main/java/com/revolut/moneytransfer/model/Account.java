package com.revolut.moneytransfer.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    @Getter
    private String accountName;

    private long id;

    private BigDecimal balance;

    private List<Transaction> transactions = new ArrayList<>();

    //  Since Account  creation is a one time process at the beginning and Account is unmodifiable,
    //  we can safely have a lock per account
    private final Object lock = new Object();

    public Account(String accountName, long id, BigDecimal balance) {
        this.accountName = accountName;
        this.id = id;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean withdraw(BigDecimal amount, long receiver) {
        synchronized (lock) {
            if (balance.compareTo(amount) >= 0) {
                this.balance = balance.subtract(amount);
                this.transactions.add(new Transaction(this.id, receiver, amount));
                return true;
            }
            return false;
        }
    }

    public boolean deposit(BigDecimal amount, long sender) {
        synchronized (lock) {
            this.balance = balance.add(amount);
            this.transactions.add(new Transaction(sender, this.id, amount));
            return true;
        }
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(this.transactions);
    }
}
