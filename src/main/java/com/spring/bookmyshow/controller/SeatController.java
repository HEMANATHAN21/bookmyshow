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

import com.spring.bookmyshow.entity.Seat;
import com.spring.bookmyshow.service.SeatService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("seat")
public class SeatController 
{
	@Autowired
	SeatService seatService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(@RequestBody Seat seat)
	{
		return seatService.saveSeat(seat);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Seat>> findSeat(@RequestParam int seatId)
	{
		return seatService.findSeat(seatId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(@RequestParam int seatId)
	{
		return seatService.deleteseat(seatId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Seat>> updateSeat(@RequestBody Seat seat, @RequestParam int seatId)
	{
		return seatService.updateSeat(seat, seatId);
	}
}
