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
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class ScreenShow 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private LocalDate showDate;
	private LocalTime showTime;
	private int totalSeatingCount;
	private int[] totalSeat;
	private Status showStatus;
	private MovieSchedule screenMovieShedule;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Movie movie;
	private int theatreId;
	private int screenId;
}
