package com.gogi.proj.admin.project.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ProjectTargetVO {

	private int ptPk; //업무 대상자 고유 번호
	private int ptProFk; //업무 고유 번호
	private int ptAdminFk; // 관리자 고유 번호
	private boolean ptDisplayFlag; // 업무 검수자 여부
	private boolean ptCreateFlag; // 업무 만든이 여부
	private Date ptAlarmCheckDate; // 알람 체크일
	private boolean ptTopAlarmFlag; // 상단 고정 여부
	private Timestamp ptRegdate; // 업무대상자 등록일 db상으로는 DATETIME으로 구성.
	
	//추가사항
	//자기 확인용 
	private boolean proIdentify;
	private String adminName;
	private boolean proInc;
	
	public ProjectTargetVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectTargetVO(int ptPk, int ptProFk, int ptAdminFk, boolean ptDisplayFlag, boolean ptCreateFlag,
			Date ptAlarmCheckDate, boolean ptTopAlarmFlag, Timestamp ptRegdate, boolean proIdentify, String adminName,
			boolean proInc) {
		super();
		this.ptPk = ptPk;
		this.ptProFk = ptProFk;
		this.ptAdminFk = ptAdminFk;
		this.ptDisplayFlag = ptDisplayFlag;
		this.ptCreateFlag = ptCreateFlag;
		this.ptAlarmCheckDate = ptAlarmCheckDate;
		this.ptTopAlarmFlag = ptTopAlarmFlag;
		this.ptRegdate = ptRegdate;
		this.proIdentify = proIdentify;
		this.adminName = adminName;
		this.proInc = proInc;
	}

	public int getPtPk() {
		return ptPk;
	}

	public void setPtPk(int ptPk) {
		this.ptPk = ptPk;
	}

	public int getPtProFk() {
		return ptProFk;
	}

	public void setPtProFk(int ptProFk) {
		this.ptProFk = ptProFk;
	}

	public int getPtAdminFk() {
		return ptAdminFk;
	}

	public void setPtAdminFk(int ptAdminFk) {
		this.ptAdminFk = ptAdminFk;
	}

	public boolean isPtDisplayFlag() {
		return ptDisplayFlag;
	}

	public void setPtDisplayFlag(boolean ptDisplayFlag) {
		this.ptDisplayFlag = ptDisplayFlag;
	}

	public boolean isPtCreateFlag() {
		return ptCreateFlag;
	}

	public void setPtCreateFlag(boolean ptCreateFlag) {
		this.ptCreateFlag = ptCreateFlag;
	}

	public Date getPtAlarmCheckDate() {
		return ptAlarmCheckDate;
	}

	public void setPtAlarmCheckDate(Date ptAlarmCheckDate) {
		this.ptAlarmCheckDate = ptAlarmCheckDate;
	}

	public boolean isPtTopAlarmFlag() {
		return ptTopAlarmFlag;
	}

	public void setPtTopAlarmFlag(boolean ptTopAlarmFlag) {
		this.ptTopAlarmFlag = ptTopAlarmFlag;
	}

	public Timestamp getPtRegdate() {
		return ptRegdate;
	}

	public void setPtRegdate(Timestamp ptRegdate) {
		this.ptRegdate = ptRegdate;
	}

	public boolean isProIdentify() {
		return proIdentify;
	}

	public void setProIdentify(boolean proIdentify) {
		this.proIdentify = proIdentify;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public boolean isProInc() {
		return proInc;
	}

	public void setProInc(boolean proInc) {
		this.proInc = proInc;
	}

	@Override
	public String toString() {
		return "ProjectTargetVO [ptPk=" + ptPk + ", ptProFk=" + ptProFk + ", ptAdminFk=" + ptAdminFk
				+ ", ptDisplayFlag=" + ptDisplayFlag + ", ptCreateFlag=" + ptCreateFlag + ", ptAlarmCheckDate="
				+ ptAlarmCheckDate + ", ptTopAlarmFlag=" + ptTopAlarmFlag + ", ptRegdate=" + ptRegdate
				+ ", proIdentify=" + proIdentify + ", adminName=" + adminName + ", proInc=" + proInc + "]";
	}

}
