package com.example.lapse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Staff extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int annualLeaveEntitlement;
	private int medicalLeaveEntitment;
	private int compensationLeaveEntitlment;
	
	@ManyToOne
	private Manager manager;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitment,
			int compensationLeaveEntitlment, Manager manager) {
		super(name, password, email);
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitment = medicalLeaveEntitment;
		this.compensationLeaveEntitlment = compensationLeaveEntitlment;
		this.manager = manager;
	}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnnualLeaveEntitlement() {
		return annualLeaveEntitlement;
	}
	public void setAnnualLeaveEntitlement(int annualLeaveEntitlement) {
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}
	public int getMedicalLeaveEntitment() {
		return medicalLeaveEntitment;
	}
	public void setMedicalLeaveEntitment(int medicalLeaveEntitment) {
		this.medicalLeaveEntitment = medicalLeaveEntitment;
	}
	public int getCompensationLeaveEntitlment() {
		return compensationLeaveEntitlment;
	}
	public void setCompensationLeaveEntitlment(int compensationLeaveEntitlment) {
		this.compensationLeaveEntitlment = compensationLeaveEntitlment;
	}
	

	
}
	
	

