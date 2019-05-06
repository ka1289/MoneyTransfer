package com.revolut.moneytransfer.service;

import com.google.inject.Inject;
import com.revolut.moneytransfer.utils.RevolutException;
import com.revolut.moneytransfer.model.Account;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class that transfers money from A to B
 */
public class TransactionService {

    private AccountService accountService;

    @Inject
    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Checks if the transaction is valid and transfers the money from A to B.
     * if the transaction is success it returns true else false
     *
     * @param sender
     * @param receiver
     * @param amount
     * @throws RevolutException is the sender and receiver is the same
     * @return
     */
    public boolean makeTransaction(long sender, long receiver, BigDecimal amount) {
        if(sender == receiver) {
            throw new RevolutException("A person cannot send money to his own account", 400);
        }
        Map<Long, Account> accounts = this.accountService.getAccounts();
        Account alice = accounts.get(sender);
        Account bob = accounts.get(receiver);

        if (alice.withdraw(amount, receiver)) {
            bob.deposit(amount, sender);
            return true;
        }
        return false;
    }
}
