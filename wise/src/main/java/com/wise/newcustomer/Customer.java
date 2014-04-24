package com.wise.newcustomer;

import java.util.List;
import java.util.Map;

import com.wise.category.Category;
import com.wise.newcustomer.detail.Detail;
import com.wise.newcustomer.detail.InfoType;

public class Customer {

	private String customerId;
	
	private String serial;
	
	private String name;
	
	private Gender gender;
	
	private List<Customer> recommends;
	
	private List<Category> categories;
	
	private Map<InfoType, Detail> details;
}
