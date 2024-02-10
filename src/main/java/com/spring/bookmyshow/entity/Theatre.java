package com.spring.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Component
public class Theatre 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	private String theatreName;
	@ManyToOne(cascade = CascadeType.ALL)
	private TheatreAdmin theatreAdmin;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Screen> theatreScreenList;
}
