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
import com.spring.bookmyshow.entity.Review;
import com.spring.bookmyshow.service.ReviewService;
import com.spring.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("review")
public class ReviewController 
{
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Review>> saveReview(@RequestBody Review review,@RequestParam int userId,@RequestParam int movieId)
	{
		return reviewService.saveReview(review,userId,movieId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Review>> findReview(@RequestParam int reviewId)
	{
		return reviewService.findReview(reviewId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Review>> deleteReview(@RequestParam int reviewId)
	{
		return reviewService.deleteReview(reviewId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Review>> updateReview(@RequestBody Review review,@RequestParam int reviewId)
	{
		return reviewService.updateReview(review,reviewId);
	}

}
