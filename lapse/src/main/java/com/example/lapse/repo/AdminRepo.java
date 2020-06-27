package com.example.lapse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
	

}
