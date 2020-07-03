package com.example.lapse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.PublicHoliday;

@Service
public interface PublicHolidayService {

	List<PublicHoliday> findAll();

	
}
