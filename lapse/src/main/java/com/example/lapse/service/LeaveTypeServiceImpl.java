package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lapse.repo.LeaveTypeRepo;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	LeaveTypeRepo ltRepo;

	@Override
	public ArrayList<String> findAllLeaveTypeNamesExCL() {
		return (ArrayList<String>) ltRepo.findAllLeaveTypeNamesExCL();
	}
}
