package com.spring.bookmyshow.exception;

public class TheatreAdminNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public TheatreAdminNotFound(String message) {
		super();
		this.message = message;
	}
	
}
