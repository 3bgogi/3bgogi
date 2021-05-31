package com.gogi.proj.product.products.vo;

import java.sql.Date;
import java.util.List;

import com.gogi.proj.product.options.vo.OptionsVO;

public class ProductsVO {

	// 기본 사항
	private int productPk;//상품 고유 번호
	private int cfFk;//상품 분류 고유 번호
	private String productName; //상품명
	private boolean productFlag; //상품의 사용여부
	private String productRemark; //비고사항
	private String productMemo1; //메모
	private String productMemo2;
	private String productMemo3;
	private String productMemo4;
	private String productMemo5;
	private String productMemo6; //메모
	private String productThumbnail1;//사진
	private String productThumbnail2;//사진
	private Date productRegdate;//상품 등록일
	
	
	//추가 사항
	private int productOptionCount;//옵션 개수 카운트
	private List<OptionsVO> optionVOList; //옵션 세부사항 체크
	
	public ProductsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductsVO(int productPk, int cfFk, String productName, boolean productFlag, String productRemark,
			String productMemo1, String productMemo2, String productMemo3, String productMemo4, String productMemo5,
			String productMemo6, String productThumbnail1, String productThumbnail2, Date productRegdate,
			int productOptionCount, List<OptionsVO> optionVOList) {
		super();
		this.productPk = productPk;
		this.cfFk = cfFk;
		this.productName = productName;
		this.productFlag = productFlag;
		this.productRemark = productRemark;
		this.productMemo1 = productMemo1;
		this.productMemo2 = productMemo2;
		this.productMemo3 = productMemo3;
		this.productMemo4 = productMemo4;
		this.productMemo5 = productMemo5;
		this.productMemo6 = productMemo6;
		this.productThumbnail1 = productThumbnail1;
		this.productThumbnail2 = productThumbnail2;
		this.productRegdate = productRegdate;
		this.productOptionCount = productOptionCount;
		this.optionVOList = optionVOList;
	}

	public int getProductPk() {
		return productPk;
	}

	public void setProductPk(int productPk) {
		this.productPk = productPk;
	}

	public int getCfFk() {
		return cfFk;
	}

	public void setCfFk(int cfFk) {
		this.cfFk = cfFk;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isProductFlag() {
		return productFlag;
	}

	public void setProductFlag(boolean productFlag) {
		this.productFlag = productFlag;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public String getProductMemo1() {
		return productMemo1;
	}

	public void setProductMemo1(String productMemo1) {
		this.productMemo1 = productMemo1;
	}

	public String getProductMemo2() {
		return productMemo2;
	}

	public void setProductMemo2(String productMemo2) {
		this.productMemo2 = productMemo2;
	}

	public String getProductMemo3() {
		return productMemo3;
	}

	public void setProductMemo3(String productMemo3) {
		this.productMemo3 = productMemo3;
	}

	public String getProductMemo4() {
		return productMemo4;
	}

	public void setProductMemo4(String productMemo4) {
		this.productMemo4 = productMemo4;
	}

	public String getProductMemo5() {
		return productMemo5;
	}

	public void setProductMemo5(String productMemo5) {
		this.productMemo5 = productMemo5;
	}

	public String getProductMemo6() {
		return productMemo6;
	}

	public void setProductMemo6(String productMemo6) {
		this.productMemo6 = productMemo6;
	}

	public String getProductThumbnail1() {
		return productThumbnail1;
	}

	public void setProductThumbnail1(String productThumbnail1) {
		this.productThumbnail1 = productThumbnail1;
	}

	public String getProductThumbnail2() {
		return productThumbnail2;
	}

	public void setProductThumbnail2(String productThumbnail2) {
		this.productThumbnail2 = productThumbnail2;
	}

	public Date getProductRegdate() {
		return productRegdate;
	}

	public void setProductRegdate(Date productRegdate) {
		this.productRegdate = productRegdate;
	}

	public int getProductOptionCount() {
		return productOptionCount;
	}

	public void setProductOptionCount(int productOptionCount) {
		this.productOptionCount = productOptionCount;
	}

	public List<OptionsVO> getOptionVOList() {
		return optionVOList;
	}

	public void setOptionVOList(List<OptionsVO> optionVOList) {
		this.optionVOList = optionVOList;
	}

	@Override
	public String toString() {
		return "ProductsVO [productPk=" + productPk + ", cfFk=" + cfFk + ", productName=" + productName
				+ ", productFlag=" + productFlag + ", productRemark=" + productRemark + ", productMemo1=" + productMemo1
				+ ", productMemo2=" + productMemo2 + ", productMemo3=" + productMemo3 + ", productMemo4=" + productMemo4
				+ ", productMemo5=" + productMemo5 + ", productMemo6=" + productMemo6 + ", productThumbnail1="
				+ productThumbnail1 + ", productThumbnail2=" + productThumbnail2 + ", productRegdate=" + productRegdate
				+ ", productOptionCount=" + productOptionCount + ", optionVOList=" + optionVOList + "]";
	}

}
