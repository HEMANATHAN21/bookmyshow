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
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.service.UserService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("user")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int userId)
	{
		return userService.findUser(userId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int userId)
	{
		return userService.deleteUser(userId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@RequestParam int userId)
	{
		return userService.updateUser(user,userId);
	}
}
