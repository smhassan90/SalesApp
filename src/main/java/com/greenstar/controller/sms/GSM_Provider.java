package com.greenstar.controller.sms;

public class GSM_Provider {

	
	private String providerCode;
	private String providerName;
	private String providerAlias;
	
	public GSM_Provider() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GSM_Provider(String providerCode, String proivderName,
			String providerAlias) {
		super();
		this.providerCode = providerCode;
		this.providerName = proivderName;
		this.providerAlias = providerAlias;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderAlias() {
		return providerAlias;
	}

	public void setProviderAlias(String providerAlias) {
		this.providerAlias = providerAlias;
	}

	@Override
	public String toString() {
		return "GSM_Provider [providerCode=" + providerCode + ", providerName="
				+ providerName + ", providerAlias=" + providerAlias + "]";
	}

	
	
	
	
}
