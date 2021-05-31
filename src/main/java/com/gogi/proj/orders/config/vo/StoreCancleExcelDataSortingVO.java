package com.gogi.proj.orders.config.vo;

public class StoreCancleExcelDataSortingVO {

	private int scedsPk;					//고유값
	private int ssFK;						//판매처 고유값
	private int scedsOrderNumber;			//주문번호
	private int scedsProductOrderNumber;	//상품주문번호
	private int scedsReason;				//취소사유
	private int scedsDate;					//취소요청일
	
	public StoreCancleExcelDataSortingVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreCancleExcelDataSortingVO(int scedsPk, int ssFK, int scedsOrderNumber, int scedsProductOrderNumber,
			int scedsReason, int scedsDate) {
		super();
		this.scedsPk = scedsPk;
		this.ssFK = ssFK;
		this.scedsOrderNumber = scedsOrderNumber;
		this.scedsProductOrderNumber = scedsProductOrderNumber;
		this.scedsReason = scedsReason;
		this.scedsDate = scedsDate;
	}

	public int getScedsPk() {
		return scedsPk;
	}

	public void setScedsPk(int scedsPk) {
		this.scedsPk = scedsPk;
	}

	public int getSsFK() {
		return ssFK;
	}

	public void setSsFK(int ssFK) {
		this.ssFK = ssFK;
	}

	public int getScedsOrderNumber() {
		return scedsOrderNumber;
	}

	public void setScedsOrderNumber(int scedsOrderNumber) {
		this.scedsOrderNumber = scedsOrderNumber;
	}

	public int getScedsProductOrderNumber() {
		return scedsProductOrderNumber;
	}

	public void setScedsProductOrderNumber(int scedsProductOrderNumber) {
		this.scedsProductOrderNumber = scedsProductOrderNumber;
	}

	public int getScedsReason() {
		return scedsReason;
	}

	public void setScedsReason(int scedsReason) {
		this.scedsReason = scedsReason;
	}

	public int getScedsDate() {
		return scedsDate;
	}

	public void setScedsDate(int scedsDate) {
		this.scedsDate = scedsDate;
	}

	@Override
	public String toString() {
		return "StoreCancleExcelDataSortingVO [scedsPk=" + scedsPk + ", ssFK=" + ssFK + ", scedsOrderNumber="
				+ scedsOrderNumber + ", scedsProductOrderNumber=" + scedsProductOrderNumber + ", scedsReason="
				+ scedsReason + ", scedsDate=" + scedsDate + "]";
	}
	
}
