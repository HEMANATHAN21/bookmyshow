package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
