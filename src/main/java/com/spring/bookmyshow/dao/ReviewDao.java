package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.Review;
import com.spring.bookmyshow.repo.ReviewRepo;

@Repository
public class ReviewDao 
{
	@Autowired
	ReviewRepo reviewRepo;
	
	public Review saveReview(Review review)
	{
		return reviewRepo.save(review);
	}
	
	public Review findReview(int reviewId)
	{
		Optional<Review> opReview = reviewRepo.findById(reviewId);
		if(opReview.isPresent())
			return opReview.get();
		return null;
	}
	
	public Review deleteReview(int reviewId)
	{
		Review review = findReview(reviewId);
		reviewRepo.delete(review);
		return review;
	}
	
	public Review updateReview(Review review, int reviewId)
	{
		Review exReview = findReview(reviewId);
		if(exReview != null)
		{
			exReview.setReviewId(reviewId);
			return reviewRepo.save(review);
		}
		return null;
	}
	
	public List<Review> findAllReview()
	{
		return reviewRepo.findAll();
	}
}
