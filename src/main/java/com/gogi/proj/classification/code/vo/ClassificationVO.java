package com.gogi.proj.classification.code.vo;

import java.sql.Timestamp;
import java.util.List;

public class ClassificationVO {
	
	//기본 사항
	private int cfPk; // 상품 분류 고유 번호
	private int cfCode; //분류 코드
	private String cfCodeType; //분류 코드 명
	private Timestamp cfRegdate; //분류 코드 등록일
	
	//추가 사항
	private int cfProductCount; // 분류코드 당 상품 개수
	
	private List<ClassificationVO> cfList; 
	
	public ClassificationVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassificationVO(int cfPk, int cfCode, String cfCodeType, Timestamp cfRegdate, int cfProductCount,
			List<ClassificationVO> cfList) {
		super();
		this.cfPk = cfPk;
		this.cfCode = cfCode;
		this.cfCodeType = cfCodeType;
		this.cfRegdate = cfRegdate;
		this.cfProductCount = cfProductCount;
		this.cfList = cfList;
	}

	public int getCfPk() {
		return cfPk;
	}

	public void setCfPk(int cfPk) {
		this.cfPk = cfPk;
	}

	public int getCfCode() {
		return cfCode;
	}

	public void setCfCode(int cfCode) {
		this.cfCode = cfCode;
	}

	public String getCfCodeType() {
		return cfCodeType;
	}

	public void setCfCodeType(String cfCodeType) {
		this.cfCodeType = cfCodeType;
	}

	public Timestamp getCfRegdate() {
		return cfRegdate;
	}

	public void setCfRegdate(Timestamp cfRegdate) {
		this.cfRegdate = cfRegdate;
	}

	public int getCfProductCount() {
		return cfProductCount;
	}

	public void setCfProductCount(int cfProductCount) {
		this.cfProductCount = cfProductCount;
	}

	public List<ClassificationVO> getCfList() {
		return cfList;
	}

	public void setCfList(List<ClassificationVO> cfList) {
		this.cfList = cfList;
	}

	@Override
	public String toString() {
		return "ClassificationVO [cfPk=" + cfPk + ", cfCode=" + cfCode + ", cfCodeType=" + cfCodeType + ", cfRegdate="
				+ cfRegdate + ", cfProductCount=" + cfProductCount + ", cfList=" + cfList + "]";
	}
	
}
