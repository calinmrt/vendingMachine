package com.sda.vendingMachine.main;

import java.util.ArrayList;
import java.util.List;

import com.sda.vendingMachine.models.*;

public class VendingMachineImpl implements IVendingMachine {
	private static VendingMachineImpl instance;

	private Storage storage = Storage.getInstance();
	private Bank banck = Bank.getInstance();
	private int userBalance;

	private VendingMachineImpl() {
	}

	/**
	 * Singleton for unique instance
	 * 
	 * @return
	 */
	public static VendingMachineImpl getInstance() {
		if (instance == null) {
			instance = new VendingMachineImpl();
		}
		return instance;
	}
	
	public int getUserBalance() {
		return userBalance;
	}

	@Override
	public void acceptCoin(Coin coin) {
		CoinHolder coinHolder = banck.getCoins()
				.get(coin);
		if (coinHolder.getStackOfCoins()
				.size() < coinHolder.getMaxCapacity()) {
			coinHolder.insertCoinInStack(coin);
		} else {
			banck.setCashOverStackedCoins(banck.getCashOverStackedCoins() + coin.getVal());
		}
		userBalance += coin.getVal();
	}

	@Override
	public void selectItem(int key) {
		ItemHolder selectedItemHolder = storage.getProducts()
				.get(key);
		Item selectedItem = selectedItemHolder.getItemType();
		try {
			checkPaidBalance(selectedItem.getPrice());
			checkAvailability(selectedItemHolder);
		} catch (NotFullPaidException | SoldOutExeption e) {
			System.out.println(e.getMessage() + " " + selectedItem);
			return;
		}
		dispenseTheProductFromItemHolder(selectedItem, selectedItemHolder);
		System.out.println("Dispensed 1 " + selectedItem);

	}
	
	/**
	 * Dispenses selected Item assuming all conditions were checked
	 * 
	 * @param item               to be dispensed
	 * @param selectedItemHolder the holder that contains this Item
	 * @return selected Item
	 */
	private Item dispenseTheProductFromItemHolder(Item item, ItemHolder selectedItemHolder) {
		userBalance -= item.getPrice();
		return selectedItemHolder.getStackOfItems()
				.poll();
	}

	/**
	 * Checks availability of selected Item in specific Holder
	 * 
	 * @param selectedItemHolder that contains selected Item
	 * @throws SoldOutExeption If selected Item not available
	 */
	private void checkAvailability(ItemHolder selectedItemHolder) throws SoldOutExeption {
		Item selectedProduct = selectedItemHolder.getItemType();
		if (selectedItemHolder.getStackOfItems()
				.isEmpty())
			throw new SoldOutExeption(selectedProduct + " This product is sold out! ");
	}

	/**
	 * Checks if fully paid for selected Item
	 * 
	 * @param itemPrice price of selected Item
	 * @throws NotFullPaidException and missing difference
	 */
	public void checkPaidBalance(int itemPrice) throws NotFullPaidException {
		if (userBalance < itemPrice)
			throw new NotFullPaidException(
					"Not full paid for this product! you are " + (itemPrice - userBalance) + " short");
	}

	@Override
	public void refundRemainingBalance() {
		List<Coin> change = new ArrayList<>();
		try {
			collectCoinsForChange(change);
		} catch (NotSufficientChangeException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(change.toString());
		}
	}

	/**
	 * Collects from the bank available coins to refund if possible the entire User
	 * Balance starting to collect from the maximum appropriate value
	 * 
	 * @param change List collector for collected change
	 * @return List of Coins collected for change
	 * @throws NotSufficientChangeException if there are no more coins available for
	 *                                      change
	 */
	private List<Coin> collectCoinsForChange(List<Coin> change) throws NotSufficientChangeException {
		for (Coin coin : Coin.getSortedCoins()) {
			if (userBalance > coin.getVal())
				collectSpecificCoin(change, coin);
		}
		if (userBalance > 0)
			throw new NotSufficientChangeException(change);
		return change;
	}

	/**
	 * CoinHolder specific collector, collects maximum available same type coins
	 * 
	 * @param coins list of collected coins
	 * @param coin  specific Coin to collect
	 * @return updated list of coins for change
	 */
	private List<Coin> collectSpecificCoin(List<Coin> coins, Coin coin) {
		while (userBalance >= coin.getVal() && !banck.isStackForCoinEmpty(coin)) {
			coins.add(banck.retrieveThisCoinFromSpecificCoinHolder(coin));
			userBalance -= coin.getVal();
		}
		return coins;
	}

	@Override
	public void adminRefillMachine(String pass) {
		try {
			checkPassword(pass);
			adminRefill();
			cashOutToDefault();
		} catch (InvalidPasswordException e) {
			e.getMessage();
		}
	}

	/**
	 * checks if user has administrator rights
	 * 
	 * @param pass user inserted password
	 * @throws InvalidPasswordException if pass is not valid
	 */
	private void checkPassword(String pass) throws InvalidPasswordException {
		if (pass != "admin")
			throw new InvalidPasswordException("invalid password");
	}

	/**
	 * Refills the storage of machine with missing Items in ItemHolders
	 */
	private void adminRefill() {
		for (Integer i : storage.getProducts()
				.keySet()) {
			ItemHolder itemHolder = storage.getProducts()
					.get(i);
			int cnt = 0;
			while (itemHolder.getStackOfItems()
					.size() != itemHolder.getQuantity()) {
				itemHolder.getStackOfItems()
						.push(itemHolder.getItemType());
				cnt++;
			}
			System.out.println("********refilled with " + cnt + " " + itemHolder.getItemType());

		}
	}

	/**
	 * cashes IN coins missing in coinHolders for change or respectively cashes OUT
	 * the Bank balance after assuring that coinHolders are full
	 */
	private void cashOutToDefault() {
		for (Coin coin : banck.getCoins()
				.keySet()) {
			CoinHolder coinHolder = banck.getCoins()
					.get(coin);
			if (coinHolder.getStackOfCoins()
					.size() < coinHolder.getMaxCapacity()) {
				int coinsToRefill = coinHolder.getMaxCapacity() - coinHolder.getStackOfCoins()
						.size();
				for (int i = 0; i < coinsToRefill; i++) {
					coinHolder.getStackOfCoins()
							.push(coin);
					banck.setCashOverStackedCoins(banck.getCashOverStackedCoins() - coin.getVal());
				}
			}
		}

		if (banck.getCashOverStackedCoins() >= 0) {
			System.out.println("******** cashed OUT " + banck.getCashOverStackedCoins() / 100d + "$");
		} else {
			System.out.println("******** cashed IN " + (banck.getCashOverStackedCoins() / 100d * -1) + " $");
		}
		banck.setCashOverStackedCoins(0);

	}

	@Override
	public String toString() {
		return storage.toString() + "\n" + banck.toString() + "\n" + "balance is: " + userBalance + "\n"
				+ "balance of banck is: " + banck.getCashOverStackedCoins();
	}
}
