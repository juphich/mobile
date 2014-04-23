package com.wise.customer.contact;

public class Address implements Contact {

	private String title;
	private String location;
	
	public Address(String title, String location) {
		this.title = title;
		this.location = location;
	}
	
	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public ContactType getType() {
		return ContactType.Address;
	}

	@Override
	public String getName() {
		return null;
	}

}
