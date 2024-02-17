package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.ScreenShow;

public interface ScreenShowRepo extends JpaRepository<ScreenShow, Integer> 
{

}
