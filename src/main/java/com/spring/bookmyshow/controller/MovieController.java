package com.spring.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.service.MovieService;
import com.spring.bookmyshow.util.ResponseStructure;
@RestController
@RequestMapping("movie")
public class MovieController 
{
	@Autowired
	MovieService movieService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie,@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return movieService.saveMovie(movie, theatreAdminEmail, theatreAdminPassword);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int movieId,@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return movieService.findMovie(movieId, theatreAdminEmail, theatreAdminPassword);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId,@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return movieService.deleteMovie(movieId, theatreAdminEmail, theatreAdminPassword);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody Movie movie,@RequestParam int movieId,@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return movieService.updateMovie(movie, movieId, theatreAdminEmail, theatreAdminPassword);		
	}

}
