package com.sda.vendingMachine.models;

public class InvalidProductSelection extends RuntimeException {
    private String message="Invalid Selection";

    public InvalidProductSelection() {
    }

    @Override
    public String getMessage() {
        return message;
    }

}
