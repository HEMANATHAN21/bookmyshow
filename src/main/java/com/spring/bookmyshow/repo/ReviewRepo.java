package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
