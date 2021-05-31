package com.gogi.proj.orders.autocomplete;

public enum GodomallInvoiceCompNum {

	EPOST("우체국택배", 12), 
	FRESH_SOLUTION("프레시솔루션", 94),
	EMPTY("없음", 0);
	
	private String delivComNm;
	private int delivComNo;

	private GodomallInvoiceCompNum(String delivComNm, int delivComNo) {
		// TODO Auto-generated constructor stub
		this.delivComNm = delivComNm;
		this.delivComNo = delivComNo;
	}

	private String getDelivComNm() {
		return delivComNm;
	}

	public static int getCompanyNumber(String dbCompanyNm) {
		for (GodomallInvoiceCompNum company : GodomallInvoiceCompNum.values()) {
			if (company.getDelivComNm().equals(dbCompanyNm)) {
				return company.delivComNo;
			}
		}
		return GodomallInvoiceCompNum.EMPTY.delivComNo;
	}
}
