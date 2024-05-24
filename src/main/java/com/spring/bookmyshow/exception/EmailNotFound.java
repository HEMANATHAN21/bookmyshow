package com.spring.bookmyshow.exception;

public class EmailNotFound extends RuntimeException{
	String message;

	public String getMessage() {
		return message;
	}

	public EmailNotFound(String message) {
		super();
		this.message = message;
	}
	
}
