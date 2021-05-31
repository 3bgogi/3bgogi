package com.gogi.proj.stock.vo;

import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.product.products.vo.ProductOptionVO;

public class ProductInputListVO {

	private int pilPk;					//상품 입고 고유값
	private int optionFk;				//옵션 고유값
	private int pilQty;					//입고 개수
	private String pilAdmin;			//신청자
	private int pilAdminPk;				//신청자 고유값
	private boolean pilPermFlag;		//승인여부
	private String pilReqDate;			//요청일
	private String pilExpDate;			//유통기한
	private String pilPermAdmin;		//승인자
	private int pilPermAdminPk;			//승인자 고유값
	private String pilResDate;			//승인일
	private Timestamp pilRegdate;		//등록일
	
	private String pilFilePath;			//경로
	private String pilFileName;			//파일명
	private String pilFileExe;			//확장자
	private String pilFileOriName;		//파일오리지널명
	
	
	private List<ProductOptionVO> productList;
	
	public ProductInputListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductInputListVO(int pilPk, int optionFk, int pilQty, String pilAdmin, int pilAdminPk, boolean pilPermFlag,
			String pilReqDate, String pilExpDate, String pilPermAdmin, int pilPermAdminPk, String pilResDate,
			Timestamp pilRegdate, String pilFilePath, String pilFileName, String pilFileExe, String pilFileOriName,
			List<ProductOptionVO> productList) {
		super();
		this.pilPk = pilPk;
		this.optionFk = optionFk;
		this.pilQty = pilQty;
		this.pilAdmin = pilAdmin;
		this.pilAdminPk = pilAdminPk;
		this.pilPermFlag = pilPermFlag;
		this.pilReqDate = pilReqDate;
		this.pilExpDate = pilExpDate;
		this.pilPermAdmin = pilPermAdmin;
		this.pilPermAdminPk = pilPermAdminPk;
		this.pilResDate = pilResDate;
		this.pilRegdate = pilRegdate;
		this.pilFilePath = pilFilePath;
		this.pilFileName = pilFileName;
		this.pilFileExe = pilFileExe;
		this.pilFileOriName = pilFileOriName;
		this.productList = productList;
	}

	public int getPilPk() {
		return pilPk;
	}

	public void setPilPk(int pilPk) {
		this.pilPk = pilPk;
	}

	public int getOptionFk() {
		return optionFk;
	}

	public void setOptionFk(int optionFk) {
		this.optionFk = optionFk;
	}

	public int getPilQty() {
		return pilQty;
	}

	public void setPilQty(int pilQty) {
		this.pilQty = pilQty;
	}

	public String getPilAdmin() {
		return pilAdmin;
	}

	public void setPilAdmin(String pilAdmin) {
		this.pilAdmin = pilAdmin;
	}

	public int getPilAdminPk() {
		return pilAdminPk;
	}

	public void setPilAdminPk(int pilAdminPk) {
		this.pilAdminPk = pilAdminPk;
	}

	public boolean isPilPermFlag() {
		return pilPermFlag;
	}

	public void setPilPermFlag(boolean pilPermFlag) {
		this.pilPermFlag = pilPermFlag;
	}

	public String getPilReqDate() {
		return pilReqDate;
	}

	public void setPilReqDate(String pilReqDate) {
		this.pilReqDate = pilReqDate;
	}

	public String getPilExpDate() {
		return pilExpDate;
	}

	public void setPilExpDate(String pilExpDate) {
		this.pilExpDate = pilExpDate;
	}

	public String getPilPermAdmin() {
		return pilPermAdmin;
	}

	public void setPilPermAdmin(String pilPermAdmin) {
		this.pilPermAdmin = pilPermAdmin;
	}

	public int getPilPermAdminPk() {
		return pilPermAdminPk;
	}

	public void setPilPermAdminPk(int pilPermAdminPk) {
		this.pilPermAdminPk = pilPermAdminPk;
	}

	public String getPilResDate() {
		return pilResDate;
	}

	public void setPilResDate(String pilResDate) {
		this.pilResDate = pilResDate;
	}

	public Timestamp getPilRegdate() {
		return pilRegdate;
	}

	public void setPilRegdate(Timestamp pilRegdate) {
		this.pilRegdate = pilRegdate;
	}

	public String getPilFilePath() {
		return pilFilePath;
	}

	public void setPilFilePath(String pilFilePath) {
		this.pilFilePath = pilFilePath;
	}

	public String getPilFileName() {
		return pilFileName;
	}

	public void setPilFileName(String pilFileName) {
		this.pilFileName = pilFileName;
	}

	public String getPilFileExe() {
		return pilFileExe;
	}

	public void setPilFileExe(String pilFileExe) {
		this.pilFileExe = pilFileExe;
	}

	public String getPilFileOriName() {
		return pilFileOriName;
	}

	public void setPilFileOriName(String pilFileOriName) {
		this.pilFileOriName = pilFileOriName;
	}

	public List<ProductOptionVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductOptionVO> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "ProductInputListVO [pilPk=" + pilPk + ", optionFk=" + optionFk + ", pilQty=" + pilQty + ", pilAdmin="
				+ pilAdmin + ", pilAdminPk=" + pilAdminPk + ", pilPermFlag=" + pilPermFlag + ", pilReqDate="
				+ pilReqDate + ", pilExpDate=" + pilExpDate + ", pilPermAdmin=" + pilPermAdmin + ", pilPermAdminPk="
				+ pilPermAdminPk + ", pilResDate=" + pilResDate + ", pilRegdate=" + pilRegdate + ", pilFilePath="
				+ pilFilePath + ", pilFileName=" + pilFileName + ", pilFileExe=" + pilFileExe + ", pilFileOriName="
				+ pilFileOriName + ", productList=" + productList + "]";
	}

}
