package com.wise.newcustomer.privacy;

public class Occupation implements Privacy {

	private String company;
	private String duty;
	
	public Occupation() {}
	
	public Occupation(String company, String duty) {
		this.company = company;
		this.duty = duty;
	}
	
	@Override
	public PrivacyType type() {
		return PrivacyType.occupation;
	}

	public String getCompany() {
		return company;
	}

	public String getDuty() {
		return duty;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
}
