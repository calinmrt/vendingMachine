package com.sda.vendingMachine.models;

public enum Coin {

    PENNY(0.01), NICKEL(0.05), DIME(0.10), QUARTER(0.25);

    private double val;

    Coin(double val) {
        this.val = val;
    }

    public double getVal() {
        return val;
    }

}
