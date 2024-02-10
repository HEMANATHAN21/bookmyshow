package com.spring.bookmyshow.entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class Seat 
{
	@Id
	private int seatId;
	private String seatNumber;
	private SeatType seatType;
}
