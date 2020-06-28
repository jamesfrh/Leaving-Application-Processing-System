package com.example.lapse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lapse.domain.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

	  List<Manager> findByEmail(String email);

	  
}
