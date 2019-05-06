package com.revolut.moneytransfer.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    @Getter
    @Expose
    private String accountName;

    @Expose
    private long id;

    @Expose
    private BigDecimal balance;

    @Expose
    private List<Transaction> transactions = new ArrayList<>();

    //  Since Account  creation is a one time process at the beginning and Account is unmodifiable,
    //  we can safely have a lock per account
    private final Object lock = new Object();

    public Account(String accountName, long id, BigDecimal balance) {
        this.accountName = accountName;
        this.id = id;
        this.balance = balance;
    }

    /**
     * Returns balance of the account
     * @return
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Withdraws the money from the account if there enough and records it in
     * the set of transactions along with the receiver.
     *
     * @param amount
     * @param receiver
     * @return
     */
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

    /**
     * Deposits the money into the account and records it in the set of transactions along with
     * the sender.
     * @param amount
     * @param sender
     * @return
     */
    public boolean deposit(BigDecimal amount, long sender) {
        synchronized (lock) {
            this.balance = balance.add(amount);
            this.transactions.add(new Transaction(sender, this.id, amount));
            return true;
        }
    }

    /**
     * Returns the list of transactions of the account.
     * @return
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(this.transactions);
    }
}
