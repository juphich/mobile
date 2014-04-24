package com.wise.newcustomer;

import java.util.ArrayList;
import java.util.List;

import com.wise.core.SequenceGenerator;
import com.wise.customer.contact.Contact;
import com.wise.customer.contact.Phone;

public class CustomerBuilder {

	private String serial;
	private String name;
	private String nick;
	private String birthday;
	private Gender gender;
	private Customer recommend;
	
	private List<Contact> contacts = new ArrayList<Contact>(10);
	private String customerId;
	
	public static CustomerBuilder as() {
		return new CustomerBuilder();
	}

	public CustomerBuilder id(String customerId) {
		this.customerId = customerId;
		return this;
	}
	
	public CustomerBuilder serial(String serial) {
		this.serial = serial;
		return this;
	}
	
	public CustomerBuilder name(String name) {
		this.name = name;
		return this;
	}

	public CustomerBuilder nick(String nick) {
		this.nick = nick;
		return this;
	}

	public CustomerBuilder birthday(String birthday) {
		this.birthday = birthday;
		return this;
	}

	public CustomerBuilder gender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	public CustomerBuilder recommend(Customer recommend) {
		this.recommend = recommend;
		return this;
	}
	
	public CustomerBuilder phone(String phone) {
		this.contacts.add(new Phone("mobile", phone));
		return this;
	}
	
	public Customer build() {
		Customer customer = new Customer(name, nick, gender, birthday);
		
		if (customerId == null) {
			customer.setCustomerId(SequenceGenerator.next());
		} else {
			customer.setCustomerId(customerId);
		}
		
		customer.setSerial(serial);
		customer.setRecommend(recommend);
		
		for (Contact contact : contacts) {
			customer.addContact(contact);
		}
		return customer;
	}
}
