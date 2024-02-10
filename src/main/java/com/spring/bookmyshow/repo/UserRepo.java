package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
