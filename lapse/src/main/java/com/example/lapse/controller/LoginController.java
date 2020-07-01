package com.example.lapse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.domain.Staff;
import com.example.lapse.service.StaffService;
import com.example.lapse.service.StaffServiceImpl;

@Controller
@RequestMapping("/home")
public class LoginController {
	
	@Autowired
	private StaffService staffservice;

	@Autowired
	public void setStaffService(StaffServiceImpl sserviceImpl) {
		this.staffservice = sserviceImpl;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new StaffValidator());
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		Staff staff = new Staff();
		model.addAttribute("staff", staff);
		return "login";
	}
	
//	@RequestMapping("/submit")
//	public String submit(@ModelAttribute("staff") Staff staff, HttpSession session) {
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("staff", staff);
//			return "login";
//		}
//		Staff currStaff = staffservice.findStaffByEmail(staff.getEmail());
//		session.setAttribute("role", currStaff.getRole());
//		session.setAttribute("id", currStaff.getId());
//		
//		return "homePage";
//	}
	
	@RequestMapping("/submit")
	public String submit(@ModelAttribute("staff") Staff staff, HttpSession session) {
		boolean staffstatus = staffservice.validateStaff(staff.getEmail(), staff.getPassword());
	    if (staffstatus==true) {
	    	Staff currStaff = staffservice.findStaffByEmail(staff.getEmail());
	    	session.setAttribute("role", currStaff.getRole());
	    	session.setAttribute("id", currStaff.getId());
	    	return "homepage";
	    }
	    return "login";
	  }
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	
}