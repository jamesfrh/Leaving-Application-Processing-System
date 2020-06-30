package com.example.lapse.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.LeaveApplication;


@Service
public interface LeaveApplicationService {

	public LeaveApplication findApplicationById(Integer id);
	public ArrayList<LeaveApplication> findApplicationByStaffId(Integer id);
	
	
	public void addLeaveApplication(LeaveApplication leaveApplication);
	public void cancelLeaveApplication(LeaveApplication leaveApplication);
	public ArrayList<LeaveApplication> listAllLeaveApplications();
	
}
