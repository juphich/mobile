package com.wise.customer.contact;

public class Email implements Contact {

	private String mailAddress;
	
	public Email(String address) {
		this.mailAddress = address;
	}
	
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public ContactType getType() {
		return ContactType.Email;
	}

	@Override
	public String getName() {
		return null;
	}

}
