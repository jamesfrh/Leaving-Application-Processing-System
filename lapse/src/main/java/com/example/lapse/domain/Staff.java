package com.example.lapse.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Staff extends User{
	
	protected int annualLeaveEntitlement;
	protected int medicalLeaveEntitlement;
	protected int compensationLeaveEntitlement;
	protected String role = "Staff";
	
	@ManyToOne
	private Manager manager;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitlement,
			int compensationLeaveEntitlement, Manager manager) {
		super(name, password, email);
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
		this.compensationLeaveEntitlement = compensationLeaveEntitlement;
		this.manager = manager;
	}

	public Staff(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitlement, int compensationLeaveEntitlement) {
		super(name, password, email);
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
		this.compensationLeaveEntitlement = compensationLeaveEntitlement;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public int getAnnualLeaveEntitlement() {
		return annualLeaveEntitlement;
	}
	public void setAnnualLeaveEntitlement(int annualLeaveEntitlement) {
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}
	public int getMedicalLeaveEntitlement() {
		return medicalLeaveEntitlement;
	}
	public void setMedicalLeaveEntitlement(int medicalLeaveEntitlement) {
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
	}
	public int getCompensationLeaveEntitlement() {
		return compensationLeaveEntitlement;
	}
	public void setCompensationLeaveEntitlment(int compensationLeaveEntitlement) {
		this.compensationLeaveEntitlement = compensationLeaveEntitlement;
	}
	
}
	
	

