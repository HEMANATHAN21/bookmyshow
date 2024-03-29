package com.spring.bookmyshow.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.dto.UserDto;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.exception.EmailNotFound;
import com.spring.bookmyshow.exception.EmptyList;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.UserNotFound;
import com.spring.bookmyshow.exception.WrongPassword;
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
		throw new NotSaved("User Not Saved");
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
		throw new UserNotFound("User Not Found In Given User Id : "+userId);
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
			throw new NotDeleted("User Not Deleted For Given UserId : "+userId);
		}
		throw new UserNotFound("User Not Found In Given User Id : "+userId);
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
			throw new NotUpdated("User Not Updated For Given User Id :"+userId);
		}
		throw new UserNotFound("User Not Found In Given User Id : "+userId);
	}
	
	public User userLogin(String userEmail,String userPassword)
	{
		List<User> userList = userDao.findAllUser();
		if(!userList.isEmpty())
		{
			for (User user : userList) 
			{
				if(user.getUserEmail().equals(userEmail))
				{
					if(user.getUserPassword().equals(userPassword))
					{
						return user;
					}
					throw new WrongPassword("User Entered Wrong Password"); 
				}
				throw new EmailNotFound("User Entered Email Not Found In Database Wrong Email");
			}
		}
		throw new EmptyList("User List Empty In Database");
	}
	
	public User findByEmail(String userEmail, String userPassword)
	{
		return userDao.findByEmail(userEmail, userPassword);
	}
}
