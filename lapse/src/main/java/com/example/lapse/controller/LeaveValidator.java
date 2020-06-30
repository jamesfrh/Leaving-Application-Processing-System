package com.example.lapse.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.repo.LeaveApplicationRepo;
import com.example.lapse.service.LeaveApplicationService;
import com.example.lapse.service.LeaveApplicationServiceImpl;

public class LeaveValidator implements Validator {
	
	  @Autowired
	  private LeaveApplicationService laservice;
	  
	  @Autowired
	  public void setLeaveApplicationService(LeaveApplicationServiceImpl laserviceImpl) {
	    this.laservice = laserviceImpl;
	  }
	  @Autowired
	  LeaveApplicationRepo laRepo;

	  @Override
	  public boolean supports(Class<?> clazz) {
	    return LeaveApplication.class.equals(clazz);
	  }
	  

	  @Override
	  public void validate(Object target, Errors errors) {
	    LeaveApplication application = (LeaveApplication) target;
	    
	    boolean status = startDateBeforeEndDate(application.getStartDate(), application.getEndDate());
	    
	    
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
	  }
	  
		public boolean startDateBeforeEndDate(Date start, Date end) {
            //startCal.getTimeInMillis() > endCal.getTimeInMillis()

			return true;
		};
	  
}
