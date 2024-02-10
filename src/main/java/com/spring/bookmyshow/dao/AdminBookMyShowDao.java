package com.spring.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.bookmyshow.entity.AdminBookMyShow;
import com.spring.bookmyshow.repo.AdminBookMyShowRepo;

@Repository
public class AdminBookMyShowDao 
{
	@Autowired
	AdminBookMyShowRepo adminBmsRepo;
	
	public AdminBookMyShow saveAdminBookMyShow(AdminBookMyShow adminBookMyShow)
	{
		return adminBmsRepo.save(adminBookMyShow);
	}
	
	public AdminBookMyShow findAdminBookMyShow(int adminBmsId)
	{
		Optional<AdminBookMyShow> opAdminBookMyShow = adminBmsRepo.findById(adminBmsId);
		if(opAdminBookMyShow.isPresent())
			return opAdminBookMyShow.get();
		return null;
	}
	
	public AdminBookMyShow deleteAdminBookMyShow(int adminBmsId)
	{
		AdminBookMyShow adminBookMyShow = findAdminBookMyShow(adminBmsId);
		adminBmsRepo.delete(adminBookMyShow);
		return adminBookMyShow;
	}
	
	public AdminBookMyShow updateAdminBookMyShow(AdminBookMyShow adminBookMyShow, int adminBmsId)
	{
		AdminBookMyShow exAdminBookMyShow = findAdminBookMyShow(adminBmsId);
		if(exAdminBookMyShow != null)
		{
			adminBookMyShow.setAdminBmsId(adminBmsId);
			return adminBmsRepo.save(adminBookMyShow);
		}
		return null;
	}
	
	public List<AdminBookMyShow> findAllAdminBookMyShow()
	{
		return adminBmsRepo.findAll();
	}
}
