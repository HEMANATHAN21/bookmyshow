package com.spring.bookmyshow.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.spring.bookmyshow.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<Object>> handleConstrainViolationException(ConstraintViolationException ex)
	{
		ResponseStructure<Object> structure = new ResponseStructure<>();
		Map<String, String> hashmap = new HashMap<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations())
		{
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			hashmap.put(field, message);
		}
		structure.setMessage("add proper data");
		structure.setData(hashmap);
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminBookMyShowNotFoundException(AdminBookMyShowNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("AdminBookMyShow Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingNotFoundException(BookingNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Booking Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> movieNotFoundException(MovieNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Movie Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> reviewNotFoundException(ReviewNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Review Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> screenNotFoundException(ScreenNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Screen Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> screenShowNotFoundException(ScreenShowNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("ScreenShow Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> seatNotFoundException(SeatNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Seat Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreNotFoundException(TheatreNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Theatre Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreAdminNotFoundException(TheatreAdminNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("TheatreAdmin Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketNotFoundException(TicketNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Ticket Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotFound ex)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("User Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
