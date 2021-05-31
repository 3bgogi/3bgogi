package com.gogi.proj.delivery.config.vo;

import java.util.List;

public class EarlyDelivAreaVO {

	private int edaPk;					//지역고유값
	private int edtFk;					//빠른배송고유값
	private String edaZipCode;			//우편번호
	private boolean edaPosFlag;			//배송가능체크 기본값 true
	private int delivCount;				//배송 불가 총 개수
	private List<DelivImposVO> diList;
	
	public EarlyDelivAreaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EarlyDelivAreaVO(int edaPk, int edtFk, String edaZipCode, boolean edaPosFlag, int delivCount,
			List<DelivImposVO> diList) {
		super();
		this.edaPk = edaPk;
		this.edtFk = edtFk;
		this.edaZipCode = edaZipCode;
		this.edaPosFlag = edaPosFlag;
		this.delivCount = delivCount;
		this.diList = diList;
	}

	public int getEdaPk() {
		return edaPk;
	}

	public void setEdaPk(int edaPk) {
		this.edaPk = edaPk;
	}

	public int getEdtFk() {
		return edtFk;
	}

	public void setEdtFk(int edtFk) {
		this.edtFk = edtFk;
	}

	public String getEdaZipCode() {
		return edaZipCode;
	}

	public void setEdaZipCode(String edaZipCode) {
		this.edaZipCode = edaZipCode;
	}

	public boolean isEdaPosFlag() {
		return edaPosFlag;
	}

	public void setEdaPosFlag(boolean edaPosFlag) {
		this.edaPosFlag = edaPosFlag;
	}

	public int getDelivCount() {
		return delivCount;
	}

	public void setDelivCount(int delivCount) {
		this.delivCount = delivCount;
	}

	public List<DelivImposVO> getDiList() {
		return diList;
	}

	public void setDiList(List<DelivImposVO> diList) {
		this.diList = diList;
	}

	@Override
	public String toString() {
		return "EarlyDelivAreaVO [edaPk=" + edaPk + ", edtFk=" + edtFk + ", edaZipCode=" + edaZipCode + ", edaPosFlag="
				+ edaPosFlag + ", delivCount=" + delivCount + ", diList=" + diList + "]";
	}

}
