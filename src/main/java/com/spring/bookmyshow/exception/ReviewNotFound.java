package com.spring.bookmyshow.exception;

public class ReviewNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public ReviewNotFound(String message) {
		super();
		this.message = message;
	}

	
	
}
