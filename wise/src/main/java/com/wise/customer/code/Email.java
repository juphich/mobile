package com.wise.customer.code;

import com.wise.customer.privacy.PrivacyType;

public enum Email implements ContactKind {

	gmail("", "gmail.com"), naver("", "naver.com"), daum("", "hanmail.net");
	
	private String name;
	private String address;
	
	private Email(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String getName() {
		return name;
	}

	public String address(String id) {
		if (id == null || id.isEmpty()) {
			return id;
		} else {
			return id + "@" + address;
		}
	}

	@Override
	public PrivacyType type() {
		return PrivacyType.email;
	}
}
