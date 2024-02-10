package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer> {

}
