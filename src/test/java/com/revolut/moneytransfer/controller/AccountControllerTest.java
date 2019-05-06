package com.revolut.moneytransfer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.utils.RevolutException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    private AccountService accountService = mock(AccountService.class);
    private Account alice;
    private Account bob;
    Map<Long, Account> accounts = new HashMap<>();
    private AccountController accountController = new AccountController(accountService);

    @Before
    public void setup() {
        alice = new Account("Alice", 1l, BigDecimal.valueOf(500.0));
        accounts.put(1l, alice);
        bob = new Account("Bob", 2l, BigDecimal.valueOf(500.0));
        accounts.put(2l, bob);
    }

    @Test
    public void getAccountDetailsTest() {
        when(accountService.getAccount(1l)).thenReturn(alice);
        Assert.assertEquals(this.accountController.getAccountDetails("1"), new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(alice));

    }

    @Test(expected = RevolutException.class)
    public void getAccountDetailsTest_inavlidAccountId() {
        when(accountService.getAccount(3l)).thenReturn(accounts.get(3l));
        this.accountController.getAccountDetails("3");
    }
}
