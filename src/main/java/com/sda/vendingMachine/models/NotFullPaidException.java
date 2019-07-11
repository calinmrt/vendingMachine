package com.sda.vendingMachine.models;

public class NotFullPaidException extends Exception {
    private String message;

    public NotFullPaidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
 