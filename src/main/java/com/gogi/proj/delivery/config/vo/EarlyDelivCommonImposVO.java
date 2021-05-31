package com.gogi.proj.delivery.config.vo;

public class EarlyDelivCommonImposVO {

	private int edciPk;						//고유값
	private int edtFk;						//빠른배송구분고유값
	private String edciKeyword;				//배송 불가 키워드
	
	public EarlyDelivCommonImposVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EarlyDelivCommonImposVO(int edciPk, int edtFk, String edciKeyword) {
		super();
		this.edciPk = edciPk;
		this.edtFk = edtFk;
		this.edciKeyword = edciKeyword;
	}

	public int getEdciPk() {
		return edciPk;
	}

	public void setEdciPk(int edciPk) {
		this.edciPk = edciPk;
	}

	public int getEdtFk() {
		return edtFk;
	}

	public void setEdtFk(int edtFk) {
		this.edtFk = edtFk;
	}

	public String getEdciKeyword() {
		return edciKeyword;
	}

	public void setEdciKeyword(String edciKeyword) {
		this.edciKeyword = edciKeyword;
	}

	@Override
	public String toString() {
		return "EarlyDelivCommonImposVO [edciPk=" + edciPk + ", edtFk=" + edtFk + ", edciKeyword=" + edciKeyword + "]";
	}
	
}
