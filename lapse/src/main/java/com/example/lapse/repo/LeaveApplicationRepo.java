package com.example.lapse.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.LeaveApplication;

public interface LeaveApplicationRepo extends JpaRepository<LeaveApplication, Integer> {
	
	ArrayList<LeaveApplication> findByStaffId(Integer id);

}
