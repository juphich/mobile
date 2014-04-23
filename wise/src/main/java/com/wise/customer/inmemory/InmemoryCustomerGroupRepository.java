package com.wise.customer.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.customer.CustomerGroup;
import com.wise.customer.CustomerGroupRepsoitory;

public class InmemoryCustomerGroupRepository implements CustomerGroupRepsoitory {

	private Map<String, CustomerGroup> groups = new HashMap<String, CustomerGroup>();
	
	@Override
	public CustomerGroup find(String key) {
		return groups.get(key);
	}

	@Override
	public List<CustomerGroup> search(Query query) {
		return new ArrayList<CustomerGroup>(groups.values());
	}

	@Override
	public void save(CustomerGroup group) {
		groups.put(group.getGroupId(), group);
	}
}
