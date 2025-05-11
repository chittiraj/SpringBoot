package com.dl.exception;

public class LeadNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LeadNotfoundException(String message) {
		super(message);
	}

}
