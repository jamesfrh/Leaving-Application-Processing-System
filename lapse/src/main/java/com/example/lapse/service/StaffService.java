package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.lapse.domain.Staff;

@Service
public interface StaffService {
	public ArrayList<Staff> findAll();
	public boolean saveStaff(Staff staff);
	public void deleteStaff(Staff staff);
	
	public ArrayList<String> findAllStaffNames();
	public Staff findStafftById(Integer id);
	public Staff findStaffByName(String name);
	
	public int promoteStaff(Integer id);
	public int demoteStaff(Integer id);
}
