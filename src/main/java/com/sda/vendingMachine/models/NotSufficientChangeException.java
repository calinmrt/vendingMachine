package com.sda.vendingMachine.models;

import java.util.ArrayList;
import java.util.List;

public class NotSufficientChangeException extends Exception {
	private final String message="Not sufficient change to refund all balance";
	List<Coin> change = new ArrayList<>();

	

	public NotSufficientChangeException(List<Coin> change) {
		super();
		this.change = change;
	}



	public String getMessage() {		
		return message;
	}

	

}
