package com.revolut.moneytransfer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    Account alice;
    Account bob;

    @Before
    public void setup () {
        alice = new Account("Test1", 1l, new BigDecimal("100.00"));
        bob = new Account("Test2", 2l, new BigDecimal("100.00"));
    }

    @Test
    public void getBalanceTest() {
        Assert.assertEquals(alice.getBalance(), new BigDecimal("100.00"));
    }

    @Test
    public void withdrawTest() {
        alice.withdraw(new BigDecimal("50.0"), 2l);
        Assert.assertEquals(0, alice.getBalance().compareTo(new BigDecimal("50.0")));
        Assert.assertEquals(alice.getTransactions().size(), 1);
    }

    @Test
    public void depositTest() {
        alice.deposit(new BigDecimal("50.0"), 2l);
        Assert.assertEquals(0, alice.getBalance().compareTo(new BigDecimal("150.0")));
        Assert.assertEquals(alice.getTransactions().size(), 1);
    }
}
