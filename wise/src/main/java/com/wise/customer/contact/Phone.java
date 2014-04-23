package com.wise.customer.contact;

public class Phone implements Contact {

	private String phoneKind;
	private String phoneNumber;
	
	public Phone(String kind, String number) {
		this.phoneKind = kind;
		this.phoneNumber = number;
	}
	
	public String getPhoneKind() {
		return phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public ContactType getType() {
		return ContactType.Phone;
	}

	@Override
	public String getName() {
		return null;
	}

}
