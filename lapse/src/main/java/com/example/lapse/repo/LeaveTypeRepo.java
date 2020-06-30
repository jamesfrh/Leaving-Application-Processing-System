package com.example.lapse.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lapse.domain.LeaveType;

public interface LeaveTypeRepo extends JpaRepository<LeaveType, Integer> {
	
	@Query("SELECT t.leaveType from LeaveType AS t where t.leaveType != 'Compensation Leave'")
	ArrayList<String> findAllLeaveTypeNamesExCL();

}
