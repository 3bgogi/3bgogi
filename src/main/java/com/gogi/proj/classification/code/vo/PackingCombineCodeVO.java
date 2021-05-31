package com.gogi.proj.classification.code.vo;

public class PackingCombineCodeVO {
	
	private int pccPk; //합포 분류 고유 번호
	private int pccCode; //분류 코드
	private String pccType; //분류 이름
	
	public PackingCombineCodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PackingCombineCodeVO(int pccPk, int pccCode, String pccType) {
		super();
		this.pccPk = pccPk;
		this.pccCode = pccCode;
		this.pccType = pccType;
	}

	public int getPccPk() {
		return pccPk;
	}

	public void setPccPk(int pccPk) {
		this.pccPk = pccPk;
	}

	public int getPccCode() {
		return pccCode;
	}

	public void setPccCode(int pccCode) {
		this.pccCode = pccCode;
	}

	public String getPccType() {
		return pccType;
	}

	public void setPccType(String pccType) {
		this.pccType = pccType;
	}

	@Override
	public String toString() {
		return "PackingCombineCodeVO [pccPk=" + pccPk + ", pccCode=" + pccCode + ", pccType=" + pccType + "]";
	}
	
	
}
