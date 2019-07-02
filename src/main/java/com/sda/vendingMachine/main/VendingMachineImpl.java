package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.*;

public class VendingMachineImpl implements IVendingMachine {
    private static VendingMachineImpl instance;

    private Storage storage = Storage.getInstance();
    private Banck banck = Banck.getInstance();
    private double balance;

    private VendingMachineImpl() {
    }

    public static VendingMachineImpl getInstance() {
        if (instance == null) {
            instance = new VendingMachineImpl();
        }
        return instance;
    }

    @Override
    public void acceptCoin(Coin coin) {
        CoinHolder coinHolder=banck.getCoins().get(coin);
        if(coinHolder.getStackOfCoins().size()<coinHolder.getMaxCapacity()){
            coinHolder.getStackOfCoins().push(coin);
        }else{
            banck.setCashOverStackedCoins(banck.getCashOverStackedCoins()+coin.getVal());
        }
        balance+=coin.getVal();
    }

    @Override
    public void selectItem(int key) {

    }

    @Override
    public void cashBalance() {
    }

    @Override
    public void adminRefillMachine(String pass) {
        System.out.println("*******************************************************************************************");
        try {
            if (pass == "admin") {
                storage.adminRefill();
                banck.cashOutToDefault();

            } else {
                throw new InvalidPasswordException("invalid password");
            }

        } catch (InvalidPasswordException x) {
            System.out.println(x.getMessage());
        } finally {
            System.out.println("*******************************************************************************************");

        }
    }

    @Override
    public String toString() {
        return storage.toString() + "\n" + banck.toString();
    }
}
