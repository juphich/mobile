package com.wise.product;

public class Sale {

	private long stock;
	private long sale;
	
	public Sale() {}
	
	public Sale(int stock, int sale) {
		this.stock = stock;
		this.sale = sale;
	}

	public long getStock() {
		return stock;
	}
	
	public void setStock(long stock) {
		this.stock = stock;
	}
	
	public long getSale() {
		return sale;
	}
	
	public void setSale(long sale) {
		this.sale = sale;
	}
}
