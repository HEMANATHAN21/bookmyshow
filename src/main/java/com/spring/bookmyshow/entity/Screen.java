package com.spring.bookmyshow.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Screen 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private LocalDate showDate;
	private LocalTime showTime;
	private MovieSchedule screenMovieShedule;
	private int totalSeatingCount;
	private int[] seatAclass;
	private int[] seatBclass;
	private int[] seatCclass;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Movie movieName;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Theatre theatre;
	private int screenTheatreId;
	private Status showStatus;
}
