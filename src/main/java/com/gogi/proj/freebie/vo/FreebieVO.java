package com.gogi.proj.freebie.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class FreebieVO {


	private int fbPk;							//사은품정책 고유값
	private int optionFk;						//옵션고유번호 Fk;
	private String ssList;						//판매처 목록
	private String fbName;						//사은품 정책명
	private int fbType;							//주문번호로, 고유값으로 적용할지 선택 : 현재는 고유값으로만 가능
	private String fbMinDate;					//사은품 시작일
	private String fbMaxDate;					//사은품 종료일
	private int fbMinPrice;						//합계 최소 금액
	private int fbMaxPrice;						//합계 최대 금액
	private int fbMinTotalQty;					//합계 최소 개수
	private int fbMaxTotalQty;					//합계 최대 개수
	private boolean fbTotalQtyFlag;				//합계 개수 사용 여부
	private boolean fbAnotherCheckFlag;			//추가 확인 사항 사용 여부
	private int fbAnotherCheckList;				//추가 확인 사항 체크 목록 : 상품명으로 할지, 옵션명으로 할지 등등
	private String fbAnotherCheckWord;			//추가 확인 사항 포함 문자
	private int fbAnotherCheckType;				//추가 확인 사항 체크 타입 : 문자가 포함일지 완전 같을지 등등
	private boolean fbAnotherCheckFlag2;
	private int fbAnotherCheckList2;
	private String fbAnotherCheckWord2;
	private int fbAnotherCheckType2;
	private int fbAddType;						//사은품 추가 개수 타입
	private int fbAddQty;						//사은품 개수
	private int fbAddMultiQty;					//사은품 배수 여부
	private Date fbUpdate;						//수정일
	private Timestamp fbRegdate;				//등록일
	
	// 추가사항
	private String optionName;					//옵션명
	private int productPk;						//상품고유값
	private String productName;					//상품명
	
	private String fbRegType;					//등록 기준
	
	private List<FreebieCheckVO> fbCheckList;	//사은품 증정 여부 체크 리스트
	private List<String> ssArrayList;
	private List<String> fbAnotherCheckWordList;
	private List<String> fbAnotherCheckWordList2;
	
	public FreebieVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FreebieVO(int fbPk, int optionFk, String ssList, String fbName, int fbType, String fbMinDate,
			String fbMaxDate, int fbMinPrice, int fbMaxPrice, int fbMinTotalQty, int fbMaxTotalQty,
			boolean fbTotalQtyFlag, boolean fbAnotherCheckFlag, int fbAnotherCheckList, String fbAnotherCheckWord,
			int fbAnotherCheckType, boolean fbAnotherCheckFlag2, int fbAnotherCheckList2, String fbAnotherCheckWord2,
			int fbAnotherCheckType2, int fbAddType, int fbAddQty, int fbAddMultiQty, Date fbUpdate, Timestamp fbRegdate,
			String optionName, int productPk, String productName, List<FreebieCheckVO> fbCheckList,
			List<String> ssArrayList, List<String> fbAnotherCheckWordList, List<String> fbAnotherCheckWordList2) {
		super();
		this.fbPk = fbPk;
		this.optionFk = optionFk;
		this.ssList = ssList;
		this.fbName = fbName;
		this.fbType = fbType;
		this.fbMinDate = fbMinDate;
		this.fbMaxDate = fbMaxDate;
		this.fbMinPrice = fbMinPrice;
		this.fbMaxPrice = fbMaxPrice;
		this.fbMinTotalQty = fbMinTotalQty;
		this.fbMaxTotalQty = fbMaxTotalQty;
		this.fbTotalQtyFlag = fbTotalQtyFlag;
		this.fbAnotherCheckFlag = fbAnotherCheckFlag;
		this.fbAnotherCheckList = fbAnotherCheckList;
		this.fbAnotherCheckWord = fbAnotherCheckWord;
		this.fbAnotherCheckType = fbAnotherCheckType;
		this.fbAnotherCheckFlag2 = fbAnotherCheckFlag2;
		this.fbAnotherCheckList2 = fbAnotherCheckList2;
		this.fbAnotherCheckWord2 = fbAnotherCheckWord2;
		this.fbAnotherCheckType2 = fbAnotherCheckType2;
		this.fbAddType = fbAddType;
		this.fbAddQty = fbAddQty;
		this.fbAddMultiQty = fbAddMultiQty;
		this.fbUpdate = fbUpdate;
		this.fbRegdate = fbRegdate;
		this.optionName = optionName;
		this.productPk = productPk;
		this.productName = productName;
		this.fbCheckList = fbCheckList;
		this.ssArrayList = ssArrayList;
		this.fbAnotherCheckWordList = fbAnotherCheckWordList;
		this.fbAnotherCheckWordList2 = fbAnotherCheckWordList2;
	}
	
	public String getFbRegType() {
		return fbRegType;
	}

	public void setFbRegType(String fbRegType) {
		this.fbRegType = fbRegType;
	}

	public List<String> getFbAnotherCheckWordList2() {
		return fbAnotherCheckWordList2;
	}

	public void setFbAnotherCheckWordList2(List<String> fbAnotherCheckWordList2) {
		this.fbAnotherCheckWordList2 = fbAnotherCheckWordList2;
	}

	public int getFbPk() {
		return fbPk;
	}

	public void setFbPk(int fbPk) {
		this.fbPk = fbPk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public String getSsList() {
		return ssList;
	}

	public void setSsList(String ssList) {
		this.ssList = ssList;
	}

	public String getFbName() {
		return fbName;
	}

	public void setFbName(String fbName) {
		this.fbName = fbName;
	}

	public int getFbType() {
		return fbType;
	}

	public void setFbType(int fbType) {
		this.fbType = fbType;
	}

	public String getFbMinDate() {
		return fbMinDate;
	}

	public void setFbMinDate(String fbMinDate) {
		this.fbMinDate = fbMinDate;
	}

	public String getFbMaxDate() {
		return fbMaxDate;
	}

	public void setFbMaxDate(String fbMaxDate) {
		this.fbMaxDate = fbMaxDate;
	}

	public int getFbMinPrice() {
		return fbMinPrice;
	}

	public void setFbMinPrice(int fbMinPrice) {
		this.fbMinPrice = fbMinPrice;
	}

	public int getFbMaxPrice() {
		return fbMaxPrice;
	}

	public void setFbMaxPrice(int fbMaxPrice) {
		this.fbMaxPrice = fbMaxPrice;
	}

	public int getFbMinTotalQty() {
		return fbMinTotalQty;
	}

	public void setFbMinTotalQty(int fbMinTotalQty) {
		this.fbMinTotalQty = fbMinTotalQty;
	}

	public int getFbMaxTotalQty() {
		return fbMaxTotalQty;
	}

	public void setFbMaxTotalQty(int fbMaxTotalQty) {
		this.fbMaxTotalQty = fbMaxTotalQty;
	}

	public boolean isFbTotalQtyFlag() {
		return fbTotalQtyFlag;
	}

	public void setFbTotalQtyFlag(boolean fbTotalQtyFlag) {
		this.fbTotalQtyFlag = fbTotalQtyFlag;
	}

	public boolean isFbAnotherCheckFlag() {
		return fbAnotherCheckFlag;
	}

	public void setFbAnotherCheckFlag(boolean fbAnotherCheckFlag) {
		this.fbAnotherCheckFlag = fbAnotherCheckFlag;
	}

	public int getFbAnotherCheckList() {
		return fbAnotherCheckList;
	}

	public void setFbAnotherCheckList(int fbAnotherCheckList) {
		this.fbAnotherCheckList = fbAnotherCheckList;
	}

	public String getFbAnotherCheckWord() {
		return fbAnotherCheckWord;
	}

	public void setFbAnotherCheckWord(String fbAnotherCheckWord) {
		this.fbAnotherCheckWord = fbAnotherCheckWord;
	}

	public int getFbAnotherCheckType() {
		return fbAnotherCheckType;
	}

	public void setFbAnotherCheckType(int fbAnotherCheckType) {
		this.fbAnotherCheckType = fbAnotherCheckType;
	}

	public boolean isFbAnotherCheckFlag2() {
		return fbAnotherCheckFlag2;
	}

	public void setFbAnotherCheckFlag2(boolean fbAnotherCheckFlag2) {
		this.fbAnotherCheckFlag2 = fbAnotherCheckFlag2;
	}

	public int getFbAnotherCheckList2() {
		return fbAnotherCheckList2;
	}

	public void setFbAnotherCheckList2(int fbAnotherCheckList2) {
		this.fbAnotherCheckList2 = fbAnotherCheckList2;
	}

	public String getFbAnotherCheckWord2() {
		return fbAnotherCheckWord2;
	}

	public void setFbAnotherCheckWord2(String fbAnotherCheckWord2) {
		this.fbAnotherCheckWord2 = fbAnotherCheckWord2;
	}

	public int getFbAnotherCheckType2() {
		return fbAnotherCheckType2;
	}

	public void setFbAnotherCheckType2(int fbAnotherCheckType2) {
		this.fbAnotherCheckType2 = fbAnotherCheckType2;
	}

	public int getFbAddType() {
		return fbAddType;
	}

	public void setFbAddType(int fbAddType) {
		this.fbAddType = fbAddType;
	}

	public int getFbAddQty() {
		return fbAddQty;
	}

	public void setFbAddQty(int fbAddQty) {
		this.fbAddQty = fbAddQty;
	}

	public int getFbAddMultiQty() {
		return fbAddMultiQty;
	}

	public void setFbAddMultiQty(int fbAddMultiQty) {
		this.fbAddMultiQty = fbAddMultiQty;
	}

	public Date getFbUpdate() {
		return fbUpdate;
	}

	public void setFbUpdate(Date fbUpdate) {
		this.fbUpdate = fbUpdate;
	}

	public Timestamp getFbRegdate() {
		return fbRegdate;
	}

	public void setFbRegdate(Timestamp fbRegdate) {
		this.fbRegdate = fbRegdate;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getProductPk() {
		return productPk;
	}

	public void setProductPk(int productPk) {
		this.productPk = productPk;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<FreebieCheckVO> getFbCheckList() {
		return fbCheckList;
	}

	public void setFbCheckList(List<FreebieCheckVO> fbCheckList) {
		this.fbCheckList = fbCheckList;
	}

	public List<String> getSsArrayList() {
		return ssArrayList;
	}

	public void setSsArrayList(List<String> ssArrayList) {
		this.ssArrayList = ssArrayList;
	}

	public List<String> getFbAnotherCheckWordList() {
		return fbAnotherCheckWordList;
	}

	public void setFbAnotherCheckWordList(List<String> fbAnotherCheckWordList) {
		this.fbAnotherCheckWordList = fbAnotherCheckWordList;
	}

	@Override
	public String toString() {
		return "FreebieVO [fbPk=" + fbPk + ", optionFk=" + optionFk + ", ssList=" + ssList + ", fbName=" + fbName
				+ ", fbType=" + fbType + ", fbMinDate=" + fbMinDate + ", fbMaxDate=" + fbMaxDate + ", fbMinPrice="
				+ fbMinPrice + ", fbMaxPrice=" + fbMaxPrice + ", fbMinTotalQty=" + fbMinTotalQty + ", fbMaxTotalQty="
				+ fbMaxTotalQty + ", fbTotalQtyFlag=" + fbTotalQtyFlag + ", fbAnotherCheckFlag=" + fbAnotherCheckFlag
				+ ", fbAnotherCheckList=" + fbAnotherCheckList + ", fbAnotherCheckWord=" + fbAnotherCheckWord
				+ ", fbAnotherCheckType=" + fbAnotherCheckType + ", fbAnotherCheckFlag2=" + fbAnotherCheckFlag2
				+ ", fbAnotherCheckList2=" + fbAnotherCheckList2 + ", fbAnotherCheckWord2=" + fbAnotherCheckWord2
				+ ", fbAnotherCheckType2=" + fbAnotherCheckType2 + ", fbAddType=" + fbAddType + ", fbAddQty=" + fbAddQty
				+ ", fbAddMultiQty=" + fbAddMultiQty + ", fbUpdate=" + fbUpdate + ", fbRegdate=" + fbRegdate
				+ ", optionName=" + optionName + ", productPk=" + productPk + ", productName=" + productName
				+ ", fbCheckList=" + fbCheckList + ", ssArrayList=" + ssArrayList + ", fbAnotherCheckWordList="
				+ fbAnotherCheckWordList + ", fbAnotherCheckWordList2=" + fbAnotherCheckWordList2 + "]";
	}

}
