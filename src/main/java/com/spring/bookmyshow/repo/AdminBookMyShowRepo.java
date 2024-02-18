package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.bookmyshow.entity.AdminBookMyShow;

public interface AdminBookMyShowRepo extends JpaRepository<AdminBookMyShow, Integer> 
{
	@Query("select a from AdminBookMyShow a where a.adminBmsMail=?1 and a.adminBmsPassword=?2")
	public AdminBookMyShow findByEmail(String adminBmsMail,String adminBmsPassword);
}
