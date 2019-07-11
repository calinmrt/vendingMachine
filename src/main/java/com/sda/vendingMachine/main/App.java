package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.Coin;

public class App {
	public static void main(String[] args) {
		IVendingMachine atm = VendingMachineImpl.getInstance();
		atm.adminRefillMachine("admin");
		
		atm.selectItem(1);
		System.out.println(atm+"\n");
		atm.acceptCoin(Coin.NICKEL);
		atm.acceptCoin(Coin.NICKEL);
		atm.acceptCoin(Coin.NICKEL);
		atm.selectItem(2);
//		atm.selectItem(1);
//		System.out.println(atm);
//		atm.cashBalance();
		System.out.println(atm+"\n");

	}
}
