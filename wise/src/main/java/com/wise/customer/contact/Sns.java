package com.wise.customer.contact;

public class Sns implements Contact {

	private String snsKind;
	private String location;
	
	public Sns(String kind, String location) {
		this.snsKind = kind;
		this.location = location;
	}
	
	public String getSnsKind() {
		return snsKind;
	}

	public String getLocation() {
		return location;
	}

	public void setSnsKind(String snsKind) {
		this.snsKind = snsKind;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public ContactType getType() {
		return ContactType.SNS;
	}

	@Override
	public String getName() {
		return null;
	}

}
