package com.gogi.proj.product.options.vo;

import java.util.List;

import com.gogi.proj.product.cost.vo.CostsVO;

public class OptionsCostsMatchingVO {

	private int ocmPk; //옵션원가매칭 고유 번호
	private int costFk; //원가 고유 번호
	private int optionFk; //옵션 고유 번호
	private int ocmGramRecalFlag; //합산 gram 플래그
	private int ocmGram; //재계산 gram
	private int ocmProductionDivide; //나누기
	private int ocmEach; //개수
	private int ocmRealGram; // 쟤게산 gram을 나눈 뒤 gram 수
	
	//추가사항
	private String costName; //원가명
	private String costUniqueNum; //원가 생성시 고유번호
	private int totalPrice;
	private List<CostsVO> costsVOList;
	
	public OptionsCostsMatchingVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OptionsCostsMatchingVO(int ocmPk, int costFk, int optionFk, int ocmGramRecalFlag, int ocmGram,
			int ocmProductionDivide, int ocmEach, int ocmRealGram, String costName, String costUniqueNum,
			int totalPrice, List<CostsVO> costsVOList) {
		super();
		this.ocmPk = ocmPk;
		this.costFk = costFk;
		this.optionFk = optionFk;
		this.ocmGramRecalFlag = ocmGramRecalFlag;
		this.ocmGram = ocmGram;
		this.ocmProductionDivide = ocmProductionDivide;
		this.ocmEach = ocmEach;
		this.ocmRealGram = ocmRealGram;
		this.costName = costName;
		this.costUniqueNum = costUniqueNum;
		this.totalPrice = totalPrice;
		this.costsVOList = costsVOList;
	}


	

	public List<CostsVO> getCostsVOList() {
		return costsVOList;
	}

	public void setCostsVOList(List<CostsVO> costsVOList) {
		this.costsVOList = costsVOList;
	}

	public int getOcmPk() {
		return ocmPk;
	}

	public void setOcmPk(int ocmPk) {
		this.ocmPk = ocmPk;
	}

	public int getCostFk() {
		return costFk;
	}

	public void setCostFk(int costFk) {
		this.costFk = costFk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public int getOcmGramRecalFlag() {
		return ocmGramRecalFlag;
	}

	public void setOcmGramRecalFlag(int ocmGramRecalFlag) {
		this.ocmGramRecalFlag = ocmGramRecalFlag;
	}

	public int getOcmGram() {
		return ocmGram;
	}

	public void setOcmGram(int ocmGram) {
		this.ocmGram = ocmGram;
	}

	public int getOcmProductionDivide() {
		return ocmProductionDivide;
	}

	public void setOcmProductionDivide(int ocmProductionDivide) {
		this.ocmProductionDivide = ocmProductionDivide;
	}

	public int getOcmEach() {
		return ocmEach;
	}

	public void setOcmEach(int ocmEach) {
		this.ocmEach = ocmEach;
	}

	public int getOcmRealGram() {
		return ocmRealGram;
	}

	public void setOcmRealGram(int ocmRealGram) {
		this.ocmRealGram = ocmRealGram;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getCostUniqueNum() {
		return costUniqueNum;
	}

	public void setCostUniqueNum(String costUniqueNum) {
		this.costUniqueNum = costUniqueNum;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OptionsCostsMatchingVO [ocmPk=" + ocmPk + ", costFk=" + costFk + ", optionFk=" + optionFk
				+ ", ocmGramRecalFlag=" + ocmGramRecalFlag + ", ocmGram=" + ocmGram + ", ocmProductionDivide="
				+ ocmProductionDivide + ", ocmEach=" + ocmEach + ", ocmRealGram=" + ocmRealGram + ", costName="
				+ costName + ", costUniqueNum=" + costUniqueNum + ", totalPrice=" + totalPrice + ", costsVOList="
				+ costsVOList + "]";
	}

}
