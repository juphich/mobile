package com.wise.newcustomer.code;

import com.wise.newcustomer.privacy.PrivacyType;

public enum Sns implements ContactKind {
	twitter("트위터"), facebook("페이스북"), me2day("미투데이");

	private String name;
	
	private Sns(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public PrivacyType type() {
		return PrivacyType.sns;
	}
}
