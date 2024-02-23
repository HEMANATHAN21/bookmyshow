package com.spring.bookmyshow.exception;

public class BookingNotSaved extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public BookingNotSaved(String message) {
		super();
		this.message = message;
	}
	
}
