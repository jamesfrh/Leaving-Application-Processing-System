package com.example.lapse.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.domain.Staff;
import com.example.lapse.enums.LeaveStatus;
import com.example.lapse.repo.LeaveApplicationRepo;
import com.example.lapse.repo.StaffRepo;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService{
	
	@Autowired
	LeaveApplicationRepo laRepo;
	
	@Autowired
	StaffRepo staffRepo;
	
	@Transactional 
	public void addLeaveApplication(LeaveApplication leaveApplication) {
		laRepo.save(leaveApplication);
	}

	@Transactional
	public void cancelLeaveApplication(LeaveApplication leaveApplication) {
		leaveApplication.setLeaveStatus(LeaveStatus.CANCELLED);
		laRepo.save(leaveApplication);
	}
	
	@Transactional
	public ArrayList<LeaveApplication> listAllLeaveApplications() {
		ArrayList<LeaveApplication> leaveApplicationList = (ArrayList<LeaveApplication>) laRepo.findAll();
		return leaveApplicationList;
	}

	@Override
	public LeaveApplication findApplicationById(Integer id) {
		
		return laRepo.findById(id).get();
	}

	@Override
	public ArrayList<LeaveApplication> findApplicationByStaffId(Integer id) {
		return (ArrayList<LeaveApplication>) laRepo.findByStaffId(id);
	}

	
	public float getSumOfLeavesAppliedByStaff(Integer staffId, Integer leaveTypeId) {
	return laRepo.getSumOfLeavesAppliedByStaff(staffId, leaveTypeId);
	}
	
	@Override	
    public List<LeaveApplication> findpendingleaveapproval(Integer mgrid){
	 	 
		 List<Staff>listofstaff=staffRepo.findByManagerId(mgrid);
		 	 
		 List<LeaveApplication> AllEmployeeLeave= new ArrayList();
		 for (Iterator iterator = listofstaff.iterator(); iterator.hasNext();) {
				Staff staff = (Staff) iterator.next();
				AllEmployeeLeave.addAll(laRepo.findByStaffId(staff.getId()));					
		}
		 
		 List<LeaveApplication> PendingLeave= new ArrayList();
			for (Iterator iterator = AllEmployeeLeave.iterator(); iterator.hasNext();) {
				LeaveApplication leave = (LeaveApplication) iterator.next();
				if(leave.getLeaveStatus()==LeaveStatus.APPLIED) {
					PendingLeave.add(leave);
				}
			}
		 return PendingLeave;
	 }
	
	
	 @Override
	 public void approveleaveapplication(Integer id) {
			LeaveApplication leaveapplication= laRepo.findById(id).get();
			leaveapplication.setLeaveStatus(LeaveStatus.APPROVED);
			laRepo.save(leaveapplication);
		 }
	 
	 @Override
     public void rejectleaveapplication(Integer id) {
			 LeaveApplication leaveapplication= laRepo.findById(id).get();
			 leaveapplication.setLeaveStatus(LeaveStatus.REJECTED); 
				laRepo.save(leaveapplication);
		 }
}
