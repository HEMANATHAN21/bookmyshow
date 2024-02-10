package com.spring.bookmyshow.dto;

import com.spring.bookmyshow.entity.Theatre;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TheatreAdminDto 
{
	private int theatreAdminId;
	private String theatreAdminName;
	private String theatreAdminEmail;
	private String theatreAdminPassword;
	private Theatre adminTheatre;
}
