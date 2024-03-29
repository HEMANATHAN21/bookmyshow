package com.spring.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class Ticket 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private int ticketCount;
	private MovieSchedule bookingMovieSchedule;
	private int theatreId;
	private int screenId;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seat> seats;
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Booking booking;
}
