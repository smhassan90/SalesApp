package com.greenstar.controller.sms;

public class GSM_FFM_Activity {
	
	private int activityID;
	private int activityCode;
	private String activityName;
	private String activityType;
	
	public GSM_FFM_Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GSM_FFM_Activity(int activityID, int activityCode,
			String activityName, String activityType) {
		super();
		this.activityID = activityID;
		this.activityCode = activityCode;
		this.activityName = activityName;
		this.activityType = activityType;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public int getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Override
	public String toString() {
		return "GSM_FFM_Activity [activityID=" + activityID + ", activityCode="
				+ activityCode + ", activityName=" + activityName
				+ ", activityType=" + activityType + "]";
	}
	
	
	
	

}
