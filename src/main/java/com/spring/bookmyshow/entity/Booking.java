package com.spring.bookmyshow.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Setter    
@Getter
@Entity
@Component
public class Booking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate showDate;
	private LocalTime showTime;
	private int ticketCount;
	private int[] bookingSeatIndexes;
	private double totalAmount;
	private SeatType seatType;
	private MovieSchedule bookingMovieSchedule;
	private String bookingMovieName;
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket ticket;
}
