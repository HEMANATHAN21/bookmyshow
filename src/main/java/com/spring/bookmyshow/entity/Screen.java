package com.spring.bookmyshow.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class Screen 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private LocalDate showDate;
	private LocalTime showTime;
	private int totalSeatingCount;
	private int[] seatAclass;
	private int[] seatBclass;
	private int[] seatCclass;
	@OneToOne(cascade = CascadeType.ALL)
	private Movie movieName;
	private Status showStatus;
}
