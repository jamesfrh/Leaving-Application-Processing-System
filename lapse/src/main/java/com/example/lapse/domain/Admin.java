package com.example.lapse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Admin extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	public Admin(String name, String password, String email) {
		super(name, password, email);
		// TODO Auto-generated constructor stub
	}


	public void promotestaff(Staff staff) {
		
		Manager newManager = new Manager();
	
		newManager.setEmail(staff.getEmail());
		newManager.setName(staff.getName());
		newManager.setPassword(staff.getPassword());
		newManager.setAnnualLeaveEntitlement(staff.getAnnualLeaveEntitlement());
		newManager.setMedicalLeaveEntitment(staff.getMedicalLeaveEntitment());
		newManager.setCompensationLeaveEntitlment(staff.getCompensationLeaveEntitlment());
	}
	

}
