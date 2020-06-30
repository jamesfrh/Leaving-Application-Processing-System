package com.example.lapse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.lapse.domain.Staff;
import com.example.lapse.repo.StaffRepo;
import com.example.lapse.service.StaffService;
import com.example.lapse.service.StaffServiceImpl;

public class StaffValidator implements Validator {

	@Autowired
	private StaffService staffservice;
	
	@Autowired
	StaffRepo staffRepo;
	
	@Autowired
	public void setStaffService(StaffServiceImpl sserviceImpl) {
		this.staffservice = sserviceImpl;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Staff staff = (Staff) target;
		if(staffservice.findStaffByEmail(staff.getEmail()) == null) {
			errors.rejectValue("email", "Invalid Email");

		}
		/*Staff staffDB = staffRepo.findByEmail(staff.getEmail()).get(0);
		if(staffDB == null) {
			errors.rejectValue("email", "Invalid Email");

		}*/
		
		/*if(staffDB.getPassword() != staff.getPassword()) {
			errors.rejectValue("password", "Wrong Password");

		}*/

		if (staffservice.findStaffByEmail(staff.getEmail()).getPassword() != staff.getPassword()) {
			errors.rejectValue("password", "Wrong Password");
		}

	}

}
