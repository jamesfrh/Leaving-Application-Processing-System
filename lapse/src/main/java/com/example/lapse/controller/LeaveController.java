package com.example.lapse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.service.LeaveApplicationService;
import com.example.lapse.service.LeaveApplicationServiceImpl;
import com.example.lapse.service.LeaveTypeService;
import com.example.lapse.service.LeaveTypeServiceImpl;
import com.example.lapse.service.StaffService;
import com.example.lapse.service.StaffServiceImpl;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired 
	private LeaveApplicationService lservice;
	
	@Autowired
	public void setLeaveApplicationService (LeaveApplicationServiceImpl lserviceImpl) {
		this.lservice = lserviceImpl;
	}
	
	@Autowired
	private LeaveTypeService ltservice;
	
	@Autowired
	public void setLeaveTypeService(LeaveTypeServiceImpl ltserviceImpl) {
		this.ltservice = ltserviceImpl;
	}
	
	@Autowired
	private StaffService staffservice;

	@Autowired
	public void setStaffService(StaffServiceImpl sserviceImpl) {
		this.staffservice = sserviceImpl;
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
		model.addAttribute("leavetypes", ltservice.findAllLeaveTypeNamesExCL());
		return "applyLeave";

	}
	 @RequestMapping(value = "/viewallpending")
	 public String viewpendingleaveapproval(Model model,HttpSession session) {	
		 int id=(int) session.getAttribute("id");
		 List<LeaveApplication> PendingLeaveList=lservice.findpendingleaveapproval(id);					  		 			 					
		 model.addAttribute(("LeaveApplication"), PendingLeaveList);
		 return"Managerapproval";
	 }
	 
	 @RequestMapping(value = "/approve/{id}")
	 public String approveleaveapplication(@PathVariable("id") Integer id) {
	 lservice.approveleaveapplication(id);
	 return "Managerapproval";
}

    @RequestMapping(value = "/reject/{id}")
    public String rejectleaveapplication(@PathVariable("id") Integer id) {
    lservice.rejectleaveapplication(id);
    return "Managerapproval";	
    } 
}
