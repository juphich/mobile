package com.wise.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wise.customer.contact.Contact;
import com.wise.customer.detail.CustomerDetail;

public class Customer {

	private String customerId;
	
	private String serial;
	
	private String name;
	private String nickName;
	private String merchantId;
	private Gender gender;
	private String birthday;
	
	private Customer recommend;
	
	private List<CustomerDetail> details;
	private List<Contact> contacts;
	private List<Memo> memos;
	private List<CustomerGroup> groups;
	
	private List<CheckPointEntry> checkPoints;
	
	
	public Customer(String name, String nickName, Gender gender, String birthday) {
		this.name = name;
		this.nickName = nickName;
		this.gender = gender;
		this.birthday = birthday;
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

	public String getNickName() {
		return nickName;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public Gender getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public Customer getRecommend() {
		return recommend;
	}

	public List<CustomerDetail> getDetails() {
		return details;
	}

	public List<Contact> getContacts() {
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	public <C extends Contact> List<C> getContacts(Class<C> type) {
		List<C> contacts = new ArrayList<C>();
		
		for (Contact contact : this.contacts) {
			if (contact.getClass().equals(type)) {
				contacts.add((C) contact);
			}
		}
		
		return Collections.unmodifiableList(contacts);
	}

	public List<Memo> getMemos() {
		return memos;
	}

	public List<CustomerGroup> getGroups() {
		return groups;
	}

	public List<CheckPointEntry> getCheckPoints() {
		return checkPoints;
	}

	void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setRecommend(Customer recommend) {
		this.recommend = recommend;
	}

	public void addDetail(CustomerDetail detail) {
		if (details == null) {
			details = new ArrayList<CustomerDetail>();
		}
		details.add(detail);
	}

	public void addContact(Contact contact) {
		if (contacts == null) {
			contacts = new ArrayList<Contact>();
		}
		contacts.add(contact);
	}

	public void addMemo(Memo memo) {
		if (memos == null) {
			memos = new ArrayList<Memo>();
		}
		memos.add(memo);
	}
	
	public void addGroup(CustomerGroup group) {
		if (groups == null) {
			groups = new ArrayList<CustomerGroup>();
		}
		groups.add(group);
	}
	
	public void addCheckPoint(CheckPoint checkPoint) {
		if (checkPoints == null) {
			checkPoints = new ArrayList<CheckPointEntry>();
		}
		checkPoints.add(new CheckPointEntry(checkPoint, null, null));
	}


	/**
	 * check point entry inner class
	 * 
	 * @author juphich
	 */
	private static class CheckPointEntry {
		private String checkStatus;
		private String remark;
		private CheckPoint checkPoint;
		
		CheckPointEntry(CheckPoint checkPoint, String status, String remark) {
			this.checkStatus = status;
			this.remark = remark;
			this.checkPoint = checkPoint;
		}
		
		void setCheckStatus(String checkStatus) {
			this.checkStatus = checkStatus;
		}

		void setRemark(String remark) {
			this.remark = remark;
		}

		String status() {
			return checkStatus;
		}
		
		String remark() {
			return remark;
		}
		
		CheckPoint checkPoint() {
			return checkPoint;
		}
	}


	public static Customer customer(CustomerBuilder builder) {
		return builder.build();
	}
}
