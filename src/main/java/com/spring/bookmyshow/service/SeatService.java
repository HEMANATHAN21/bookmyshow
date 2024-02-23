package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.SeatDao;
import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.SeatNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class SeatService 
{
	@Autowired
	SeatDao seatDao;
	
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(Seat seat)
	{
		Seat seatNew = seatDao.saveSeat(seat);
		if(seatNew != null)
		{
			ResponseStructure<Seat> structure = new ResponseStructure<>();
			structure.setMessage("Seat Saved");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(seatNew);
			return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.CREATED);
		}
		throw new NotSaved("Seat Not Saved");
	}
	
	public ResponseEntity<ResponseStructure<Seat>> findSeat(int seatId)
	{
		Seat seatfind = seatDao.findSeat(seatId);
		if(seatfind != null)
		{
			ResponseStructure<Seat> structure = new ResponseStructure<>();
			structure.setMessage("Seat Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(seatfind);
			return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.FOUND);
		}
		throw new SeatNotFound("Seat Not Found In Given Id : "+seatId);
	}
	
	public ResponseEntity<ResponseStructure<Seat>> deleteseat(int seatId)
	{
		Seat seat = seatDao.findSeat(seatId);
		if(seat != null)
		{
			Seat deletedSeat = seatDao.deleteSeat(seatId);
			if(deletedSeat != null)
			{
				ResponseStructure<Seat> structure = new ResponseStructure<>();
				structure.setMessage("Seat Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedSeat);
				return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
			}
			throw new NotDeleted("Seat Not Deleted");
		}
		throw new SeatNotFound("Seat Not Found In Given Id : "+seatId);
	}
	
	public ResponseEntity<ResponseStructure<Seat>> updateSeat(Seat seat, int seatId)
	{
		Seat exSeat = seatDao.findSeat(seatId);
		if(exSeat != null)
		{
			Seat updatedSeat = seatDao.updateSeat(seat, seatId);
			if(updatedSeat != null)
			{
				ResponseStructure<Seat> structure = new ResponseStructure<>();
				structure.setMessage("Seat Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updatedSeat);
				return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
				
			}
			throw new NotUpdated("Seat Not Updated");
		}
		throw new SeatNotFound("Seat Not Found In Given Id : "+seatId);
	}
}
