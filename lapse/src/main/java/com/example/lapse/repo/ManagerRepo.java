package com.example.lapse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lapse.domain.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

	
}
