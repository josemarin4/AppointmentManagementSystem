package com.app.exception;

@SuppressWarnings("serial")
public class AppointmentNotFoundException extends RuntimeException{
	
	public AppointmentNotFoundException(String msg) {
		
		super(msg);
	}

}
