package com.gogi.proj.another.vo;

public class DatesVO {
	
	//날짜 관련 변수 받아오는 클래스
	
	//출퇴근 기록 로그용
	private String workStart;
	private String workEnd;
	
	//날짜 반환 후 반환 타입
	private String formatYear;
	private String formatMonth;
	private String formatDate;
	
	// 관리자 고유 번호
	private int adminPk;
	
	public DatesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatesVO(String workStart, String workEnd, String formatYear, String formatMonth, String formatDate,
			int adminPk) {
		super();
		this.workStart = workStart;
		this.workEnd = workEnd;
		this.formatYear = formatYear;
		this.formatMonth = formatMonth;
		this.formatDate = formatDate;
		this.adminPk = adminPk;
	}

	public String getWorkStart() {
		return workStart;
	}

	public void setWorkStart(String workStart) {
		this.workStart = workStart;
	}

	public String getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(String workEnd) {
		this.workEnd = workEnd;
	}

	public String getFormatYear() {
		return formatYear;
	}

	public void setFormatYear(String formatYear) {
		this.formatYear = formatYear;
	}

	public String getFormatMonth() {
		return formatMonth;
	}

	public void setFormatMonth(String formatMonth) {
		this.formatMonth = formatMonth;
	}

	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public int getAdminPk() {
		return adminPk;
	}

	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}

	@Override
	public String toString() {
		return "DatesVO [workStart=" + workStart + ", workEnd=" + workEnd + ", formatYear=" + formatYear
				+ ", formatMonth=" + formatMonth + ", formatDate=" + formatDate + ", adminPk=" + adminPk + "]";
	}

}
