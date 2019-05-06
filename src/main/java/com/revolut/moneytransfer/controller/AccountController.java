package com.revolut.moneytransfer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.revolut.moneytransfer.utils.RevolutException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;

public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retuns the account details which includes the name, accountId, balance and list of transactions.
     * @param accountId
     * @return
     */
    public String getAccountDetails(String accountId) {
        Account account = accountService.getAccount(Long.valueOf(accountId));
        if(account != null) {
            return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(account);
        } else {
            throw new RevolutException("Invalid Account Details: Account does not exist", 400);
        }
    }
}

