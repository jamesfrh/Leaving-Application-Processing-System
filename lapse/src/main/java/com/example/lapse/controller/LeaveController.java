package com.example.lapse.controller;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.domain.LeaveType;
import com.example.lapse.domain.Staff;
import com.example.lapse.enums.LeaveStatus;
import com.example.lapse.service.LeaveApplicationService;
import com.example.lapse.service.LeaveApplicationServiceImpl;
import com.example.lapse.service.LeaveTypeService;
import com.example.lapse.service.LeaveTypeServiceImpl;
import com.example.lapse.service.StaffService;
import com.example.lapse.service.StaffServiceImpl;
import com.example.lapse.utils.DateUtils;

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
	
	@Autowired
	private LeaveValidator leaveValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(leaveValidator);
	}
//	@RequestMapping(value = "/list")
//	public String list(Model model) {
//		model.addAttribute("leaveapplications", lservice.listAllLeaveApplications());
//		return "leaveapplications";
//	}

	
	//view history
	
	@RequestMapping(value = "/viewhistory")
	public String viewMyLeaveHistory(Model model, HttpSession session) {
		int id = (int) session.getAttribute("id");
		List<LeaveApplication> leaves = lservice.findApplicationByStaffId(id);
		model.addAttribute("leaves", leaves);
		return "viewMyHistory";
	}
	
	@RequestMapping(value = "/viewSubHistory")
	public String viewSubHistory(Model model, HttpSession session) {
		int id = (int) session.getAttribute("id");
		List<LeaveApplication> leaves = lservice.findSubLeaveAppByManagerId(id);
		model.addAttribute("leaves", leaves);
		return "viewSubHistory";
	}
	
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("leaveapplication", new LeaveApplication());
		model.addAttribute("leavetypes", ltservice.findAllLeaveTypeNamesExCL());
		return "applyLeave";

	}
	//missing validation part
	@RequestMapping("/submit")
	public String submit(@ModelAttribute("leaveapplication") LeaveApplication application, HttpSession session, Model model) {
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("leaveapplication", application);
//			model.addAttribute("leavetypes", ltservice.findAllLeaveTypeNamesExCL());
//			return "applyLeave";
//		}
			
		Staff currStaff = staffservice.findStafftById((Integer)session.getAttribute("id"));
		LeaveType leaveType = ltservice.findLeaveTypeByLeaveType(application.getLeaveType().getLeaveType());
		application.setStaff(currStaff);
		application.setLeaveType(leaveType);
		Calendar calStart = DateUtils.dateToCalendar(application.getStartDate());
	    Calendar calEnd = DateUtils.dateToCalendar(application.getEndDate());
		float daysBetween = ChronoUnit.DAYS.between(calStart.toInstant(), calEnd.toInstant()) + 1;
		if(daysBetween <= 14) {
			daysBetween = DateUtils.removeWeekends(calStart, calEnd);
		}
		application.setNoOfDays(daysBetween);
		lservice.addLeaveApplication(application);
		
		return "homePage";
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
    
    @RequestMapping(value="/viewdetails/{id}")
	public String viewDetailPending(@PathVariable("id") int id,Model model, HttpSession session)
	{
		LeaveApplication leave=lservice.findApplicationById(id);
		model.addAttribute("leaveapplication", leave);
		
		//retrieve leave applications between start and end date of current application
		
		Date currStartDate=leave.getStartDate();
		Date currEndDate=leave.getEndDate();		
		int currUserId=(int) session.getAttribute("id");
		
		List<LeaveApplication> leaveList=lservice.findApplicationByManagerId(currUserId);
		ArrayList<LeaveApplication> finalLeaveAppList=new ArrayList<LeaveApplication>();
		
		for (Iterator<LeaveApplication> iterator=leaveList.iterator();iterator.hasNext();) {
			 LeaveApplication la=(LeaveApplication)iterator.next();
			 boolean isTrue=lservice.isWithinDateRange(currStartDate, currEndDate, la.getStartDate(), la.getEndDate());
			 if(isTrue) {
				 finalLeaveAppList.add(la);
			 }
		}
		
		model.addAttribute("leaveListExceptApproveAndReject",finalLeaveAppList);
		return "viewDetailPending";
	}
	
	@RequestMapping(value = "/updateStatus")
	public String updatePendingStatus(@ModelAttribute("leaveapplication") LeaveApplication leaveApp, Model model) {
		if(leaveApp.getLeaveStatus()==LeaveStatus.REJECTED) {
			if(!(leaveApp.getManagerComment()!=null) || leaveApp.getManagerComment()=="") {
				model.addAttribute("error", "ERROR: Please add comment!");
				return "forward:/leave/viewdetails/"+leaveApp.getId();
			}
		}
		
		lservice.updateLeaveStatus(leaveApp.getId(), leaveApp.getLeaveStatus(), leaveApp.getManagerComment());
		return "forward:/leave/viewallpending";
	}
	
	@RequestMapping(value = "/delete/{id}")
	 public String deleteLeaveapplication(@PathVariable("id") Integer id) {
	  lservice.deleteLeaveApplication(lservice.findApplicationById(id));
	  return "forward:/leave/viewhistory";
	 }
	
	  @RequestMapping(value="/cancel/{id}")
	  public String cancel(@PathVariable("id") Integer id,Model model)
	  {
	    LeaveApplication leaveapplication= lservice.findApplicationById(id);
	    lservice.cancelLeaveApplication(leaveapplication);
	    return "forward:/leave/viewhistory";
	  }
}
