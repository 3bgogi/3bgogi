package com.gogi.proj.log.vo;

import java.sql.Timestamp;

public class ResourceChangeLogVO {

	private int rclPk; //자재 변동 고유번호
	private int rclFk; //자재 고유 번호
	private String rclAdmin; //자재 수정자
	private String rclName; //자재명
	private String rclOlderName; //전 자재명
	private int rclCost; // 자재원가
	private int rclOlderCost; // 자재 전 원가
	private int rclStockAmount; //자재 입고 개수
	private int rclSOlderStockAmount; // 자재 입고 전 개수
	private Timestamp rclChangeDate; //자재 변동일
	
	public ResourceChangeLogVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceChangeLogVO(int rclPk, int rclFk, String rclAdmin, String rclName, String rclOlderName, int rclCost,
			int rclOlderCost, int rclStockAmount, int rclSOlderStockAmount, Timestamp rclChangeDate) {
		super();
		this.rclPk = rclPk;
		this.rclFk = rclFk;
		this.rclAdmin = rclAdmin;
		this.rclName = rclName;
		this.rclOlderName = rclOlderName;
		this.rclCost = rclCost;
		this.rclOlderCost = rclOlderCost;
		this.rclStockAmount = rclStockAmount;
		this.rclSOlderStockAmount = rclSOlderStockAmount;
		this.rclChangeDate = rclChangeDate;
	}

	public int getRclPk() {
		return rclPk;
	}

	public void setRclPk(int rclPk) {
		this.rclPk = rclPk;
	}

	public int getRclFk() {
		return rclFk;
	}

	public void setRclFk(int rclFk) {
		this.rclFk = rclFk;
	}

	public String getRclAdmin() {
		return rclAdmin;
	}

	public void setRclAdmin(String rclAdmin) {
		this.rclAdmin = rclAdmin;
	}

	public String getRclName() {
		return rclName;
	}

	public void setRclName(String rclName) {
		this.rclName = rclName;
	}

	public String getRclOlderName() {
		return rclOlderName;
	}

	public void setRclOlderName(String rclOlderName) {
		this.rclOlderName = rclOlderName;
	}

	public int getRclCost() {
		return rclCost;
	}

	public void setRclCost(int rclCost) {
		this.rclCost = rclCost;
	}

	public int getRclOlderCost() {
		return rclOlderCost;
	}

	public void setRclOlderCost(int rclOlderCost) {
		this.rclOlderCost = rclOlderCost;
	}

	public int getRclStockAmount() {
		return rclStockAmount;
	}

	public void setRclStockAmount(int rclStockAmount) {
		this.rclStockAmount = rclStockAmount;
	}

	public int getRclSOlderStockAmount() {
		return rclSOlderStockAmount;
	}

	public void setRclSOlderStockAmount(int rclSOlderStockAmount) {
		this.rclSOlderStockAmount = rclSOlderStockAmount;
	}

	public Timestamp getRclChangeDate() {
		return rclChangeDate;
	}

	public void setRclChangeDate(Timestamp rclChangeDate) {
		this.rclChangeDate = rclChangeDate;
	}

	@Override
	public String toString() {
		return "ResourceChangeLogVO [rclPk=" + rclPk + ", rclFk=" + rclFk + ", rclAdmin=" + rclAdmin + ", rclName="
				+ rclName + ", rclOlderName=" + rclOlderName + ", rclCost=" + rclCost + ", rclOlderCost=" + rclOlderCost
				+ ", rclStockAmount=" + rclStockAmount + ", rclSOlderStockAmount=" + rclSOlderStockAmount
				+ ", rclChangeDate=" + rclChangeDate + "]";
	}
	
}
