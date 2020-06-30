package com.example.lapse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.LeaveType;

public interface LeaveTypeRepo extends JpaRepository<LeaveType, Integer> {

}
