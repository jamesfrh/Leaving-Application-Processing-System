package com.example.lapse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.LeaveApplication;

public interface LeaveApplicationRepo extends JpaRepository<LeaveApplication, Integer> {
	
	LeaveApplication findByStaffId(Integer id);

}
