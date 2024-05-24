package com.spring.bookmyshow.exception;

public class NotUpdated extends RuntimeException{
	
	String message;

	public String getMessage() {
		return message;
	}

	public NotUpdated(String message) {
		super();
		this.message = message;
	}
	

}
