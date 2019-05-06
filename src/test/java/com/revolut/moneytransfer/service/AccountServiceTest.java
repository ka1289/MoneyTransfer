package com.revolut.moneytransfer.service;

import com.google.inject.Inject;
import com.revolut.moneytransfer.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class AccountServiceTest {

    private AccountService accountService = new AccountService();

    @Test(expected = UnsupportedOperationException.class)
    public void getAccountsTest_unmodifiableMap() {
        Map<Long, Account> accounts = accountService.getAccounts();
        accounts.put(3l, new Account("Carr", 3l, new BigDecimal("300.0")));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAccountsTest_unmodifiableMapRemove() {
        Map<Long, Account> accounts = accountService.getAccounts();
        accounts.remove(2l);
    }
}
