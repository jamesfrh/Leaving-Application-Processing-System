package com.example.lapse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.service.LeaveApplicationService;
import com.example.lapse.service.LeaveApplicationServiceImpl;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired 
	private LeaveApplicationService lservice;
	
	@Autowired
	public void setLeaveApplicationService (LeaveApplicationServiceImpl lserviceImpl) {
		this.lservice = lserviceImpl;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveValidator());
	}
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("leaveapplications", lservice.listAllLeaveApplications());
		return "leaveapplications";
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("leaveapplication", new LeaveApplication());
		return "leaveapplication-form";
	}
	
}
