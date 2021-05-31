package com.gogi.proj.log.vo;

public class AdminLogVO {

	private int alPk; //관리자 기록 고유번호
	private int adminFk; //관리자 고유번호
	private String alType; //기록 타입
	private String alDetail; //기록 상세사항
	private String alMacAddress; //접속 주소
	private String alRegdate; // 기록일
	
	public AdminLogVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminLogVO(int alPk, int adminFk, String alType, String alDetail, String alMacAddress, String alRegdate) {
		super();
		this.alPk = alPk;
		this.adminFk = adminFk;
		this.alType = alType;
		this.alDetail = alDetail;
		this.alMacAddress = alMacAddress;
		this.alRegdate = alRegdate;
	}

	public int getAlPk() {
		return alPk;
	}

	public void setAlPk(int alPk) {
		this.alPk = alPk;
	}

	public int getAdminFk() {
		return adminFk;
	}

	public void setAdminFk(int adminFk) {
		this.adminFk = adminFk;
	}

	public String getAlType() {
		return alType;
	}

	public void setAlType(String alType) {
		this.alType = alType;
	}

	public String getAlDetail() {
		return alDetail;
	}

	public void setAlDetail(String alDetail) {
		this.alDetail = alDetail;
	}

	public String getAlMacAddress() {
		return alMacAddress;
	}

	public void setAlMacAddress(String alMacAddress) {
		this.alMacAddress = alMacAddress;
	}

	public String getAlRegdate() {
		return alRegdate;
	}

	public void setAlRegdate(String alRegdate) {
		this.alRegdate = alRegdate;
	}

	@Override
	public String toString() {
		return "AdminLogVO [alPk=" + alPk + ", adminFk=" + adminFk + ", alType=" + alType + ", alDetail=" + alDetail
				+ ", alMacAddress=" + alMacAddress + ", alRegdate=" + alRegdate + "]";
	}

}
