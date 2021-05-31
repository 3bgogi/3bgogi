package com.gogi.proj.product.options.vo;

import java.util.List;

public class OptionsCostsMatchingListVO {

	private int ocmPk; //옵션원가매칭 고유 번호
	private int costsFk; //원가 고유 번호
	private int optionFk; //옵션 고유 번호
	private List<String> optionMatching;
	private List<Integer> ocmGramRecalFlag; //합산 gram 플래그
	private List<Integer> ocmGram; //재계산 gram
	private List<Integer> ocmProductionDivide; //나누기
	private List<Integer> ocmEach; //개수
	
	public OptionsCostsMatchingListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OptionsCostsMatchingListVO(int ocmPk, int costsFk, int optionFk, List<String> optionMatching,
			List<Integer> ocmGramRecalFlag, List<Integer> ocmGram, List<Integer> ocmProductionDivide,
			List<Integer> ocmEach) {
		super();
		this.ocmPk = ocmPk;
		this.costsFk = costsFk;
		this.optionFk = optionFk;
		this.optionMatching = optionMatching;
		this.ocmGramRecalFlag = ocmGramRecalFlag;
		this.ocmGram = ocmGram;
		this.ocmProductionDivide = ocmProductionDivide;
		this.ocmEach = ocmEach;
	}

	public int getOcmPk() {
		return ocmPk;
	}

	public void setOcmPk(int ocmPk) {
		this.ocmPk = ocmPk;
	}

	public int getCostsFk() {
		return costsFk;
	}

	public void setCostsFk(int costsFk) {
		this.costsFk = costsFk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public List<String> getOptionMatching() {
		return optionMatching;
	}

	public void setOptionMatching(List<String> optionMatching) {
		this.optionMatching = optionMatching;
	}

	public List<Integer> getOcmGramRecalFlag() {
		return ocmGramRecalFlag;
	}

	public void setOcmGramRecalFlag(List<Integer> ocmGramRecalFlag) {
		this.ocmGramRecalFlag = ocmGramRecalFlag;
	}

	public List<Integer> getOcmGram() {
		return ocmGram;
	}

	public void setOcmGram(List<Integer> ocmGram) {
		this.ocmGram = ocmGram;
	}

	public List<Integer> getOcmProductionDivide() {
		return ocmProductionDivide;
	}

	public void setOcmProductionDivide(List<Integer> ocmProductionDivide) {
		this.ocmProductionDivide = ocmProductionDivide;
	}

	public List<Integer> getOcmEach() {
		return ocmEach;
	}

	public void setOcmEach(List<Integer> ocmEach) {
		this.ocmEach = ocmEach;
	}

	@Override
	public String toString() {
		return "OptionsCostsMatchingListVO [ocmPk=" + ocmPk + ", costsFk=" + costsFk + ", optionFk=" + optionFk
				+ ", optionMatching=" + optionMatching + ", ocmGramRecalFlag=" + ocmGramRecalFlag + ", ocmGram="
				+ ocmGram + ", ocmProductionDivide=" + ocmProductionDivide + ", ocmEach=" + ocmEach + "]";
	}

}
