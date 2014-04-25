package com.wise.newcustomer.privacy;

public enum PrivacyType {
	phone("전화번호"), 
	family("가족"), 
	email("email"), 
	address("주소"), 
	sns("SNS"), 
	homepage("홈페이지"), 
	nick("별명"), 
	occupation("직업"), 
	anniversary("기념일");
	
	private String title;

	private PrivacyType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
