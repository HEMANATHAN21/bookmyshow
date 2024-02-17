package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.bookmyshow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> 
{
	@Query("select u from User u where u.userEmail =?1 and u.userPassword =?2")
	public User findByEmail(String userEmail,String userPassword);
}
