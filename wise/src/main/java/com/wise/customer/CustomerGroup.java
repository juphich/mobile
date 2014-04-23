package com.wise.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerGroup {

	private String groupId;
	private String name;
	
	private List<Customer> customers;
	
	public CustomerGroup(String groupId, String name) {
		this.groupId = groupId;
		this.name = name;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getName() {
		return name;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void addCustomer(Customer customer) {
		if (this.customers == null) {
			this.customers = new ArrayList<Customer>();
		}
		
		customers.add(customer);
	}
}
