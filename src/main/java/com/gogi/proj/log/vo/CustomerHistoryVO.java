package com.gogi.proj.log.vo;

import java.sql.Timestamp;

//주문서 cs 기록
public class CustomerHistoryVO {

	
	private int chPk;						//고유값
	private String chIp;					//아이피
	private String chAdmin;					//관리자
	private String chDetail;				//상세사항
	private Timestamp chRegdate;			//기록일
	
	public CustomerHistoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerHistoryVO(int chPk, String chIp, String chAdmin, String chDetail, Timestamp chRegdate) {
		super();
		this.chPk = chPk;
		this.chIp = chIp;
		this.chAdmin = chAdmin;
		this.chDetail = chDetail;
		this.chRegdate = chRegdate;
	}

	public int getChPk() {
		return chPk;
	}

	public void setChPk(int chPk) {
		this.chPk = chPk;
	}

	public String getChIp() {
		return chIp;
	}

	public void setChIp(String chIp) {
		this.chIp = chIp;
	}

	public String getChAdmin() {
		return chAdmin;
	}

	public void setChAdmin(String chAdmin) {
		this.chAdmin = chAdmin;
	}

	public String getChDetail() {
		return chDetail;
	}

	public void setChDetail(String chDetail) {
		this.chDetail = chDetail;
	}

	public Timestamp getChRegdate() {
		return chRegdate;
	}

	public void setChRegdate(Timestamp chRegdate) {
		this.chRegdate = chRegdate;
	}

	@Override
	public String toString() {
		return "CustomerHistoryVO [chPk=" + chPk + ", chIp=" + chIp + ", chAdmin=" + chAdmin + ", chDetail=" + chDetail
				+ ", chRegdate=" + chRegdate + "]";
	}
	
}
