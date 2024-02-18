package com.spring.bookmyshow.exception;

public class ScreenShowNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public ScreenShowNotFound(String message) {
		super();
		this.message = message;
	}
	
}
