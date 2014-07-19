package com.wise.product;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.wise.category.Category;

public class Product {

	private int productId;
	
	private String name;
	private String serial;
	
	private BigInteger price;
	private BigInteger discount;
	
	private int pv;
	
	private String origin;
	private String maker;
	private String seller;

	private String description;
	
	private String contact;
	
	private Sale sale = new Sale(0,0);
	
	private Set<Category> categories;

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getSerial() {
		return serial;
	}

	public BigInteger getPrice() {
		return price;
	}

	public BigInteger getDiscount() {
		return discount;
	}

	public int getPv() {
		return pv;
	}

	public String getOrigin() {
		return origin;
	}

	public String getMaker() {
		return maker;
	}

	public String getSeller() {
		return seller;
	}

	public String getDescription() {
		return description;
	}

	public String getContact() {
		return contact;
	}

	public Sale getSale() {
		return sale;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	void setId(int productId) {
		this.productId = productId;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public void setDiscount(BigInteger discount) {
		this.discount = discount;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void addCategory(Category category) {
		if (categories == null) {
			categories = new HashSet<>();
		}
		categories.add(category);
	}
}
