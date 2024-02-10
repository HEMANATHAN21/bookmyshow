package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.TheatreAdmin;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdmin, Integer> {

}
