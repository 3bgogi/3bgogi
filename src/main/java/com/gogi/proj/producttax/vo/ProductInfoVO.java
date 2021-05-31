package com.gogi.proj.producttax.vo;

public class ProductInfoVO {

	private int piPk;
	private int rcFk;
	private String piInputDate;
	private String piName;
	private int piQty;
	private String piMeasure;
	private int piCost;
	private int piTax;
	private int piTotalCost;
	private boolean piTaxbilCheckFlag;
	private boolean piAccFlag;
	private int piAccountReceivable;
	private String piRemark1;
	private String piRemark2;
	private String piFileOriName;
	private String piFilePath;
	private String piFileUniqName;
	private String piFileExe;
	private String piUpdate;
	private String piRegdate;
	
	private String rcNames;
	
	public ProductInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductInfoVO(int piPk, int rcFk, String piInputDate, String piName, int piQty, String piMeasure, int piCost,
			int piTax, int piTotalCost, boolean piTaxbilCheckFlag, boolean piAccFlag, int piAccountReceivable,
			String piRemark1, String piRemark2, String piFileOriName, String piFilePath, String piFileUniqName,
			String piFileExe, String piUpdate, String piRegdate) {
		super();
		this.piPk = piPk;
		this.rcFk = rcFk;
		this.piInputDate = piInputDate;
		this.piName = piName;
		this.piQty = piQty;
		this.piMeasure = piMeasure;
		this.piCost = piCost;
		this.piTax = piTax;
		this.piTotalCost = piTotalCost;
		this.piTaxbilCheckFlag = piTaxbilCheckFlag;
		this.piAccFlag = piAccFlag;
		this.piAccountReceivable = piAccountReceivable;
		this.piRemark1 = piRemark1;
		this.piRemark2 = piRemark2;
		this.piFileOriName = piFileOriName;
		this.piFilePath = piFilePath;
		this.piFileUniqName = piFileUniqName;
		this.piFileExe = piFileExe;
		this.piUpdate = piUpdate;
		this.piRegdate = piRegdate;
	}

	public String getRcNames() {
		return rcNames;
	}

	public void setRcNames(String rcNames) {
		this.rcNames = rcNames;
	}

	public int getPiPk() {
		return piPk;
	}

	public void setPiPk(int piPk) {
		this.piPk = piPk;
	}

	public int getRcFk() {
		return rcFk;
	}

	public void setRcFk(int rcFk) {
		this.rcFk = rcFk;
	}

	public String getPiInputDate() {
		return piInputDate;
	}

	public void setPiInputDate(String piInputDate) {
		this.piInputDate = piInputDate;
	}

	public String getPiName() {
		return piName;
	}

	public void setPiName(String piName) {
		this.piName = piName;
	}

	public int getPiQty() {
		return piQty;
	}

	public void setPiQty(int piQty) {
		this.piQty = piQty;
	}

	public String getPiMeasure() {
		return piMeasure;
	}

	public void setPiMeasure(String piMeasure) {
		this.piMeasure = piMeasure;
	}

	public int getPiCost() {
		return piCost;
	}

	public void setPiCost(int piCost) {
		this.piCost = piCost;
	}

	public int getPiTax() {
		return piTax;
	}

	public void setPiTax(int piTax) {
		this.piTax = piTax;
	}

	public int getPiTotalCost() {
		return piTotalCost;
	}

	public void setPiTotalCost(int piTotalCost) {
		this.piTotalCost = piTotalCost;
	}

	public boolean isPiTaxbilCheckFlag() {
		return piTaxbilCheckFlag;
	}

	public void setPiTaxbilCheckFlag(boolean piTaxbilCheckFlag) {
		this.piTaxbilCheckFlag = piTaxbilCheckFlag;
	}

	public boolean isPiAccFlag() {
		return piAccFlag;
	}

	public void setPiAccFlag(boolean piAccFlag) {
		this.piAccFlag = piAccFlag;
	}

	public int getPiAccountReceivable() {
		return piAccountReceivable;
	}

	public void setPiAccountReceivable(int piAccountReceivable) {
		this.piAccountReceivable = piAccountReceivable;
	}

	public String getPiRemark1() {
		return piRemark1;
	}

	public void setPiRemark1(String piRemark1) {
		this.piRemark1 = piRemark1;
	}

	public String getPiRemark2() {
		return piRemark2;
	}

	public void setPiRemark2(String piRemark2) {
		this.piRemark2 = piRemark2;
	}

	public String getPiFileOriName() {
		return piFileOriName;
	}

	public void setPiFileOriName(String piFileOriName) {
		this.piFileOriName = piFileOriName;
	}

	public String getPiFilePath() {
		return piFilePath;
	}

	public void setPiFilePath(String piFilePath) {
		this.piFilePath = piFilePath;
	}

	public String getPiFileUniqName() {
		return piFileUniqName;
	}

	public void setPiFileUniqName(String piFileUniqName) {
		this.piFileUniqName = piFileUniqName;
	}

	public String getPiFileExe() {
		return piFileExe;
	}

	public void setPiFileExe(String piFileExe) {
		this.piFileExe = piFileExe;
	}

	public String getPiUpdate() {
		return piUpdate;
	}

	public void setPiUpdate(String piUpdate) {
		this.piUpdate = piUpdate;
	}

	public String getPiRegdate() {
		return piRegdate;
	}

	public void setPiRegdate(String piRegdate) {
		this.piRegdate = piRegdate;
	}

	@Override
	public String toString() {
		return "ProductInfoVO [piPk=" + piPk + ", rcFk=" + rcFk + ", piInputDate=" + piInputDate + ", piName=" + piName
				+ ", piQty=" + piQty + ", piMeasure=" + piMeasure + ", piCost=" + piCost + ", piTax=" + piTax
				+ ", piTotalCost=" + piTotalCost + ", piTaxbilCheckFlag=" + piTaxbilCheckFlag + ", piAccFlag="
				+ piAccFlag + ", piAccountReceivable=" + piAccountReceivable + ", piRemark1=" + piRemark1
				+ ", piRemark2=" + piRemark2 + ", piFileOriName=" + piFileOriName + ", piFilePath=" + piFilePath
				+ ", piFileUniqName=" + piFileUniqName + ", piFileExe=" + piFileExe + ", piUpdate=" + piUpdate
				+ ", piRegdate=" + piRegdate + "]";
	}
	
}
