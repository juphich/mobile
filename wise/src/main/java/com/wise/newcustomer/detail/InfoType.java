package com.wise.newcustomer.detail;

public enum InfoType {
	phone("전화번호"), 
	family("가족"), 
	mail("email"), 
	address("주소"), 
	sns("SNS"), 
	homepage("홈페이지"), 
	nick("별명"), 
	job("직업"), 
	anniversary("기념일");
	
	private String title;

	private InfoType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
