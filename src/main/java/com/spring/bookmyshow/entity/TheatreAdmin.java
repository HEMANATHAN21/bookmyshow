package com.spring.bookmyshow.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	
	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name cannot be blank")
	private String theatreAdminName;
	
	@NotNull(message = "email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email(message = "Invalid email format")
    @Pattern(regexp=".+@.+\\..+", message="Email address must contain @ symbol")
	private String theatreAdminEmail;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message="Password must be 8 digit given all validations")
	private String theatreAdminPassword;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre adminTheatre;
}
