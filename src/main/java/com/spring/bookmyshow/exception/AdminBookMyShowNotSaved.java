package com.spring.bookmyshow.exception;

public class AdminBookMyShowNotSaved extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public AdminBookMyShowNotSaved(String message) {
		super();
		this.message = message;
	}

	
}
