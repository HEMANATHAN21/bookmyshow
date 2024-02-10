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
import com.spring.bookmyshow.entity.AdminBookMyShow;
import com.spring.bookmyshow.service.AdminBookMyShowService;
import com.spring.bookmyshow.util.ResponseStructure;
@RestController
@RequestMapping("adminbms")
public class AdminBookMyShowController 
{
	@Autowired
	AdminBookMyShowService adminBmsService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> saveAdminBookMyShow(@RequestBody AdminBookMyShow adminBookMyShow)
	{
		return adminBmsService.saveAdminBookMyShow(adminBookMyShow);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> findAdminBookMyShow(@RequestParam int adminBmsId)
	{
		return adminBmsService.findAdminBookMyShow(adminBmsId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> deleteAdminBookMyShow(@RequestParam int adminBmsId)
	{
		return adminBmsService.deleteAdminBookMyShow(adminBmsId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> updateAdminBookMyShow(@RequestBody AdminBookMyShow adminBookMyShow,@RequestParam int adminBmsId)
	{
		return adminBmsService.updateAdminBookMyShow(adminBookMyShow,adminBmsId);
	}

}
