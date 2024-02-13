package com.spring.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.BookingDao;
import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.SeatDao;
import com.spring.bookmyshow.dao.TicketDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.entity.SeatType;
import com.spring.bookmyshow.entity.Ticket;
import com.spring.bookmyshow.entity.User;
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
	
	public ResponseEntity<ResponseStructure<Booking>> bookingMovie(Booking booking, int userId, int movieId) 
	{
        User exUser = userDao.findUser(userId);
        if (exUser != null) {
            Movie exMovie = movieDao.findMovie(movieId);
            if (exMovie != null) {
                booking.setBookingMovieName(exMovie.getMovieName());
                Ticket bookingTicket = new Ticket();
                bookingTicket.setTicketCount(booking.getTicketCount());
                List<Screen> listOfScreens = screenDao.findAllScreen();
                Screen exScreen = null;
                for (Screen screen : listOfScreens) 
                {
                    if (screen.getMovieName().getMovieId() == movieId) 
                    {
                        exScreen = screen;
                        break;
                    }
                }
                if (exScreen != null) 
                {
                    booking.setShowDate(exScreen.getShowDate());
                    booking.setShowTime(exScreen.getShowTime());
                    double totalAmount = 0;
                    List<Seat> listOfSeats = new ArrayList<>();
                    if (booking.getSeatType().equals(SeatType.FirstClass)) 
                    {
                        int[] firstClass = exScreen.getSeatAclass();
                        for (int i=0; i<booking.getTicketCount(); i++) 
                        {
                            int availableSeatIndex = -1;
                            for (int j=0; j<firstClass.length; j++) 
                            {
                                if (firstClass[j] == 0) {
                                    availableSeatIndex = j;
                                    break;
                                }
                            }
                            if (availableSeatIndex != -1) 
                            {
                                firstClass[availableSeatIndex] = 1;
                                totalAmount += 500;
                                Seat s = new Seat();
                                s.setSeatNumber("A00" + availableSeatIndex);
                                s.setSeatType(SeatType.FirstClass);
                                seatDao.saveSeat(s);
                                listOfSeats.add(s);
                            } 
                            else 
                            {
                                return null;//seat not available
                            }
                        }
                        exScreen.setSeatAclass(firstClass);
                        screenDao.updateScreen(exScreen, exScreen.getScreenId());
                    }
                    bookingTicket.setSeats(listOfSeats);
                    bookingTicket.setBooking(booking);
                    booking.setTicket(bookingTicket);
                    booking.setTotalAmount(totalAmount);
                    Ticket savedTicket = ticketDao.saveTicket(bookingTicket);
                    if (savedTicket != null) 
                    {
                        List<Booking> bookingHistory = exUser.getBookingHistory();
                        bookingHistory.add(booking);
                        exUser.setBookingHistory(bookingHistory);
                        userDao.updateUser(exUser, userId);
                        ResponseStructure<Booking> structure = new ResponseStructure<>();
                        structure.setMessage("Ticket Booked Successfully /n Your Ticket Id Is" + savedTicket.getTicketId());
                        structure.setStatus(HttpStatus.CREATED.value());
                        structure.setData(booking);
                        return new ResponseEntity<>(structure, HttpStatus.CREATED);
                    }
                    return null; // ticket not saved
                }
                return null; // movie not in screen list
            }
            return null; // movie not found
        }
        return null; // user not found
    }
}
