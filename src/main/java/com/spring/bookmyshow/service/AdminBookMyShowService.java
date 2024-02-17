package com.spring.bookmyshow.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.bookmyshow.dao.AdminBookMyShowDao;
import com.spring.bookmyshow.dto.AdminBookMyShowDto;
import com.spring.bookmyshow.entity.AdminBookMyShow;
import com.spring.bookmyshow.util.ResponseStructure;

@Service
public class AdminBookMyShowService 
{
	@Autowired
	AdminBookMyShowDao adminBmsDao;
	
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> saveAdminBookMyShow(AdminBookMyShow adminBookMyShow)
	{
		AdminBookMyShow adminBms = adminBmsDao.saveAdminBookMyShow(adminBookMyShow);
		if(adminBms != null)
		{
			ResponseStructure<AdminBookMyShowDto> structure = new ResponseStructure<>();
			AdminBookMyShowDto adminBmsDto = new AdminBookMyShowDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminBms, adminBmsDto);
			structure.setMessage("Admin Book My Show Created");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(adminBmsDto);
			return new ResponseEntity<ResponseStructure<AdminBookMyShowDto>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> findAdminBookMyShow(int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			AdminBookMyShowDto dto = new AdminBookMyShowDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminBms, dto);
			ResponseStructure<AdminBookMyShowDto> structure = new ResponseStructure<>();
			structure.setMessage("Admin Book My Show Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminBookMyShowDto>>(structure,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> deleteAdminBookMyShow(int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			AdminBookMyShow deletedAdminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
			if(deletedAdminBms != null)
			{
				AdminBookMyShowDto dto = new AdminBookMyShowDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(deletedAdminBms, dto);
				ResponseStructure<AdminBookMyShowDto> structure = new ResponseStructure<>();
				structure.setMessage("Admin Book My Show Deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<AdminBookMyShowDto>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<AdminBookMyShowDto>> updateAdminBookMyShow(AdminBookMyShow adminBookMyShow, int adminBmsId)
	{
		AdminBookMyShow adminBms = adminBmsDao.findAdminBookMyShow(adminBmsId);
		if(adminBms != null)
		{
			AdminBookMyShow updateAdminBms = adminBmsDao.updateAdminBookMyShow(adminBookMyShow,adminBmsId);
			if(updateAdminBms != null)
			{
				AdminBookMyShowDto dto = new AdminBookMyShowDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(updateAdminBms, dto);
				ResponseStructure<AdminBookMyShowDto> structure = new ResponseStructure<>();
				structure.setMessage("Admin Book My Show Updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<AdminBookMyShowDto>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public AdminBookMyShow adminBmsLogin(String adminBmsMail, String adminBmsPassword)
	{
		List<AdminBookMyShow> adminBmsList = adminBmsDao.findAllAdminBookMyShow();
		if(!adminBmsList.isEmpty())
		{
			for (AdminBookMyShow adminBookMyShow : adminBmsList) 
			{
				if(adminBookMyShow.getAdminBmsMail().equals(adminBmsMail))
				{
					if(adminBookMyShow.getAdminBmsPassword().equals(adminBmsList))
					{
						return adminBookMyShow;
					}
					return null;//password wrong
				}
				return null;//email invalid or not found
			}
		}
		return null;//list Empty
	}
}
