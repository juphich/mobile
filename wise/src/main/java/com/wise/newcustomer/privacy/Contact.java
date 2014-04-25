package com.wise.newcustomer.privacy;

import com.wise.newcustomer.code.ContactKind;

public class Contact implements Privacy {

	private PrivacyType type = PrivacyType.homepage;
	
	private ContactKind kind;
	
	private String contact;
	
	public Contact(ContactKind kind) {
		this.type = kind.type();
		this.kind = kind;
	}
	
	public Contact(ContactKind kind, String contact) {
		this.type = kind.type();
		this.kind = kind;
		this.contact = contact;
	}
	
	public Contact(String contact) {
		this.contact = contact;
	}

	@Override
	public PrivacyType type() {
		return type;
	}

	public ContactKind getKind() {
		return this.kind;
	}
	
	public String getContact() {
		return this.contact;
	}

	public void setKind(ContactKind kind) {
		this.kind = kind;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
