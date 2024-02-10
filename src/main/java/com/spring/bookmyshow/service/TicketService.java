package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.TicketDao;
import com.spring.bookmyshow.entity.Ticket;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	
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
		return null;
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
		return null;
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
			return null;
		}
		return null;
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
			return null;
		}
		return null;
	}
}
