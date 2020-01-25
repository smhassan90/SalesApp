package com.greenstar.controller.sms;

import java.util.Date;

public class SMS_Formatted {
	
	private int ID;
	private String staffCode;
	private int activityID;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String status;
	private Date smsDate;
	private int rcID;
	private String staffDesigCode;
	private String rcRemarks;
	private int startID;
	private Date startDate;
	
	public SMS_Formatted() {
		super();
		// TODO Auto-generated constructor stub
		ID = -1;
		
		this.setStaffCode("");
		
		this.activityID = -1;
		
		this.setParam1("");
		this.setParam1("");
		this.setParam1("");
		this.setParam1("");
		this.setParam1("");
		
		this.setStatus("");
		
		this.smsDate = null;
		this.rcID = -1;
		
		this.setStaffDesigCode("");
		
		this.setRcRemarks("");
		
		this.startID = -1;
		
		this.startDate = null;
		
	}

	public SMS_Formatted(int iD, String staffCode, int activityID,
			String param1, String param2, String param3, String param4,
			String param5, String status, Date smsDate, int rcID,
			String staffDesigCode, String rcRemarks, int startID, Date startDate) {
		super();
		ID = iD;
		
		this.setStaffCode(staffCode);
		
		this.activityID = activityID;
		
		this.setParam1(param1);
		this.setParam1(param2);
		this.setParam1(param3);
		this.setParam1(param4);
		this.setParam1(param5);
		
		this.setStatus(status);
		
		this.smsDate = smsDate;
		this.rcID = rcID;
		
		this.setStaffDesigCode(staffDesigCode);
		
		this.setRcRemarks(rcRemarks);
		
		this.startID = startID;
		
		this.startDate = startDate;
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
		
		this.staffCode = (staffCode != null) ? staffCode : "";
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = (param1 != null) ? param1 : "";
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = (param2 != null) ? param2 : "";
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = (param3 != null) ? param3 : "";
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = (param4 != null) ? param4 : "";
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = (param5 != null) ? param5 : "";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = (status != null) ? status : "Error";
	}

	public Date getSmsDate() {
		return smsDate;
	}

	public void setSmsDate(Date smsDate) {
		this.smsDate = smsDate;
	}

	public int getRcID() {
		return rcID;
	}

	public void setRcID(int rcID) {
		this.rcID = rcID;
	}

	public String getStaffDesigCode() {
		return staffDesigCode;
	}

	public void setStaffDesigCode(String staffDesigCode) {
		this.staffDesigCode = (staffDesigCode != null) ? staffDesigCode : "";
	}
	
	public String getRcRemarks() {
		return rcRemarks;
	}

	public void setRcRemarks(String rcRemarks) {
		this.rcRemarks = (rcRemarks != null) ? rcRemarks : "";
	}
	
	public int getStartID() {
		return startID;
	}

	public void setStartID(int startID) {
		this.startID = startID;
	}

	public Date getStartDate() {
		
		// If user has not reported the start of activity then there will be no start date and sms date will be treated start date
		if (startDate == null) startDate = smsDate;
		
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "SMS_Formatted [ID=" + ID + ", staffCode=" + staffCode
				+ ", activityID=" + activityID + ", param1=" + param1
				+ ", param2=" + param2 + ", param3=" + param3 + ", param4="
				+ param4 + ", param5=" + param5 + ", status=" + status
				+ ", smsDate=" + smsDate + ", rcID=" + rcID
				+ ", staffDesigCode=" + staffDesigCode + ", rcRemarks="
				+ rcRemarks + ", startID=" + startID + ", startDate="
				+ startDate + "]";
	}

		
	
}
