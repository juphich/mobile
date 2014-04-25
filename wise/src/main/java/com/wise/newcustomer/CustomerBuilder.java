package com.wise.newcustomer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wise.category.Category;
import com.wise.core.RepositoryContextHolder;
import com.wise.core.SequenceGenerator;
import com.wise.customer.contact.Contact;
import com.wise.customer.contact.Phone;
import com.wise.note.Note;
import com.wise.note.NoteRepository;
import com.wise.volume.Volume;
import com.wise.volume.VolumeRepository;

public class CustomerBuilder {

	private String serial;
	private String name;
	private Gender gender;
	private Customer recommend;
	
	private Set<Category> categories = new HashSet<>();
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
	
	public CustomerBuilder category(Category category) {
		this.categories.add(category);
		return this;
	}
	
	public Customer build() {
		if (customerId == null) {
			this.customerId = SequenceGenerator.next();
		}
		
		Customer customer = new Customer(customerId, name, gender);
		customer.setNoteRepository((NoteRepository) RepositoryContextHolder.repository(Note.class));
		customer.setVolumeRepository((VolumeRepository) RepositoryContextHolder.repository(Volume.class));
		
		customer.setSerial(serial);
//		customer.recommend(recommend);
		
		for (Category category : categories) {
			customer.addCategory(category);
		}
		
		return customer;
	}
}
