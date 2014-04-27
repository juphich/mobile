package com.wise.customer.privacy;

import com.wise.customer.code.Annual;

public class Anniversary implements Privacy {

	private Annual annual;
	
	private String date;
	
	@Override
	public PrivacyType type() {
		return PrivacyType.anniversary;
	}

	public Anniversary() { }
	
	public Anniversary(Annual annual, String date) {
		this.annual = annual;
		this.date = date;
	}

	public Annual getAnnual() {
		return annual;
	}

	public String getDate() {
		return date;
	}

	public void setAnnual(Annual annual) {
		this.annual = annual;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
