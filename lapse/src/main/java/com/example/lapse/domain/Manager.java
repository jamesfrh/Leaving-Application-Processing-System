package com.example.lapse.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Manager extends User {

	private int annualLeaveEntitlement;
	private int medicalLeaveEntitment;
	private int compensationLeaveEntitlment;

	@OneToMany(mappedBy = "manager")
	private List<Staff> staffList;


	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitment,
			int compensationLeaveEntitlment) {
		super(name, password, email);
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitment = medicalLeaveEntitment;
		this.compensationLeaveEntitlment = compensationLeaveEntitlment;
		

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


	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
}
