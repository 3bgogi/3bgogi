package com.gogi.proj.matching.vo;

import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.product.products.vo.ProductsVO;

public class ProductMatchingVO {

	private int pmPk;
	private int productFk;
	private String pmStoreProductName;
	private boolean pmEachFlag;
	private int pmEachs;
	private Timestamp pmRegdate;
	
	//옵션 매칭 추가사항
	private List<OptionMatchingVO> optionMatchingVOList;
	private List<ProductsVO> productsVOList;
	
	//상품 저장좀 추가사항
	private String pmProductName;
	
	public ProductMatchingVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductMatchingVO(int pmPk, int productFk, String pmStoreProductName, boolean pmEachFlag, int pmEachs,
			Timestamp pmRegdate, List<OptionMatchingVO> optionMatchingVOList, List<ProductsVO> productsVOList) {
		super();
		this.pmPk = pmPk;
		this.productFk = productFk;
		this.pmStoreProductName = pmStoreProductName;
		this.pmEachFlag = pmEachFlag;
		this.pmEachs = pmEachs;
		this.pmRegdate = pmRegdate;
		this.optionMatchingVOList = optionMatchingVOList;
		this.productsVOList = productsVOList;
	}

	public String getPmProductName() {
		return pmProductName;
	}

	public void setPmProductName(String pmProductName) {
		this.pmProductName = pmProductName;
	}

	public int getPmPk() {
		return pmPk;
	}

	public void setPmPk(int pmPk) {
		this.pmPk = pmPk;
	}

	public int getProductFk() {
		return productFk;
	}

	public void setProductFk(int productFk) {
		this.productFk = productFk;
	}

	public String getPmStoreProductName() {
		return pmStoreProductName;
	}

	public void setPmStoreProductName(String pmStoreProductName) {
		this.pmStoreProductName = pmStoreProductName;
	}

	public boolean isPmEachFlag() {
		return pmEachFlag;
	}

	public void setPmEachFlag(boolean pmEachFlag) {
		this.pmEachFlag = pmEachFlag;
	}

	public int getPmEachs() {
		return pmEachs;
	}

	public void setPmEachs(int pmEachs) {
		this.pmEachs = pmEachs;
	}

	public Timestamp getPmRegdate() {
		return pmRegdate;
	}

	public void setPmRegdate(Timestamp pmRegdate) {
		this.pmRegdate = pmRegdate;
	}

	public List<OptionMatchingVO> getOptionMatchingVOList() {
		return optionMatchingVOList;
	}

	public void setOptionMatchingVOList(List<OptionMatchingVO> optionMatchingVOList) {
		this.optionMatchingVOList = optionMatchingVOList;
	}

	public List<ProductsVO> getProductsVOList() {
		return productsVOList;
	}

	public void setProductsVOList(List<ProductsVO> productsVOList) {
		this.productsVOList = productsVOList;
	}

	@Override
	public String toString() {
		return "ProductMatchingVO [pmPk=" + pmPk + ", productFk=" + productFk + ", pmStoreProductName="
				+ pmStoreProductName + ", pmEachFlag=" + pmEachFlag + ", pmEachs=" + pmEachs + ", pmRegdate="
				+ pmRegdate + ", optionMatchingVOList=" + optionMatchingVOList + ", productsVOList=" + productsVOList
				+ "]";
	}
	
}
