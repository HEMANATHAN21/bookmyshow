package com.spring.bookmyshow.controller;

import java.util.List;

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

import com.spring.bookmyshow.entity.ScreenShow;
import com.spring.bookmyshow.entity.SeatType;
import com.spring.bookmyshow.service.ScreenShowService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("screenshow")
public class ScreenShowController 
{
	@Autowired
	ScreenShowService screenShowService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenShow>> saveScreenShow(@RequestBody ScreenShow show,@RequestParam int screenId,@RequestParam int movieId,@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return screenShowService.saveScreenShow(show, screenId, movieId, theatreAdminEmail, theatreAdminPassword);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenShow>> findScreenShow(@RequestParam int showId)
	{
		return screenShowService.findScreenShow(showId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenShow>> deleteScreenShow(@RequestParam int showId)
	{
		return screenShowService.deleteScreenShow(showId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenShow>> updateSeat(@RequestBody ScreenShow show, @RequestParam int showId)
	{
		return screenShowService.updateScreenShow(show, showId);
	}
	
	@GetMapping("findshowbasedonmoviename")
	public ResponseEntity<ResponseStructure<List<ScreenShow>>> findShowScreenBasedOnMovieName(@RequestParam String userEmail,@RequestParam String userPassword,@RequestParam String movieName)
	{
		return screenShowService.findShowScreenBasedOnMovieName(userEmail, userPassword, movieName);
	}
	
	@GetMapping("checkseatavailability")
	public ResponseEntity<ResponseStructure<List<Integer>>> checkSeatAvailability(int theatreId,int screenId,int screenShowId,SeatType seatType)
	{
		return screenShowService.checkSeatAvailability(theatreId, screenId, screenShowId, seatType);
	}
}
