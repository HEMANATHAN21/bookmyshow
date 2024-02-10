package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.repo.BookingRepo;

@Repository
public class BookingDao 
{
	@Autowired
	BookingRepo bookinRepo;
	
	public Booking saveBooking(Booking booking)
	{
		return bookinRepo.save(booking);
	}
	
	public Booking findBooking(int bookingId)
	{
		Optional<Booking> opBooking = bookinRepo.findById(bookingId);
		if(opBooking.isPresent())
			return opBooking.get();
		return null;
	}
	
	public Booking deleteBooking(int bookingId)
	{
		Booking booking = findBooking(bookingId);
		bookinRepo.delete(booking);
		return booking;
	}
	
	public Booking updateBooking(Booking booking, int bookingId)
	{
		Booking exBooking = findBooking(bookingId);
		if(exBooking != null)
		{
			booking.setBookingId(bookingId);
			return bookinRepo.save(booking);
		}
		return null;
	}
	
	public List<Booking> findAllBooking()
	{
		return bookinRepo.findAll();
	}
	
}
