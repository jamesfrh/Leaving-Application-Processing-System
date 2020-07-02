package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.LeaveType;

@Service
public interface LeaveTypeService {
	
	public ArrayList<LeaveType> findAll();
	public boolean saveLeaveType(LeaveType leavetype);
	public void deleteLeaveType(LeaveType leavetype);
	public LeaveType findLeaveTypeById(Integer id);
	
	public ArrayList<String> findAllLeaveTypeNamesExCL();
	public LeaveType findLeaveTypeByLeaveType(String name);

}
