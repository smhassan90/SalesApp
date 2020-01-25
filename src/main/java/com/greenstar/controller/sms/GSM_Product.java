package com.greenstar.controller.sms;

public class GSM_Product {
	
	private String SKU;
	private String Name;
	private String Type;
	private String Shortcode;
	private String status;
	
	
	
	/**
	 * 
	 */
	public GSM_Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 * @param sKU
	 * @param name
	 * @param type
	 * @param shortcode
	 * @param status
	 */
	public GSM_Product(String sKU, String name, String type, String shortcode,
			String status) {
		super();
		SKU = sKU;
		Name = name;
		Type = type;
		Shortcode = shortcode;
		this.status = status;
	}



	/**
	 * 
	 * @return
	 */
	public String getSKU() {
		return SKU;
	}



	/**
	 * 
	 * @param sKU
	 */
	public void setSKU(String sKU) {
		SKU = sKU;
	}



	/**
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}



	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}



	/**
	 * 
	 * @return
	 */
	public String getType() {
		return Type;
	}



	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		Type = type;
	}



	/**
	 * 
	 * @return
	 */
	public String getShortcode() {
		return Shortcode;
	}



	/**
	 * 
	 * @param shortcode
	 */
	public void setShortcode(String shortcode) {
		Shortcode = shortcode;
	}



	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * 
	 */
	@Override
	public String toString() {
		return "GSM_Proudct [SKU=" + SKU + ", Name=" + Name + ", Type=" + Type
				+ ", Shortcode=" + Shortcode + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
	

}
