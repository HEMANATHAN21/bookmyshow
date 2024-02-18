package com.spring.bookmyshow.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.exception.ScreenNotFound;
import com.spring.bookmyshow.exception.TheatreAdminNotFound;
import com.spring.bookmyshow.exception.TheatreNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class ScreenService 
{
	@Autowired
	ScreenDao screenDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	TheatreDao theatreDao;
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(Screen screen,String theatreAdminEmail,String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Theatre adminTheatre = theatreDao.findTheatre(exTheatreAdmin.getAdminTheatre().getTheatreId());
			if(adminTheatre != null)
			{
				screen.setTheatre(adminTheatre);
				screen.setTheatreId(adminTheatre.getTheatreId());
				Screen saveScreen = screenDao.saveScreen(screen);
				if(saveScreen != null)
				{
					ResponseStructure<Screen> structure = new ResponseStructure<>();
					structure.setMessage("Screen Saved");
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(saveScreen);
					return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.CREATED);
				}
				return null;//not saved
			}
			throw new TheatreNotFound("Theatre Not Found in Given Theatre Id : "+exTheatreAdmin.getAdminTheatre().getTheatreId());
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found Check Your Login Credentials....");
		
	}
	
	public ResponseEntity<ResponseStructure<Screen>> findScreen(int screenId)
	{
		Screen screen = screenDao.findScreen(screenId);
		if(screen != null)
		{
			ResponseStructure<Screen> structure = new ResponseStructure<>();
			structure.setMessage("Screen Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(screen);
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.FOUND);
		}
		throw new ScreenNotFound("Screen Not Found In Given ScreenId : "+screenId);
	}
	
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(int screenId)
	{
		Screen screen = screenDao.findScreen(screenId);
		if(screen != null)
		{
			Screen deletedScreen = screenDao.findScreen(screenId);
			if(deletedScreen != null)
			{
				ResponseStructure<Screen> structure = new ResponseStructure<>();
				structure.setMessage("Screen Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedScreen);
				return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
			}
			return null;//not deleted
		}
		throw new ScreenNotFound("Screen Not Found In Given ScreenId : "+screenId);
	}
	
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(Screen screen, int screenId)
	{
		Screen screenfind = screenDao.findScreen(screenId);
		if(screenfind != null)
		{
			Screen updateScreen = screenDao.updateScreen(screen,screenId);
			if(updateScreen != null)
			{
				ResponseStructure<Screen> structure = new ResponseStructure<>();
				structure.setMessage("Screen Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateScreen);
				return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
			}
			return null;//not updated
		}
		throw new ScreenNotFound("Screen Not Found In Given ScreenId : "+screenId);
	}
	
	
	
	
	
	
}
