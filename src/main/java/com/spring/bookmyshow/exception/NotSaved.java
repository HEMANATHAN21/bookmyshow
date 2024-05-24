package com.spring.bookmyshow.exception;

public class NotSaved extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public NotSaved(String message) {
		super();
		this.message = message;
	}
	
}
