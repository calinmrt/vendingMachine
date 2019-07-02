package com.sda.vendingMachine.models;

import java.util.ArrayDeque;

public class ItemHolder {
    private ArrayDeque<Item> stackOfItems=new ArrayDeque<>();
    private int quantity;

    ItemHolder(Item item){
        this.quantity=item.getQuantity();
        for (int i = 0; i <quantity ; i++) {
            this.stackOfItems.add(item);
        }
    }

    ArrayDeque<Item> getStackOfItems() {
        return stackOfItems;
    }


    @Override
    public String toString() {
        return stackOfItems.toString();
    }
}
