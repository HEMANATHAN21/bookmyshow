package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Ticket;
import com.spring.bookmyshow.repo.TicketRepo;

@Repository
public class TicketDao 
{
	@Autowired
	TicketRepo ticketRepo;
	
	public Ticket saveTicket(Ticket ticket)
	{
		return ticketRepo.save(ticket);
	}
	
	public Ticket findTicket(int ticketId)
	{
		Optional<Ticket> opTicket = ticketRepo.findById(ticketId);
		if(opTicket.isPresent())
			return opTicket.get();
		return null;
	}
	
	public Ticket deleteTicket(int ticketId)
	{
		Ticket ticket = findTicket(ticketId);
		ticketRepo.delete(ticket);
		return ticket;
	}
	
	public Ticket updateTicket(Ticket ticket, int ticketId)
	{
		Ticket exTicket = findTicket(ticketId);
		if(exTicket != null)
		{
			ticket.setTicketId(ticketId);
			return ticketRepo.save(ticket);
		}
		return null;
	}
	
	public List<Ticket> findAllTicket()
	{
		return ticketRepo.findAll();
	}
}
