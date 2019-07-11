package com.sda.vendingMachine.models;

import java.util.Arrays;
import java.util.Comparator;

public enum Coin {

    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private int val;

    Coin(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
    
    public static Coin[] getSortedCoins() {
    	Coin[] coins=values();
    	Arrays.sort(coins, new Comparator<Coin>() {
			@Override
			public int compare(Coin arg0, Coin arg1) {
				if (arg0.getVal() > arg1.getVal()) {
					return -1;
				} else
					return 1;
			}
		});
		return coins;
    	
    }

}
