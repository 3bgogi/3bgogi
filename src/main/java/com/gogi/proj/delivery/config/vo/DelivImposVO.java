package com.gogi.proj.delivery.config.vo;

public class DelivImposVO {

	private int diPk;				//불가지역고유값
	private int edaFk;				//지역고유값
	private String diAddress;		//주소
	
	public DelivImposVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DelivImposVO(int diPk, int edaFk, String diAddress) {
		super();
		this.diPk = diPk;
		this.edaFk = edaFk;
		this.diAddress = diAddress;
	}

	public int getDiPk() {
		return diPk;
	}

	public void setDiPk(int diPk) {
		this.diPk = diPk;
	}

	public int getEdaFk() {
		return edaFk;
	}

	public void setEdaFk(int edaFk) {
		this.edaFk = edaFk;
	}

	public String getDiAddress() {
		return diAddress;
	}

	public void setDiAddress(String diAddress) {
		this.diAddress = diAddress;
	}

	@Override
	public String toString() {
		return "DelivImposVO [diPk=" + diPk + ", edaFk=" + edaFk + ", diAddress=" + diAddress + "]";
	}
	
}
