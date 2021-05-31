package com.gogi.proj.configurations.vo;

import java.sql.Timestamp;

//판매처 묶음정리
public class StoreMergeVO {

	private int smPk; //묶음 정리 고유 번호
	private int ssFk; //판매처 고유 번호
	private boolean smOrderNumber; //주문번호
	private boolean smProductNumber; //상품 번호
	private boolean smProductOption; //상품옵션명
	private boolean smReceiverName; //수령자 이름
	private boolean smBuyerName; //구매자 이름
	private boolean smBuyerId; //구매자아이디
	private boolean smStoreName; //판매처명
	private boolean smRequest; //요구사항별
	private boolean smOrderSettlementDay; //결제일 기준
	private boolean smBuyerPhone; //구매자 핸드폰 번호
	private boolean smReceiverPhone; //수령자 핸드폰 번호
	private Timestamp smRegdate; //판매처 묶음정리 등록일
	
	public StoreMergeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreMergeVO(int smPk, int ssFk, boolean smOrderNumber, boolean smProductNumber, boolean smProductOption,
			boolean smReceiverName, boolean smBuyerName, boolean smBuyerId, boolean smStoreName, boolean smRequest,
			boolean smOrderSettlementDay, boolean smBuyerPhone, boolean smReceiverPhone, Timestamp smRegdate) {
		super();
		this.smPk = smPk;
		this.ssFk = ssFk;
		this.smOrderNumber = smOrderNumber;
		this.smProductNumber = smProductNumber;
		this.smProductOption = smProductOption;
		this.smReceiverName = smReceiverName;
		this.smBuyerName = smBuyerName;
		this.smBuyerId = smBuyerId;
		this.smStoreName = smStoreName;
		this.smRequest = smRequest;
		this.smOrderSettlementDay = smOrderSettlementDay;
		this.smBuyerPhone = smBuyerPhone;
		this.smReceiverPhone = smReceiverPhone;
		this.smRegdate = smRegdate;
	}

	public int getSmPk() {
		return smPk;
	}

	public void setSmPk(int smPk) {
		this.smPk = smPk;
	}

	public int getSsFk() {
		return ssFk;
	}

	public void setSsFk(int ssFk) {
		this.ssFk = ssFk;
	}

	public boolean isSmOrderNumber() {
		return smOrderNumber;
	}

	public void setSmOrderNumber(boolean smOrderNumber) {
		this.smOrderNumber = smOrderNumber;
	}

	public boolean isSmProductNumber() {
		return smProductNumber;
	}

	public void setSmProductNumber(boolean smProductNumber) {
		this.smProductNumber = smProductNumber;
	}

	public boolean isSmProductOption() {
		return smProductOption;
	}

	public void setSmProductOption(boolean smProductOption) {
		this.smProductOption = smProductOption;
	}

	public boolean isSmReceiverName() {
		return smReceiverName;
	}

	public void setSmReceiverName(boolean smReceiverName) {
		this.smReceiverName = smReceiverName;
	}

	public boolean isSmBuyerName() {
		return smBuyerName;
	}

	public void setSmBuyerName(boolean smBuyerName) {
		this.smBuyerName = smBuyerName;
	}

	public boolean isSmBuyerId() {
		return smBuyerId;
	}

	public void setSmBuyerId(boolean smBuyerId) {
		this.smBuyerId = smBuyerId;
	}

	public boolean isSmStoreName() {
		return smStoreName;
	}

	public void setSmStoreName(boolean smStoreName) {
		this.smStoreName = smStoreName;
	}

	public boolean isSmRequest() {
		return smRequest;
	}

	public void setSmRequest(boolean smRequest) {
		this.smRequest = smRequest;
	}

	public boolean isSmOrderSettlementDay() {
		return smOrderSettlementDay;
	}

	public void setSmOrderSettlementDay(boolean smOrderSettlementDay) {
		this.smOrderSettlementDay = smOrderSettlementDay;
	}

	public boolean isSmBuyerPhone() {
		return smBuyerPhone;
	}

	public void setSmBuyerPhone(boolean smBuyerPhone) {
		this.smBuyerPhone = smBuyerPhone;
	}

	public boolean isSmReceiverPhone() {
		return smReceiverPhone;
	}

	public void setSmReceiverPhone(boolean smReceiverPhone) {
		this.smReceiverPhone = smReceiverPhone;
	}

	public Timestamp getSmRegdate() {
		return smRegdate;
	}

	public void setSmRegdate(Timestamp smRegdate) {
		this.smRegdate = smRegdate;
	}

	@Override
	public String toString() {
		return "StoreMergeVO [smPk=" + smPk + ", ssFk=" + ssFk + ", smOrderNumber=" + smOrderNumber
				+ ", smProductNumber=" + smProductNumber + ", smProductOption=" + smProductOption + ", smReceiverName="
				+ smReceiverName + ", smBuyerName=" + smBuyerName + ", smBuyerId=" + smBuyerId + ", smStoreName="
				+ smStoreName + ", smRequest=" + smRequest + ", smOrderSettlementDay=" + smOrderSettlementDay
				+ ", smBuyerPhone=" + smBuyerPhone + ", smReceiverPhone=" + smReceiverPhone + ", smRegdate=" + smRegdate
				+ "]";
	}
	
}
