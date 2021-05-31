package com.gogi.proj.stock.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;

public class CarcassInputListVO {

	private int cilPk;							//고유값
	private String cilAnimalProdTraceNum;		//이력번호
	private String cilPurchaseStore;			//구매처
	private int cilPurchasePrice;				//구매가
	private String costDetailPkList;			//원가 세부사항 목록
	private int cilWeight;						//도체무게
	private String cilNum;						//도체번호
	private String cilProduct;					//품목명
	private int cilQty;							//도체 수
	private String cilAdmin;					//등록자
	private int cilAdminPk;						//등록자 고유값
	private String cilFilePath;					//파일 경로
	private String cilFileExe;					//파일 확장자명
	private String cilFileOriName;				//원본 파일명
	private String cilFileUniqName;				//고유 파일명
	private String cilTransDetailFilePath;		//명세서 파일경로
	private String cilTransDetailFileExe;		//명세서 파일 확장자명
	private String cilTransDetailFileOriName;	//명세서 원본 파일명
	private String cilTransDetailFileUniqName;	//명세서 고유 파일명
	private String cilInputDate;				//입고일
	private Timestamp cilRegdate;				//등록일
	
	private int ciTotalCounting;				//부분육 개수
	//특별 추가
	private List<String> costDetailPk;			//원가 세부사항 목록 배열값 
	private List<CostIoVO> costIoList;	//원가 상세사항 입력하는 목록값
	
	public CarcassInputListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarcassInputListVO(int cilPk, String cilAnimalProdTraceNum, String cilPurchaseStore, int cilPurchasePrice,
			String costDetailPkList, int cilWeight, String cilNum, String cilProduct, int cilQty, String cilAdmin,
			int cilAdminPk, String cilFilePath, String cilFileExe, String cilFileOriName, String cilFileUniqName,
			String cilTransDetailFilePath, String cilTransDetailFileExe, String cilTransDetailFileOriName,
			String cilTransDetailFileUniqName, String cilInputDate, Timestamp cilRegdate, List<String> costDetailPk,
			List<CostIoVO> costIoList) {
		super();
		this.cilPk = cilPk;
		this.cilAnimalProdTraceNum = cilAnimalProdTraceNum;
		this.cilPurchaseStore = cilPurchaseStore;
		this.cilPurchasePrice = cilPurchasePrice;
		this.costDetailPkList = costDetailPkList;
		this.cilWeight = cilWeight;
		this.cilNum = cilNum;
		this.cilProduct = cilProduct;
		this.cilQty = cilQty;
		this.cilAdmin = cilAdmin;
		this.cilAdminPk = cilAdminPk;
		this.cilFilePath = cilFilePath;
		this.cilFileExe = cilFileExe;
		this.cilFileOriName = cilFileOriName;
		this.cilFileUniqName = cilFileUniqName;
		this.cilTransDetailFilePath = cilTransDetailFilePath;
		this.cilTransDetailFileExe = cilTransDetailFileExe;
		this.cilTransDetailFileOriName = cilTransDetailFileOriName;
		this.cilTransDetailFileUniqName = cilTransDetailFileUniqName;
		this.cilInputDate = cilInputDate;
		this.cilRegdate = cilRegdate;
		this.costDetailPk = costDetailPk;
		this.costIoList = costIoList;
	}

	public int getCiTotalCounting() {
		return ciTotalCounting;
	}

	public void setCiTotalCounting(int ciTotalCounting) {
		this.ciTotalCounting = ciTotalCounting;
	}

	public int getCilPk() {
		return cilPk;
	}

	public void setCilPk(int cilPk) {
		this.cilPk = cilPk;
	}

	public String getCilAnimalProdTraceNum() {
		return cilAnimalProdTraceNum;
	}

	public void setCilAnimalProdTraceNum(String cilAnimalProdTraceNum) {
		this.cilAnimalProdTraceNum = cilAnimalProdTraceNum;
	}

	public String getCilPurchaseStore() {
		return cilPurchaseStore;
	}

	public void setCilPurchaseStore(String cilPurchaseStore) {
		this.cilPurchaseStore = cilPurchaseStore;
	}

	public int getCilPurchasePrice() {
		return cilPurchasePrice;
	}

	public void setCilPurchasePrice(int cilPurchasePrice) {
		this.cilPurchasePrice = cilPurchasePrice;
	}

	public String getCostDetailPkList() {
		return costDetailPkList;
	}

	public void setCostDetailPkList(String costDetailPkList) {
		this.costDetailPkList = costDetailPkList;
	}

	public int getCilWeight() {
		return cilWeight;
	}

	public void setCilWeight(int cilWeight) {
		this.cilWeight = cilWeight;
	}

