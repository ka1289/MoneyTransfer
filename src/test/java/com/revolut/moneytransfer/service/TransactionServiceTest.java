package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
    public AccountService accountService = mock(AccountService.class);
    private TransactionService transactionService;
    Map<Long, Account> accounts = new HashMap<>();
    Account alice;
    Account bob;

    @Before
    public void setup() {

        alice = new Account("Alice", 1l, BigDecimal.valueOf(500.0));
        accounts.put(1l, alice);
        bob = new Account("Bob", 2l, BigDecimal.valueOf(500.0));
        accounts.put(2l, bob);
        transactionService = new TransactionService(accountService);
    }

    @Test
    public void makeTransactionTest() {
        when(accountService.getAccounts()).thenReturn(accounts);
        Assert.assertTrue(this.transactionService.makeTransaction(1, 2, new BigDecimal("200.0")));
        Assert.assertEquals(0, alice.getBalance().compareTo(new BigDecimal("300.0")));
        Assert.assertEquals(0, bob.getBalance().compareTo(new BigDecimal("700.0")));
    }

    @Test(expected = RuntimeException.class)
    public void makeTransactionTest_sameAccount() {
        when(accountService.getAccounts()).thenReturn(accounts);
        Assert.assertFalse(this.transactionService.makeTransaction(1, 1, new BigDecimal("200.0")));
    }
}