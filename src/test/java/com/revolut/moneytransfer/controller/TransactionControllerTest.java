package com.revolut.moneytransfer.controller;

import com.google.gson.Gson;
import com.revolut.moneytransfer.service.TransactionService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    TransactionService transactionService = mock(TransactionService.class);

    TransactionController transactionController = new TransactionController(transactionService);

    @Test
    public void makeTransactionTest() {
        when(transactionService.makeTransaction(1l, 2l, new BigDecimal("100")))
                .thenReturn(true);
        Assert.assertEquals(this.transactionController.makeTransaction("1", "2", "100"),
                new Gson().toJson(new TransactionResponse(true)));

    }

    @Test
    public void makeTransactionTest_False() {
        when(transactionService.makeTransaction(1l, 2l, new BigDecimal("100")))
                .thenReturn(false);
        Assert.assertEquals(this.transactionController.makeTransaction("1", "2", "100"),
                new Gson().toJson(new TransactionResponse(false)));

    }
}