	public String getCilNum() {
		return cilNum;
	}

	public void setCilNum(String cilNum) {
		this.cilNum = cilNum;
	}

	public String getCilProduct() {
		return cilProduct;
	}

	public void setCilProduct(String cilProduct) {
		this.cilProduct = cilProduct;
	}

	public int getCilQty() {
		return cilQty;
	}

	public void setCilQty(int cilQty) {
		this.cilQty = cilQty;
	}

	public String getCilAdmin() {
		return cilAdmin;
	}

	public void setCilAdmin(String cilAdmin) {
		this.cilAdmin = cilAdmin;
	}

	public int getCilAdminPk() {
		return cilAdminPk;
	}

	public void setCilAdminPk(int cilAdminPk) {
		this.cilAdminPk = cilAdminPk;
	}

	public String getCilFilePath() {
		return cilFilePath;
	}

	public void setCilFilePath(String cilFilePath) {
		this.cilFilePath = cilFilePath;
	}

	public String getCilFileExe() {
		return cilFileExe;
	}

	public void setCilFileExe(String cilFileExe) {
		this.cilFileExe = cilFileExe;
	}

	public String getCilFileOriName() {
		return cilFileOriName;
	}

	public void setCilFileOriName(String cilFileOriName) {
		this.cilFileOriName = cilFileOriName;
	}

	public String getCilFileUniqName() {
		return cilFileUniqName;
	}

	public void setCilFileUniqName(String cilFileUniqName) {
		this.cilFileUniqName = cilFileUniqName;
	}

	public String getCilTransDetailFilePath() {
		return cilTransDetailFilePath;
	}

	public void setCilTransDetailFilePath(String cilTransDetailFilePath) {
		this.cilTransDetailFilePath = cilTransDetailFilePath;
	}

	public String getCilTransDetailFileExe() {
		return cilTransDetailFileExe;
	}

	public void setCilTransDetailFileExe(String cilTransDetailFileExe) {
		this.cilTransDetailFileExe = cilTransDetailFileExe;
	}

	public String getCilTransDetailFileOriName() {
		return cilTransDetailFileOriName;
	}

	public void setCilTransDetailFileOriName(String cilTransDetailFileOriName) {
		this.cilTransDetailFileOriName = cilTransDetailFileOriName;
	}

	public String getCilTransDetailFileUniqName() {
		return cilTransDetailFileUniqName;
	}

	public void setCilTransDetailFileUniqName(String cilTransDetailFileUniqName) {
		this.cilTransDetailFileUniqName = cilTransDetailFileUniqName;
	}

	public String getCilInputDate() {
		return cilInputDate;
	}

	public void setCilInputDate(String cilInputDate) {
		this.cilInputDate = cilInputDate;
	}

	public Timestamp getCilRegdate() {
		return cilRegdate;
	}

	public void setCilRegdate(Timestamp cilRegdate) {
		this.cilRegdate = cilRegdate;
	}

	public List<String> getCostDetailPk() {
		return costDetailPk;
	}

	public void setCostDetailPk(List<String> costDetailPk) {
		this.costDetailPk = costDetailPk;
	}

	public List<CostIoVO> getCostIoList() {
		return costIoList;
	}

	public void setCostIoList(List<CostIoVO> costIoList) {
		this.costIoList = costIoList;
	}

	@Override
	public String toString() {
		return "CarcassInputListVO [cilPk=" + cilPk + ", cilAnimalProdTraceNum=" + cilAnimalProdTraceNum
				+ ", cilPurchaseStore=" + cilPurchaseStore + ", cilPurchasePrice=" + cilPurchasePrice
				+ ", costDetailPkList=" + costDetailPkList + ", cilWeight=" + cilWeight + ", cilNum=" + cilNum
				+ ", cilProduct=" + cilProduct + ", cilQty=" + cilQty + ", cilAdmin=" + cilAdmin + ", cilAdminPk="
				+ cilAdminPk + ", cilFilePath=" + cilFilePath + ", cilFileExe=" + cilFileExe + ", cilFileOriName="
				+ cilFileOriName + ", cilFileUniqName=" + cilFileUniqName + ", cilTransDetailFilePath="
				+ cilTransDetailFilePath + ", cilTransDetailFileExe=" + cilTransDetailFileExe
				+ ", cilTransDetailFileOriName=" + cilTransDetailFileOriName + ", cilTransDetailFileUniqName="
				+ cilTransDetailFileUniqName + ", cilInputDate=" + cilInputDate + ", cilRegdate=" + cilRegdate
				+ ", costDetailPk=" + costDetailPk + ", costIoList=" + costIoList + "]";
	}

}
