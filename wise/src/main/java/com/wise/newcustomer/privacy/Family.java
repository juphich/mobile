package com.wise.newcustomer.privacy;

import com.wise.newcustomer.code.Relation;

public class Family implements Privacy {

	private Relation relation;
	
	private String birth;
	
	private String name;
	
	public Family() {}
	
	public Family(Relation relation, String birth, String name) {
		super();
		this.relation = relation;
		this.birth = birth;
		this.name = name;
	}

	@Override
	public PrivacyType type() {
		return PrivacyType.family;
	}

	public Relation getRelation() {
		return relation;
	}

	public String getBirth() {
		return birth;
	}

	public String getName() {
		return name;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setName(String name) {
		this.name = name;
	}
}
