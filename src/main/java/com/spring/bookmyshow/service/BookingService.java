package com.spring.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.BookingDao;
import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.ScreenShowDao;
import com.spring.bookmyshow.dao.SeatDao;
import com.spring.bookmyshow.dao.TicketDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.entity.ScreenShow;
import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.entity.SeatType;
import com.spring.bookmyshow.entity.Ticket;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.exception.BookingNotFound;
import com.spring.bookmyshow.exception.ScreenShowNotFound;
import com.spring.bookmyshow.exception.UserNotFound;
import com.spring.bookmyshow.util.ResponseStructure;
@Service
public class BookingService 
{
	@Autowired
	BookingDao bookingDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	ScreenDao screenDao;
	@Autowired
	TicketDao ticketDao;
	@Autowired
	SeatDao seatDao;
	@Autowired
	ScreenShowDao screenShowDao;
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
		return null;//not saved
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
		throw new BookingNotFound("Booking Not Found In Given BookinId : "+bookingId);
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
			return null;//not deleted
		}
		throw new BookingNotFound("Booking Not Found In Given BookinId : "+bookingId);
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
			return null;//not updated
		}
		throw new BookingNotFound("Booking Not Found In Given BookinId : "+bookingId);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> bookingMovie(Booking booking,String userEmail,String userPassword,int screenShowhowId)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			ScreenShow exScreenShow = screenShowDao.findScreenShow(screenShowhowId);
			if(exScreenShow != null)
			{
				Movie exMovie = exScreenShow.getMovie();
				
				booking.setShowDate(exScreenShow.getShowDate());
				booking.setShowTime(exScreenShow.getShowTime());
				booking.setShowId(exScreenShow.getSId());
				booking.setBookingMovieName(exMovie.getMovieName());
				booking.setBookingMovieSchedule(exScreenShow.getScreenMovieShedule());
				
				Ticket bookingTicket = new Ticket();
//				Screen exScreen = screenDao.findScreen(exScreenShow.getScreenId());
				int[] bookingSeatIndexes = booking.getBookingSeatIndexes();
				List<Seat> listOfSeats = new ArrayList<>();
				double totalAmount = 0;
				int[] totalSeat = exScreenShow.getTotalSeat();
				if(booking.getSeatType().equals(SeatType.FirstClass))
				{
					totalAmount = booking.getTicketCount() * 500;
				}
				else if(booking.getSeatType().equals(SeatType.SecondClass))
				{
					totalAmount = booking.getTicketCount() * 300;
				}
				else if(booking.getSeatType().equals(SeatType.ThirdClass))
				{
					totalAmount = booking.getTicketCount() * 150;
				}
				
				for(int i=0;i<bookingSeatIndexes.length;i++)
				{
					totalSeat[bookingSeatIndexes[i]] = 1;
					Seat s = new Seat();
					s.setSeatNumber("S0"+bookingSeatIndexes[i]);
					s.setSeatType(booking.getSeatType());
					seatDao.saveSeat(s);
					listOfSeats.add(s);
				}
				exScreenShow.setTotalSeat(totalSeat);
				screenShowDao.updateScreenShow(exScreenShow, exScreenShow.getSId());
				
				bookingTicket.setSeats(listOfSeats);
				bookingTicket.setBooking(booking);
				bookingTicket.setTicketCount(booking.getTicketCount());
				bookingTicket.setBookingMovieSchedule(exScreenShow.getScreenMovieShedule());
				bookingTicket.setTheatreId(exScreenShow.getTheatreId());
				bookingTicket.setScreenId(exScreenShow.getScreenId());
				booking.setTicket(bookingTicket);
				booking.setTotalAmount(totalAmount);
				Ticket savedTicked = ticketDao.saveTicket(bookingTicket);
				
				
				if(savedTicked != null)
				{
					List<Booking> bookingHistory = exUser.getBookingHistory();
					bookingHistory.add(booking);
					exUser.setBookingHistory(bookingHistory);
					userDao.updateUser(exUser, exUser.getUserId());
					ResponseStructure<Booking> structure = new ResponseStructure<>();
					structure.setMessage("Ticket Booked Successfully... Your Ticket Id Is : "+savedTicked.getTicketId());
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(booking);
					return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.CREATED);
				}
				return null;//not saved
			}
			throw new ScreenShowNotFound("ScreenShow not found in Given Id : "+screenShowhowId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
	}
	
	
}
