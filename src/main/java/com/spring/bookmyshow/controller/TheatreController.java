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
import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.service.TheatreService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("theatre")
public class TheatreController 
{
	@Autowired
	TheatreService theatreService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre)
	{
		return theatreService.saveTheatre(theatre);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int theatreId)
	{
		return theatreService.findTheatre(theatreId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int theatreId)
	{
		return theatreService.deleteTheatre(theatreId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestBody Theatre theatre,@RequestParam int theatreId)
	{
		return theatreService.updateTheatre(theatre,theatreId);
	}
	
	@PutMapping("assigntheatre")
	public ResponseEntity<ResponseStructure<Theatre>> assignTheatreAdminToTheatre(@RequestParam int theatreId,@RequestParam int theatreAdminId)
	{
		return theatreService.assignTheatreAdminToTheatre(theatreId,theatreAdminId);
	}
}
