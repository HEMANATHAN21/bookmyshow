package com.spring.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.dto.UserDto;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class UserService 
{
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user)
	{
		User userNew = userDao.saveUser(user);
		if(userNew != null)
		{
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(userNew, dto);
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setMessage("User Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId)
	{
		User user = userDao.findUser(userId);
		if(user != null)
		{
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(user, dto);
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setMessage("User Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userId)
	{
		User user = userDao.findUser(userId);
		if(user != null)
		{
			User deletedUser = userDao.findUser(userId);
			if(deletedUser != null)
			{
				UserDto dto = new UserDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(deletedUser, dto);
				ResponseStructure<UserDto> structure = new ResponseStructure<>();
				structure.setMessage("User Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User user, int userId)
	{
		User userfind = userDao.findUser(userId);
		if(userfind != null)
		{
			User updateUser = userDao.updateUser(user, userId);
			if(updateUser != null)
			{
				UserDto dto = new UserDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(updateUser, dto);
				ResponseStructure<UserDto> structure = new ResponseStructure<>();
				structure.setMessage("User Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
}
