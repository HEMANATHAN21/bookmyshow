package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
