package com.revolut.moneytransfer.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.revolut.moneytransfer.service.TransactionService;

import java.math.BigDecimal;

public class TransactionController {

    private final TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Takes as input the sender, receiver and the amount to be transferred
     * returns the json response which tells us whether the transaction was a success or not.
     *
     * @param sender
     * @param receiver
     * @param amount
     * @return
     */
    public String  makeTransaction(String sender, String receiver, String amount) {
        boolean result = this.transactionService.makeTransaction(Long.valueOf(sender), Long.valueOf(receiver), new BigDecimal(amount));
        TransactionResponse response = new TransactionResponse(result);
        return new Gson().toJson(response);
    }
}

class TransactionResponse {
    private boolean success;

    public TransactionResponse(boolean success) {
        this.success = success;
    }
}
