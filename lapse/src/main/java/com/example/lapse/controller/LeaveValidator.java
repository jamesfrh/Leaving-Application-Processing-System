package com.example.lapse.controller;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.service.LeaveApplicationService;
import com.example.lapse.service.LeaveApplicationServiceImpl;
import com.example.lapse.utils.DateUtils;

//@SessionAttributes
@Component
public class LeaveValidator implements Validator {
	
	  @Autowired
	  private LeaveApplicationService laservice;
	  
	  @Autowired
	  public void setLeaveApplicationService(LeaveApplicationServiceImpl laserviceImpl) {
	    this.laservice = laserviceImpl;
	  }

	  @Override
	  public boolean supports(Class<?> clazz) {
	    return LeaveApplication.class.equals(clazz);
	  }
	  

	  @Override
	  public void validate(Object target, Errors errors) {
	    LeaveApplication application = (LeaveApplication) target;
	    
//		Must select a type of leave (not working yet)
	    if (application.getLeaveType().getLeaveType() == null) {
	    	errors.rejectValue("leaveType", "leave.type.empty");
	    }

////		Dates cannot be empty
//	    if (application.getStartDate() == null || application.getEndDate() == null) {
//	    	errors.rejectValue("startDate", "leave.date.empty");
//	    }

//		Need dates to begin this validation	    
	    if (application.getStartDate() != null && application.getEndDate() != null) {
	    	Calendar calStart = DateUtils.dateToCalendar(application.getStartDate());
	    	Calendar calEnd = DateUtils.dateToCalendar(application.getEndDate());

	    	//	    Applied startdate <= endDate (min 1 day)
	    	boolean status = DateUtils.startDateBeforeEndDate(calStart, calEnd);
	    	if (status == false) { 
	    		errors.rejectValue("startDate", "leave.date.conflict");
	    	}

	    	//	    Retrieve number of days in between start and end date (inclusive of start and end)
	    	float daysBetween = ChronoUnit.DAYS.between(calStart.toInstant(), calEnd.toInstant()) + 1;
	    	if(daysBetween <= 14) {
	    		daysBetween = DateUtils.removeWeekends(calStart, calEnd);
	    	}

	    	//		Current application period not overlapping with old applications
	    	List<LeaveApplication> lalist = laservice.findApplicationByStaffId((Integer) application.getStaff().getId());

	    	for (Iterator<LeaveApplication> iterator = lalist.iterator(); iterator.hasNext();) {
	    		LeaveApplication application2 = (LeaveApplication) iterator.next();
	    		if (application2.getEndDate().after(application.getStartDate())) {
	    			errors.rejectValue("startDate", "leave.date.repeat");
	    		}
	    	}


	    	//	 	Check for sufficient leave balance 
	    	if ((application.getLeaveType().getEntitlement() - laservice.getSumOfLeavesAppliedByStaff(application.getStaff().getId(), application.getLeaveType().getId()) - daysBetween) < 0) {
	    		errors.rejectValue("endDate", "leave.balance");
	    	};  

	    	//		Overseas Trip true, contact details required	    
	    	if (application.isOverseasTrip() && application.getContactDetails().isEmpty()) {
	    		errors.rejectValue("contactDetails", "leave.contact.empty");
	    	}
	    }
	  }	  
}
