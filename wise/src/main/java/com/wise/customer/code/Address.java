package com.wise.customer.code;

import com.wise.customer.privacy.PrivacyType;

public enum Address implements ContactKind {
	home("집"), work("직장");

	private String name;
	
	private Address(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public PrivacyType type() {
		return PrivacyType.address;
	}
}
