package com.wise.customer.code;

import com.wise.customer.privacy.PrivacyType;

public enum Phone implements ContactKind {
	home("집"), mobile("휴대폰"), work("직장");

	private String name;
	
	private Phone(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public PrivacyType type() {
		return PrivacyType.phone;
	}

}
