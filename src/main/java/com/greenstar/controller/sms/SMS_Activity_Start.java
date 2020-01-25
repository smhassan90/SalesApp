package com.greenstar.controller.sms;

import java.util.Date;

public class SMS_Activity_Start {
	
	private int ID;
	private String staffCode;
	private String activityDetail;
	private Date startDate;
	private String status;
	private int rcID;
	
	
	public SMS_Activity_Start() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SMS_Activity_Start(int iD, String staffCode, String activityDetail,
			Date startDate, String status, int rcID) {
		super();
		ID = iD;
		this.staffCode = staffCode;
		this.activityDetail = activityDetail;
		this.startDate = startDate;
		this.status = status;
		this.rcID = rcID;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getStaffCode() {
		return staffCode;
	}


	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}


	public String getActivityDetail() {
		return activityDetail;
	}


	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getRcID() {
		return rcID;
	}


	public void setRcID(int rcID) {
		this.rcID = rcID;
	}


	@Override
	public String toString() {
		return "SMS_Activity_Start [ID=" + ID + ", staffCode=" + staffCode
				+ ", activityDetail=" + activityDetail + ", startDate="
				+ startDate + ", status=" + status + ", rcID=" + rcID + "]";
	}
	
	
	

}
