package com.wise.newcustomer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.category.Category;
import com.wise.core.GenericQuery;
import com.wise.core.Query;
import com.wise.newcustomer.privacy.Privacy;
import com.wise.newcustomer.privacy.PrivacyType;
import com.wise.note.Note;
import com.wise.note.NoteBuilder;
import com.wise.note.NoteRepository;

public class Customer {

	private String customerId;
	
	private String serial;
	
	private String name;
	
	private Gender gender;
	
	private List<Customer> recommends;
	
	private List<Category> categories;
	
	private Map<PrivacyType, List<? extends Privacy>> infomations;
	
	private NoteRepository noteRepository;

	public Customer(String customerId, String name, Gender gender) {
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
	}
	
	public void setNoteRepository(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public String getCustomerId() {
		return customerId;
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

	public List<Customer> getRecommends() {
		return recommends;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	public <D extends Privacy> List<D> findPrivacy(PrivacyType type) {
		return (List<D>) infomations.get(type);
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@SuppressWarnings("unchecked")
	public <D extends Privacy> void addPrivacy(D info) {
		PrivacyType type = info.type();
		
		if (infomations == null) {
			infomations = new HashMap<>();
		}
		
		List<? extends Privacy> supplements = infomations.get(type);
		if (supplements == null) {
			supplements = new ArrayList<>();
		}
		
		appendSupplement((List<? super Privacy>) supplements, info);
		infomations.put(type, supplements);
	}
	
	private void appendSupplement(List<? super Privacy> list, Privacy detail) {
		list.add(detail);
	}
	
	public void writeMemo(Note note) {
		noteRepository.save(note);
	}

	public void writeMemo(String memo) {
		Note note = new NoteBuilder().note(memo).referer(this).build();
		noteRepository.save(note);
	}
	
	public List<Note> getMemo() {
		Query query = new GenericQuery();
		query.q("referer", this);
		return noteRepository.search(query);
	}
}
