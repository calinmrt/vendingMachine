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
                this.adminRefill();
                this.cashOutToDefault();

            } else {
                throw new InvalidPasswordException("invalid password");
            }

        } catch (InvalidPasswordException x) {
            System.out.println(x.getMessage());
        } finally {
            System.out.println("*******************************************************************************************");

        }
    }

    private void adminRefill() {
        for (Integer i :
                storage.getProducts().keySet()) {
            ItemHolder itemHolder = storage.getProducts().get(i);
            int cnt=0;
            while(itemHolder.getStackOfItems().size()!=itemHolder.getQuantity()){
                itemHolder.getStackOfItems().push(itemHolder.getItemType());
                cnt++;
            }
            System.out.println("refilled with "+cnt+" "+itemHolder.getItemType());

        }
    }

    private void cashOutToDefault() {

        for (Coin coin : banck.getCoins().keySet()) {
            CoinHolder coinHolder = banck.getCoins().get(coin);
            if (coinHolder.getStackOfCoins().size() < coinHolder.getMaxCapacity()) {
                int coinsToRefill = coinHolder.getMaxCapacity() - coinHolder.getStackOfCoins().size();
                for (int i = 0; i < coinsToRefill; i++) {
                    coinHolder.getStackOfCoins().push(coin);
                    banck.setCashOverStackedCoins(banck.getCashOverStackedCoins()- coin.getVal());
                }
            }
        }
        if(banck.getCashOverStackedCoins()>=0) {
            System.out.println("You cashed OUT " + banck.getCashOverStackedCoins() + " $");
        }else{
            System.out.println("You cashed IN "+ banck.getCashOverStackedCoins()*-1 + " $");
        }
        banck.setCashOverStackedCoins(0);

    }
    @Override
    public String toString() {
        return storage.toString() + "\n" + banck.toString();
    }
}
