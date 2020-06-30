package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.enums.LeaveStatus;
import com.example.lapse.repo.LeaveApplicationRepo;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService{
	
	@Autowired
	LeaveApplicationRepo lrepo;
	
	@Transactional 
	public void addLeaveApplication(LeaveApplication leaveApplication) {
		lrepo.save(leaveApplication);
	}

	@Transactional
	public void cancelLeaveApplication(LeaveApplication leaveApplication) {
		leaveApplication.setLeaveStatus(LeaveStatus.CANCELLED);
		lrepo.save(leaveApplication);
	}
	
	@Transactional
	public ArrayList<LeaveApplication> listAllLeaveApplications() {
		ArrayList<LeaveApplication> leaveApplicationList = (ArrayList<LeaveApplication>) lrepo.findAll();
		return leaveApplicationList;
	}

	@Override
	public LeaveApplication findApplicationById(Integer id) {
		
		return lrepo.findById(id).get();
	}

	@Override
	public ArrayList<LeaveApplication> findApplicationByStaffId(Integer id) {
		return (ArrayList<LeaveApplication>) lrepo.findByStaffId(id);
	}

	@Override
	public float getSumOfLeavesAppliedByStaff(Integer staffId, Integer leaveTypeId) {
	return lrepo.getSumOfLeavesAppliedByStaff(staffId, leaveTypeId);
	}
}
