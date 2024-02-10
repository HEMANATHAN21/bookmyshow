package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class UserService 
{
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user)
	{
		User userNew = userDao.saveUser(user);
		if(userNew != null)
		{
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(userNew);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> findUser(int userId)
	{
		User user = userDao.findUser(userId);
		if(user != null)
		{
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(user);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId)
	{
		User user = userDao.findUser(userId);
		if(user != null)
		{
			User deletedUser = userDao.findUser(userId);
			if(deletedUser != null)
			{
				ResponseStructure<User> structure = new ResponseStructure<>();
				structure.setMessage("User Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedUser);
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int userId)
	{
		User userfind = userDao.findUser(userId);
		if(userfind != null)
		{
			User updateUser = userDao.updateUser(user, userId);
			if(updateUser != null)
			{
				ResponseStructure<User> structure = new ResponseStructure<>();
				structure.setMessage("User Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateUser);
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
}
