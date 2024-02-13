package com.spring.bookmyshow.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.entity.Theatre;
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
	
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(Screen screen,int movieId,int theatreId)
	{
		Theatre theatre = theatreDao.findTheatre(theatreId);
		if(theatre != null)
		{
			Movie movie = movieDao.findMovie(movieId);
			if(movie != null)
			{
				screen.setMovieName(movie);
				screen.setTheatre(theatre);
				int totalSeatCount = screen.getTotalSeatingCount();
				screen.setSeatAclass(new int[(15 * totalSeatCount)/100]);
				screen.setSeatBclass(new int[(30 * totalSeatCount)/100]);
				screen.setSeatCclass(new int[(55 * totalSeatCount)/100]);
				screen.setScreenTheatreId(theatre.getTheatreId());
				Screen screenNew = screenDao.saveScreen(screen);
				if(screenNew != null)
				{
					List<Screen> theatreScreenList = theatre.getTheatreScreenList();
					theatreScreenList.add(screenNew);
					theatre.setTheatreScreenList(theatreScreenList);
					theatreDao.updateTheatre(theatre, theatreId);
					ResponseStructure<Screen> structure = new ResponseStructure<>();
					structure.setMessage("Screen Created And Assigned To Theatre Id : "+theatreId);
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(screenNew);
					return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.CREATED);
				}
				return null;
			}
			return null;
		}
		return null;
		
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
		return null;
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
			return null;
		}
		return null;
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
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Screen>> updateSeatClass(int screenId)
	{
		Screen exScreen = screenDao.findScreen(screenId);
		if(exScreen != null)
		{
			int totalSeatCount = exScreen.getTotalSeatingCount();
			exScreen.setSeatAclass(new int[(15 * totalSeatCount)/100]);
			exScreen.setSeatBclass(new int[(30 * totalSeatCount)/100]);
			exScreen.setSeatCclass(new int[(55 * totalSeatCount)/100]);
			Screen updatedScreen = screenDao.updateScreen(exScreen, screenId);
			if(updatedScreen != null)
			{
				ResponseStructure<Screen> structure = new ResponseStructure<>();
				structure.setMessage("Screen Seat Classes Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updatedScreen);
				return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
			}
			return null;//screen seat not updated
		}
		return null;//screen not found
	}
}
