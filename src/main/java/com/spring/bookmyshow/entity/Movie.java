package com.spring.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class Movie 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Review> moviewReviews;
	private int rating;
}
