package com.spring.bookmyshow.entity;

import java.util.List;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name cannot be blank")
	private String userName;
	
	@NotNull(message = "email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email(message = "Invalid email format")
    @Pattern(regexp=".+@.+\\..+", message="Email address must contain @ symbol")
	private String userEmail;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message="Password must be 8 digit")
	private String userPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> bookingHistory;
}
