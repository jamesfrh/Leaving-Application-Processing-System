package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface LeaveTypeService {
	public ArrayList<String> findAllLeaveTypeNamesExCL();

}
