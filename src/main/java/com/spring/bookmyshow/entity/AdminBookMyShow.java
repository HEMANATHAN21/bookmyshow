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
@Component
@Entity
public class AdminBookMyShow 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminBmsId;
	private String adminBmsName;
	private String adminBmsMail;
	private String adminBmsPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> BmsTheatres;
	
}
