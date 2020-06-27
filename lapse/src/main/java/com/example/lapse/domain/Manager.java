package com.example.lapse.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Manager extends Staff {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
	private Collection<Staff> staffList;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitment,
			int compensationLeaveEntitlment) {
		super(name, password, email, annualLeaveEntitlement, medicalLeaveEntitment, compensationLeaveEntitlment);

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


	public Collection<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
}
