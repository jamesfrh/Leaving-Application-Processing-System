package com.example.lapse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.enums.LeaveStatus;


@Service
public interface LeaveApplicationService {

	public LeaveApplication findApplicationById(Integer id);
	public ArrayList<LeaveApplication> findApplicationByStaffId(Integer id);
	
	
	public void addLeaveApplication(LeaveApplication leaveApplication);
	public void cancelLeaveApplication(LeaveApplication leaveApplication);
	public ArrayList<LeaveApplication> listAllLeaveApplications();
	
	public float getSumOfLeavesAppliedByStaff(Integer staffId, Integer leaveTypeId);
	
	public List<LeaveApplication> findpendingleaveapproval(Integer mgrid);
	public void approveleaveapplication(Integer id);
	public void rejectleaveapplication(Integer id);
	public void updateLeaveStatus(int LeaveId, LeaveStatus status, String mComment);
}
