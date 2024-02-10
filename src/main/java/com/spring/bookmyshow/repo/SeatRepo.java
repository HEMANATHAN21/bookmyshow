package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> 
{

}
