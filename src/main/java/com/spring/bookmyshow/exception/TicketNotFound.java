package com.spring.bookmyshow.exception;

public class TicketNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public TicketNotFound(String message) {
		super();
		this.message = message;
	}
	
}
