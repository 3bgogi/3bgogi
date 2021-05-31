package com.gogi.proj.product.cost.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CostsVO {

	private int costPk; //원가 고유 번호
	private int cdFk; //원가 세부사항 고유 번호
	private int optionFk; //옵션 고유 번호
	private String costUniqueNum; // 원가 고유 값
	private String costName; //원가명
	private int totalPrice; //group by로 합해진 총 가격 받아오기
	private int costMeasureCal; //원가 단위당 계산
	private int costProductionTime; //상품 생산 시간
	private Timestamp costUpdate; //원가 수정일
	private Date costRegdate; //원가 등록일
	
	//추가사항 : controller에서 modelattribute로 CostsVO를 list 형태로 가져올 수 있도록
	private List<CostsVO> costsVOList;
	
	private List<CostDetailVO> costDetailList;
	
	public CostsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostsVO(int costPk, int cdFk, int optionFk, String costUniqueNum, String costName, int totalPrice,
			int costMeasureCal, int costProductionTime, Timestamp costUpdate, Date costRegdate,
			List<CostsVO> costsVOList, List<CostDetailVO> costDetailList) {
		super();
		this.costPk = costPk;
		this.cdFk = cdFk;
		this.optionFk = optionFk;
		this.costUniqueNum = costUniqueNum;
		this.costName = costName;
		this.totalPrice = totalPrice;
		this.costMeasureCal = costMeasureCal;
		this.costProductionTime = costProductionTime;
		this.costUpdate = costUpdate;
		this.costRegdate = costRegdate;
		this.costsVOList = costsVOList;
		this.costDetailList = costDetailList;
	}

	public int getCostPk() {
		return costPk;
	}

	public void setCostPk(int costPk) {
		this.costPk = costPk;
	}

	public int getCdFk() {
		return cdFk;
	}

	public void setCdFk(int cdFk) {
		this.cdFk = cdFk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public String getCostUniqueNum() {
		return costUniqueNum;
	}

	public void setCostUniqueNum(String costUniqueNum) {
		this.costUniqueNum = costUniqueNum;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCostMeasureCal() {
		return costMeasureCal;
	}

	public void setCostMeasureCal(int costMeasureCal) {
		this.costMeasureCal = costMeasureCal;
	}

	public int getCostProductionTime() {
		return costProductionTime;
	}

	public void setCostProductionTime(int costProductionTime) {
		this.costProductionTime = costProductionTime;
	}

	public Timestamp getCostUpdate() {
		return costUpdate;
	}

	public void setCostUpdate(Timestamp costUpdate) {
		this.costUpdate = costUpdate;
	}

	public Date getCostRegdate() {
		return costRegdate;
	}

	public void setCostRegdate(Date costRegdate) {
		this.costRegdate = costRegdate;
	}

	public List<CostsVO> getCostsVOList() {
		return costsVOList;
	}

	public void setCostsVOList(List<CostsVO> costsVOList) {
		this.costsVOList = costsVOList;
	}

	public List<CostDetailVO> getCostDetailList() {
		return costDetailList;
	}

	public void setCostDetailList(List<CostDetailVO> costDetailList) {
		this.costDetailList = costDetailList;
	}

	@Override
	public String toString() {
		return "CostsVO [costPk=" + costPk + ", cdFk=" + cdFk + ", optionFk=" + optionFk + ", costUniqueNum="
				+ costUniqueNum + ", costName=" + costName + ", totalPrice=" + totalPrice + ", costMeasureCal="
				+ costMeasureCal + ", costProductionTime=" + costProductionTime + ", costUpdate=" + costUpdate
				+ ", costRegdate=" + costRegdate + ", costsVOList=" + costsVOList + ", costDetailList=" + costDetailList
				+ "]";
	}

}
