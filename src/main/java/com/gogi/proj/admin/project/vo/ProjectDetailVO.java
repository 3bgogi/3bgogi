package com.gogi.proj.admin.project.vo;

import java.sql.Timestamp;

public class ProjectDetailVO {

	private int pdPk; //업무 상세사항 고유 번호
	private int pdProFk; //업무 대상자 고유 번호
	private String pdDetail; // 업무 완료 사항
	private String pdFile1; //첨부 파일1
	private String pdFileRealName;
	private String pdFileExtType;
	private boolean pdDisplayFlag; // 보이기 여부
	private boolean pdImportant;
	private int pdCheckAdminPk; // 업무 삭제 시 본인 확인용
	private int pdSorting; // 업무 정렬
	private Timestamp pdFinishTime; //업무 완료일
	
	//추가사항
	private String pdAdmin;
	
	public ProjectDetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectDetailVO(int pdPk, int pdProFk, String pdDetail, String pdFile1, String pdFileRealName,
			String pdFileExtType, boolean pdDisplayFlag, boolean pdImportant, int pdCheckAdminPk, int pdSorting,
			Timestamp pdFinishTime, String pdAdmin) {
		super();
		this.pdPk = pdPk;
		this.pdProFk = pdProFk;
		this.pdDetail = pdDetail;
		this.pdFile1 = pdFile1;
		this.pdFileRealName = pdFileRealName;
		this.pdFileExtType = pdFileExtType;
		this.pdDisplayFlag = pdDisplayFlag;
		this.pdImportant = pdImportant;
		this.pdCheckAdminPk = pdCheckAdminPk;
		this.pdSorting = pdSorting;
		this.pdFinishTime = pdFinishTime;
		this.pdAdmin = pdAdmin;
	}

	public int getPdPk() {
		return pdPk;
	}

	public void setPdPk(int pdPk) {
		this.pdPk = pdPk;
	}

	public int getPdProFk() {
		return pdProFk;
	}

	public void setPdProFk(int pdProFk) {
		this.pdProFk = pdProFk;
	}

	public String getPdDetail() {
		return pdDetail;
	}

	public void setPdDetail(String pdDetail) {
		this.pdDetail = pdDetail;
	}

	public String getPdFile1() {
		return pdFile1;
	}

	public void setPdFile1(String pdFile1) {
		this.pdFile1 = pdFile1;
	}

	public String getPdFileRealName() {
		return pdFileRealName;
	}

	public void setPdFileRealName(String pdFileRealName) {
		this.pdFileRealName = pdFileRealName;
	}

	public String getPdFileExtType() {
		return pdFileExtType;
	}

	public void setPdFileExtType(String pdFileExtType) {
		this.pdFileExtType = pdFileExtType;
	}

	public boolean isPdDisplayFlag() {
		return pdDisplayFlag;
	}

	public void setPdDisplayFlag(boolean pdDisplayFlag) {
		this.pdDisplayFlag = pdDisplayFlag;
	}

	public boolean isPdImportant() {
		return pdImportant;
	}

	public void setPdImportant(boolean pdImportant) {
		this.pdImportant = pdImportant;
	}

	public int getPdCheckAdminPk() {
		return pdCheckAdminPk;
	}

	public void setPdCheckAdminPk(int pdCheckAdminPk) {
		this.pdCheckAdminPk = pdCheckAdminPk;
	}

	public int getPdSorting() {
		return pdSorting;
	}

	public void setPdSorting(int pdSorting) {
		this.pdSorting = pdSorting;
	}

	public Timestamp getPdFinishTime() {
		return pdFinishTime;
	}

	public void setPdFinishTime(Timestamp pdFinishTime) {
		this.pdFinishTime = pdFinishTime;
	}

	public String getPdAdmin() {
		return pdAdmin;
	}

	public void setPdAdmin(String pdAdmin) {
		this.pdAdmin = pdAdmin;
	}

	@Override
	public String toString() {
		return "ProjectDetailVO [pdPk=" + pdPk + ", pdProFk=" + pdProFk + ", pdDetail=" + pdDetail + ", pdFile1="
				+ pdFile1 + ", pdFileRealName=" + pdFileRealName + ", pdFileExtType=" + pdFileExtType
				+ ", pdDisplayFlag=" + pdDisplayFlag + ", pdImportant=" + pdImportant + ", pdCheckAdminPk="
				+ pdCheckAdminPk + ", pdSorting=" + pdSorting + ", pdFinishTime=" + pdFinishTime + ", pdAdmin="
				+ pdAdmin + "]";
	}

}
