package com.gogi.proj.stock.vo;

public class PrintDataSetVO {

	private int qty;
	private String product;
	private String weight;
	private String countryOfOrigin;
	private String rawMeterials;
	private String sellByDate;
	private String storageMethod;
	private String levels;
	private String itemsManufNum;
	private String abattoir;
	private String animalProdTraceNum;
	private String barcodeNum;
	
	public PrintDataSetVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrintDataSetVO(int qty, String product, String weight, String countryOfOrigin, String rawMeterials,
			String sellByDate, String storageMethod, String levels, String itemsManufNum, String abattoir,
			String animalProdTraceNum, String barcodeNum) {
		super();
		this.qty = qty;
		this.product = product;
		this.weight = weight;
		this.countryOfOrigin = countryOfOrigin;
		this.rawMeterials = rawMeterials;
		this.sellByDate = sellByDate;
		this.storageMethod = storageMethod;
		this.levels = levels;
		this.itemsManufNum = itemsManufNum;
		this.abattoir = abattoir;
		this.animalProdTraceNum = animalProdTraceNum;
		this.barcodeNum = barcodeNum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getRawMeterials() {
		return rawMeterials;
	}

	public void setRawMeterials(String rawMeterials) {
		this.rawMeterials = rawMeterials;
	}

	public String getSellByDate() {
		return sellByDate;
	}

	public void setSellByDate(String sellByDate) {
		this.sellByDate = sellByDate;
	}

	public String getStorageMethod() {
		return storageMethod;
	}

	public void setStorageMethod(String storageMethod) {
		this.storageMethod = storageMethod;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getItemsManufNum() {
		return itemsManufNum;
	}

	public void setItemsManufNum(String itemsManufNum) {
		this.itemsManufNum = itemsManufNum;
	}

	public String getAbattoir() {
		return abattoir;
	}

	public void setAbattoir(String abattoir) {
		this.abattoir = abattoir;
	}

	public String getAnimalProdTraceNum() {
		return animalProdTraceNum;
	}

	public void setAnimalProdTraceNum(String animalProdTraceNum) {
		this.animalProdTraceNum = animalProdTraceNum;
	}

	public String getBarcodeNum() {
		return barcodeNum;
	}

	public void setBarcodeNum(String barcodeNum) {
		this.barcodeNum = barcodeNum;
	}

	@Override
	public String toString() {
		return "PrintDataSetVO [qty=" + qty + ", product=" + product + ", weight=" + weight + ", countryOfOrigin="
				+ countryOfOrigin + ", rawMeterials=" + rawMeterials + ", sellByDate=" + sellByDate + ", storageMethod="
				+ storageMethod + ", levels=" + levels + ", itemsManufNum=" + itemsManufNum + ", abattoir=" + abattoir
				+ ", animalProdTraceNum=" + animalProdTraceNum + ", barcodeNum=" + barcodeNum + "]";
	}
}
