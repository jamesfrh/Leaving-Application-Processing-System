package com.example.lapse.service;

import java.util.ArrayList;

import com.example.lapse.domain.Manager;


public interface ManagerService {
	
		public ArrayList<String> findAllManagerNames();
		
		public Manager findManagerByName(String name);
}
