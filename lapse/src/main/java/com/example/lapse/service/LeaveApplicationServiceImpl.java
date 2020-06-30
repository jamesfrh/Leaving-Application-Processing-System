package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.lapse.domain.LeaveApplication;
import com.example.lapse.repo.LeaveApplicationRepo;

public class LeaveApplicationServiceImpl implements LeaveApplicationService{
	
	@Autowired
	LeaveApplicationRepo laRepo;

	@Override
	public ArrayList<LeaveApplication> findAll() {
		ArrayList<LeaveApplication> appList = (ArrayList<LeaveApplication>) laRepo.findAll();
		return appList;
	}

	@Override
	public LeaveApplication findApplicationById(Integer id) {
		
		return laRepo.findById(id).get();
	}

	@Override
	public LeaveApplication findApplicationByStaffId(Integer id) {
		
		return laRepo.findByStaffId(id);
	}

}
