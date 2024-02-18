package com.spring.bookmyshow.exception;

public class AdminBookMyShowNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public AdminBookMyShowNotFound(String message) {
		super();
		this.message = message;
	}

	
	
}
