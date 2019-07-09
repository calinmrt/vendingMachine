package com.sda.vendingMachine.models;

public class NotSufficientChangeException extends Exception {
	private String message;

	public NotSufficientChangeException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
