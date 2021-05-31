package com.gogi.proj.product.options.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class OptionsVO {
	
	private int optionPk; //옵션 고유 번호
	private int eoFk; //주문서 위치 고유번호
	private int resFk; //자재 고유 번호
	private int eventFk; //이벤트 고유번호
	private int pccFk;// 합포 분류 고유 번호
	private int productFk; // 상품 고유 번호
	private String optionName; //옵션명
	private int optionPrice; //상품, 옵션의 가격
	private int optionWidthSize; //상품 가로 길이
	private int optionHeightSize; //상품 높이
	private int optionLengthSize; //상품 세로 길이
	private boolean optionFlag; //옵션 사용 여부
	private int optionSeq; //옵션 순서
	private String optionBarcodeNumber1; // 바코드1
	private String optionBarcodeNumber2; // 바코드2
	private String optionRemark; //비고사항
	private boolean optionTaxFlag; //면세, 과세 여부
	private boolean optionStockFlag; //재고 체크 여부
	private int optionStock; //상품 재고
	private int optionStockMaxAlarm; // 재고 알람 개수
	private boolean optionExcelColorCheck; //엑셀 컬러 여부
	private String optionMemo1; //옵션 메모
	private String optionMemo2;
	private String optionMemo3;
	private String optionMemo4; //옵션 메모
	private String optionThumbnail1; // 상품, 옵션 사진
	private String optionThumbnail2; // 상품, 옵션 사진
	private Timestamp optionUpdate; //옵션 수정일
	private Date optionRegdate; // 옵션 등록일
	
	//추가사항
	private int optionCost;
	private boolean optionCostFlag;
	
	private int anotherOptionPk;
	private int optionSupplyCost;
	private int anotherOptionQty;
	
	//추가사항
	private List<OptionsCostsMatchingVO> optionCostsMatchingVOList;

	public OptionsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OptionsVO(int optionPk, int eoFk, int resFk, int eventFk, int pccFk, int productFk, String optionName,
			int optionPrice, int optionWidthSize, int optionHeightSize, int optionLengthSize, boolean optionFlag,
			int optionSeq, String optionBarcodeNumber1, String optionBarcodeNumber2, String optionRemark,
			boolean optionTaxFlag, boolean optionStockFlag, int optionStock, int optionStockMaxAlarm,
			boolean optionExcelColorCheck, String optionMemo1, String optionMemo2, String optionMemo3,
			String optionMemo4, String optionThumbnail1, String optionThumbnail2, Timestamp optionUpdate,
			Date optionRegdate, int optionCost, boolean optionCostFlag, int anotherOptionPk, int optionSupplyCost,
			int anotherOptionQty, List<OptionsCostsMatchingVO> optionCostsMatchingVOList) {
		super();
		this.optionPk = optionPk;
		this.eoFk = eoFk;
		this.resFk = resFk;
		this.eventFk = eventFk;
		this.pccFk = pccFk;
		this.productFk = productFk;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.optionWidthSize = optionWidthSize;
		this.optionHeightSize = optionHeightSize;
		this.optionLengthSize = optionLengthSize;
		this.optionFlag = optionFlag;
		this.optionSeq = optionSeq;
		this.optionBarcodeNumber1 = optionBarcodeNumber1;
		this.optionBarcodeNumber2 = optionBarcodeNumber2;
		this.optionRemark = optionRemark;
		this.optionTaxFlag = optionTaxFlag;
		this.optionStockFlag = optionStockFlag;
		this.optionStock = optionStock;
		this.optionStockMaxAlarm = optionStockMaxAlarm;
		this.optionExcelColorCheck = optionExcelColorCheck;
		this.optionMemo1 = optionMemo1;
		this.optionMemo2 = optionMemo2;
		this.optionMemo3 = optionMemo3;
		this.optionMemo4 = optionMemo4;
		this.optionThumbnail1 = optionThumbnail1;
		this.optionThumbnail2 = optionThumbnail2;
		this.optionUpdate = optionUpdate;
		this.optionRegdate = optionRegdate;
		this.optionCost = optionCost;
		this.optionCostFlag = optionCostFlag;
		this.anotherOptionPk = anotherOptionPk;
		this.optionSupplyCost = optionSupplyCost;
		this.anotherOptionQty = anotherOptionQty;
		this.optionCostsMatchingVOList = optionCostsMatchingVOList;
	}

	public int getOptionPk() {
		return optionPk;
	}

	public void setOptionPk(int optionPk) {
		this.optionPk = optionPk;
	}

	public int getEoFk() {
		return eoFk;
	}

	public void setEoFk(int eoFk) {
		this.eoFk = eoFk;
	}

	public int getResFk() {
		return resFk;
	}

	public void setResFk(int resFk) {
		this.resFk = resFk;
	}

	public int getEventFk() {
		return eventFk;
	}

	public void setEventFk(int eventFk) {
		this.eventFk = eventFk;
	}

	public int getPccFk() {
		return pccFk;
	}

	public void setPccFk(int pccFk) {
		this.pccFk = pccFk;
	}

	public int getProductFk() {
		return productFk;
	}

	public void setProductFk(int productFk) {
		this.productFk = productFk;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	public int getOptionWidthSize() {
		return optionWidthSize;
	}

	public void setOptionWidthSize(int optionWidthSize) {
		this.optionWidthSize = optionWidthSize;
	}

	public int getOptionHeightSize() {
		return optionHeightSize;
	}

	public void setOptionHeightSize(int optionHeightSize) {
		this.optionHeightSize = optionHeightSize;
	}

	public int getOptionLengthSize() {
		return optionLengthSize;
	}

	public void setOptionLengthSize(int optionLengthSize) {
		this.optionLengthSize = optionLengthSize;
	}

	public boolean getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(boolean optionFlag) {
		this.optionFlag = optionFlag;
	}

	public int getOptionSeq() {
		return optionSeq;
	}

	public void setOptionSeq(int optionSeq) {
		this.optionSeq = optionSeq;
	}

	public String getOptionBarcodeNumber1() {
		return optionBarcodeNumber1;
	}

	public void setOptionBarcodeNumber1(String optionBarcodeNumber1) {
		this.optionBarcodeNumber1 = optionBarcodeNumber1;
	}

	public String getOptionBarcodeNumber2() {
		return optionBarcodeNumber2;
	}

	public void setOptionBarcodeNumber2(String optionBarcodeNumber2) {
		this.optionBarcodeNumber2 = optionBarcodeNumber2;
	}

	public String getOptionRemark() {
		return optionRemark;
	}

	public void setOptionRemark(String optionRemark) {
		this.optionRemark = optionRemark;
	}

	public boolean getOptionTaxFlag() {
		return optionTaxFlag;
	}

	public void setOptionTaxFlag(boolean optionTaxFlag) {
		this.optionTaxFlag = optionTaxFlag;
	}

	public boolean getOptionStockFlag() {
		return optionStockFlag;
	}

	public void setOptionStockFlag(boolean optionStockFlag) {
		this.optionStockFlag = optionStockFlag;
	}

	public int getOptionStock() {
		return optionStock;
	}

	public void setOptionStock(int optionStock) {
		this.optionStock = optionStock;
	}

	public int getOptionStockMaxAlarm() {
		return optionStockMaxAlarm;
	}

	public void setOptionStockMaxAlarm(int optionStockMaxAlarm) {
		this.optionStockMaxAlarm = optionStockMaxAlarm;
	}

	public boolean getOptionExcelColorCheck() {
		return optionExcelColorCheck;
	}

	public void setOptionExcelColorCheck(boolean optionExcelColorCheck) {
		this.optionExcelColorCheck = optionExcelColorCheck;
	}

	public String getOptionMemo1() {
		return optionMemo1;
	}

	public void setOptionMemo1(String optionMemo1) {
		this.optionMemo1 = optionMemo1;
	}

	public String getOptionMemo2() {
		return optionMemo2;
	}

	public void setOptionMemo2(String optionMemo2) {
		this.optionMemo2 = optionMemo2;
	}

	public String getOptionMemo3() {
		return optionMemo3;
	}

	public void setOptionMemo3(String optionMemo3) {
		this.optionMemo3 = optionMemo3;
	}

	public String getOptionMemo4() {
		return optionMemo4;
	}

	public void setOptionMemo4(String optionMemo4) {
		this.optionMemo4 = optionMemo4;
	}

	public String getOptionThumbnail1() {
		return optionThumbnail1;
	}

	public void setOptionThumbnail1(String optionThumbnail1) {
		this.optionThumbnail1 = optionThumbnail1;
	}

	public String getOptionThumbnail2() {
		return optionThumbnail2;
	}

	public void setOptionThumbnail2(String optionThumbnail2) {
		this.optionThumbnail2 = optionThumbnail2;
	}

	public Timestamp getOptionUpdate() {
		return optionUpdate;
	}

	public void setOptionUpdate(Timestamp optionUpdate) {
		this.optionUpdate = optionUpdate;
	}

	public Date getOptionRegdate() {
		return optionRegdate;
	}

	public void setOptionRegdate(Date optionRegdate) {
		this.optionRegdate = optionRegdate;
	}

	public int getOptionCost() {
		return optionCost;
	}

	public void setOptionCost(int optionCost) {
		this.optionCost = optionCost;
	}

	public boolean getOptionCostFlag() {
		return optionCostFlag;
	}

	public void setOptionCostFlag(boolean optionCostFlag) {
		this.optionCostFlag = optionCostFlag;
	}

	public int getAnotherOptionPk() {
		return anotherOptionPk;
	}

	public void setAnotherOptionPk(int anotherOptionPk) {
		this.anotherOptionPk = anotherOptionPk;
	}

	public int getOptionSupplyCost() {
		return optionSupplyCost;
	}

	public void setOptionSupplyCost(int optionSupplyCost) {
		this.optionSupplyCost = optionSupplyCost;
	}

	public int getAnotherOptionQty() {
		return anotherOptionQty;
	}

	public void setAnotherOptionQty(int anotherOptionQty) {
		this.anotherOptionQty = anotherOptionQty;
	}

	public List<OptionsCostsMatchingVO> getOptionCostsMatchingVOList() {
		return optionCostsMatchingVOList;
	}

	public void setOptionCostsMatchingVOList(List<OptionsCostsMatchingVO> optionCostsMatchingVOList) {
		this.optionCostsMatchingVOList = optionCostsMatchingVOList;
	}

	@Override
	public String toString() {
		return "OptionsVO [optionPk=" + optionPk + ", eoFk=" + eoFk + ", resFk=" + resFk + ", eventFk=" + eventFk
				+ ", pccFk=" + pccFk + ", productFk=" + productFk + ", optionName=" + optionName + ", optionPrice="
				+ optionPrice + ", optionWidthSize=" + optionWidthSize + ", optionHeightSize=" + optionHeightSize
				+ ", optionLengthSize=" + optionLengthSize + ", optionFlag=" + optionFlag + ", optionSeq=" + optionSeq
				+ ", optionBarcodeNumber1=" + optionBarcodeNumber1 + ", optionBarcodeNumber2=" + optionBarcodeNumber2
				+ ", optionRemark=" + optionRemark + ", optionTaxFlag=" + optionTaxFlag + ", optionStockFlag="
				+ optionStockFlag + ", optionStock=" + optionStock + ", optionStockMaxAlarm=" + optionStockMaxAlarm
				+ ", optionExcelColorCheck=" + optionExcelColorCheck + ", optionMemo1=" + optionMemo1 + ", optionMemo2="
				+ optionMemo2 + ", optionMemo3=" + optionMemo3 + ", optionMemo4=" + optionMemo4 + ", optionThumbnail1="
				+ optionThumbnail1 + ", optionThumbnail2=" + optionThumbnail2 + ", optionUpdate=" + optionUpdate
				+ ", optionRegdate=" + optionRegdate + ", optionCost=" + optionCost + ", optionCostFlag="
				+ optionCostFlag + ", anotherOptionPk=" + anotherOptionPk + ", optionSupplyCost=" + optionSupplyCost
				+ ", anotherOptionQty=" + anotherOptionQty + ", optionCostsMatchingVOList=" + optionCostsMatchingVOList
				+ "]";
	}
}
