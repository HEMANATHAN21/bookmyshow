package com.spring.bookmyshow.dto;

import java.util.List;

import com.spring.bookmyshow.entity.Booking;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDto 
{
	private int userId;
	private String userName;
	private String userEmail;
	private List<Booking> bookingHistory;
}
