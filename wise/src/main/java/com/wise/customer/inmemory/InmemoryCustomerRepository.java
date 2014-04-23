package com.wise.customer.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.customer.Customer;
import com.wise.customer.CustomerRepository;

public class InmemoryCustomerRepository implements CustomerRepository {

	private Map<String, Customer> customers = new HashMap<String, Customer>();
	
	@Override
	public Customer find(String key) {
		return customers.get(key);
	}

	@Override
	public List<Customer> search(Query query) {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public void save(Customer customer) {
		customers.put(customer.getCustomerId(), customer);
	}
}
