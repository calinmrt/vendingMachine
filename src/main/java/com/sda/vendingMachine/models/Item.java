package com.sda.vendingMachine.models;

public enum Item {
    COKE(50, 5), WATER(10, 5), CRACKERS(25, 10);

    private int price;
    private int quantity;

    Item(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    int getQuantity() {

        return quantity;
    }


    public int getPrice() {

        return price;
    }


}
 