package com.gogi.proj.producttax.vo;

public class ResCompanyVO {

	private int rcPk;
	private String rcName;
	private String rcNum;
	private String rcContractNum;
	private String rcRemark1;
	private String rcRemark2;
	private String rcRemark3;
	private String rcRemark4;
	private String rcRemark5;
	private String rcRemark6;
	private String rcRegdate;
	
	public ResCompanyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResCompanyVO(int rcPk, String rcName, String rcNum, String rcContractNum, String rcRemark1, String rcRemark2,
			String rcRemark3, String rcRemark4, String rcRemark5, String rcRemark6, String rcRegdate) {
		super();
		this.rcPk = rcPk;
		this.rcName = rcName;
		this.rcNum = rcNum;
		this.rcContractNum = rcContractNum;
		this.rcRemark1 = rcRemark1;
		this.rcRemark2 = rcRemark2;
		this.rcRemark3 = rcRemark3;
		this.rcRemark4 = rcRemark4;
		this.rcRemark5 = rcRemark5;
		this.rcRemark6 = rcRemark6;
		this.rcRegdate = rcRegdate;
	}

	public int getRcPk() {
		return rcPk;
	}

	public void setRcPk(int rcPk) {
		this.rcPk = rcPk;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public String getRcNum() {
		return rcNum;
	}

	public void setRcNum(String rcNum) {
		this.rcNum = rcNum;
	}

	public String getRcContractNum() {
		return rcContractNum;
	}

	public void setRcContractNum(String rcContractNum) {
		this.rcContractNum = rcContractNum;
	}

	public String getRcRemark1() {
		return rcRemark1;
	}

	public void setRcRemark1(String rcRemark1) {
		this.rcRemark1 = rcRemark1;
	}

	public String getRcRemark2() {
		return rcRemark2;
	}

	public void setRcRemark2(String rcRemark2) {
		this.rcRemark2 = rcRemark2;
	}

	public String getRcRemark3() {
		return rcRemark3;
	}

	public void setRcRemark3(String rcRemark3) {
		this.rcRemark3 = rcRemark3;
	}

	public String getRcRemark4() {
		return rcRemark4;
	}

	public void setRcRemark4(String rcRemark4) {
		this.rcRemark4 = rcRemark4;
	}

	public String getRcRemark5() {
		return rcRemark5;
	}

	public void setRcRemark5(String rcRemark5) {
		this.rcRemark5 = rcRemark5;
	}

	public String getRcRemark6() {
		return rcRemark6;
	}

	public void setRcRemark6(String rcRemark6) {
		this.rcRemark6 = rcRemark6;
	}

	public String getRcRegdate() {
		return rcRegdate;
	}

	public void setRcRegdate(String rcRegdate) {
		this.rcRegdate = rcRegdate;
	}

	@Override
	public String toString() {
		return "ResCompanyVO [rcPk=" + rcPk + ", rcName=" + rcName + ", rcNum=" + rcNum + ", rcContractNum="
				+ rcContractNum + ", rcRemark1=" + rcRemark1 + ", rcRemark2=" + rcRemark2 + ", rcRemark3=" + rcRemark3
				+ ", rcRemark4=" + rcRemark4 + ", rcRemark5=" + rcRemark5 + ", rcRemark6=" + rcRemark6 + ", rcRegdate="
				+ rcRegdate + "]";
	}
	
}
