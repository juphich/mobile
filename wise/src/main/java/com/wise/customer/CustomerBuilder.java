package com.wise.customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wise.category.Category;
import com.wise.core.RepositoryContextHolder;
import com.wise.core.SequenceGenerator;
import com.wise.customer.code.Phone;
import com.wise.customer.privacy.Contact;
import com.wise.customer.privacy.Privacy;
import com.wise.note.Note;
import com.wise.note.NoteRepository;
import com.wise.volume.Volume;
import com.wise.volume.VolumeRepository;

public class CustomerBuilder {

	private String serial;
	private String name;
	private Gender gender = Gender.UNKNOWN;
	private Customer recommend;
	
	private Set<Category> categories = new HashSet<>();
	private List<Privacy> privacies = new ArrayList<Privacy>(10);
	
	private String customerId;
	
	public static CustomerBuilder as() {
		return new CustomerBuilder();
	}
	
	public CustomerBuilder() {}
	
	public CustomerBuilder(Customer customer) {
		if (customer != null) {
			this.customerId = customer.getCustomerId();
			this.serial = customer.getSerial();
			this.name = customer.getName();
			this.gender = customer.getGender();
			this.recommend = customer.getRecommend();
			
			this.categories.addAll(customer.getCategories());
		}
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

	public CustomerBuilder gender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	public CustomerBuilder recommend(Customer recommend) {
		this.recommend = recommend;
		return this;
	}
	
	public CustomerBuilder phone(String phone) {
		this.privacies.add(new Contact(Phone.mobile, phone));
		return this;
	}
	
	public CustomerBuilder category(Category category) {
		this.categories.add(category);
		return this;
	}
	
	public String getSerial() {
		return serial;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public Customer getRecommend() {
		return recommend;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public List<Privacy> getPrivacies() {
		return privacies;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Customer build() {		
		if (name == null || name.isEmpty()) {
			throw new CustomerValidationException("customer name is empty");
		}
		
		if (customerId == null) {
			this.customerId = SequenceGenerator.next();
		}
		
		Customer customer = new Customer(customerId, name, gender);
		customer.setNoteRepository((NoteRepository) RepositoryContextHolder.repository(Note.class));
		customer.setVolumeRepository((VolumeRepository) RepositoryContextHolder.repository(Volume.class));
		
		customer.setSerial(serial);
		customer.recommend(recommend);
		
		for (Category category : categories) {
			customer.addCategory(category);
		}
		
		for (Privacy privacy : privacies) {
			customer.addPrivacy(privacy);
		}
		
		return customer;
	}
}
