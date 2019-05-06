package com.revolut.moneytransfer;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.TransactionController;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransactionService;
import com.revolut.moneytransfer.utils.ErrorResponse;
import com.revolut.moneytransfer.utils.RevolutException;
import io.javalin.Javalin;

public class App extends AbstractModule {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        Injector injector = Guice.createInjector(new App());
//        TransactionService txnService = injector.getInstance(TransactionService.class);
        TransactionController txnController = injector.getInstance(TransactionController.class);
        AccountController accountController = injector.getInstance(AccountController.class);
//        AccountService accountService = injector.getInstance(AccountService.class);
//
//        Map<Long, Account> accounts = accountService.getAccounts();


//        txnService.makeTransaction(1l, 2l, BigDecimal.valueOf(50));
//        Account alice = accounts.get(1l);
//        Account bob = accounts.get(2l);
//        System.out.println(alice.getBalance());
//        System.out.println(bob.getBalance());
//
        app.post("/transaction/" , ctx -> {

            String obj = txnController.makeTransaction(ctx.formParam("sender"),
                    ctx.formParam("receiver"), ctx.formParam("amount"));
            ctx.result(obj);
            ctx.header("content-type", "application/json");
            ctx.status(200);
        });

        app.get("/account/:accountid" , ctx -> {

            String obj = accountController.getAccountDetails(ctx.pathParam("accountid"));
            ctx.result(obj);
            ctx.header("content-type", "application/json");
            ctx.status(200);
        });

        app.exception(RevolutException.class, (e, ctx) -> {
            // handle general exceptions here
            // will not trigger if more specific exception-mapper found

            ctx.result(new Gson().toJson(new ErrorResponse(e.getMessage())));
            ctx.header("content-type", "application/json");
            ctx.status(e.getHttpCode());
        });
}

    @Override
    protected void configure() {
        bind(AccountService.class).asEagerSingleton();
        bind(TransactionService.class);
        bind(TransactionController.class);
        bind(AccountController.class);
    }
}
