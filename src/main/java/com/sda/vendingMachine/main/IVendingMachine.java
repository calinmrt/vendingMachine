package com.sda.vendingMachine.main;

import com.sda.vendingMachine.models.Coin;
import com.sda.vendingMachine.models.NotFullPaidException;
import com.sda.vendingMachine.models.SoldOutExeption;

public interface IVendingMachine {
	/**
	 * Accepts a Coin stacking it in the correct CoinHolder if available slot or
	 * directly in the Bank if the correspondent CoinHoder is full; Updates the user
	 * available balance
	 * 
	 * @param coin inserted in the vending machine
	 */
	void acceptCoin(Coin coin);

	/**
	 * Selects an Item and dispense it based on availability in the storage and
	 * fully paid value
	 * 
	 * @param key key-id of this Item shelf in the Storage
	 */
	void selectItem(int key);

	/**
	 * Dispense the remaining change if any *
	 */
	void refundRemainingBalance();

	/**
	 * Refills the machine with missing products; Cashes out or in the bank leaving
	 * the machine with full stacks of Coins available for change
	 * 
	 * @param pass Password for administrator rights only
	 */
	void adminRefillMachine(String pass);

}
