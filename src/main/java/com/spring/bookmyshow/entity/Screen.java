package com.spring.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Screen 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private int totalSeatingCount;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Theatre theatre;
	private int theatreId;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ScreenShow> showList;
	
}
