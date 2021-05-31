package com.gogi.proj.admin.attendance.vo;

import java.sql.Timestamp;

public class AdminAttendanceVO {

	private int aaPk; //출결 고유 번호
	private int adminFk; //관리자 고유 번호
	private Timestamp aaWorkStart; //출근시각
	private Timestamp aaWorkEnd; //퇴근 시각
	private int aaBreakTime; // 휴게시간
	
	//그 외 관련 변수
	// 날짜 조회용
	private String workYear;
	private String workMonth;
	
	//일한 시간 조회용
	private String workTime;
	
	//휴무 타입 조회용
	private Timestamp dcDate;
	private String workBreaks;
	
	//종합 조회용
	private int adminPk;
	private String jcType;
	private int jcFk;
	private String adminName;
	private int adType;
	private String adTitle;
	
	//출근 순위
	private int attendance_rank;

	public AdminAttendanceVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminAttendanceVO(int aaPk, int adminFk, Timestamp aaWorkStart, Timestamp aaWorkEnd, String workYear,
			String workMonth, String workTime, Timestamp dcDate, String workBreaks, int adminPk, String jcType,
			int jcFk, String adminName, int adType, String adTitle, int attendance_rank) {
		super();
		this.aaPk = aaPk;
		this.adminFk = adminFk;
		this.aaWorkStart = aaWorkStart;
		this.aaWorkEnd = aaWorkEnd;
		this.workYear = workYear;
		this.workMonth = workMonth;
		this.workTime = workTime;
		this.dcDate = dcDate;
		this.workBreaks = workBreaks;
		this.adminPk = adminPk;
		this.jcType = jcType;
		this.jcFk = jcFk;
		this.adminName = adminName;
		this.adType = adType;
		this.adTitle = adTitle;
		this.attendance_rank = attendance_rank;
	}
	
	public int getAaBreakTime() {
		return aaBreakTime;
	}

	public void setAaBreakTime(int aaBreakTime) {
		this.aaBreakTime = aaBreakTime;
	}

	public int getAaPk() {
		return aaPk;
	}

	public void setAaPk(int aaPk) {
		this.aaPk = aaPk;
	}

	public int getAdminFk() {
		return adminFk;
	}

	public void setAdminFk(int adminFk) {
		this.adminFk = adminFk;
	}

	public Timestamp getAaWorkStart() {
		return aaWorkStart;
	}

	public void setAaWorkStart(Timestamp aaWorkStart) {
		this.aaWorkStart = aaWorkStart;
	}

	public Timestamp getAaWorkEnd() {
		return aaWorkEnd;
	}

	public void setAaWorkEnd(Timestamp aaWorkEnd) {
		this.aaWorkEnd = aaWorkEnd;
	}

	public String getWorkYear() {
		return workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public String getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public Timestamp getDcDate() {
		return dcDate;
	}

	public void setDcDate(Timestamp dcDate) {
		this.dcDate = dcDate;
	}

	public String getWorkBreaks() {
		return workBreaks;
	}

	public void setWorkBreaks(String workBreaks) {
		this.workBreaks = workBreaks;
	}

	public int getAdminPk() {
		return adminPk;
	}

	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public int getJcFk() {
		return jcFk;
	}

	public void setJcFk(int jcFk) {
		this.jcFk = jcFk;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public int getAttendance_rank() {
		return attendance_rank;
	}

	public void setAttendance_rank(int attendance_rank) {
		this.attendance_rank = attendance_rank;
	}

	@Override
	public String toString() {
		return "AdminAttendanceVO [aaPk=" + aaPk + ", adminFk=" + adminFk + ", aaWorkStart=" + aaWorkStart
				+ ", aaWorkEnd=" + aaWorkEnd + ", workYear=" + workYear + ", workMonth=" + workMonth + ", workTime="
				+ workTime + ", dcDate=" + dcDate + ", workBreaks=" + workBreaks + ", adminPk=" + adminPk + ", jcType="
				+ jcType + ", jcFk=" + jcFk + ", adminName=" + adminName + ", adType=" + adType + ", adTitle=" + adTitle
				+ ", attendance_rank=" + attendance_rank + "]";
	}
	
}
