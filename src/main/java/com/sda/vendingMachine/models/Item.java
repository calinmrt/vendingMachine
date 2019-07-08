package com.sda.vendingMachine.models;

public enum Item {
    COKE(0.75, 5), WATER(0.10, 5), CRACKERS(0.25, 10);

    private double price;
    private int quantity;

    Item(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    int getQuantity() {

        return quantity;
    }


    public double getPrice() {

        return price;
    }


}
