package com.gogi.proj.security;

public class JobCodeVO {

	private int jcPk;
	private String jcType;
	
	public JobCodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobCodeVO(int jcPk, String jcType) {
		super();
		this.jcPk = jcPk;
		this.jcType = jcType;
	}

	public int getJcPk() {
		return jcPk;
	}

	public void setJcPk(int jcPk) {
		this.jcPk = jcPk;
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	@Override
	public String toString() {
		return "JobCodeVO [jcPk=" + jcPk + ", jcType=" + jcType + "]";
	}
	
}
