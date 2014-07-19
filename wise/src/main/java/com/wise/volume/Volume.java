package com.wise.volume;

import java.util.ArrayList;
import java.util.List;

import com.wise.customer.Customer;

public class Volume {

	private Customer master;
	private List<Customer> members;
	
	private int size;
	
	public Volume(Customer member) {
		this.master = member;
	}

	public String getMaster() {
		return master.getCustomerId();
	}

	public int getSize() {
		return size;
	}

	public List<Customer> getMembers() {
		return members;
	}
	
	public void addMamber(Customer member) {
		if (members == null) {
			members = new ArrayList<>();
		}
		members.add(member);
		size += 1;
	}
}
