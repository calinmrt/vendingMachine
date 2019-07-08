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
        CoinHolder coinHolder = banck.getCoins().get(coin);
        if (coinHolder.getStackOfCoins().size() < coinHolder.getMaxCapacity()) {
            coinHolder.getStackOfCoins().push(coin);
        } else {
            banck.setCashOverStackedCoins(banck.getCashOverStackedCoins() + coin.getVal());
        }
        balance += coin.getVal();
    }

    @Override
    public void selectItem(int key) {
        ItemHolder selectedItemHolder = storage.getProducts().get(key);
        Item selectedItem = selectedItemHolder.getItemType();
        boolean hasEnoughBalance = false;
        boolean hasTheItem = false;
        try {
            hasEnoughBalance = this.checkPaidBalance(selectedItem.getPrice());
        } catch (NotFullPaidException x) {
            System.out.println(x.getMessage());
            return;
        }
        try {
            hasTheItem = this.checkAvailability(selectedItemHolder);
        } catch (SoldOutExeption x) {
            System.out.println(x.getMessage());
            return;
        }
        if(hasEnoughBalance&&hasTheItem){
            this.dispenseTheProduct(selectedItemHolder);

        }



    }

    private void dispenseTheProduct(ItemHolder selectedItemHolder) {
        Item dispensedItem=selectedItemHolder.getStackOfItems().poll();
        System.out.println("Dispensed 1 pc of: "+dispensedItem);
    }

    private boolean checkAvailability(ItemHolder selectedItemHolder) throws SoldOutExeption {
        if (selectedItemHolder.getStackOfItems().isEmpty()) throw new SoldOutExeption("This product is sold out! ");
        else return true;

    }

    private boolean checkPaidBalance(Double itemPrice) throws NotFullPaidException {
        if (balance < itemPrice) throw new NotFullPaidException("Not full paid for this product!");
        else
            return true;
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
            int cnt = 0;
            while (itemHolder.getStackOfItems().size() != itemHolder.getQuantity()) {
                itemHolder.getStackOfItems().push(itemHolder.getItemType());
                cnt++;
            }
            System.out.println("refilled with " + cnt + " " + itemHolder.getItemType());

        }
    }

    private void cashOutToDefault() {

        for (Coin coin : banck.getCoins().keySet()) {
            CoinHolder coinHolder = banck.getCoins().get(coin);
            if (coinHolder.getStackOfCoins().size() < coinHolder.getMaxCapacity()) {
                int coinsToRefill = coinHolder.getMaxCapacity() - coinHolder.getStackOfCoins().size();
                for (int i = 0; i < coinsToRefill; i++) {
                    coinHolder.getStackOfCoins().push(coin);
                    banck.setCashOverStackedCoins(banck.getCashOverStackedCoins() - coin.getVal());
                }
            }
        }
        if (banck.getCashOverStackedCoins() >= 0) {
            System.out.println("You cashed OUT " + banck.getCashOverStackedCoins() + " $");
        } else {
            System.out.println("You cashed IN " + (banck.getCashOverStackedCoins()) + " $");
        }
        banck.setCashOverStackedCoins(0);

    }

    @Override
    public String toString() {
        return storage.toString() + "\n" + banck.toString() + "\n" + "balance is: " + balance+"\n"+"balance of banck is"+banck.getCashOverStackedCoins();
    }
}
