package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.LeaveType;

@Service
public interface LeaveTypeService {
	public ArrayList<String> findAllLeaveTypeNamesExCL();
	public LeaveType findLeaveTypeByLeaveType(String name);

}
