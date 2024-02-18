package com.spring.bookmyshow.exception;

public class UserNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public UserNotFound(String message) {
		super();
		this.message = message;
	}

	
	
}
