package com.spring.bookmyshow.exception;

public class WrongPassword extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public WrongPassword(String message) {
		super();
		this.message = message;
	}
	
}
