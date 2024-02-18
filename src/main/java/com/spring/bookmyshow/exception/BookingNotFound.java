package com.spring.bookmyshow.exception;

public class BookingNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}
	public BookingNotFound(String message) {
		super();
		this.message = message;
	}
	
}
