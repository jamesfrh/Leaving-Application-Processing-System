package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.LeaveApplication;


@Service
public interface LeaveApplicationService {
	public ArrayList<LeaveApplication> findAll();
	public LeaveApplication findApplicationById(Integer id);
	public LeaveApplication findApplicationByStaffId(Integer id);
}
