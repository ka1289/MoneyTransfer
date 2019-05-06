package com.revolut.moneytransfer.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private long sender;

    private long receiver;

    private BigDecimal amount;

    public Transaction(long sender, long receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return sender == that.sender &&
                receiver == that.receiver &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount);
    }
}
