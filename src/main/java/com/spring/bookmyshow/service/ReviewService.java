package com.spring.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.MovieDao;
import com.spring.bookmyshow.dao.ReviewDao;
import com.spring.bookmyshow.dao.UserDao;
import com.spring.bookmyshow.entity.Movie;
import com.spring.bookmyshow.entity.Review;
import com.spring.bookmyshow.entity.User;
import com.spring.bookmyshow.exception.MovieNotFound;
import com.spring.bookmyshow.exception.ReviewNotFound;
import com.spring.bookmyshow.exception.UserNotFound;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class ReviewService 
{
	@Autowired
	ReviewDao reviewDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MovieDao movieDao;
	
	public ResponseEntity<ResponseStructure<Review>> saveReview(Review review,int movieId,String userEmail,String userPassword)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			Movie exMovie = movieDao.findMovie(movieId);
			if(exMovie != null)
			{
				review.setMovie(exMovie);
				review.setUserId(exUser.getUserId());
				Review reviewNew = reviewDao.saveReview(review);
				List<Review> reviewList = exMovie.getMoviewReviews();
				reviewList.add(reviewNew);
				exMovie.setMoviewReviews(reviewList);
				double total = 0;
				int count = 0;
				for (Review rev : reviewList) 
				{
					total = total + rev.getRating();
					count++;
				}
				exMovie.setRating(total / count);
				movieDao.updateMovie(exMovie, exMovie.getMovieId());
				if(reviewNew != null)
				{
					ResponseStructure<Review> structure = new ResponseStructure<>();
					structure.setMessage("Review Created");
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(reviewNew);
					return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.CREATED);
				}
				return null;//not saved
			}
			throw new MovieNotFound("Movie not found in Given MovieId : "+movieId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
		
	}
	
	public ResponseEntity<ResponseStructure<Review>> findReview(int reviewId,String userEmail,String userPassword)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			Review review = reviewDao.findReview(reviewId);
			if(review != null)
			{
				ResponseStructure<Review> structure = new ResponseStructure<>();
				structure.setMessage("Review Founded");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(review);
				return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.FOUND);
			}
			throw new ReviewNotFound("Review not found in Given ReviewId : "+reviewId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
		
	}
	
	public ResponseEntity<ResponseStructure<Review>> deleteReview(int reviewId,String userEmail,String userPassword)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			Review review = reviewDao.findReview(reviewId);
			if(review != null)
			{
				Review deletedReview = reviewDao.findReview(reviewId);
				if(deletedReview != null)
				{
					ResponseStructure<Review> structure = new ResponseStructure<>();
					structure.setMessage("Review Deleted");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(deletedReview);
					return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
				}
				return null;//not deleted
			}
			throw new ReviewNotFound("Review not found in Given ReviewId : "+reviewId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
	}
	
	public ResponseEntity<ResponseStructure<Review>> updateReview(Review review, int reviewId,String userEmail,String userPassword)
	{
		User exUser = userDao.findByEmail(userEmail, userPassword);
		if(exUser != null)
		{
			Review reviewfind = reviewDao.findReview(reviewId);
			if(reviewfind != null)
			{
				Review updateReview = reviewDao.updateReview(review,reviewId);
				if(updateReview != null)
				{
					ResponseStructure<Review> structure = new ResponseStructure<>();
					structure.setMessage("Review Updated");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(updateReview);
					return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
				}
				return null;//not updated
			}
			throw new ReviewNotFound("Review not found in Given ReviewId : "+reviewId);
		}
		throw new UserNotFound("User Not Found Check Your Login Credentials..");
	}
}
