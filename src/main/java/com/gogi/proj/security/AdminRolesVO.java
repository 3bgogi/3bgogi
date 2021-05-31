package com.gogi.proj.security;

public class AdminRolesVO {
	
	private int adminRolePk;
	private String adminId;
	private String role;
	
	public AdminRolesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminRolesVO(int adminRolePk, String adminId, String role) {
		super();
		this.adminRolePk = adminRolePk;
		this.adminId = adminId;
		this.role = role;
	}

	public int getAdminRolePk() {
		return adminRolePk;
	}

	public void setAdminRolePk(int adminRolePk) {
		this.adminRolePk = adminRolePk;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminRolesVO [adminRolePk=" + adminRolePk + ", adminId=" + adminId + ", role=" + role + "]";
	}
	
}
