package com.example.lapse.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.domain.Staff;
import com.example.lapse.service.StaffService;
import com.example.lapse.service.StaffServiceImpl;
import com.example.lapse.utils.Login;

@Controller
@RequestMapping("/home")
public class LoginController {
	
	@Autowired
	private StaffService staffservice;

	@Autowired
	public void setStaffService(StaffServiceImpl sserviceImpl) {
		this.staffservice = sserviceImpl;
	}
	
	@Autowired
	private LoginValidator loginValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping("/submit")
	public String submit(@ModelAttribute("login") @Valid Login login, BindingResult bindingResult, HttpSession session, Model model) {
		System.out.println(login);
		if (bindingResult.hasErrors()) {
			return "login";
		}
		Staff currStaff = new Staff();
		currStaff.setEmail(login.getEmail());
		currStaff.setPassword(login.getPassword());
		currStaff = staffservice.findStaffByEmail(currStaff.getEmail());
		session.setAttribute("role", currStaff.getRole());
		session.setAttribute("id", currStaff.getId());
		
		return "homePage";
	}
	
//	@RequestMapping("/submit")
//	public String submit(@ModelAttribute("staff") Staff staff, HttpSession session) {
//		boolean staffstatus = staffservice.validateStaff(staff.getEmail(), staff.getPassword());
//	    if (staffstatus==true) {
//	    	Staff currStaff = staffservice.findStaffByEmail(staff.getEmail());
//	    	session.setAttribute("role", currStaff.getRole());
//	    	session.setAttribute("id", currStaff.getId());
//	    	return "homepage";
//	    }
//	    return "login";
//	  }
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	
}