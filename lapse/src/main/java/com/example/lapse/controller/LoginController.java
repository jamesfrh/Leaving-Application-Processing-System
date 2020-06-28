package com.example.lapse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.repo.AdminRepo;
import com.example.lapse.repo.ManagerRepo;
import com.example.lapse.repo.StaffRepo;

@Controller
@RequestMapping("/home")
public class LoginController {
	
	@Autowired
	StaffRepo staffRepo;

	@Autowired
	ManagerRepo mgrRepo;

	@Autowired
	AdminRepo adminRepo;

	
	@GetMapping("/login")
	public String login(HttpSession session) {
		
		
		return "login";
	}
	
	@GetMapping("/submit")
	public String submit(HttpSession session) {
		
		
		return "login";
	}
	
	@RequestMapping("/homepage")
	public String home(HttpSession session, Model model) {
		
		//testing hyperlink
		model.addAttribute("role", "manager");
		return "homePage";
	}

}
