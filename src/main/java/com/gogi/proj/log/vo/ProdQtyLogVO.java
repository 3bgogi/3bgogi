package com.gogi.proj.log.vo;

import java.sql.Timestamp;

public class ProdQtyLogVO {

	private String productName;
	private String optionName;
	private int pqlPk;
	private int optionFk;
	private int pqlQty;
	private boolean pqlAdminFlag;
	private Timestamp pqlRegdate;
	
	public ProdQtyLogVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdQtyLogVO(String productName, String optionName, int pqlPk, int optionFk, int pqlQty,
			boolean pqlAdminFlag, Timestamp pqlRegdate) {
		super();
		this.productName = productName;
		this.optionName = optionName;
		this.pqlPk = pqlPk;
		this.optionFk = optionFk;
		this.pqlQty = pqlQty;
		this.pqlAdminFlag = pqlAdminFlag;
		this.pqlRegdate = pqlRegdate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getPqlPk() {
		return pqlPk;
	}

	public void setPqlPk(int pqlPk) {
		this.pqlPk = pqlPk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public int getPqlQty() {
		return pqlQty;
	}

	public void setPqlQty(int pqlQty) {
		this.pqlQty = pqlQty;
	}

	public boolean isPqlAdminFlag() {
		return pqlAdminFlag;
	}

	public void setPqlAdminFlag(boolean pqlAdminFlag) {
		this.pqlAdminFlag = pqlAdminFlag;
	}

	public Timestamp getPqlRegdate() {
		return pqlRegdate;
	}

	public void setPqlRegdate(Timestamp pqlRegdate) {
		this.pqlRegdate = pqlRegdate;
	}

	@Override
	public String toString() {
		return "ProdQtyLogVO [productName=" + productName + ", optionName=" + optionName + ", pqlPk=" + pqlPk
				+ ", optionFk=" + optionFk + ", pqlQty=" + pqlQty + ", pqlAdminFlag=" + pqlAdminFlag + ", pqlRegdate="
				+ pqlRegdate + "]";
	}
	
}
