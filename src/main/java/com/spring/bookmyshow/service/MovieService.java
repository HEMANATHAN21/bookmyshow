package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.TheatreAdminDao;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.entity.TheatreAdmin;
import com.spring.bookmyshow.exception.MovieNotFound;
import com.spring.bookmyshow.exception.NotDeleted;
import com.spring.bookmyshow.exception.NotSaved;
import com.spring.bookmyshow.exception.NotUpdated;
import com.spring.bookmyshow.exception.TheatreAdminNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class MovieService 
{
	@Autowired
	MovieDao movieDao;
	@Autowired
	TheatreAdminDao theatreAdminDao;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie,String theatreAdminEmail,String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Movie movieNew = movieDao.saveMovie(movie);
			if(movieNew != null)
			{
				ResponseStructure<Movie> structure = new ResponseStructure<>();
				structure.setMessage("Movie Created");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(movieNew);
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.CREATED);
			}
			throw new NotSaved("Movie Not Saved");
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found Check Your Login Credentials...");
		
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId, String theatreAdminEmail, String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Movie movie = movieDao.findMovie(movieId);
			if(movie != null)
			{
				ResponseStructure<Movie> structure = new ResponseStructure<>();
				structure.setMessage("Movie Founded");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(movie);
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
			}
			throw new MovieNotFound("Movie Not Found In Give Id : "+movieId);
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found Check Your Login Credentials...");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId,String theatreAdminEmail, String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Movie movie = movieDao.findMovie(movieId);
			if(movie != null)
			{
				Movie deletedMovie = movieDao.findMovie(movieId);
				if(deletedMovie != null)
				{
					ResponseStructure<Movie> structure = new ResponseStructure<>();
					structure.setMessage("Movie Deleted");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(deletedMovie);
					return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
				}
				throw new NotDeleted("Movie Not Deleted");
			}
			throw new MovieNotFound("Movie Not Found In Give Id : "+movieId);
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found Check Your Login Credentials...");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie, int movieId,String theatreAdminEmail, String theatreAdminPassword)
	{
		TheatreAdmin exTheatreAdmin = theatreAdminDao.findByEmail(theatreAdminEmail, theatreAdminPassword);
		if(exTheatreAdmin != null)
		{
			Movie moviefind = movieDao.findMovie(movieId);
			if(moviefind != null)
			{
				Movie updateMovie = movieDao.updateMovie(movie,movieId);
				if(updateMovie != null)
				{
					ResponseStructure<Movie> structure = new ResponseStructure<>();
					structure.setMessage("Movie Updated");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(updateMovie);
					return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
				}
				throw new NotUpdated("Movie Not Updated");
			}
			throw new MovieNotFound("Movie Not Found In Give Id : "+movieId);
		}
		throw new TheatreAdminNotFound("TheatreAdmin Not Found Check Your Login Credentials...");
	}
}
