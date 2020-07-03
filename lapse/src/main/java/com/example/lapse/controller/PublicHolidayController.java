package com.example.lapse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.service.PublicHolidayService;
import com.example.lapse.service.PublicHolidayServiceImpl;

@Controller
@RequestMapping("/publicHol")
public class PublicHolidayController {

	@Autowired
	private PublicHolidayService pubservice;

	@Autowired
	public void setPublicService(PublicHolidayServiceImpl publicServiceImpl) {
		this.pubservice = publicServiceImpl;


	}
}
