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
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.service.ScreenService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("screen")
public class ScreenController 
{
	@Autowired
	ScreenService screenService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(@RequestBody Screen screen)
	{
		return screenService.saveScreen(screen);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Screen>> findScreen(@RequestParam int screenId)
	{
		return screenService.findScreen(screenId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(@RequestParam int screenId)
	{
		return screenService.deleteScreen(screenId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(@RequestBody Screen screen,@RequestParam int screenId)
	{
		return screenService.updateScreen(screen,screenId);
	}
	
	@PutMapping("updateseatclass")
	public ResponseEntity<ResponseStructure<Screen>> updateSeatClass(@RequestParam int screenId)
	{
		return screenService.updateSeatClass(screenId);
	}
}
