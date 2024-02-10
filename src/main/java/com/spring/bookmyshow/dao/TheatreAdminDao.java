package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.repo.TheatreAdminRepo;

@Repository
public class TheatreAdminDao 
{
	@Autowired
	TheatreAdminRepo theatreAdminRepo;
	
	public TheatreAdmin saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		return theatreAdminRepo.save(theatreAdmin);
	}
	
	public TheatreAdmin findTheatreAdmin(int theatreAdminId)
	{
		Optional<TheatreAdmin> opTheatreAdmin = theatreAdminRepo.findById(theatreAdminId);
		if(opTheatreAdmin.isPresent())
			return opTheatreAdmin.get();
		return null;
	}
	
	public TheatreAdmin deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdmin theatreAdmin = findTheatreAdmin(theatreAdminId);
		theatreAdminRepo.delete(theatreAdmin);
		return theatreAdmin;
	}
	
	public TheatreAdmin updateTheatreAdmin(TheatreAdmin theatreAdmin, int theatreAdminId)
	{
		TheatreAdmin exTheatreAdmin = findTheatreAdmin(theatreAdminId);
		if(exTheatreAdmin != null)
		{
			exTheatreAdmin.setTheatreAdminId(theatreAdminId);
			return theatreAdminRepo.save(theatreAdmin);
		}
		return null;
	}
	
	public List<TheatreAdmin> findAllTheatreAdmin()
	{
		return theatreAdminRepo.findAll();
	}
}
