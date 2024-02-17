package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.bookmyshow.entity.TheatreAdmin;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdmin, Integer> 
{
	@Query("select ta from TheatreAdmin ta where ta.theatreAdminEmail=?1 and ta.theatreAdminPassword=?2")
	public TheatreAdmin findByEmail(String theatreAdminEmail,String theatreAdminPassword);
}
