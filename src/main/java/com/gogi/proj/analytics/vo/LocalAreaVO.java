package com.gogi.proj.analytics.vo;

import java.util.List;

public class LocalAreaVO {

	private int cfPk;
	private String cfCodeType;
	private int totalPrice;
	private int totalOrder;
	private int maxPrice;
	private int minPrice;
	private int maxQty;
	private int minQty;
	private String orInflowRoute;
	
	private List<ProductAnalyVO> paList;

	public LocalAreaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalAreaVO(int cfPk, String cfCodeType, int totalPrice, int totalOrder, int maxPrice, int minPrice,
			int maxQty, int minQty, String orInflowRoute, List<ProductAnalyVO> paList) {
		super();
		this.cfPk = cfPk;
		this.cfCodeType = cfCodeType;
		this.totalPrice = totalPrice;
		this.totalOrder = totalOrder;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.maxQty = maxQty;
		this.minQty = minQty;
		this.orInflowRoute = orInflowRoute;
		this.paList = paList;
	}

	public int getCfPk() {
		return cfPk;
	}

	public void setCfPk(int cfPk) {
		this.cfPk = cfPk;
	}

	public String getCfCodeType() {
		return cfCodeType;
	}

	public void setCfCodeType(String cfCodeType) {
		this.cfCodeType = cfCodeType;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public String getOrInflowRoute() {
		return orInflowRoute;
	}

	public void setOrInflowRoute(String orInflowRoute) {
		this.orInflowRoute = orInflowRoute;
	}

	public List<ProductAnalyVO> getPaList() {
		return paList;
	}

	public void setPaList(List<ProductAnalyVO> paList) {
		this.paList = paList;
	}

	@Override
	public String toString() {
		return "LocalAreaVO [cfPk=" + cfPk + ", cfCodeType=" + cfCodeType + ", totalPrice=" + totalPrice
				+ ", totalOrder=" + totalOrder + ", maxPrice=" + maxPrice + ", minPrice=" + minPrice + ", maxQty="
				+ maxQty + ", minQty=" + minQty + ", orInflowRoute=" + orInflowRoute + ", paList=" + paList + "]";
	}
	
}
