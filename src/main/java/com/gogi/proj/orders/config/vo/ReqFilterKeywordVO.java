package com.gogi.proj.orders.config.vo;

public class ReqFilterKeywordVO {

	private int rfkPk;
	private String rfkWord;
	private boolean rfkFlag;
	private String rfkRegdate;
	
	public ReqFilterKeywordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReqFilterKeywordVO(int rfkPk, String rfkWord, boolean rfkFlag, String rfkRegdate) {
		super();
		this.rfkPk = rfkPk;
		this.rfkWord = rfkWord;
		this.rfkFlag = rfkFlag;
		this.rfkRegdate = rfkRegdate;
	}

	public int getRfkPk() {
		return rfkPk;
	}

	public void setRfkPk(int rfkPk) {
		this.rfkPk = rfkPk;
	}

	public String getRfkWord() {
		return rfkWord;
	}

	public void setRfkWord(String rfkWord) {
		this.rfkWord = rfkWord;
	}

	public boolean getRfkFlag() {
		return rfkFlag;
	}

	public void setRfkFlag(boolean rfkFlag) {
		this.rfkFlag = rfkFlag;
	}

	public String getRfkRegdate() {
		return rfkRegdate;
	}

	public void setRfkRegdate(String rfkRegdate) {
		this.rfkRegdate = rfkRegdate;
	}

	@Override
	public String toString() {
		return "ReqFliterKeywordVO [rfkPk=" + rfkPk + ", rfkWord=" + rfkWord + ", rfkFlag=" + rfkFlag + ", rfkRegdate="
				+ rfkRegdate + "]";
	}
	
}
