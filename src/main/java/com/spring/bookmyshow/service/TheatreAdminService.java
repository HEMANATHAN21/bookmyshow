package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TheatreAdminService 
{
	@Autowired
	TheatreAdminDao theatreAdminDao;
	
	public ResponseEntity<ResponseStructure<TheatreAdmin>> saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		TheatreAdmin theatreAdminNew = theatreAdminDao.saveTheatreAdmin(theatreAdmin);
		if(theatreAdminNew != null)
		{
			ResponseStructure<TheatreAdmin> structure = new ResponseStructure<>();
			structure.setMessage("TheatreAdmin Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(theatreAdminNew);
			return new ResponseEntity<ResponseStructure<TheatreAdmin>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdmin>> findTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin theatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin != null)
		{
			ResponseStructure<TheatreAdmin> structure = new ResponseStructure<>();
			structure.setMessage("TheatreAdmin Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatreAdmin);
			return new ResponseEntity<ResponseStructure<TheatreAdmin>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdmin>> deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin theatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin != null)
		{
			TheatreAdmin deletedTheatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
			if(deletedTheatreAdmin != null)
			{
				ResponseStructure<TheatreAdmin> structure = new ResponseStructure<>();
				structure.setMessage("TheatreAdmin Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedTheatreAdmin);
				return new ResponseEntity<ResponseStructure<TheatreAdmin>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdmin>> updateTheatreAdmin(TheatreAdmin theatreAdmin, int theatreAdminId)
	{
		TheatreAdmin theatreAdminfind = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdminfind != null)
		{
			TheatreAdmin updateTheatreAdmin = theatreAdminDao.updateTheatreAdmin(theatreAdminfind, theatreAdminId);
			if(updateTheatreAdmin != null)
			{
				ResponseStructure<TheatreAdmin> structure = new ResponseStructure<>();
				structure.setMessage("TheatreAdmin Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateTheatreAdmin);
				return new ResponseEntity<ResponseStructure<TheatreAdmin>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
}
