package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.bookmyshow.dao.ReviewDao;
import com.spring.bookmyshow.entity.Review;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class ReviewService 
{
	@Autowired
	ReviewDao reviewDao;
	
	public ResponseEntity<ResponseStructure<Review>> saveReview(Review review)
	{
		Review reviewNew = reviewDao.saveReview(review);
		if(reviewNew != null)
		{
			ResponseStructure<Review> structure = new ResponseStructure<>();
			structure.setMessage("Review Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(reviewNew);
			return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Review>> findReview(int reviewId)
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
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Review>> deleteReview(int reviewId)
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
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Review>> updateReview(Review review, int reviewId)
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
			return null;
		}
		return null;
	}
}
