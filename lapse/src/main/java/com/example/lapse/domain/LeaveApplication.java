package com.example.lapse.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.lapse.enums.LeaveStatus;
import com.example.lapse.enums.TimeOfDay;

@Entity
public class LeaveApplication {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private TimeOfDay startTimeOfDay;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	private TimeOfDay endTimeOfDay;
	
	private float noOfDays;
	
	@ManyToOne
	private LeaveType leaveType;
	
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;
	
	private String comments;
	
	@ManyToOne
	private Staff staff;

	public LeaveApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveApplication(Date startDate, Date endDate, LeaveType leaveType,
			LeaveStatus leaveStatus, String comments, Staff staff) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.comments = comments;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public TimeOfDay getStartTimeOfDay() {
		return startTimeOfDay;
	}

	public void setStartTimeOfDay(TimeOfDay startTimeOfDay) {
		this.startTimeOfDay = startTimeOfDay;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public TimeOfDay getEndTimeOfDay() {
		return endTimeOfDay;
	}

	public void setEndTimeOfDay(TimeOfDay endTimeOfDay) {
		this.endTimeOfDay = endTimeOfDay;
	}

	public float getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
		

}
