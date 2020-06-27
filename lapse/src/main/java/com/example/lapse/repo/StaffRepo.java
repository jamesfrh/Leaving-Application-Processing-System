package com.example.lapse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

}
