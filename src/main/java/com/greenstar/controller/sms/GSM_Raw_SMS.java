package com.greenstar.controller.sms;

import java.util.Date;

public class GSM_Raw_SMS {

	int rc_id;
	String MSISDN;
	String smsText;
	Date smsDate;
	String staffCode;
	String status;
	String rc_remarks;
	
	public GSM_Raw_SMS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GSM_Raw_SMS(int rc_id, String mSISDN, String smsText, Date smsDate,
			String staffCode, String status, String rc_remarks) {
		super();
		this.rc_id = rc_id;
		this.MSISDN = mSISDN;
		this.smsText = smsText;
		this.smsDate = smsDate;
		this.staffCode = staffCode;
		this.status = status;
		this.rc_remarks = rc_remarks;
	}

	public int getRc_id() {
		return rc_id;
	}

	public void setRc_id(int rc_id) {
		this.rc_id = rc_id;
	}

	public String getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	public Date getSmsDate() {
		return smsDate;
	}

	public void setSmsDate(Date smsDate) {
		this.smsDate = smsDate;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRc_remarks() {
		return rc_remarks;
	}

	public void setRc_remarks(String rc_remarks) {
		this.rc_remarks = rc_remarks;
	}

	@Override
	public String toString() {
		return "GSM_Raw_SMS [rc_id=" + rc_id + ", MSISDN=" + MSISDN
				+ ", smsText=" + smsText + ", smsDate=" + smsDate
				+ ", staffCode=" + staffCode + ", status=" + status
				+ ", rc_remarks=" + rc_remarks + "]";
	}
	
	
	
}
