package com.sda.vendingMachine.models;

import java.util.ArrayDeque;

public class CoinHolder {
    private  final int MAX_CAPACITY=20;
    private ArrayDeque<Coin> stackOfCoins=new ArrayDeque<>();


    public  int getMaxCapacity() { 
        return MAX_CAPACITY;
    }

    public ArrayDeque<Coin> getStackOfCoins() {
        return stackOfCoins;
    }
    
    public void insertCoinInStack(Coin coin) {
    	stackOfCoins.push(coin);
    }
    
    public boolean isStackEmpty() {
    	return stackOfCoins.isEmpty();
    }
    
    public Coin getThisCoin() {
    	return stackOfCoins.pop();
    }

    @Override
    public String toString() {
        return stackOfCoins.size()+"";
    }
}
