package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {

}
