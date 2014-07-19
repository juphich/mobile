package com.wise.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wise.category.Category;
import com.wise.core.GenericQuery;
import com.wise.core.Query;
import com.wise.customer.privacy.Privacy;
import com.wise.customer.privacy.PrivacyType;
import com.wise.note.Note;
import com.wise.note.NoteBuilder;
import com.wise.note.NoteRepository;
import com.wise.volume.Volume;
import com.wise.volume.VolumeRepository;

public class Customer {

	private String customerId;
	
	private String serial;
	
	private String name;
	
	private Gender gender;
	
	private Customer recommend;
	
	private Set<Category> categories = new HashSet<>();
	
	private Map<PrivacyType, List<? extends Privacy>> privacies;
	
	private NoteRepository noteRepository;
	
	private VolumeRepository volumeRepository;

	public Customer(String customerId, String name, Gender gender) {
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
	}
	
	public void setNoteRepository(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	public void setVolumeRepository(VolumeRepository volumeRepository) {
		this.volumeRepository = volumeRepository;
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

	public Set<Category> getCategories() {
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	public <D extends Privacy> List<D> findPrivacy(PrivacyType type) {
		if (privacies != null && !privacies.isEmpty()) {
			return (List<D>) privacies.get(type);
		} else {
			return null;
		}
	}
	
	public List<Note> getMemo() {
		Query query = new GenericQuery();
		query.q("referer", this);
		return noteRepository.search(query);
	}
	
	public Customer getRecommend() {
		return recommend;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public void addCategory(Category category) {
		if (categories == null) {
			categories = new HashSet<>();
		}
		categories.add(category);
	}

	@SuppressWarnings("unchecked")
	public <D extends Privacy> void addPrivacy(D info) {
		PrivacyType type = info.type();
		
		if (privacies == null) {
			privacies = new HashMap<>();
		}
		
		List<? extends Privacy> supplements = privacies.get(type);
		if (supplements == null) {
			supplements = new ArrayList<>();
		}
		
		appendSupplement((List<? super Privacy>) supplements, info);
		privacies.put(type, supplements);
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
	
	public void recommend(Customer customer) {
		if (customer == null) {
			return;
		}
		
		Volume volume = volumeRepository.find(customer.getCustomerId());
		if (volume == null) {
			volume = new Volume(customer);
		}
		volume.addMamber(this);
		volumeRepository.save(volume);
		
		this.recommend = customer;
	}
}
