package com.spring.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.AdminBookMyShowDao;
import com.spring.bookmyshow.entity.AdminBookMyShow;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class AdminBookMyShowService 
{
	@Autowired
	AdminBookMyShowDao adminBmsDao;
	
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> saveAdminBookMyShow(AdminBookMyShow adminBookMyShow)
	{
		AdminBookMyShow adminBms = adminBmsDao.saveAdminBookMyShow(adminBookMyShow);
		if(adminBms != null)
		{
			ResponseStructure<AdminBookMyShow> structure = new ResponseStructure<>();
			structure.setMessage("Admin Book My Show Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(adminBms);
			return new ResponseEntity<ResponseStructure<AdminBookMyShow>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> findAdminBookMyShow(int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			ResponseStructure<AdminBookMyShow> structure = new ResponseStructure<>();
			structure.setMessage("Admin Book My Show Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(adminBms);
			return new ResponseEntity<ResponseStructure<AdminBookMyShow>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> deleteAdminBookMyShow(int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			AdminBookMyShow deletedAdminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
			if(deletedAdminBms != null)
			{
				ResponseStructure<AdminBookMyShow> structure = new ResponseStructure<>();
				structure.setMessage("Admin Book My Show Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(deletedAdminBms);
				return new ResponseEntity<ResponseStructure<AdminBookMyShow>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShow>> updateAdminBookMyShow(AdminBookMyShow adminBookMyShow, int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			AdminBookMyShow updateAdminBms = adminBmsDao.updateAdminBookMyShow(adminBookMyShow,adminBmsId);
			if(updateAdminBms != null)
			{
				ResponseStructure<AdminBookMyShow> structure = new ResponseStructure<>();
				structure.setMessage("Admin Book My Show Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(updateAdminBms);
				return new ResponseEntity<ResponseStructure<AdminBookMyShow>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
}
