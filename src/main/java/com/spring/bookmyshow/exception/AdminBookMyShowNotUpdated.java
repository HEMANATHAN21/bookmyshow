package com.spring.bookmyshow.exception;

public class AdminBookMyShowNotUpdated extends RuntimeException
{
	String mesage;

	public String getMesage() {
		return mesage;
	}

	public AdminBookMyShowNotUpdated(String mesage) {
		super();
		this.mesage = mesage;
	}
	
}
