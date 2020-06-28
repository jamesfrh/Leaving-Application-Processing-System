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
	
	public Manager(String name, String password, String email, int annualLeaveEntitlement, int medicalLeaveEntitlement,
			int compensationLeaveEntitlement) {
		super(name, password, email, annualLeaveEntitlement, medicalLeaveEntitlement, compensationLeaveEntitlement);
		this.setRole("Manager");
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

	public void setCompensationLeaveEntitlement(int compensationLeaveEntitlement) {
		this.compensationLeaveEntitlement = compensationLeaveEntitlement;
	}


	public Collection<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	@Override
	public String toString() {
		return "Manager [name = " + super.getName() + " ID = " + super.getId() +  " Password = " + super.getPassword() +" annualLeaveEntitlement=" + annualLeaveEntitlement + ", medicalLeaveEntitlement="
				+ medicalLeaveEntitlement + ", compensationLeaveEntitlement=" + compensationLeaveEntitlement + "]";
	}
	
}
