package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.repo.SeatRepo;

@Repository
public class SeatDao 
{
	@Autowired
	SeatRepo seatRepo;
	
	public Seat saveSeat(Seat seat)
	{
		return seatRepo.save(seat);
	}
	
	public Seat findSeat(int seatId)
	{
		Optional<Seat> opSeat = seatRepo.findById(seatId);
		if(opSeat.isPresent())
			return opSeat.get();
		return null;
	}
	
	public Seat deleteSeat(int seatId)
	{
		Seat seat = findSeat(seatId);
		seatRepo.delete(seat);
		return seat;
	}
	
	public Seat updateSeat(Seat seat, int seatId)
	{
		Seat exSeat = findSeat(seatId);
		if(exSeat != null)
		{
			seat.setSeatId(seatId);
			return seatRepo.save(seat);
		}
		return null;
	}
	
	public List<Seat> findAllSeat()
	{
		return seatRepo.findAll();
	}
}
