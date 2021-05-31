package com.gogi.proj.configurations.vo;

public class BlockSendingListVO {

	private int bslPk;					//고유값
	private String bslNumber;			//전화번호
	private String bslRegdate;			//등록일
	
	public BlockSendingListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlockSendingListVO(int bslPk, String bslNumber, String bslRegdate) {
		super();
		this.bslPk = bslPk;
		this.bslNumber = bslNumber;
		this.bslRegdate = bslRegdate;
	}

	public int getBslPk() {
		return bslPk;
	}

	public void setBslPk(int bslPk) {
		this.bslPk = bslPk;
	}

	public String getBslNumber() {
		return bslNumber;
	}

	public void setBslNumber(String bslNumber) {
		this.bslNumber = bslNumber;
	}

	public String getBslRegdate() {
		return bslRegdate;
	}

	public void setBslRegdate(String bslRegdate) {
		this.bslRegdate = bslRegdate;
	}

	@Override
	public String toString() {
		return "BlockSendingListVO [bslPk=" + bslPk + ", bslNumber=" + bslNumber + ", bslRegdate=" + bslRegdate + "]";
	}

}
