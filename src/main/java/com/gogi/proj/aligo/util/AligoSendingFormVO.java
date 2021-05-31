package com.gogi.proj.aligo.util;

public class AligoSendingFormVO {

	
	private int asfPk;
	private String asfHead;
	private String asfBody;
	private String asfFooter;
	private String asfRegdate;
	
	public AligoSendingFormVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoSendingFormVO(int asfPk, String asfHead, String asfBody, String asfFooter, String asfRegdate) {
		super();
		this.asfPk = asfPk;
		this.asfHead = asfHead;
		this.asfBody = asfBody;
		this.asfFooter = asfFooter;
		this.asfRegdate = asfRegdate;
	}

	public int getAsfPk() {
		return asfPk;
	}

	public void setAsfPk(int asfPk) {
		this.asfPk = asfPk;
	}

	public String getAsfHead() {
		return asfHead;
	}

	public void setAsfHead(String asfHead) {
		this.asfHead = asfHead;
	}

	public String getAsfBody() {
		return asfBody;
	}

	public void setAsfBody(String asfBody) {
		this.asfBody = asfBody;
	}

	public String getAsfFooter() {
		return asfFooter;
	}

	public void setAsfFooter(String asfFooter) {
		this.asfFooter = asfFooter;
	}

	public String getAsfRegdate() {
		return asfRegdate;
	}

	public void setAsfRegdate(String asfRegdate) {
		this.asfRegdate = asfRegdate;
	}

	@Override
	public String toString() {
		return "AligoSendingFormVO [asfPk=" + asfPk + ", asfHead=" + asfHead + ", asfBody=" + asfBody + ", asfFooter="
				+ asfFooter + ", asfRegdate=" + asfRegdate + "]";
	}
	
}
