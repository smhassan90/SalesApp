package com.greenstar.controller.sms;

import java.util.Date;

public class GSM_Staff {
	
	private String staffCode;
	private String staffName;
	private String staffMSISDN;
	private String designationCode;
	private String designation;
	private Date effDate;
	
	
	public GSM_Staff() {
		super();
		// TODO Auto-generated constructor stub
		this.staffCode = "";
		this.staffName = "";
		this.staffMSISDN = "";
		this.designationCode = "";
		this.designation = "";
		this.effDate = new Date();
	}


	public GSM_Staff(String staffCode, String staffName, String staffMSISDN,
			String designationCode, String designation, Date effDate) {
		super();
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.staffMSISDN = staffMSISDN;
		this.designationCode = designationCode;
		this.designation = designation;
		this.effDate = effDate;
	}


	public String getStaffCode() {
		return staffCode;
	}


	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getStaffMSISDN() {
		return staffMSISDN;
	}


	public void setStaffMSISDN(String staffMSISDN) {
		this.staffMSISDN = staffMSISDN;
	}


	public String getDesignationCode() {
		if (designationCode != null)
		return designationCode;
		else
			return "NA";
	}


	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Date getEffDate() {
		return effDate;
	}


	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}


	@Override
	public String toString() {
		return "GSM_Staff [staffCode=" + staffCode + ", staffName=" + staffName
				+ ", staffMSISDN=" + staffMSISDN + ", designationCode="
				+ designationCode + ", designation=" + designation
				+ ", effDate=" + effDate + "]";
	}


	
	

	
}
