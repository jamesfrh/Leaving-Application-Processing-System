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

import com.example.lapse.domain.Admin;
import com.example.lapse.service.AdminService;
import com.example.lapse.utils.AdminLogin;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Autowired
	private AdminService adminservice;
	
	@Autowired
	private AdminLoginValidator adminLoginValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(adminLoginValidator);
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new AdminLogin());
		return "admin-login";
	}
	
	@RequestMapping("/submit")
	public String submit(@ModelAttribute("login") @Valid AdminLogin login, BindingResult bindingResult, HttpSession session, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin-login";
		}
		Admin currAdmin = new Admin();
		currAdmin.setEmail(login.getEmail());
		currAdmin.setPassword(login.getPassword());
		currAdmin = adminservice.findAdminByEmail(currAdmin.getEmail());
		session.setAttribute("role", "Admin");
		session.setAttribute("id", currAdmin.getId());
		
		return "admin-home";
	}

}
