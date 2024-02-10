package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.repo.ScreenRepo;

@Repository
public class ScreenDao 
{
	@Autowired
	ScreenRepo screenRepo;
	
	public Screen saveScreen(Screen screen)
	{
		return screenRepo.save(screen);
	}
	
	public Screen findScreen(int screenId)
	{
		Optional<Screen> opScreen = screenRepo.findById(screenId);
		if(opScreen.isPresent())
			return opScreen.get();
		return null;
	}
	
	public Screen deleteScreen(int screenId)
	{
		Screen screen = findScreen(screenId);
		screenRepo.delete(screen);
		return screen;
	}
	
	public Screen updateScreen(Screen screen, int screenId)
	{
		Screen exScreen = findScreen(screenId);
		if(exScreen != null)
		{
			screen.setScreenId(screenId);
			return screenRepo.save(screen);
		}
		return null;
	}
	
	public List<Screen> findAllScreen()
	{
		return screenRepo.findAll();
	}
}
