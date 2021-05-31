package com.gogi.proj.orders.vo;

import java.sql.Timestamp;

//주문서 입력 후 구매자 체크사항
public class IrregularOrderVO {

	private int iroPk; //고유값
	private int ssFk; // 스토어 구분
	private String iroName; //구매자명
	private String iroPhoneNumber; //구매자 핸드폰 번호
	private String iroDetail; //확인 할 사항
	private boolean iroFlag; //확인 여부
	private Timestamp iroRegdate; //등록일
	
	public IrregularOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IrregularOrderVO(int iroPk, int ssFk, String iroName, String iroPhoneNumber, String iroDetail,
			boolean iroFlag, Timestamp iroRegdate) {
		super();
		this.iroPk = iroPk;
		this.ssFk = ssFk;
		this.iroName = iroName;
		this.iroPhoneNumber = iroPhoneNumber;
		this.iroDetail = iroDetail;
		this.iroFlag = iroFlag;
		this.iroRegdate = iroRegdate;
	}

	public int getIroPk() {
		return iroPk;
	}

	public void setIroPk(int iroPk) {
		this.iroPk = iroPk;
	}

	public int getSsFk() {
		return ssFk;
	}

	public void setSsFk(int ssFk) {
		this.ssFk = ssFk;
	}

	public String getIroName() {
		return iroName;
	}

	public void setIroName(String iroName) {
		this.iroName = iroName;
	}

	public String getIroPhoneNumber() {
		return iroPhoneNumber;
	}

	public void setIroPhoneNumber(String iroPhoneNumber) {
		this.iroPhoneNumber = iroPhoneNumber;
	}

	public String getIroDetail() {
		return iroDetail;
	}

	public void setIroDetail(String iroDetail) {
		this.iroDetail = iroDetail;
	}

	public boolean isIroFlag() {
		return iroFlag;
	}

	public void setIroFlag(boolean iroFlag) {
		this.iroFlag = iroFlag;
	}

	public Timestamp getIroRegdate() {
		return iroRegdate;
	}

	public void setIroRegdate(Timestamp iroRegdate) {
		this.iroRegdate = iroRegdate;
	}

	@Override
	public String toString() {
		return "IrregularOrderVO [iroPk=" + iroPk + ", ssFk=" + ssFk + ", iroName=" + iroName + ", iroPhoneNumber="
				+ iroPhoneNumber + ", iroDetail=" + iroDetail + ", iroFlag=" + iroFlag + ", iroRegdate=" + iroRegdate
				+ "]";
	}

}
