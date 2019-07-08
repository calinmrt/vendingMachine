package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.Coin;
import com.sda.vendingMachine.models.Item;

public class App {
    public static void main(String[] args) {
        IVendingMachine atm = VendingMachineImpl.getInstance();
        System.out.println(atm);
        atm.adminRefillMachine("admin");
        System.out.println(atm);
        atm.acceptCoin(Coin.NICKEL);
        System.out.println(atm);
        atm.selectItem(2);
        System.out.println(atm);


    }
}
