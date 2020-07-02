package com.example.lapse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lapse.domain.PublicHoliday;
import com.example.lapse.repo.PublicRepo;


@Service
public class PublicServiceImpl implements PublicService {
	
	@Autowired
	PublicRepo pubRepo;
	
	@Override
	public List<PublicHoliday> findAll(){
		List<PublicHoliday> phList = (List<PublicHoliday>)pubRepo.findAll();
		return phList;
	};

	

}
