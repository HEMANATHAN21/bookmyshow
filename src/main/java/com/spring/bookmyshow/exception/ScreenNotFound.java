package com.spring.bookmyshow.exception;

public class ScreenNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public ScreenNotFound(String message) {
		super();
		this.message = message;
	}

	
	
}
