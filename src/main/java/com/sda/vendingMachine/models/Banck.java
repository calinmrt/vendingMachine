package com.sda.vendingMachine.models;

import java.util.Map;
import java.util.TreeMap;

public class Banck {
    private static Banck instance;
    private double cashOverStackedCoins;
    private Map<Coin, CoinHolder> coins = new TreeMap<>();

    public double getCashOverStackedCoins() {
        return cashOverStackedCoins;
    }

    public void setCashOverStackedCoins(double cashOverStackedCoins) {
        this.cashOverStackedCoins = cashOverStackedCoins;
    }

    public Map<Coin, CoinHolder> getCoins() {
        return coins;
    }


    private Banck() {
    }

    //singleton
    public static Banck getInstance() {
        if (instance == null) {
            instance = new Banck();
            for (int i = 0; i < Coin.values().length; i++) {
                instance.coins.put(Coin.values()[i], new CoinHolder());
            }
        }
        return instance;
    }



    public void cashOutToDefault() {
        for (Coin coin : coins.keySet()) {
            CoinHolder coinHolder = coins.get(coin);
            if (coinHolder.getStackOfCoins().size() < coinHolder.getMaxCapacity()) {
                int coinsToRefill = coinHolder.getMaxCapacity() - coinHolder.getStackOfCoins().size();
                for (int i = 0; i < coinsToRefill; i++) {
                    coinHolder.getStackOfCoins().push(coin);
                    cashOverStackedCoins -= coin.getVal();
                }
            }
        }
        if(cashOverStackedCoins>=0) {
            System.out.println("You cashed OUT " + cashOverStackedCoins + " $");
        }else{
            System.out.println("You cashed IN "+ cashOverStackedCoins + " $");
        }
        cashOverStackedCoins=0;

    }

    @Override
    public String toString() {
        return coins.toString();
    }
}
