package com.example.lapse.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class LeaveType {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String leaveType;
	private float noOfDays;
	
	@ManyToMany (mappedBy="leaveTypes")
	private List<Staff> staffList;
	
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveType(String leaveType, float noOfDays) {
		super();
		this.leaveType = leaveType;
		this.noOfDays = noOfDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public float getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(float noOfDays) {
		this.noOfDays = noOfDays;
	}

	@Override
	public String toString() {
		return "LeaveType [id=" + id + ", leaveType=" + leaveType + ", noOfDays=" + noOfDays + "]";
	}
	
	
}