package com.gogi.proj.security;

public class AdminNormalVO {

	private int ptPk;
	private int adminPk;
	private String adminId;
	private String adminName;
	private String adminPhone;
	private String adminAddress;
	private boolean proInc;
	
	public AdminNormalVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminNormalVO(int ptPk, int adminPk, String adminId, String adminName, String adminPhone,
			String adminAddress, boolean proInc) {
		super();
		this.ptPk = ptPk;
		this.adminPk = adminPk;
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminAddress = adminAddress;
		this.proInc = proInc;
	}

	public int getPtPk() {
		return ptPk;
	}

	public void setPtPk(int ptPk) {
		this.ptPk = ptPk;
	}

	public int getAdminPk() {
		return adminPk;
	}

	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public boolean isProInc() {
		return proInc;
	}

	public void setProInc(boolean proInc) {
		this.proInc = proInc;
	}

	@Override
	public String toString() {
		return "AdminNormalVO [ptPk=" + ptPk + ", adminPk=" + adminPk + ", adminId=" + adminId + ", adminName="
				+ adminName + ", adminPhone=" + adminPhone + ", adminAddress=" + adminAddress + ", proInc=" + proInc
				+ "]";
	}

}
