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
	    Calendar calStart = DateUtils.dateToCalendar(application.getStartDate());
	    Calendar calEnd = DateUtils.dateToCalendar(application.getEndDate());

//	    1. Applied startdate <= endDate (min 1 day)
	    boolean status = DateUtils.startDateBeforeEndDate(calStart, calEnd);
	    if (status == false) { 
			errors.rejectValue("dates", "Start Date is after End Date");
	    }

	    
//	    2. (Jaye) 
	    //Check if end date of previous transaction is not after current startdate ("Start date can not be in between other application date"
//

	    
	    
//	    3.Retrieve number of days in between start and end date (inclusive of start and end)
		float daysBetween = ChronoUnit.DAYS.between(calStart.toInstant(), calEnd.toInstant()) + 1;
		if(daysBetween <= 14) {
			daysBetween = DateUtils.removeWeekends(calStart, calEnd);
		}
		
	    
	    //Jayes part
	    List<LeaveApplication> lalist = laservice.findApplicationByStaffId(application.getId());
	      
	    if(application.getEndDate().before(application.getStartDate()))
	    {
	      errors.rejectValue("endDate", "Start date can not be later than end date");
	    }
	    
	    for (Iterator<LeaveApplication> iterator = lalist.iterator(); iterator.hasNext();) {
	        LeaveApplication application2 = (LeaveApplication) iterator.next();
	      if (application2.getEndDate().after(application.getStartDate())) {
	          errors.rejectValue("startDate", "Start date can not inbetween other application date");
	        }
	    }
	    
	    
	    //5. Check balance 
	    if ((application.getLeaveType().getEntitlement() - laservice.getSumOfLeavesAppliedByStaff(application.getStaff().getId(), application.getLeaveType().getId()) - daysBetween) < 0) {
	          errors.rejectValue("endDate", "Not enough leave balance "+application.getLeaveType().getLeaveType());
	    };    
	    
	  }	  
}
