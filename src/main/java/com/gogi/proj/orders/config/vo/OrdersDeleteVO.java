package com.gogi.proj.orders.config.vo;

import java.sql.Timestamp;

public class OrdersDeleteVO {

	private int orderDelPk;
	private String orderDelOrderNumber;
	private String orderDelProductOrderNumber;
	private String orderDelProduct;
	private String orderDelProductOption;
	private int orderDelProductQty;
	private String orderDelSendingDeadline;
	private Timestamp orderDelRegdate;
	private String orderDelIp;
	private String orderDelAdmin;
	
	public OrdersDeleteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersDeleteVO(int orderDelPk, String orderDelOrderNumber, String orderDelProductOrderNumber,
			String orderDelProduct, String orderDelProductOption, int orderDelProductQty,
			String orderDelSendingDeadline, Timestamp orderDelRegdate, String orderDelIp, String orderDelAdmin) {
		super();
		this.orderDelPk = orderDelPk;
		this.orderDelOrderNumber = orderDelOrderNumber;
		this.orderDelProductOrderNumber = orderDelProductOrderNumber;
		this.orderDelProduct = orderDelProduct;
		this.orderDelProductOption = orderDelProductOption;
		this.orderDelProductQty = orderDelProductQty;
		this.orderDelSendingDeadline = orderDelSendingDeadline;
		this.orderDelRegdate = orderDelRegdate;
		this.orderDelIp = orderDelIp;
		this.orderDelAdmin = orderDelAdmin;
	}

	public int getOrderDelPk() {
		return orderDelPk;
	}

	public void setOrderDelPk(int orderDelPk) {
		this.orderDelPk = orderDelPk;
	}

	public String getOrderDelOrderNumber() {
		return orderDelOrderNumber;
	}

	public void setOrderDelOrderNumber(String orderDelOrderNumber) {
		this.orderDelOrderNumber = orderDelOrderNumber;
	}

	public String getOrderDelProductOrderNumber() {
		return orderDelProductOrderNumber;
	}

	public void setOrderDelProductOrderNumber(String orderDelProductOrderNumber) {
		this.orderDelProductOrderNumber = orderDelProductOrderNumber;
	}

	public String getOrderDelProduct() {
		return orderDelProduct;
	}

	public void setOrderDelProduct(String orderDelProduct) {
		this.orderDelProduct = orderDelProduct;
	}

	public String getOrderDelProductOption() {
		return orderDelProductOption;
	}

	public void setOrderDelProductOption(String orderDelProductOption) {
		this.orderDelProductOption = orderDelProductOption;
	}

	public int getOrderDelProductQty() {
		return orderDelProductQty;
	}

	public void setOrderDelProductQty(int orderDelProductQty) {
		this.orderDelProductQty = orderDelProductQty;
	}

	public String getOrderDelSendingDeadline() {
		return orderDelSendingDeadline;
	}

	public void setOrderDelSendingDeadline(String orderDelSendingDeadline) {
		this.orderDelSendingDeadline = orderDelSendingDeadline;
	}

	public Timestamp getOrderDelRegdate() {
		return orderDelRegdate;
	}

	public void setOrderDelRegdate(Timestamp orderDelRegdate) {
		this.orderDelRegdate = orderDelRegdate;
	}

	public String getOrderDelIp() {
		return orderDelIp;
	}

	public void setOrderDelIp(String orderDelIp) {
		this.orderDelIp = orderDelIp;
	}

	public String getOrderDelAdmin() {
		return orderDelAdmin;
	}

	public void setOrderDelAdmin(String orderDelAdmin) {
		this.orderDelAdmin = orderDelAdmin;
	}

	@Override
	public String toString() {
		return "OrdersDeleteVO [orderDelPk=" + orderDelPk + ", orderDelOrderNumber=" + orderDelOrderNumber
				+ ", orderDelProductOrderNumber=" + orderDelProductOrderNumber + ", orderDelProduct=" + orderDelProduct
				+ ", orderDelProductOption=" + orderDelProductOption + ", orderDelProductQty=" + orderDelProductQty
				+ ", orderDelSendingDeadline=" + orderDelSendingDeadline + ", orderDelRegdate=" + orderDelRegdate
				+ ", orderDelIp=" + orderDelIp + ", orderDelAdmin=" + orderDelAdmin + "]";
	}

}
