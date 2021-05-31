package com.gogi.proj.producttax.vo;

import java.util.List;

public class ProductInfoListVO {

	private int resCompany;
	private String inputDates;
	private List<ProductInfoVO> piList;
	
	public ProductInfoListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductInfoListVO(int resCompany, String inputDates, List<ProductInfoVO> piList) {
		super();
		this.resCompany = resCompany;
		this.inputDates = inputDates;
		this.piList = piList;
	}

	public int getResCompany() {
		return resCompany;
	}

	public void setResCompany(int resCompany) {
		this.resCompany = resCompany;
	}

	public String getInputDates() {
		return inputDates;
	}

	public void setInputDates(String inputDates) {
		this.inputDates = inputDates;
	}

	public List<ProductInfoVO> getPiList() {
		return piList;
	}

	public void setPiList(List<ProductInfoVO> piList) {
		this.piList = piList;
	}

	@Override
	public String toString() {
		return "ProductInfoListVO [resCompany=" + resCompany + ", inputDates=" + inputDates + ", piList=" + piList
				+ "]";
	}
	
}
