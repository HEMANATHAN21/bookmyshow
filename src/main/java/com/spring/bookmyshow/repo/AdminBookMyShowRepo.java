package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.AdminBookMyShow;

public interface AdminBookMyShowRepo extends JpaRepository<AdminBookMyShow, Integer> {

}
