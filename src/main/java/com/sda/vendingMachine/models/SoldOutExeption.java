package com.sda.vendingMachine.models;

public class SoldOutExeption extends Exception {
    private String message;

    public SoldOutExeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
