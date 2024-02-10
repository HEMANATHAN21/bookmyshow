package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.BookingDao;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.util.ResponseStructure;
@Service
public class BookingService 
{
	@Autowired
	BookingDao bookingDao;
	
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking)
	{
		Booking bookingNew = bookingDao.saveBooking(booking);
		if(bookingNew != null)
		{
			ResponseStructure<Booking> structure = new ResponseStructure<>();
			structure.setMessage("Booking Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(bookingNew);
			return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int bookingId)
	{
		Booking booking = bookingDao.findBooking(bookingId);
		if(booking != null)
		{
			ResponseStructure<Booking> structure = new ResponseStructure<>();
			structure.setMessage("Booking Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(booking);
			return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int bookingId)
	{
		Booking booking = bookingDao.findBooking(bookingId);
		if(booking != null)
		{
			Booking deletedBooking = bookingDao.findBooking(bookingId);
			if(deletedBooking != null)
			{
				ResponseStructure<Booking> structure = new ResponseStructure<>();
				structure.setMessage("Booking Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking, int bookingId)
	{
		Booking bookingfind = bookingDao.findBooking(bookingId);
		if(bookingfind != null)
		{
			Booking updateBooking = bookingDao.updateBooking(booking,bookingId);
			if(updateBooking != null)
			{
				ResponseStructure<Booking> structure = new ResponseStructure<>();
				structure.setMessage("Booking Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
}
