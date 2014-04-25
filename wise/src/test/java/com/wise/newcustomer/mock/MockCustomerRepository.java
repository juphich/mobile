package com.wise.newcustomer.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.newcustomer.Customer;
import com.wise.newcustomer.CustomerRepository;

public class MockCustomerRepository implements CustomerRepository {

	private Map<String, Customer> customers = new HashMap<String, Customer>();
	
	@Override
	public Customer find(String key) {
		return customers.get(key);
	}

	@Override
	public List<Customer> search(Query query) {
		Object key = query.key("category");
		List<Customer> results = new ArrayList<>();
		
		if (key != null) {
			for (Customer c : customers.values()) {
				if (c.getCategories().contains(key)) {
					results.add(c);
				}
			}
		} else {
			results.addAll(customers.values());
		}
		
		return results;
	}

	@Override
	public void save(Customer customer) {
		customers.put(customer.getCustomerId(), customer);
	}
}
