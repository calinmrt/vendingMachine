package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.Coin;

public interface IVendingMachine {
    void acceptCoin(Coin coin);

    void selectItem(int key);

    void cashBalance();

    void adminRefillMachine(String pass);


}
