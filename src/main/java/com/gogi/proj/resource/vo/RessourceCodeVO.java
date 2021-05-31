package com.gogi.proj.resource.vo;

import java.sql.Timestamp;

public class RessourceCodeVO {
	
	private int rcPk; //자재분류코드 고유번호
	private int rcCode; //분류코드
	private String rcCodeType; //분류코드명
	private Timestamp rcRegdate; //등록일
	
	public RessourceCodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RessourceCodeVO(int rcPk, int rcCode, String rcCodeType, Timestamp rcRegdate) {
		super();
		this.rcPk = rcPk;
		this.rcCode = rcCode;
		this.rcCodeType = rcCodeType;
		this.rcRegdate = rcRegdate;
	}

	public int getRcPk() {
		return rcPk;
	}

	public void setRcPk(int rcPk) {
		this.rcPk = rcPk;
	}

	public int getRcCode() {
		return rcCode;
	}

	public void setRcCode(int rcCode) {
		this.rcCode = rcCode;
	}

	public String getRcCodeType() {
		return rcCodeType;
	}

	public void setRcCodeType(String rcCodeType) {
		this.rcCodeType = rcCodeType;
	}

	public Timestamp getRcRegdate() {
		return rcRegdate;
	}

	public void setRcRegdate(Timestamp rcRegdate) {
		this.rcRegdate = rcRegdate;
	}

	@Override
	public String toString() {
		return "RessourceCodeVO [rcPk=" + rcPk + ", rcCode=" + rcCode + ", rcCodeType=" + rcCodeType + ", rcRegdate="
				+ rcRegdate + "]";
	}
	
}
