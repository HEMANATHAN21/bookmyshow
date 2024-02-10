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

import com.spring.bookmyshow.dto.AdminBookMyShowDto;
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
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> saveAdminBookMyShow(@RequestBody AdminBookMyShow adminBookMyShow)
	{
		return adminBmsService.saveAdminBookMyShow(adminBookMyShow);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> findAdminBookMyShow(@RequestParam int adminBmsId)
	{
		return adminBmsService.findAdminBookMyShow(adminBmsId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> deleteAdminBookMyShow(@RequestParam int adminBmsId)
	{
		return adminBmsService.deleteAdminBookMyShow(adminBmsId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> updateAdminBookMyShow(@RequestBody AdminBookMyShow adminBookMyShow,@RequestParam int adminBmsId)
	{
		return adminBmsService.updateAdminBookMyShow(adminBookMyShow,adminBmsId);
	}

}
