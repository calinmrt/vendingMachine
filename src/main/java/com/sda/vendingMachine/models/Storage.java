package com.sda.vendingMachine.models;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static Storage instance;
    private Map<Integer, ItemHolder> products = new HashMap<>();

    public Map<Integer, ItemHolder> getProducts() {
        return products;
    }

    private Storage() {
    }

    //singleton
    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage();

        return instance;
    }

    public void adminRefill() {
        for (int i = 0; i < Item.values().length; i++) {
            Item item = Item.values()[i];
            products.put(i + 1, new ItemHolder(item));
        }
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
