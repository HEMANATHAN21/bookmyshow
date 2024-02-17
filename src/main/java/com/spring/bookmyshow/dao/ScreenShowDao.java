package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmyshow.entity.ScreenShow;
import com.spring.bookmyshow.repo.ScreenShowRepo;

@Repository
public class ScreenShowDao 
{
	@Autowired
	ScreenShowRepo screenShowRepo;
	
	public ScreenShow saveScreenShow(ScreenShow show)
	{
		return screenShowRepo.save(show);
	}
	
	public ScreenShow findScreenShow(int showId)
	{
		Optional<ScreenShow> opShow = screenShowRepo.findById(showId);
		if(opShow.isPresent())
			return opShow.get();
		return null;
	}
	
	public ScreenShow deleteScreenShow(int showId)
	{
		ScreenShow show = findScreenShow(showId);
		screenShowRepo.delete(show);
		return show;
	}
	
	public ScreenShow updateScreenShow(ScreenShow show, int showId)
	{
		ScreenShow exShow = findScreenShow(showId);
		if(exShow != null)
		{
			show.setSId(showId);
			return screenShowRepo.save(show);
		}
		return null;
	}
	
	public List<ScreenShow> findAllShow()
	{
		return screenShowRepo.findAll();
	}
}
