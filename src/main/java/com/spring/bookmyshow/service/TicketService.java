package com.spring.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.BookingDao;
import com.spring.bookmyshow.dao.ScreenShowDao;
import com.spring.bookmyshow.dao.SeatDao;
import com.spring.bookmyshow.dao.TicketDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Booking;
import com.spring.bookmyshow.entity.ScreenShow;
import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.entity.Ticket;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.TicketNotFound;
import com.spring.bookmyshow.exception.UserNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BookingDao bookingDao;
	@Autowired
	SeatDao seatDao;
	@Autowired
	ScreenShowDao screenShowDao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket)
	{
		Ticket ticketNew = ticketDao.saveTicket(ticket);
		if(ticketNew != null)
		{
			ResponseStructure<Ticket> structure = new ResponseStructure<>();
			structure.setMessage("Ticket Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(ticketNew);
			return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.CREATED);
		}
		throw new NotSaved("Ticket Not Saved");
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int ticketId)
	{
		Ticket ticket = ticketDao.findTicket(ticketId);
		if(ticket != null)
		{
			ResponseStructure<Ticket> structure = new ResponseStructure<>();
			structure.setMessage("Ticket Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticket);
			return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket Not Found in Given TicketId : "+ticketId);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int ticketId)
	{
		Ticket ticket = ticketDao.findTicket(ticketId);
		if(ticket != null)
		{
			Ticket deletedTicket = ticketDao.findTicket(ticketId);
			if(deletedTicket != null)
			{
				ResponseStructure<Ticket> structure = new ResponseStructure<>();
				structure.setMessage("Ticket Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedTicket);
				return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
			}
			throw new NotDeleted("Ticket Not Deleted For Given Id : "+ticketId);
		}
		throw new TicketNotFound("Ticket Not Found in Given TicketId : "+ticketId);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int ticketId)
	{
		Ticket ticketfind = ticketDao.findTicket(ticketId);
		if(ticketfind != null)
		{
			Ticket updateTicket = ticketDao.updateTicket(ticket,ticketId);
			if(updateTicket != null)
			{
				ResponseStructure<Ticket> structure = new ResponseStructure<>();
				structure.setMessage("Ticket Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateTicket);
				return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
			}
			throw new NotUpdated("Ticket Not Updated For Given Id : "+ticketId);
		}
		throw new TicketNotFound("Ticket Not Found in Given TicketId : "+ticketId);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(String userEmail,String userPassword,int ticketId)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			Ticket exTicket = ticketDao.findTicket(ticketId);
			if(exTicket != null)
			{
				Booking exBooking = bookingDao.findBooking(exTicket.getBooking().getBookingId());
				List<Seat> listOfSeats = exTicket.getSeats();
				List<Seat> storedSeatList = new ArrayList<>();
				for (Seat seat : listOfSeats) 
				{
					storedSeatList.add(seat);
				}
				int showId = exBooking.getShowId();
				int[] bookingSeatIndexes = exBooking.getBookingSeatIndexes();
				int ticketCount = exBooking.getTicketCount();
				ScreenShow exScreenShow = screenShowDao.findScreenShow(showId);
				int[] totalSeat = exScreenShow.getTotalSeat();
				for(int i=0;i<ticketCount;i++)
				{
					totalSeat[bookingSeatIndexes[i]]=0;
				}
				exScreenShow.setTotalSeat(totalSeat);
				screenShowDao.updateScreenShow(exScreenShow, exScreenShow.getSId());
				
				exTicket.setSeats(null);
				exTicket.setBooking(null);
				ticketDao.updateTicket(exTicket, exTicket.getTicketId());
				for(Seat s : storedSeatList)
				{
//					System.out.println(s.getSeatId());
					seatDao.deleteSeat(s.getSeatId());
				}
				
				List<Booking> bookingHistory = exUser.getBookingHistory();
				List<Booking> newBookingHistory = new ArrayList<>();
				for(Booking b : bookingHistory)
				{
					if(b.getBookingId() != exBooking.getBookingId())
					{
						newBookingHistory.add(b);
					}
					else if(b.getBookingId() == exBooking.getBookingId())
					{
						b.setTicket(null);
						bookingDao.updateBooking(b, b.getBookingId());
					}
				}
				exUser.setBookingHistory(newBookingHistory);
				userDao.updateUser(exUser, exUser.getUserId());
				exBooking.setTicket(null);
				bookingDao.deleteBooking(exBooking.getBookingId());
				Ticket deletedTicket = ticketDao.deleteTicket(ticketId);
				if(deletedTicket != null)
				{
					ResponseStructure<Ticket> structure = new ResponseStructure<>();
					structure.setMessage("Ticket Deleted... Given Ticket Id "+deletedTicket.getTicketId());
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(deletedTicket);
					return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.OK);
				}
				throw new NotDeleted("Ticket Not Deleted For Given Id :"+ticketId);
			}
			throw new TicketNotFound("Ticket Not Found in Given TicketId : "+ticketId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
	}
}
