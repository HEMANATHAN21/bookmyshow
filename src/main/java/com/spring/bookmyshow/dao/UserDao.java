package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.repo.UserRepo;

@Repository
public class UserDao 
{
	@Autowired
	UserRepo userRepo;
	
	public User saveUser(User user)
	{
		return userRepo.save(user);
	}
	
	public User findUser(int userId)
	{
		Optional<User> opUser = userRepo.findById(userId);
		if(opUser.isPresent())
			return opUser.get();
		return null;
	}
	
	public User deleteUser(int userId)
	{
		User user = findUser(userId);
		userRepo.delete(user);
		return user;
	}
	
	public User updateSUser(User user, int userId)
	{
		User exUser = findUser(userId);
		if(exUser != null)
		{
			exUser.setUserId(userId);
			return userRepo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser()
	{
		return userRepo.findAll();
	}
}
