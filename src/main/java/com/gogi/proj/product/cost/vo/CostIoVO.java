package com.gogi.proj.product.cost.vo;

import java.sql.Timestamp;

public class CostIoVO {

	private int ciPk;								//입출고고유번호
	private int cdFk;								//원가세부사항고유번호
	private int ciPrice;							//입고원가
	private int ciWeight;							//입고무게
	private int ciOutputWeight;						//출고 무게
	private String ciAnimalProdTraceNum;			//이력번호
	private String ciLevel;							//등급
	private String ciAbattoir;						//도축장
	private String ciCountryOfOrigin;				//원산지
	private String ciItemsManufNum;					//품목제조번호
	private int ciOutputQty;						//출고 개수
	private Timestamp ciOutputLastTime;				//마지막 출고시간
	private boolean ciOutputFlag;					//출고 가능 여부
	private Timestamp ciRegdate;					//등록일
	
	//추가 사항
	private int cilFk;								//도체 고유값 값이 0일 경우 부분육으로 입고된 값
	private int ciFreshMeatWeight;					//정육 무게
	private boolean ciStockFlag;					//재고 감소 여부
	private String ciMarblingLevel;
	
	private String costDetailName; 
	
	public CostIoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostIoVO(int ciPk, int cdFk, int ciPrice, int ciWeight, int ciOutputWeight, String ciAnimalProdTraceNum,
			String ciLevel, String ciAbattoir, String ciCountryOfOrigin, String ciItemsManufNum, int ciOutputQty,
			Timestamp ciOutputLastTime, boolean ciOutputFlag, Timestamp ciRegdate, int cilFk, int ciFreshMeatWeight,
			boolean ciStockFlag, String ciMarblingLevel, String costDetailName) {
		super();
		this.ciPk = ciPk;
		this.cdFk = cdFk;
		this.ciPrice = ciPrice;
		this.ciWeight = ciWeight;
		this.ciOutputWeight = ciOutputWeight;
		this.ciAnimalProdTraceNum = ciAnimalProdTraceNum;
		this.ciLevel = ciLevel;
		this.ciAbattoir = ciAbattoir;
		this.ciCountryOfOrigin = ciCountryOfOrigin;
		this.ciItemsManufNum = ciItemsManufNum;
		this.ciOutputQty = ciOutputQty;
		this.ciOutputLastTime = ciOutputLastTime;
		this.ciOutputFlag = ciOutputFlag;
		this.ciRegdate = ciRegdate;
		this.cilFk = cilFk;
		this.ciFreshMeatWeight = ciFreshMeatWeight;
		this.ciStockFlag = ciStockFlag;
		this.ciMarblingLevel = ciMarblingLevel;
		this.costDetailName = costDetailName;
	}

	public int getCiPk() {
		return ciPk;
	}

	public void setCiPk(int ciPk) {
		this.ciPk = ciPk;
	}

	public int getCdFk() {
		return cdFk;
	}

	public void setCdFk(int cdFk) {
		this.cdFk = cdFk;
	}

	public int getCiPrice() {
		return ciPrice;
	}

	public void setCiPrice(int ciPrice) {
		this.ciPrice = ciPrice;
	}

	public int getCiWeight() {
		return ciWeight;
	}

	public void setCiWeight(int ciWeight) {
		this.ciWeight = ciWeight;
	}

	public int getCiOutputWeight() {
		return ciOutputWeight;
	}

	public void setCiOutputWeight(int ciOutputWeight) {
		this.ciOutputWeight = ciOutputWeight;
	}

	public String getCiAnimalProdTraceNum() {
		return ciAnimalProdTraceNum;
	}

	public void setCiAnimalProdTraceNum(String ciAnimalProdTraceNum) {
		this.ciAnimalProdTraceNum = ciAnimalProdTraceNum;
	}

	public String getCiLevel() {
		return ciLevel;
	}

	public void setCiLevel(String ciLevel) {
		this.ciLevel = ciLevel;
	}

	public String getCiAbattoir() {
		return ciAbattoir;
	}

	public void setCiAbattoir(String ciAbattoir) {
		this.ciAbattoir = ciAbattoir;
	}

	public String getCiCountryOfOrigin() {
		return ciCountryOfOrigin;
	}

	public void setCiCountryOfOrigin(String ciCountryOfOrigin) {
		this.ciCountryOfOrigin = ciCountryOfOrigin;
	}

	public String getCiItemsManufNum() {
		return ciItemsManufNum;
	}

	public void setCiItemsManufNum(String ciItemsManufNum) {
		this.ciItemsManufNum = ciItemsManufNum;
	}

	public int getCiOutputQty() {
		return ciOutputQty;
	}

	public void setCiOutputQty(int ciOutputQty) {
		this.ciOutputQty = ciOutputQty;
	}

	public Timestamp getCiOutputLastTime() {
		return ciOutputLastTime;
	}

	public void setCiOutputLastTime(Timestamp ciOutputLastTime) {
		this.ciOutputLastTime = ciOutputLastTime;
	}

	public boolean isCiOutputFlag() {
		return ciOutputFlag;
	}

	public void setCiOutputFlag(boolean ciOutputFlag) {
		this.ciOutputFlag = ciOutputFlag;
	}

	public Timestamp getCiRegdate() {
		return ciRegdate;
	}

	public void setCiRegdate(Timestamp ciRegdate) {
		this.ciRegdate = ciRegdate;
	}

	public int getCilFk() {
		return cilFk;
	}

	public void setCilFk(int cilFk) {
		this.cilFk = cilFk;
	}

	public int getCiFreshMeatWeight() {
		return ciFreshMeatWeight;
	}

	public void setCiFreshMeatWeight(int ciFreshMeatWeight) {
		this.ciFreshMeatWeight = ciFreshMeatWeight;
	}

	public boolean isCiStockFlag() {
		return ciStockFlag;
	}

	public void setCiStockFlag(boolean ciStockFlag) {
		this.ciStockFlag = ciStockFlag;
	}

	public String getCiMarblingLevel() {
		return ciMarblingLevel;
	}

	public void setCiMarblingLevel(String ciMarblingLevel) {
		this.ciMarblingLevel = ciMarblingLevel;
	}

	public String getCostDetailName() {
		return costDetailName;
	}

	public void setCostDetailName(String costDetailName) {
		this.costDetailName = costDetailName;
	}

	@Override
	public String toString() {
		return "CostIoVO [ciPk=" + ciPk + ", cdFk=" + cdFk + ", ciPrice=" + ciPrice + ", ciWeight=" + ciWeight
				+ ", ciOutputWeight=" + ciOutputWeight + ", ciAnimalProdTraceNum=" + ciAnimalProdTraceNum + ", ciLevel="
				+ ciLevel + ", ciAbattoir=" + ciAbattoir + ", ciCountryOfOrigin=" + ciCountryOfOrigin
				+ ", ciItemsManufNum=" + ciItemsManufNum + ", ciOutputQty=" + ciOutputQty + ", ciOutputLastTime="
				+ ciOutputLastTime + ", ciOutputFlag=" + ciOutputFlag + ", ciRegdate=" + ciRegdate + ", cilFk=" + cilFk
				+ ", ciFreshMeatWeight=" + ciFreshMeatWeight + ", ciStockFlag=" + ciStockFlag + ", ciMarblingLevel="
				+ ciMarblingLevel + ", costDetailName=" + costDetailName + "]";
	}
	
}
