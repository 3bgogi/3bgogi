package com.gogi.proj.delivery.config.vo;

import java.util.List;

/**
 * 배송 구분 클레스
 * @author 3bgogi
 *2020. 11. 27.
 */
public class EarlyDelivTypeVO {

	private int edtPk;					// 빠른 배송 고유값
	private String edtType;				// 구분
	private String edtUrl;				// 배송조회 주소
	private List<EarlyDelivAreaVO> edtList;
	
	public EarlyDelivTypeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EarlyDelivTypeVO(int edtPk, String edtType, String edtUrl, List<EarlyDelivAreaVO> edtList) {
		super();
		this.edtPk = edtPk;
		this.edtType = edtType;
		this.edtUrl = edtUrl;
		this.edtList = edtList;
	}

	public int getEdtPk() {
		return edtPk;
	}

	public void setEdtPk(int edtPk) {
		this.edtPk = edtPk;
	}

	public String getEdtType() {
		return edtType;
	}

	public void setEdtType(String edtType) {
		this.edtType = edtType;
	}

	public String getEdtUrl() {
		return edtUrl;
	}

	public void setEdtUrl(String edtUrl) {
		this.edtUrl = edtUrl;
	}

	public List<EarlyDelivAreaVO> getEdtList() {
		return edtList;
	}

	public void setEdtList(List<EarlyDelivAreaVO> edtList) {
		this.edtList = edtList;
	}

	@Override
	public String toString() {
		return "EarlyDelivTypeVO [edtPk=" + edtPk + ", edtType=" + edtType + ", edtUrl=" + edtUrl + ", edtList="
				+ edtList + "]";
	}

}
