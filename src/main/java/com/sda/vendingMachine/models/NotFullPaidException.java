package com.sda.vendingMachine.models;

public class NotFullPaidException extends Exception {
    private final String message;

    public NotFullPaidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
