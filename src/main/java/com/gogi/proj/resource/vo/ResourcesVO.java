package com.gogi.proj.resource.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ResourcesVO {
	
	private int resPk; //자재 고유 번호
	private int rcFk; //자재 분류코드 고유 번호
	private String resName; //자재명
	private int resCost; //자재원가
	private String resCompany; //자재 제조사
	private String resCompanyContractNumber; // 제조사 번호
	private String resCompanyAddress; //제조사 주소
	private String resStock; //자재 재고
	private boolean resStockAlarmFlag; //자재 재고 알람 여부
	private int resStockMaxAlarm; //자재 재고 알람 개수
	private int resWidthSize; //자재 가로 길이
	private int resHeightSize; //자재 높이
	private int resLenghtSize; //자재 세로 길이
	private String resRemark; //자재 비고사항
	private String resThumbnail1; //자재 사진1
	private String resThumbnail2; //자재 사진2
	private Timestamp resUpdate; //자재 수정
	private Date resRegdate; // 자재 등록일
	
	public ResourcesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourcesVO(int resPk, int rcFk, String resName, int resCost, String resCompany,
			String resCompanyContractNumber, String resCompanyAddress, String resStock, boolean resStockAlarmFlag,
			int resStockMaxAlarm, int resWidthSize, int resHeightSize, int resLenghtSize, String resRemark,
			String resThumbnail1, String resThumbnail2, Timestamp resUpdate, Date resRegdate) {
		super();
		this.resPk = resPk;
		this.rcFk = rcFk;
		this.resName = resName;
		this.resCost = resCost;
		this.resCompany = resCompany;
		this.resCompanyContractNumber = resCompanyContractNumber;
		this.resCompanyAddress = resCompanyAddress;
		this.resStock = resStock;
		this.resStockAlarmFlag = resStockAlarmFlag;
		this.resStockMaxAlarm = resStockMaxAlarm;
		this.resWidthSize = resWidthSize;
		this.resHeightSize = resHeightSize;
		this.resLenghtSize = resLenghtSize;
		this.resRemark = resRemark;
		this.resThumbnail1 = resThumbnail1;
		this.resThumbnail2 = resThumbnail2;
		this.resUpdate = resUpdate;
		this.resRegdate = resRegdate;
	}

	public int getResPk() {
		return resPk;
	}

	public void setResPk(int resPk) {
		this.resPk = resPk;
	}

	public int getRcFk() {
		return rcFk;
	}

	public void setRcFk(int rcFk) {
		this.rcFk = rcFk;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public int getResCost() {
		return resCost;
	}

	public void setResCost(int resCost) {
		this.resCost = resCost;
	}

	public String getResCompany() {
		return resCompany;
	}

	public void setResCompany(String resCompany) {
		this.resCompany = resCompany;
	}

	public String getResCompanyContractNumber() {
		return resCompanyContractNumber;
	}

	public void setResCompanyContractNumber(String resCompanyContractNumber) {
		this.resCompanyContractNumber = resCompanyContractNumber;
	}

	public String getResCompanyAddress() {
		return resCompanyAddress;
	}

	public void setResCompanyAddress(String resCompanyAddress) {
		this.resCompanyAddress = resCompanyAddress;
	}

	public String getResStock() {
		return resStock;
	}

	public void setResStock(String resStock) {
		this.resStock = resStock;
	}

	public boolean isResStockAlarmFlag() {
		return resStockAlarmFlag;
	}

	public void setResStockAlarmFlag(boolean resStockAlarmFlag) {
		this.resStockAlarmFlag = resStockAlarmFlag;
	}

	public int getResStockMaxAlarm() {
		return resStockMaxAlarm;
	}

	public void setResStockMaxAlarm(int resStockMaxAlarm) {
		this.resStockMaxAlarm = resStockMaxAlarm;
	}

	public int getResWidthSize() {
		return resWidthSize;
	}

	public void setResWidthSize(int resWidthSize) {
		this.resWidthSize = resWidthSize;
	}

	public int getResHeightSize() {
		return resHeightSize;
	}

	public void setResHeightSize(int resHeightSize) {
		this.resHeightSize = resHeightSize;
	}

	public int getResLenghtSize() {
		return resLenghtSize;
	}

	public void setResLenghtSize(int resLenghtSize) {
		this.resLenghtSize = resLenghtSize;
	}

	public String getResRemark() {
		return resRemark;
	}

	public void setResRemark(String resRemark) {
		this.resRemark = resRemark;
	}

	public String getResThumbnail1() {
		return resThumbnail1;
	}

	public void setResThumbnail1(String resThumbnail1) {
		this.resThumbnail1 = resThumbnail1;
	}

	public String getResThumbnail2() {
		return resThumbnail2;
	}

	public void setResThumbnail2(String resThumbnail2) {
		this.resThumbnail2 = resThumbnail2;
	}

	public Timestamp getResUpdate() {
		return resUpdate;
	}

	public void setResUpdate(Timestamp resUpdate) {
		this.resUpdate = resUpdate;
	}

	public Date getResRegdate() {
		return resRegdate;
	}

	public void setResRegdate(Date resRegdate) {
		this.resRegdate = resRegdate;
	}

	@Override
	public String toString() {
		return "ResourcesVO [resPk=" + resPk + ", rcFk=" + rcFk + ", resName=" + resName + ", resCost=" + resCost
				+ ", resCompany=" + resCompany + ", resCompanyContractNumber=" + resCompanyContractNumber
				+ ", resCompanyAddress=" + resCompanyAddress + ", resStock=" + resStock + ", resStockAlarmFlag="
				+ resStockAlarmFlag + ", resStockMaxAlarm=" + resStockMaxAlarm + ", resWidthSize=" + resWidthSize
				+ ", resHeightSize=" + resHeightSize + ", resLenghtSize=" + resLenghtSize + ", resRemark=" + resRemark
				+ ", resThumbnail1=" + resThumbnail1 + ", resThumbnail2=" + resThumbnail2 + ", resUpdate=" + resUpdate
				+ ", resRegdate=" + resRegdate + "]";
	}

}
