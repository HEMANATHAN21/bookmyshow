package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.repo.MovieRepo;

@Repository
public class MovieDao 
{
	@Autowired
	MovieRepo movieRepo;
	
	public Movie saveMovie(Movie movie)
	{
		return movieRepo.save(movie);
	}
	
	public Movie findMovie(int movieId)
	{
		Optional<Movie> opMovie = movieRepo.findById(movieId);
		if(opMovie.isPresent())
			return opMovie.get();
		return null;
	}
	
	public Movie deleteMovie(int movieId)
	{
		Movie movie = findMovie(movieId);
		movieRepo.delete(movie);
		return movie;
	}
	
	public Movie updateMovie(Movie movie, int movieId)
	{
		Movie exMovie = findMovie(movieId);
		if(exMovie != null)
		{
			movie.setMovieId(movieId);
			return movieRepo.save(movie);
		}
		return null;
	}
	
	public List<Movie> findAllMovie()
	{
		return movieRepo.findAll();
	}
}
