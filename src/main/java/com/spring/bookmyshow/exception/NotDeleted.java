package com.spring.bookmyshow.exception;

public class NotDeleted extends RuntimeException{
	String message;

	public String getMessage() {
		return message;
	}

	public NotDeleted(String message) {
		super();
		this.message = message;
	}
	

}
