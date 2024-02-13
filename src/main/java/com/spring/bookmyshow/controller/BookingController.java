package com.spring.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.service.BookingService;
import com.spring.bookmyshow.util.ResponseStructure;
@RestController
@RequestMapping("booking")
public class BookingController 
{
	@Autowired
	BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking)
	{
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Booking>> findBooking(@RequestParam int bookingId)
	{
		return bookingService.findBooking(bookingId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(@RequestParam int bookingId)
	{
		return bookingService.deleteBooking(bookingId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking,@RequestParam int bookingId)
	{
		return bookingService.updateBooking(booking,bookingId);
	}
	@PostMapping("bookingmovie")
	public ResponseEntity<ResponseStructure<Booking>> bookingMovie(@RequestBody Booking booking,@RequestParam int userId,@RequestParam int movieId )
	{
		return bookingService.bookingMovie(booking, userId, movieId);
	}

}
