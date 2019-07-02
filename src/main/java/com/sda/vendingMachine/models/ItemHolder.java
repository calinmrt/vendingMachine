package com.sda.vendingMachine.models;

import java.util.ArrayDeque;

public class ItemHolder {
    private ArrayDeque<Item> stackOfItems=new ArrayDeque<>();
    private Item itemType;
    private int quantity;

    ItemHolder(Item item){
        this.itemType=item;
        this.quantity=item.getQuantity();
        for (int i = 0; i <quantity ; i++) {
            this.stackOfItems.add(item);
        }
    }

    public ArrayDeque<Item> getStackOfItems() {
        return stackOfItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return stackOfItems.toString()+"\n";
    }
}
