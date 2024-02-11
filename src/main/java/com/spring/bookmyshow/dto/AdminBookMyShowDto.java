package com.spring.bookmyshow.dto;

import java.util.List;

import com.spring.bookmyshow.entity.Theatre;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdminBookMyShowDto 
{
	private int adminBmsId;
	private String adminBmsName;
	private String adminBmsMail;
	private List<Theatre> BmsTheatres;
}
