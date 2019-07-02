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
        for (int i = 0; i < Item.values().length; i++) {
            instance.products.put(i+1, new ItemHolder(Item.values()[i]));
        }

        return instance;
    }


    @Override
    public String toString() {
        return products.toString()+"\n";
    }
}
