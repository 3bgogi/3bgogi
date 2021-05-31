package com.gogi.proj.producttax.vo;

import java.sql.Date;

public class TaxTableVO {

	private int ttPk;
	private String ttDate;
	private String ttOrderNumber;
	private String ttProductOrderNumber;
	private String ttProduct;
	
	private String ttTaxType;
	private String ttTaxStat;
	
	private int ttTotalPrice;
	private int ttTaxPrice;
	private int ttDutyFreePrice;
	private int ttCreditPrice;
	private int ttCashDeductionPrice;
	private int ttCashReceiptPrice;
	private int ttAnotherPrice;
	
	private int totalCount;
	
	public TaxTableVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaxTableVO(int ttPk, String ttDate, String ttOrderNumber, String ttProductOrderNumber, String ttProduct,
			int ttTotalPrice, int ttTaxPrice, int ttDutyFreePrice, int ttCreditPrice, int ttCashDeductionPrice,
			int ttCashReceiptPrice, int ttAnotherPrice) {
		super();
		this.ttPk = ttPk;
		this.ttDate = ttDate;
		this.ttOrderNumber = ttOrderNumber;
		this.ttProductOrderNumber = ttProductOrderNumber;
		this.ttProduct = ttProduct;
		this.ttTotalPrice = ttTotalPrice;
		this.ttTaxPrice = ttTaxPrice;
		this.ttDutyFreePrice = ttDutyFreePrice;
		this.ttCreditPrice = ttCreditPrice;
		this.ttCashDeductionPrice = ttCashDeductionPrice;
		this.ttCashReceiptPrice = ttCashReceiptPrice;
		this.ttAnotherPrice = ttAnotherPrice;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTtTaxType() {
		return ttTaxType;
	}

	public void setTtTaxType(String ttTaxType) {
		this.ttTaxType = ttTaxType;
	}

	public String getTtTaxStat() {
		return ttTaxStat;
	}

	public void setTtTaxStat(String ttTaxStat) {
		this.ttTaxStat = ttTaxStat;
	}

	public int getTtPk() {
		return ttPk;
	}

	public void setTtPk(int ttPk) {
		this.ttPk = ttPk;
	}

	public String getTtDate() {
		return ttDate;
	}

	public void setTtDate(String ttDate) {
		this.ttDate = ttDate;
	}

	public String getTtOrderNumber() {
		return ttOrderNumber;
	}

	public void setTtOrderNumber(String ttOrderNumber) {
		this.ttOrderNumber = ttOrderNumber;
	}

	public String getTtProductOrderNumber() {
		return ttProductOrderNumber;
	}

	public void setTtProductOrderNumber(String ttProductOrderNumber) {
		this.ttProductOrderNumber = ttProductOrderNumber;
	}

	public String getTtProduct() {
		return ttProduct;
	}

	public void setTtProduct(String ttProduct) {
		this.ttProduct = ttProduct;
	}

	public int getTtTotalPrice() {
		return ttTotalPrice;
	}

	public void setTtTotalPrice(int ttTotalPrice) {
		this.ttTotalPrice = ttTotalPrice;
	}

	public int getTtTaxPrice() {
		return ttTaxPrice;
	}

	public void setTtTaxPrice(int ttTaxPrice) {
		this.ttTaxPrice = ttTaxPrice;
	}

	public int getTtDutyFreePrice() {
		return ttDutyFreePrice;
	}

	public void setTtDutyFreePrice(int ttDutyFreePrice) {
		this.ttDutyFreePrice = ttDutyFreePrice;
	}

	public int getTtCreditPrice() {
		return ttCreditPrice;
	}

	public void setTtCreditPrice(int ttCreditPrice) {
		this.ttCreditPrice = ttCreditPrice;
	}

	public int getTtCashDeductionPrice() {
		return ttCashDeductionPrice;
	}

	public void setTtCashDeductionPrice(int ttCashDeductionPrice) {
		this.ttCashDeductionPrice = ttCashDeductionPrice;
	}

	public int getTtCashReceiptPrice() {
		return ttCashReceiptPrice;
	}

	public void setTtCashReceiptPrice(int ttCashReceiptPrice) {
		this.ttCashReceiptPrice = ttCashReceiptPrice;
	}

	public int getTtAnotherPrice() {
		return ttAnotherPrice;
	}

	public void setTtAnotherPrice(int ttAnotherPrice) {
		this.ttAnotherPrice = ttAnotherPrice;
	}

	@Override
	public String toString() {
		return "TaxTableVO [ttPk=" + ttPk + ", ttDate=" + ttDate + ", ttOrderNumber=" + ttOrderNumber
				+ ", ttProductOrderNumber=" + ttProductOrderNumber + ", ttProduct=" + ttProduct + ", ttTotalPrice="
				+ ttTotalPrice + ", ttTaxPrice=" + ttTaxPrice + ", ttDutyFreePrice=" + ttDutyFreePrice
				+ ", ttCreditPrice=" + ttCreditPrice + ", ttCashDeductionPrice=" + ttCashDeductionPrice
				+ ", ttCashReceiptPrice=" + ttCashReceiptPrice + ", ttAnotherPrice=" + ttAnotherPrice + "]";
	}
	
}
