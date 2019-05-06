package com.revolut.moneytransfer.utils;

import lombok.Getter;

public class RevolutException extends RuntimeException{

    private String message;

    private int httpCode;

    public RevolutException(String message, int httpCode) {
        super(message);
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
