package com.gogi.proj.log.vo;

import java.sql.Timestamp;

//주문서 기록
public class OrderHistoryVO {

	private int ohPk;						//고유값
	private int orFk;						//주문서 고유값
	private String ohIp;					//실행 아이피
	private String ohAdmin;					//관리자
	private String ohEndPoint;				//엔드포인트
	private String ohDetail;				//상세사항
	private String ohRegdate;			//기록일
	
	public OrderHistoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public OrderHistoryVO(int ohPk, int orFk, String ohIp, String ohAdmin, String ohEndPoint, String ohDetail,
			String ohRegdate) {
		super();
		this.ohPk = ohPk;
		this.orFk = orFk;
		this.ohIp = ohIp;
		this.ohAdmin = ohAdmin;
		this.ohEndPoint = ohEndPoint;
		this.ohDetail = ohDetail;
		this.ohRegdate = ohRegdate;
	}

	
	public int getOhPk() {
		return ohPk;
	}

	public void setOhPk(int ohPk) {
		this.ohPk = ohPk;
	}

	public int getOrFk() {
		return orFk;
	}

	public void setOrFk(int orFk) {
		this.orFk = orFk;
	}

	public String getOhIp() {
		return ohIp;
	}

	public void setOhIp(String ohIp) {
		this.ohIp = ohIp;
	}

	public String getOhAdmin() {
		return ohAdmin;
	}

	public void setOhAdmin(String ohAdmin) {
		this.ohAdmin = ohAdmin;
	}

	public String getOhEndPoint() {
		return ohEndPoint;
	}

	public void setOhEndPoint(String ohEndPoint) {
		this.ohEndPoint = ohEndPoint;
	}

	public String getOhDetail() {
		return ohDetail;
	}

	public void setOhDetail(String ohDetail) {
		this.ohDetail = ohDetail;
	}

	public String getOhRegdate() {
		return ohRegdate;
	}

	public void setOhRegdate(String ohRegdate) {
		this.ohRegdate = ohRegdate;
	}

	@Override
	public String toString() {
		return "OrderHistoryVO [ohPk=" + ohPk + ", orFk=" + orFk + ", ohIp=" + ohIp + ", ohAdmin=" + ohAdmin
				+ ", ohEndPoint=" + ohEndPoint + ", ohDetail=" + ohDetail + ", ohRegdate=" + ohRegdate + "]";
	}
	
}
