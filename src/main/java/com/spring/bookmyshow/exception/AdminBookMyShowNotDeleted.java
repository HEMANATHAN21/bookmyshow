package com.spring.bookmyshow.exception;

public class AdminBookMyShowNotDeleted extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public AdminBookMyShowNotDeleted(String message) {
		super();
		this.message = message;
	}
	
}
