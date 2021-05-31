package com.gogi.proj.orders.config.vo;

/**
 * 
 * @author 3bgogi
 *2020. 7. 2.
 * 설명 : 제외 주소 테이블 VO 값
 */
public class ExceptAddressKeywordVO {

	private int eakPk;					//제외주소 고유값
	private String eakWord;				//제외주소 키워드
	private String eakReason;			//제외 사유
	private boolean eakFlag;			//제외 주소값 사용 여부
	private String eakRegdate;			//제외주소 등록일
	
	public ExceptAddressKeywordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExceptAddressKeywordVO(int eakPk, String eakWord, String eakReason, boolean eakFlag, String eakRegdate) {
		super();
		this.eakPk = eakPk;
		this.eakWord = eakWord;
		this.eakReason = eakReason;
		this.eakFlag = eakFlag;
		this.eakRegdate = eakRegdate;
	}

	public int getEakPk() {
		return eakPk;
	}

	public void setEakPk(int eakPk) {
		this.eakPk = eakPk;
	}

	public String getEakWord() {
		return eakWord;
	}

	public void setEakWord(String eakWord) {
		this.eakWord = eakWord;
	}

	public String getEakReason() {
		return eakReason;
	}

	public void setEakReason(String eakReason) {
		this.eakReason = eakReason;
	}

	public boolean isEakFlag() {
		return eakFlag;
	}

	public void setEakFlag(boolean eakFlag) {
		this.eakFlag = eakFlag;
	}

	public String getEakRegdate() {
		return eakRegdate;
	}

	public void setEakRegdate(String eakRegdate) {
		this.eakRegdate = eakRegdate;
	}

	@Override
	public String toString() {
		return "ExceptAddressKeywordVO [eakPk=" + eakPk + ", eakWord=" + eakWord + ", eakReason=" + eakReason
				+ ", eakFlag=" + eakFlag + ", eakRegdate=" + eakRegdate + "]";
	}

}
