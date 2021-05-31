package com.gogi.proj.product.cost.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.classification.code.vo.CostCodeVO;

public class CostDetailVO {

	private int cdPk;						//원가 세부사항 고유 번호
	private int ccFk;						// 원가 분류 고유 번호
	private String cdName; 					//원가명
	private int cdCost;						//원가
	private boolean cdLossFlag;				//손실 여부
	private int cdLossRate;					//손실률
	private boolean cdCompanyDiagnosis;		//제조사, 판매처 구분
	private String cdManufacturer;			//제조사명
	private String cdStoreCompany;			//판매사명
	private String cdMeasure;				//원가의 단위 EA, kg, mL
	private String cdRemark;				//특이사항, 비고사항
	private Timestamp cdUpdate;				//수정일
	private Date cdRegdate;					// 등록일
	
	//추가사항
	private List<CostCodeVO> costCodeVOList;
	private List<CostIoVO> costIoVOList;
	
	public CostDetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostDetailVO(int cdPk, int ccFk, String cdName, int cdCost, boolean cdLossFlag, int cdLossRate,
			boolean cdCompanyDiagnosis, String cdManufacturer, String cdStoreCompany, String cdMeasure, String cdRemark,
			Timestamp cdUpdate, Date cdRegdate, List<CostCodeVO> costCodeVOList, List<CostIoVO> costIoVOList) {
		super();
		this.cdPk = cdPk;
		this.ccFk = ccFk;
		this.cdName = cdName;
		this.cdCost = cdCost;
		this.cdLossFlag = cdLossFlag;
		this.cdLossRate = cdLossRate;
		this.cdCompanyDiagnosis = cdCompanyDiagnosis;
		this.cdManufacturer = cdManufacturer;
		this.cdStoreCompany = cdStoreCompany;
		this.cdMeasure = cdMeasure;
		this.cdRemark = cdRemark;
		this.cdUpdate = cdUpdate;
		this.cdRegdate = cdRegdate;
		this.costCodeVOList = costCodeVOList;
		this.costIoVOList = costIoVOList;
	}

	public List<CostIoVO> getCostIoVOList() {
		return costIoVOList;
	}

	public void setCostIoVOList(List<CostIoVO> costIoVOList) {
		this.costIoVOList = costIoVOList;
	}

	public int getCdPk() {
		return cdPk;
	}

	public void setCdPk(int cdPk) {
		this.cdPk = cdPk;
	}

	public int getCcFk() {
		return ccFk;
	}

	public void setCcFk(int ccFk) {
		this.ccFk = ccFk;
	}

	public String getCdName() {
		return cdName;
	}

	public void setCdName(String cdName) {
		this.cdName = cdName;
	}

	public int getCdCost() {
		return cdCost;
	}

	public void setCdCost(int cdCost) {
		this.cdCost = cdCost;
	}

	public boolean isCdLossFlag() {
		return cdLossFlag;
	}

	public void setCdLossFlag(boolean cdLossFlag) {
		this.cdLossFlag = cdLossFlag;
	}

	public int getCdLossRate() {
		return cdLossRate;
	}

	public void setCdLossRate(int cdLossRate) {
		this.cdLossRate = cdLossRate;
	}

	public boolean isCdCompanyDiagnosis() {
		return cdCompanyDiagnosis;
	}

	public void setCdCompanyDiagnosis(boolean cdCompanyDiagnosis) {
		this.cdCompanyDiagnosis = cdCompanyDiagnosis;
	}

	public String getCdManufacturer() {
		return cdManufacturer;
	}

	public void setCdManufacturer(String cdManufacturer) {
		this.cdManufacturer = cdManufacturer;
	}

	public String getCdStoreCompany() {
		return cdStoreCompany;
	}

	public void setCdStoreCompany(String cdStoreCompany) {
		this.cdStoreCompany = cdStoreCompany;
	}

	public String getCdMeasure() {
		return cdMeasure;
	}

	public void setCdMeasure(String cdMeasure) {
		this.cdMeasure = cdMeasure;
	}

	public String getCdRemark() {
		return cdRemark;
	}

	public void setCdRemark(String cdRemark) {
		this.cdRemark = cdRemark;
	}

	public Timestamp getCdUpdate() {
		return cdUpdate;
	}

	public void setCdUpdate(Timestamp cdUpdate) {
		this.cdUpdate = cdUpdate;
	}

	public Date getCdRegdate() {
		return cdRegdate;
	}

	public void setCdRegdate(Date cdRegdate) {
		this.cdRegdate = cdRegdate;
	}

	public List<CostCodeVO> getCostCodeVOList() {
		return costCodeVOList;
	}

	public void setCostCodeVOList(List<CostCodeVO> costCodeVOList) {
		this.costCodeVOList = costCodeVOList;
	}

	@Override
	public String toString() {
		return "CostDetailVO [cdPk=" + cdPk + ", ccFk=" + ccFk + ", cdName=" + cdName + ", cdCost=" + cdCost
				+ ", cdLossFlag=" + cdLossFlag + ", cdLossRate=" + cdLossRate + ", cdCompanyDiagnosis="
				+ cdCompanyDiagnosis + ", cdManufacturer=" + cdManufacturer + ", cdStoreCompany=" + cdStoreCompany
				+ ", cdMeasure=" + cdMeasure + ", cdRemark=" + cdRemark + ", cdUpdate=" + cdUpdate + ", cdRegdate="
				+ cdRegdate + ", costCodeVOList=" + costCodeVOList + ", costIoVOList=" + costIoVOList + "]";
	}
	
}
