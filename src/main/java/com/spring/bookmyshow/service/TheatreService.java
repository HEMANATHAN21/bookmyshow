package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TheatreService 
{
	@Autowired
	TheatreDao theatreDao;
	
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
		return null;
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
		return null;
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
			return null;
		}
		return null;
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
			return null;
		}
		return null;
	}
}
