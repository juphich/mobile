package com.wise.customer;

public class CustomerValidationException extends RuntimeException {

	private static final long serialVersionUID = -4839701820822638626L;

	public CustomerValidationException(String message, Throwable t) {
		super(message, t);
	}

	public CustomerValidationException(String message) {
		super(message);
	}
}
