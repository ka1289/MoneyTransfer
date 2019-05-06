package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private static Map<Long, Account> accounts = null;

    private AccountService() {
        accounts = new HashMap<>();
        Account alice = new Account("Alice", 1l, BigDecimal.valueOf(500.0));
        accounts.put(1l, alice);
        Account bob = new Account("Bob", 2l, BigDecimal.valueOf(500.0));
        accounts.put(2l, bob);
    }

    public Map<Long, Account> getAccounts() {
        if (accounts == null) {
            new AccountService();
        }

        return Collections.unmodifiableMap(accounts);
    }

    public Account getAccount(Long accountId) {
        return accounts.get(accountId);
    }
}
