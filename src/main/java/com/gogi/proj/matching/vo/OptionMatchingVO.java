package com.gogi.proj.matching.vo;

import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

public class OptionMatchingVO {

	private int omPk;
	private int pmFk;
	private int optionFk;
	private String omStoreOptionName;
	private int omOptionAmount;
	private boolean omOptionMultipleFlag;
	private int omOptionMultipleEach;
	private int omOptionMultipleAmount;
	private boolean omCombineFlag;
	private Timestamp omRegdate;
	
	//추가사항
	private List<OptionsVO> optionsVOList;
	private List<ProductOptionVO> productOptionList;
	
	private boolean omSupplyFlag;
	
	public OptionMatchingVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OptionMatchingVO(int omPk, int pmFk, int optionFk, String omStoreOptionName, int omOptionAmount,
			boolean omOptionMultipleFlag, int omOptionMultipleEach, int omOptionMultipleAmount, boolean omCombineFlag,
			Timestamp omRegdate, List<OptionsVO> optionsVOList, List<ProductOptionVO> productOptionList,
			boolean omSupplyFlag) {
		super();
		this.omPk = omPk;
		this.pmFk = pmFk;
		this.optionFk = optionFk;
		this.omStoreOptionName = omStoreOptionName;
		this.omOptionAmount = omOptionAmount;
		this.omOptionMultipleFlag = omOptionMultipleFlag;
		this.omOptionMultipleEach = omOptionMultipleEach;
		this.omOptionMultipleAmount = omOptionMultipleAmount;
		this.omCombineFlag = omCombineFlag;
		this.omRegdate = omRegdate;
		this.optionsVOList = optionsVOList;
		this.productOptionList = productOptionList;
		this.omSupplyFlag = omSupplyFlag;
	}

	public int getOmPk() {
		return omPk;
	}

	public void setOmPk(int omPk) {
		this.omPk = omPk;
	}

	public int getPmFk() {
		return pmFk;
	}

	public void setPmFk(int pmFk) {
		this.pmFk = pmFk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public String getOmStoreOptionName() {
		return omStoreOptionName;
	}

	public void setOmStoreOptionName(String omStoreOptionName) {
		this.omStoreOptionName = omStoreOptionName;
	}

	public int getOmOptionAmount() {
		return omOptionAmount;
	}

	public void setOmOptionAmount(int omOptionAmount) {
		this.omOptionAmount = omOptionAmount;
	}

	public boolean isOmOptionMultipleFlag() {
		return omOptionMultipleFlag;
	}

	public void setOmOptionMultipleFlag(boolean omOptionMultipleFlag) {
		this.omOptionMultipleFlag = omOptionMultipleFlag;
	}

	public int getOmOptionMultipleEach() {
		return omOptionMultipleEach;
	}

	public void setOmOptionMultipleEach(int omOptionMultipleEach) {
		this.omOptionMultipleEach = omOptionMultipleEach;
	}

	public int getOmOptionMultipleAmount() {
		return omOptionMultipleAmount;
	}

	public void setOmOptionMultipleAmount(int omOptionMultipleAmount) {
		this.omOptionMultipleAmount = omOptionMultipleAmount;
	}

	public boolean isOmCombineFlag() {
		return omCombineFlag;
	}

	public void setOmCombineFlag(boolean omCombineFlag) {
		this.omCombineFlag = omCombineFlag;
	}

	public Timestamp getOmRegdate() {
		return omRegdate;
	}

	public void setOmRegdate(Timestamp omRegdate) {
		this.omRegdate = omRegdate;
	}

	public List<OptionsVO> getOptionsVOList() {
		return optionsVOList;
	}

	public void setOptionsVOList(List<OptionsVO> optionsVOList) {
		this.optionsVOList = optionsVOList;
	}

	public List<ProductOptionVO> getProductOptionList() {
		return productOptionList;
	}

	public void setProductOptionList(List<ProductOptionVO> productOptionList) {
		this.productOptionList = productOptionList;
	}

	public boolean isOmSupplyFlag() {
		return omSupplyFlag;
	}

	public void setOmSupplyFlag(boolean omSupplyFlag) {
		this.omSupplyFlag = omSupplyFlag;
	}

	@Override
	public String toString() {
		return "OptionMatchingVO [omPk=" + omPk + ", pmFk=" + pmFk + ", optionFk=" + optionFk + ", omStoreOptionName="
				+ omStoreOptionName + ", omOptionAmount=" + omOptionAmount + ", omOptionMultipleFlag="
				+ omOptionMultipleFlag + ", omOptionMultipleEach=" + omOptionMultipleEach + ", omOptionMultipleAmount="
				+ omOptionMultipleAmount + ", omCombineFlag=" + omCombineFlag + ", omRegdate=" + omRegdate
				+ ", optionsVOList=" + optionsVOList + ", productOptionList=" + productOptionList + ", omSupplyFlag="
				+ omSupplyFlag + "]";
	}
	
}
