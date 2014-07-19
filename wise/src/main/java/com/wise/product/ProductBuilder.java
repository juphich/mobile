package com.wise.product;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.wise.category.Category;

public class ProductBuilder {

	private int productId;
	
	private String name;
	private String serial;
	
	private BigInteger price;
	private BigInteger discount;
	
	
	private String origin;
	private String maker;
	private String seller;

	private String description;
	
	private String contact;
	
	private Set<Category> categories;
	
	public ProductBuilder id(int id) {
		this.productId = id;
		return this;
	}
	
	public ProductBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ProductBuilder serial(String serial) {
		this.serial = serial;
		return this;
	}
	
	public ProductBuilder price(int price) {
		this.price = BigInteger.valueOf(price);
		return this;
	}
	
	public ProductBuilder price(long price) {
		this.price = BigInteger.valueOf(price);
		return this;
	}
	
	public ProductBuilder discount(int discount) {
		this.discount = BigInteger.valueOf(discount);
		return this;
	}
	
	public ProductBuilder discount(long discount) {
		this.discount = BigInteger.valueOf(discount);
		return this;
	}
	
	public ProductBuilder origin(String origin) {
		this.origin = origin;
		return this;
	}
	
	public ProductBuilder maker(String maker) {
		this.maker = maker;
		return this;
	}
	
	public ProductBuilder seller(String seller) {
		this.seller = seller;
		return this;
	}
	
	public ProductBuilder contact(String contact) {
		this.contact = contact;
		return this;
	}
	
	public ProductBuilder description(String desc) {
		this.description = desc;
		return this;
	}
	
	public ProductBuilder category(Category category) {
		if (categories == null) {
			categories = new HashSet<>();
		}
		categories.add(category);
		
		return this;
	}
	
	public Product build() {
		Product product = new Product();
		product.setName(name);
		product.setSerial(serial);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setOrigin(origin);
		product.setMaker(maker);
		product.setSeller(seller);
		product.setDescription(description);
		product.setContact(contact);
		
		return product; 
	}
	
	public void bindTo(Product product) {
		product.setId(productId);
	}
}
