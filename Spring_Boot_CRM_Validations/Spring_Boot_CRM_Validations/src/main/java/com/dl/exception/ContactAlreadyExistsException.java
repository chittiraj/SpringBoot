package com.dl.exception;

public class ContactAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ContactAlreadyExistsException(String message) {
		super(message);
	}
	

}
