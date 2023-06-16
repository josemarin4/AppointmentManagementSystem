package com.app.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException{
	
	public EmailAlreadyExistsException(String msg) {
		
		super(msg);
	}

}
