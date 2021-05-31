package com.gogi.proj.analytics.vo;

public class ProductAnalyVO {

	private int totalAmount;
	private int totalPrice;
	private String products;
	
	public ProductAnalyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAnalyVO(int totalAmount, int totalPrice, String products) {
		super();
		this.totalAmount = totalAmount;
		this.totalPrice = totalPrice;
		this.products = products;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductAnaly [totalAmount=" + totalAmount + ", totalPrice=" + totalPrice + ", products=" + products
				+ "]";
	}
}
