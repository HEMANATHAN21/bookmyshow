package com.spring.bookmyshow.exception;

public class EmptyList extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public EmptyList(String message) {
		super();
		this.message = message;
	}
	
}
