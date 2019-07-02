package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.Coin;
import com.sda.vendingMachine.models.Item;

public class App {
    public static void main(String[] args) {
        IVendingMachine atm = VendingMachineImpl.getInstance();
        // atm.adminRefillMachine("admin");
        atm.selectItem(4);

    }
}
