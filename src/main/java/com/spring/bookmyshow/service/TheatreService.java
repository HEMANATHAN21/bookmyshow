package com.spring.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.TheatreAdminNotFound;
import com.spring.bookmyshow.exception.TheatreNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TheatreService 
{
	@Autowired
	TheatreDao theatreDao;
	
	@Autowired
	TheatreAdminDao theatreAdminDao;
	
	@Autowired
	ScreenDao screenDao;
	
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre)
	{
		Theatre theatreNew = theatreDao.saveTheatre(theatre);
		if(theatreNew != null)
		{
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setMessage("Theatre Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(theatreNew);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
		}
		throw new NotSaved("Theatre Not Saved");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId)
	{
		Theatre theatre = theatreDao.findTheatre(theatreId);
		if(theatre != null)
		{
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setMessage("Theatre Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre Not Found In Given Theatre Id : "+theatreId);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId)
	{
		Theatre theatre = theatreDao.findTheatre(theatreId);
		if(theatre != null)
		{
			Theatre deletedTheatre = theatreDao.findTheatre(theatreId);
			if(deletedTheatre != null)
			{
				ResponseStructure<Theatre> structure = new ResponseStructure<>();
				structure.setMessage("Theatre Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
			}
			throw new NotDeleted("Theatre Not Deleted For Given Id : "+theatreId);
		}
		throw new TheatreNotFound("Theatre Not Found In Given Theatre Id : "+theatreId);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre, int theatreId)
	{
		Theatre theatrefind = theatreDao.findTheatre(theatreId);
		if(theatrefind != null)
		{
			Theatre updateTheatre = theatreDao.updateTheatre(theatre,theatreId);
			if(updateTheatre != null)
			{
				ResponseStructure<Theatre> structure = new ResponseStructure<>();
				structure.setMessage("Theatre Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
			}
			throw new NotUpdated("Theatre Not Updated For Given Theatre Id : "+theatreId);
		}
		throw new TheatreNotFound("Theatre Not Found In Given Theatre Id : "+theatreId);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> assignTheatreAdminToTheatre(String theatreAdminEmail,String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			int theatreId = exTheatreAdmin.getAdminTheatre().getTheatreId();
			Theatre exTheatre = theatreDao.findTheatre(theatreId);
			if(exTheatre != null)
			{
				exTheatre.setTheatreAdmin(exTheatreAdmin);
				Theatre updatedTheatre = theatreDao.updateTheatre(exTheatre, theatreId);
				if(updatedTheatre != null)
				{
					ResponseStructure<Theatre> structure = new ResponseStructure<>();
					structure.setMessage("TheatreAdmin Successfully Assigned To Theatre");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(updatedTheatre);
					return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
				}
				throw new NotUpdated("Theatre Not Updated For Given Theatre Id : "+theatreId);
			}
			throw new TheatreNotFound("Theatre Not Found In Given Theatre Id : "+theatreId);
		}
		throw new TheatreAdminNotFound("Theatre Admin Not Found Check Your Login Credentials..");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> addScreenInTheatre(int screenId,String theatreAdminEmail,String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			int theatreId = exTheatreAdmin.getAdminTheatre().getTheatreId();
			Theatre exTheatre = theatreDao.findTheatre(theatreId);
			if(exTheatre != null)
			{
				List<Screen> theatreScreenList = exTheatre.getTheatreScreenList();
				Screen screen = screenDao.findScreen(screenId);
				theatreScreenList.add(screen);
				exTheatre.setTheatreScreenList(theatreScreenList);
				Theatre updatedTheatre = theatreDao.updateTheatre(exTheatre, theatreId);
				if(updatedTheatre != null)
				{
					ResponseStructure<Theatre> structure = new ResponseStructure<>();
					structure.setMessage("Successfuly Screen Added In Theatre");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(updatedTheatre);
					return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
				}
				throw new NotUpdated("Theatre Not Updated For Given Theatre Id : "+theatreId);
			}
			throw new TheatreNotFound("Theatre Not Found In Given Theatre Id : "+theatreId);
		}
		throw new TheatreAdminNotFound("Theatre Admin Not Found Check Your Login Credentials..");
	}
}
