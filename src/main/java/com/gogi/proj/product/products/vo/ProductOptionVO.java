package com.gogi.proj.product.products.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.stock.vo.ProductInputListVO;

public class ProductOptionVO {

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
	private List<OptionsCostsMatchingVO> optionCostsMatchingVOList;
	
	private int anotherOptionPk;
	private int optionSupplyCost;
	private int anotherOptionQty;
	private int prodSorting;
	
	private List<ProductInputListVO> pilList;
	
	public ProductOptionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOptionVO(int productPk, int cfFk, String productName, boolean productFlag, String productRemark,
			String productMemo1, String productMemo2, String productMemo3, String productMemo4, String productMemo5,
			String productMemo6, String productThumbnail1, String productThumbnail2, Date productRegdate, int optionPk,
			int eoFk, int resFk, int eventFk, int pccFk, int productFk, String optionName, int optionPrice,
			int optionWidthSize, int optionHeightSize, int optionLengthSize, boolean optionFlag, int optionSeq,
			String optionBarcodeNumber1, String optionBarcodeNumber2, String optionRemark, boolean optionTaxFlag,
			boolean optionStockFlag, int optionStock, int optionStockMaxAlarm, boolean optionExcelColorCheck,
			String optionMemo1, String optionMemo2, String optionMemo3, String optionMemo4, String optionThumbnail1,
			String optionThumbnail2, Timestamp optionUpdate, Date optionRegdate, int optionCost, boolean optionCostFlag,
			List<OptionsCostsMatchingVO> optionCostsMatchingVOList, int anotherOptionPk, int optionSupplyCost,
			int anotherOptionQty) {
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
		this.optionCostsMatchingVOList = optionCostsMatchingVOList;
		this.anotherOptionPk = anotherOptionPk;
		this.optionSupplyCost = optionSupplyCost;
		this.anotherOptionQty = anotherOptionQty;
	}
	
	public int getProdSorting() {
		return prodSorting;
	}

	public void setProdSorting(int prodSorting) {
		this.prodSorting = prodSorting;
	}

	public List<ProductInputListVO> getPilList() {
		return pilList;
	}

	public void setPilList(List<ProductInputListVO> pilList) {
		this.pilList = pilList;
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

	public boolean isOptionFlag() {
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

	public boolean isOptionTaxFlag() {
		return optionTaxFlag;
	}

	public void setOptionTaxFlag(boolean optionTaxFlag) {
		this.optionTaxFlag = optionTaxFlag;
	}

	public boolean isOptionStockFlag() {
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

	public boolean isOptionExcelColorCheck() {
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

	public boolean isOptionCostFlag() {
		return optionCostFlag;
	}

	public void setOptionCostFlag(boolean optionCostFlag) {
		this.optionCostFlag = optionCostFlag;
	}

	public List<OptionsCostsMatchingVO> getOptionCostsMatchingVOList() {
		return optionCostsMatchingVOList;
	}

	public void setOptionCostsMatchingVOList(List<OptionsCostsMatchingVO> optionCostsMatchingVOList) {
		this.optionCostsMatchingVOList = optionCostsMatchingVOList;
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

	@Override
	public String toString() {
		return "ProductOptionVO [productPk=" + productPk + ", cfFk=" + cfFk + ", productName=" + productName
				+ ", productFlag=" + productFlag + ", productRemark=" + productRemark + ", productMemo1=" + productMemo1
				+ ", productMemo2=" + productMemo2 + ", productMemo3=" + productMemo3 + ", productMemo4=" + productMemo4
				+ ", productMemo5=" + productMemo5 + ", productMemo6=" + productMemo6 + ", productThumbnail1="
				+ productThumbnail1 + ", productThumbnail2=" + productThumbnail2 + ", productRegdate=" + productRegdate
				+ ", optionPk=" + optionPk + ", eoFk=" + eoFk + ", resFk=" + resFk + ", eventFk=" + eventFk + ", pccFk="
				+ pccFk + ", productFk=" + productFk + ", optionName=" + optionName + ", optionPrice=" + optionPrice
				+ ", optionWidthSize=" + optionWidthSize + ", optionHeightSize=" + optionHeightSize
				+ ", optionLengthSize=" + optionLengthSize + ", optionFlag=" + optionFlag + ", optionSeq=" + optionSeq
				+ ", optionBarcodeNumber1=" + optionBarcodeNumber1 + ", optionBarcodeNumber2=" + optionBarcodeNumber2
				+ ", optionRemark=" + optionRemark + ", optionTaxFlag=" + optionTaxFlag + ", optionStockFlag="
				+ optionStockFlag + ", optionStock=" + optionStock + ", optionStockMaxAlarm=" + optionStockMaxAlarm
				+ ", optionExcelColorCheck=" + optionExcelColorCheck + ", optionMemo1=" + optionMemo1 + ", optionMemo2="
				+ optionMemo2 + ", optionMemo3=" + optionMemo3 + ", optionMemo4=" + optionMemo4 + ", optionThumbnail1="
				+ optionThumbnail1 + ", optionThumbnail2=" + optionThumbnail2 + ", optionUpdate=" + optionUpdate
				+ ", optionRegdate=" + optionRegdate + ", optionCost=" + optionCost + ", optionCostFlag="
				+ optionCostFlag + ", optionCostsMatchingVOList=" + optionCostsMatchingVOList + ", anotherOptionPk="
				+ anotherOptionPk + ", optionSupplyCost=" + optionSupplyCost + ", anotherOptionQty=" + anotherOptionQty
				+ "]";
	}

}
