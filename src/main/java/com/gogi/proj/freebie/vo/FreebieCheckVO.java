package com.gogi.proj.freebie.vo;

public class FreebieCheckVO {

	private int fcPk;
	private int fbFk;
	private String fcOrderTarget;
	private int fcType;
	
	public FreebieCheckVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreebieCheckVO(int fcPk, int fbFk, String fcOrderTarget, int fcType) {
		super();
		this.fcPk = fcPk;
		this.fbFk = fbFk;
		this.fcOrderTarget = fcOrderTarget;
		this.fcType = fcType;
	}

	public int getFcPk() {
		return fcPk;
	}

	public void setFcPk(int fcPk) {
		this.fcPk = fcPk;
	}

	public int getFbFk() {
		return fbFk;
	}

	public void setFbFk(int fbFk) {
		this.fbFk = fbFk;
	}

	public String getFcOrderTarget() {
		return fcOrderTarget;
	}

	public void setFcOrderTarget(String fcOrderTarget) {
		this.fcOrderTarget = fcOrderTarget;
	}

	public int getFcType() {
		return fcType;
	}

	public void setFcType(int fcType) {
		this.fcType = fcType;
	}

	@Override
	public String toString() {
		return "FreebieCheckVO [fcPk=" + fcPk + ", fbFk=" + fbFk + ", fcOrderTarget=" + fcOrderTarget + ", fcType="
				+ fcType + "]";
	}
	
}
