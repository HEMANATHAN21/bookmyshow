package com.spring.bookmyshow.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.dto.TheatreAdminDto;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.exception.EmailNotFound;
import com.spring.bookmyshow.exception.EmptyList;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.TheatreAdminNotFound;
import com.spring.bookmyshow.exception.WrongPassword;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class TheatreAdminService 
{
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	TheatreDao theatreDao;
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		TheatreAdmin theatreAdminNew = theatreAdminDao.saveTheatreAdmin(theatreAdmin);
		if(theatreAdminNew != null)
		{
			TheatreAdminDto dto = new TheatreAdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(theatreAdminNew, dto);
			ResponseStructure<TheatreAdminDto> structure = new ResponseStructure<>();
			structure.setMessage("TheatreAdmin Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.CREATED);
		}
		throw new NotSaved("TheatreAdmin Not Saved");
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin theatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin != null)
		{
			TheatreAdminDto dto = new TheatreAdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(theatreAdmin, dto);
			ResponseStructure<TheatreAdminDto> structure = new ResponseStructure<>();
			structure.setMessage("TheatreAdmin Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found In Given theatreId : "+theatreAdminId);
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin theatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin != null)
		{
			TheatreAdmin deletedTheatreAdmin = theatreAdminDao.findTheatreAdmin(theatreAdminId);
			if(deletedTheatreAdmin != null)
			{
				TheatreAdminDto dto = new TheatreAdminDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(deletedTheatreAdmin, dto);
				ResponseStructure<TheatreAdminDto> structure = new ResponseStructure<>();
				structure.setMessage("TheatreAdmin Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			throw new NotDeleted("TheatreAdmin Not Deleted");
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found In Given theatreId : "+theatreAdminId);
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(TheatreAdmin theatreAdmin, int theatreAdminId)
	{
		TheatreAdmin theatreAdminfind = theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdminfind != null)
		{
			TheatreAdmin updateTheatreAdmin = theatreAdminDao.updateTheatreAdmin(theatreAdminfind, theatreAdminId);
			if(updateTheatreAdmin != null)
			{
				TheatreAdminDto dto = new TheatreAdminDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(updateTheatreAdmin, dto);
				ResponseStructure<TheatreAdminDto> structure = new ResponseStructure<>();
				structure.setMessage("TheatreAdmin Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			throw new NotUpdated("TheatreAdmin Not Updated For Given Id : "+theatreAdminId);
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found In Given theatreId : "+theatreAdminId);
	}
	
	public TheatreAdmin theatreAdminLogin(String theatreAdminEmail, String theatreAdminPassword)
	{
		List<TheatreAdmin> theatreAdminList = theatreAdminDao.findAllTheatreAdmin();
		if(!theatreAdminList.isEmpty())
		{
			for (TheatreAdmin theatreAdmin : theatreAdminList) 
			{
				if(theatreAdmin.getTheatreAdminEmail().equals(theatreAdminEmail))
				{
					if(theatreAdmin.getTheatreAdminPassword().equals(theatreAdminPassword))
					{
						return theatreAdmin;
					}
					throw new WrongPassword("Entered Wrong Password");
				}
				throw new EmailNotFound("Email Not Found In Database You Entered Wrong Email");
			}
		}
		throw new EmptyList("Empty List..");
	}
	
}
