package com.gogi.proj.classification.code.vo;

import java.util.List;

public class ExcelOrderSeqVO {

	private int eosPk; //주문서 위치 고유 번호
	private int eosSeq; //주문서 순서
	private String eosLocation; //담당 주문 위치 명
	private String eosCode; //담당 위치 고유 코드
	private boolean eosExcelTotalQtyFlag; // 엑셀 주문서 총 합계 표기 여부
	
	private List<ExcelOrderSeqVO> eosList;
	
	public ExcelOrderSeqVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelOrderSeqVO(int eosPk, int eosSeq, String eosLocation, String eosCode, boolean eosExcelTotalQtyFlag,
			List<ExcelOrderSeqVO> eosList) {
		super();
		this.eosPk = eosPk;
		this.eosSeq = eosSeq;
		this.eosLocation = eosLocation;
		this.eosCode = eosCode;
		this.eosExcelTotalQtyFlag = eosExcelTotalQtyFlag;
		this.eosList = eosList;
	}

	public int getEosPk() {
		return eosPk;
	}

	public void setEosPk(int eosPk) {
		this.eosPk = eosPk;
	}

	public int getEosSeq() {
		return eosSeq;
	}

	public void setEosSeq(int eosSeq) {
		this.eosSeq = eosSeq;
	}

	public String getEosLocation() {
		return eosLocation;
	}

	public void setEosLocation(String eosLocation) {
		this.eosLocation = eosLocation;
	}

	public String getEosCode() {
		return eosCode;
	}

	public void setEosCode(String eosCode) {
		this.eosCode = eosCode;
	}

	public boolean isEosExcelTotalQtyFlag() {
		return eosExcelTotalQtyFlag;
	}

	public void setEosExcelTotalQtyFlag(boolean eosExcelTotalQtyFlag) {
		this.eosExcelTotalQtyFlag = eosExcelTotalQtyFlag;
	}

	public List<ExcelOrderSeqVO> getEosList() {
		return eosList;
	}

	public void setEosList(List<ExcelOrderSeqVO> eosList) {
		this.eosList = eosList;
	}

	@Override
	public String toString() {
		return "ExcelOrderSeqVO [eosPk=" + eosPk + ", eosSeq=" + eosSeq + ", eosLocation=" + eosLocation + ", eosCode="
				+ eosCode + ", eosExcelTotalQtyFlag=" + eosExcelTotalQtyFlag + ", eosList=" + eosList + "]";
	}
	
}
