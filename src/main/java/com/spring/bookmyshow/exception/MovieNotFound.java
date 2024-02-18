package com.spring.bookmyshow.exception;

public class MovieNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public MovieNotFound(String message) {
		super();
		this.message = message;
	}

	
	
}
