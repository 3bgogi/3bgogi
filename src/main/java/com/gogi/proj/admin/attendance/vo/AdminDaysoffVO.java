package com.gogi.proj.admin.attendance.vo;

import java.sql.Date;

public class AdminDaysoffVO {

	private int adPk; //휴무 고유 번호
	private int adminFk; //관리자 고유 번호
	private String adTitle; //휴가명
	private boolean adFlag; //휴가 승인 여부
	private int adType; //휴무 타입
	private int adWeeks; //고정 휴무 요일
	private Date adStart; //휴가 시작일
	private Date adEnd; //휴가 종료일
	private Date adRegdate; //휴가 등록일
	
	public AdminDaysoffVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDaysoffVO(int adPk, int adminFk, String adTitle, boolean adFlag, int adType, int adWeeks, Date adStart,
			Date adEnd, Date adRegdate) {
		super();
		this.adPk = adPk;
		this.adminFk = adminFk;
		this.adTitle = adTitle;
		this.adFlag = adFlag;
		this.adType = adType;
		this.adWeeks = adWeeks;
		this.adStart = adStart;
		this.adEnd = adEnd;
		this.adRegdate = adRegdate;
	}

	public int getAdPk() {
		return adPk;
	}

	public void setAdPk(int adPk) {
		this.adPk = adPk;
	}

	public int getAdminFk() {
		return adminFk;
	}

	public void setAdminFk(int adminFk) {
		this.adminFk = adminFk;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public boolean isAdFlag() {
		return adFlag;
	}

	public void setAdFlag(boolean adFlag) {
		this.adFlag = adFlag;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	public int getAdWeeks() {
		return adWeeks;
	}

	public void setAdWeeks(int adWeeks) {
		this.adWeeks = adWeeks;
	}

	public Date getAdStart() {
		return adStart;
	}

	public void setAdStart(Date adStart) {
		this.adStart = adStart;
	}

	public Date getAdEnd() {
		return adEnd;
	}

	public void setAdEnd(Date adEnd) {
		this.adEnd = adEnd;
	}

	public Date getAdRegdate() {
		return adRegdate;
	}

	public void setAdRegdate(Date adRegdate) {
		this.adRegdate = adRegdate;
	}

	@Override
	public String toString() {
		return "AdminDaysoffVO [adPk=" + adPk + ", adminFk=" + adminFk + ", adTitle=" + adTitle + ", adFlag=" + adFlag
				+ ", adType=" + adType + ", adWeeks=" + adWeeks + ", adStart=" + adStart + ", adEnd=" + adEnd
				+ ", adRegdate=" + adRegdate + "]";
	}
	
}
