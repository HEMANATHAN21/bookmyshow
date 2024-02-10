package com.spring.bookmyshow.entity;

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
public class TheatreAdmin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminId;
	private String theatreAdminName;
	private String theatreAdminEmail;
	private String theatreAdminPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre adminTheatre;
}
