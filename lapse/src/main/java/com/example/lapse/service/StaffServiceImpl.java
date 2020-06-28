package com.example.lapse.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lapse.domain.Manager;
import com.example.lapse.domain.Staff;
import com.example.lapse.repo.ManagerRepo;
import com.example.lapse.repo.StaffRepo;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffRepo srepo;
	@Autowired
	ManagerRepo mrepo;

	@Override
	public ArrayList<Staff> findAll() {
		ArrayList<Staff> staffList = (ArrayList<Staff>) srepo.findAll();
	    return staffList;
	}

	@Override
	public boolean saveStaff(Staff staff) {
		if(srepo.save(staff)!=null) 
		    return true; 
		else 
		    return false;
	}
	
	@Override
    public void deleteStaff(Staff staff) {
		srepo.delete(staff);
	}
	
	@Override
	public ArrayList<String> findAllStaffNames() {
		return srepo.findAllStaffNames();
	}

	@Override
	public Staff findStaffByName(String name) {
		ArrayList<Staff> list = (ArrayList<Staff>) srepo.findByName(name);
		return list.get(0);
	}

	@Override
	public Staff findStafftById(Integer id) {
		return srepo.findById(id).get();
	}

	@Override
	public int promoteStaff(Integer id) {
		return srepo.promoteUser(id);
	}

	@Override
	public int demoteStaff(Integer id) {
		Manager currentStaff = mrepo.getOne(id);
		if (currentStaff.getStaffList().size() > 0 ) {
			return 0;
		}
		return srepo.demoteUser(id);
	}

	@Override
	public Staff findStaffByEmail(String email) {
		return srepo.findByEmail(email);
	}

}
