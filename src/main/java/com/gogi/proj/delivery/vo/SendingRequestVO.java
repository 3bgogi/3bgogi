package com.gogi.proj.delivery.vo;

import java.sql.Timestamp;

public class SendingRequestVO {

	private int srPk;					//고유값
	private String srInvoiceNum;		//송장번호 -  fk값
	private String srAdminId;			//관리자 아이디
	private String srAdminName;			//관리자 이름
	private String srReason;			//강제출고 사유
	private boolean srCheckFlag;		//강제출고 메세지 확인 여부
	private boolean srSendingFlag;		//발송 여부
	private String srRegdate;			//요청일(등록일)
	
	public SendingRequestVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendingRequestVO(int srPk, String srInvoiceNum, String srAdminId, String srAdminName, String srReason,
			boolean srCheckFlag, boolean srSendingFlag, String srRegdate) {
		super();
		this.srPk = srPk;
		this.srInvoiceNum = srInvoiceNum;
		this.srAdminId = srAdminId;
		this.srAdminName = srAdminName;
		this.srReason = srReason;
		this.srCheckFlag = srCheckFlag;
		this.srSendingFlag = srSendingFlag;
		this.srRegdate = srRegdate;
	}

	public int getSrPk() {
		return srPk;
	}

	public void setSrPk(int srPk) {
		this.srPk = srPk;
	}

	public String getSrInvoiceNum() {
		return srInvoiceNum;
	}

	public void setSrInvoiceNum(String srInvoiceNum) {
		this.srInvoiceNum = srInvoiceNum;
	}

	public String getSrAdminId() {
		return srAdminId;
	}

	public void setSrAdminId(String srAdminId) {
		this.srAdminId = srAdminId;
	}

	public String getSrAdminName() {
		return srAdminName;
	}

	public void setSrAdminName(String srAdminName) {
		this.srAdminName = srAdminName;
	}

	public String getSrReason() {
		return srReason;
	}

	public void setSrReason(String srReason) {
		this.srReason = srReason;
	}

	public boolean isSrCheckFlag() {
		return srCheckFlag;
	}

	public void setSrCheckFlag(boolean srCheckFlag) {
		this.srCheckFlag = srCheckFlag;
	}

	public boolean isSrSendingFlag() {
		return srSendingFlag;
	}

	public void setSrSendingFlag(boolean srSendingFlag) {
		this.srSendingFlag = srSendingFlag;
	}

	public String getSrRegdate() {
		return srRegdate;
	}

	public void setSrRegdate(String srRegdate) {
		this.srRegdate = srRegdate;
	}

	@Override
	public String toString() {
		return "SendingRequestVO [srPk=" + srPk + ", srInvoiceNum=" + srInvoiceNum + ", srAdminId=" + srAdminId
				+ ", srAdminName=" + srAdminName + ", srReason=" + srReason + ", srCheckFlag=" + srCheckFlag
				+ ", srSendingFlag=" + srSendingFlag + ", srRegdate=" + srRegdate + "]";
	}

}
