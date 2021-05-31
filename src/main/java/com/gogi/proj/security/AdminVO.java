package com.gogi.proj.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminVO implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String adminname;
	
	private int adminPk;
	private int jcFk;
	private String adminAddress;
	private String adminId;
	private String adminName;
	private String adminPhone;
	private String adminPass;
	private int enabled;
	private String role;
	private String adminWorktime;
	
	//그외
	private String jcType;
	//업무대상자 확인
	private boolean proInc;
	
	public AdminVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminVO(int adminPk, int jcFk, String adminName, int enabled, String jcType, boolean proInc) {
		super();
		this.adminPk = adminPk;
		this.jcFk = jcFk;
		this.adminName = adminName;
		this.enabled = enabled;
		this.jcType = jcType;
		this.proInc = proInc;
	}

	public int getJcFk() {
		return jcFk;
	}

	public void setJcFk(int jcFk) {
		this.jcFk = jcFk;
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public String getAdminWorktime() {
		return adminWorktime;
	}

	public void setAdminWorktime(String adminWorktime) {
		this.adminWorktime = adminWorktime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAdminPk() {
		return adminPk;
	}
	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private List<String> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> grants = new ArrayList<SimpleGrantedAuthority>();
		
		for(String role : roles) {
			grants.add(new SimpleGrantedAuthority(role));
		}
		
		return grants;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "AdminVO [username=" + username + ", password=" + password + ", adminname=" + adminname + ", adminPk="
				+ adminPk + ", jcFk=" + jcFk + ", adminAddress=" + adminAddress + ", adminId=" + adminId
				+ ", adminName=" + adminName + ", adminPhone=" + adminPhone + ", adminPass=" + adminPass + ", enabled="
				+ enabled + ", role=" + role + ", adminWorktime=" + adminWorktime + ", jcType=" + jcType + ", proInc="
				+ proInc + ", roles=" + roles + "]";
	}


}
