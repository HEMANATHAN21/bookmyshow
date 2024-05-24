package com.spring.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ScreenDao;
import com.spring.bookmyshow.dao.ScreenShowDao;
import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.dao.TheatreDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Screen;
import com.spring.bookmyshow.entity.ScreenShow;
import com.spring.bookmyshow.entity.SeatType;
import com.spring.bookmyshow.entity.Theatre;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.exception.EmptyList;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.ScreenNotFound;
import com.spring.bookmyshow.exception.ScreenShowNotFound;
import com.spring.bookmyshow.exception.TheatreAdminNotFound;
import com.spring.bookmyshow.exception.TheatreNotFound;
import com.spring.bookmyshow.exception.UserNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class ScreenShowService 
{
	@Autowired
	ScreenShowDao screenShowDao;
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	TheatreDao theatreDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	ScreenDao screenDao;
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<ScreenShow>> saveScreenShow(ScreenShow show,int screenId,int movieId,String theatreAdminEmail,String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Theatre exTheatre = theatreDao.findTheatre(exTheatreAdmin.getAdminTheatre().getTheatreId());
			if(exTheatre != null)
			{
				Screen exScreen = screenDao.findScreen(screenId);
				int totalSeatingCount = exScreen.getTotalSeatingCount();
				show.setTotalSeatingCount(totalSeatingCount);
				show.setTotalSeat(new int[totalSeatingCount]);
				show.setMovie(movieDao.findMovie(movieId));
				show.setScreenId(screenId);
				show.setTheatreId(exTheatreAdmin.getAdminTheatre().getTheatreId());
				ScreenShow showNew = screenShowDao.saveScreenShow(show);
				if(showNew != null)
				{
					List<ScreenShow> showList = exScreen.getShowList();
					showList.add(showNew);
					exScreen.setShowList(showList);
					Screen updatedScreen = screenDao.updateScreen(exScreen, exScreen.getScreenId());
					if(updatedScreen != null)
					{
						ResponseStructure<ScreenShow> structure = new ResponseStructure<>();
						structure.setMessage("Show Saved");
						structure.setStatus(HttpStatus.CREATED.value());
						structure.setData(showNew);
						return new ResponseEntity<ResponseStructure<ScreenShow>>(structure,HttpStatus.CREATED);
					}
					throw new NotUpdated("Screen Not Updated"); //screen not updated
				}
				throw new NotSaved("ScreenShow Not Saved.."); //screenshow not saved
			}
			throw new TheatreNotFound("Theatre Not Found in Given Theatre Id : "+exTheatreAdmin.getAdminTheatre().getTheatreId());
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not found check Ypur Login credentials..");
	}
	
	public ResponseEntity<ResponseStructure<ScreenShow>> findScreenShow(int showId)
	{
		ScreenShow showfind = screenShowDao.findScreenShow(showId);
		if(showfind != null)
		{
			ResponseStructure<ScreenShow> structure = new ResponseStructure<>();
			structure.setMessage("ScreenShow Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(showfind);
			return new ResponseEntity<ResponseStructure<ScreenShow>>(structure,HttpStatus.FOUND);
		}
		throw new ScreenShowNotFound("Screen Show Not Found In Given Id : "+showId);
	}
	
	public ResponseEntity<ResponseStructure<ScreenShow>> deleteScreenShow(int showId)
	{
		ScreenShow show = screenShowDao.findScreenShow(showId);
		if(show != null)
		{
			ScreenShow deletedShow = screenShowDao.deleteScreenShow(showId);
			if(deletedShow != null)
			{
				ResponseStructure<ScreenShow> structure = new ResponseStructure<>();
				structure.setMessage("ScreenShow Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedShow);
				return new ResponseEntity<ResponseStructure<ScreenShow>>(structure,HttpStatus.OK);
			}
			throw new NotDeleted("ScreenShow Not Deleted");
		}
		throw new ScreenShowNotFound("Screen Show Not Found In Given Id : "+showId);
	}
	
	public ResponseEntity<ResponseStructure<ScreenShow>> updateScreenShow(ScreenShow show, int showId)
	{
		ScreenShow exShow = screenShowDao.findScreenShow(showId);
		if(exShow != null)
		{
			ScreenShow updatedShow = screenShowDao.updateScreenShow(show, showId);
			if(updatedShow != null)
			{
				ResponseStructure<ScreenShow> structure = new ResponseStructure<>();
				structure.setMessage("Show Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updatedShow);
				return new ResponseEntity<ResponseStructure<ScreenShow>>(structure,HttpStatus.OK);
				
			}
			throw new NotUpdated("ScreenShow Not Updated");
		}
		throw new ScreenShowNotFound("Screen Show Not Found In Given Id : "+showId);
	}
	
	public ResponseEntity<ResponseStructure<List<ScreenShow>>> findShowScreenBasedOnMovieName(String userEmail,String userPassword,String movieName)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			List<ScreenShow> screenShowList = screenShowDao.findAllShow();
			List<ScreenShow> movieShowInfo = new ArrayList<>();
			for (ScreenShow screenShow : screenShowList) 
			{
				if(screenShow.getMovie().getMovieName().equals(movieName))
				{
					movieShowInfo.add(screenShow);
				}
			}
			if(!movieShowInfo.isEmpty())
			{
				ResponseStructure<List<ScreenShow>> structure = new ResponseStructure<>();
				structure.setMessage("Movie Available Show List Info");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(movieShowInfo);
				return new ResponseEntity<ResponseStructure<List<ScreenShow>>>(structure,HttpStatus.FOUND);
			}
			throw new EmptyList("ShowScreen Details Not Found For Given Movie Name : "+movieName);
		}
		throw new UserNotFound("User Not Found Check Your Login Credential...");
	}
	
	public ResponseEntity<ResponseStructure<List<Integer>>> checkSeatAvailability(int theatreId,int screenId,int screenShowId,SeatType seatType)
	{
		Theatre exTheatre = theatreDao.findTheatre(theatreId);
		if(exTheatre != null)
		{
			Screen exScreen = null;
			List<Screen> screenList = exTheatre.getTheatreScreenList();
			for (Screen screen : screenList) 
			{
				if(screen.getScreenId() == screenId)
				{
					exScreen = screen;
				}
			}
			if(exScreen != null)
			{
				ScreenShow exScreenShow = null;
				List<ScreenShow> screenShowList = exScreen.getShowList();
				for (ScreenShow screenShow : screenShowList) 
				{
					if(screenShow.getScreenId() == screenId)
					{
						exScreenShow = screenShow;
					}
				}
				if(exScreenShow != null)
				{
					int totalSeatCount = exScreenShow.getTotalSeatingCount();
					int[] totalSeat = exScreenShow.getTotalSeat();
					int start = -1;
					int end = -1;
					List<Integer> seatAvailability = new ArrayList<>();
					if(seatType.equals(SeatType.FirstClass))
					{
						start = 0;
						end = (15 * totalSeatCount) / 100;
					}
					else if(seatType.equals(SeatType.SecondClass))
					{
						start = (15 * totalSeatCount) / 100;;
						end = (35 * totalSeatCount) / 100;
					}
					else if(seatType.equals(SeatType.ThirdClass))
					{
						start = (35 * totalSeatCount) / 100;;
						end = totalSeatCount;
					}
					
					for(int i=start;i<end;i++)
					{
						if(totalSeat[i] == 0)
						{
							seatAvailability.add(i);
						}
					}
					
					if(!seatAvailability.isEmpty())
					{
						ResponseStructure<List<Integer>> structure = new ResponseStructure<>();
						structure.setMessage("Available Seat Indexes Retrived For "+seatType+" Seat Type");
						structure.setData(seatAvailability);
						structure.setStatus(HttpStatus.OK.value());
						return new ResponseEntity<ResponseStructure<List<Integer>>>(structure,HttpStatus.OK);

					}
					throw new EmptyList("Seat Not Available  For "+seatType+" Seat Type");
				}
				throw new ScreenShowNotFound("ScreenShow not found in ScreenList Given Show Id Is : "+screenShowId);
			}
			throw new ScreenNotFound("Screen not Found In Theatre Screen List Given Screen Id Is : "+screenId);
		}
		throw new TheatreNotFound("Theatre Not Found In Given Id : "+theatreId);
	}
}
