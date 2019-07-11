package com.sda.vendingMachine.models;

import java.util.Map;
import java.util.TreeMap;

public class Bank {
	private static Bank instance;
	private int cashOverStackedCoins;
	private Map<Coin, CoinHolder> coins = new TreeMap<>();

	private Bank() {
	}

	// singleton
	public static Bank getInstance() {
		if (instance == null) {
			instance = new Bank();
			for (int i = 0; i < Coin.values().length; i++) {
				instance.coins.put(Coin.values()[i], new CoinHolder());
			}
		}
		return instance;
	}

	public int getCashOverStackedCoins() {
		return cashOverStackedCoins;
	}

	public void setCashOverStackedCoins(int cashOverStackedCoins) {
		this.cashOverStackedCoins = cashOverStackedCoins;
	}

	public Map<Coin, CoinHolder> getCoins() {
		return coins;
	}

	public boolean isStackForCoinEmpty(Coin coin) {
		return getCoinHolderForCoin(coin).isStackEmpty();
	}

	private CoinHolder getCoinHolderForCoin(Coin coin) {
		return coins.get(coin);
	}
	
	public Coin retrieveThisCoinFromSpecificCoinHolder(Coin c) {
		return getCoinHolderForCoin(c).getThisCoin();
	}
	
	

	@Override
	public String toString() {
		return coins.toString();
	}
}
