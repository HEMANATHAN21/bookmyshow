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
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.service.TheatreAdminService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("theatreadmin")
public class TheatreAdminController 
{
	@Autowired
	TheatreAdminService theatreAdminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<TheatreAdmin>> saveTheatreAdmin(@RequestBody TheatreAdmin theatreAdmin)
	{
		return theatreAdminService.saveTheatreAdmin(theatreAdmin);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<TheatreAdmin>> findTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.findTheatreAdmin(theatreAdminId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<TheatreAdmin>> deleteTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.deleteTheatreAdmin(theatreAdminId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<TheatreAdmin>> updateTheatreAdmin(@RequestBody TheatreAdmin theatreAdmin,@RequestParam int theatreAdminId)
	{
		return theatreAdminService.updateTheatreAdmin(theatreAdmin, theatreAdminId);
	}
}
