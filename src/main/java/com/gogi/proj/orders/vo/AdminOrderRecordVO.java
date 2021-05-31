package com.gogi.proj.orders.vo;

import java.sql.Timestamp;

public class AdminOrderRecordVO {

	private int aorPk;
	private String aorSerialSpecialNumber;
	private String aorAdminId;
	private String aorAdminName;
	private String aorReason;
	private Timestamp aorRegdate;
	
	public AdminOrderRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminOrderRecordVO(int aorPk, String aorSerialSpecialNumber, String aorAdminId, String aorAdminName,
			String aorReason, Timestamp aorRegdate) {
		super();
		this.aorPk = aorPk;
		this.aorSerialSpecialNumber = aorSerialSpecialNumber;
		this.aorAdminId = aorAdminId;
		this.aorAdminName = aorAdminName;
		this.aorReason = aorReason;
		this.aorRegdate = aorRegdate;
	}

	public int getAorPk() {
		return aorPk;
	}

	public void setAorPk(int aorPk) {
		this.aorPk = aorPk;
	}

	public String getAorSerialSpecialNumber() {
		return aorSerialSpecialNumber;
	}

	public void setAorSerialSpecialNumber(String aorSerialSpecialNumber) {
		this.aorSerialSpecialNumber = aorSerialSpecialNumber;
	}

	public String getAorAdminId() {
		return aorAdminId;
	}

	public void setAorAdminId(String aorAdminId) {
		this.aorAdminId = aorAdminId;
	}

	public String getAorAdminName() {
		return aorAdminName;
	}

	public void setAorAdminName(String aorAdminName) {
		this.aorAdminName = aorAdminName;
	}

	public String getAorReason() {
		return aorReason;
	}

	public void setAorReason(String aorReason) {
		this.aorReason = aorReason;
	}

	public Timestamp getAorRegdate() {
		return aorRegdate;
	}

	public void setAorRegdate(Timestamp aorRegdate) {
		this.aorRegdate = aorRegdate;
	}

	@Override
	public String toString() {
		return "AdminOrderRecordVO [aorPk=" + aorPk + ", aorSerialSpecialNumber=" + aorSerialSpecialNumber
				+ ", aorAdminId=" + aorAdminId + ", aorAdminName=" + aorAdminName + ", aorReason=" + aorReason
				+ ", aorRegdate=" + aorRegdate + "]";
	}

}
