package com.gogi.proj.orders.config.vo;

public class StoreExcelDataSortingVO {

	
	private int sedsPk;							//스토어 엑셀 열 번호 고유 값
	private int ssFk;							//판매처 고유 번호
	private int sedsBuyerId;					//구매자 아이디
	private int sedsBuyerName;					//구매자 이름
	private int sedsBuyerContractNumber1;		//구매자 연락처1
	private int sedsBuyerContractNumber2;		//구매자 연락처2
	private int sedsReceiverName;				//수령인 명, 수취인명
	private int sedsProduct;					//상품명
	private int sedsProductType;				//상품종류
	private int sedsProductOption;				//옵션명
	private int sedsQuantity;					//구매 수량
	private int sedsDeliveryMessage;			//배송메세지
	private int sedsDeliveryCompany;			//택배사
	private int sedsDeliveryType;				//배송방법 -- 퀵, 택배, 직접수령 등등
	private int sedsOrderNumber;				//주문번호
	private int sedsProductOrderNumber;			//상품주문번호
	private int sedsProductNumber;				//상품번호 -- 나중에 웹페이지로 바로 가기 위함
	private int sedsPaymentPositionType;		//결제위치 -- 모바일 등등
	private int sedsSettlementDay;				//결제일
	private int sedsProductPrice;				//상품가격
	private int sedsProductOptionPrice;			//옵션가격
	private int sedsDiscountPrice;				//상품할인가
	private int sedsTotalPrice;					//총 주문가격
	private int sedsTotalCost;					//총 원가
	private int sedsOrderCheckDay;				//주문확인일, 발주확인일
	private int sedsSendingDeadline;			//발송기한
	private int sedsSendingDay;					//발송일
	private int sedsDeliveryChargeType;			//배송비형태
	private int sedsDeliveryNumber;				//배송묶음번호
	private int sedsDeliveryPrice;				//배송비
	private int sedsDeliveryDiscountPrice;		//배송비 할인액
	private int sedsReceiverContractNumber1;	//수령인, 수취인 연락처 1
	private int sedsReceiverContractNumber2;	//수령인, 수취인 연락처 2
	private int sedsShippingAddressNumber;		//우편번호
	private int sedsShippingAddress;			//주소
	private int sedsShippingAddressDetail;		//주소 상세사항
	private int sedsSpecialRegionFlag;			//섬 지역 여부 
	private int sedsPaymentType;				//결제 수단
	private int sedsPaymentCommision;			//결제수수료
	private int sedsInflowRoute;				//유입경로
	private int sedsRequest;					//특별 요청 사항
	private int sedsGender;						//성별
	private int sedsBirthDate;					//생년월일
	private int sedsUserColumn1;				//사용자 설정 값
	private int sedsUserColumn2;
	private int sedsUserColumn3;
	private int sedsUserColumn4;
	private int sedsStartRow;
	
	public StoreExcelDataSortingVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreExcelDataSortingVO(int sedsPk, int ssFk, int sedsBuyerId, int sedsBuyerName,
			int sedsBuyerContractNumber1, int sedsBuyerContractNumber2, int sedsReceiverName, int sedsProduct,
			int sedsProductType, int sedsProductOption, int sedsQuantity, int sedsDeliveryMessage,
			int sedsDeliveryCompany, int sedsDeliveryType, int sedsOrderNumber, int sedsProductOrderNumber,
			int sedsProductNumber, int sedsPaymentPositionType, int sedsSettlementDay, int sedsProductPrice,
			int sedsProductOptionPrice, int sedsDiscountPrice, int sedsTotalPrice, int sedsTotalCost,
			int sedsOrderCheckDay, int sedsSendingDeadline, int sedsSendingDay, int sedsDeliveryChargeType,
			int sedsDeliveryNumber, int sedsDeliveryPrice, int sedsDeliveryDiscountPrice,
			int sedsReceiverContractNumber1, int sedsReceiverContractNumber2, int sedsShippingAddressNumber,
			int sedsShippingAddress, int sedsShippingAddressDetail, int sedsSpecialRegionFlag, int sedsPaymentType,
			int sedsPaymentCommision, int sedsInflowRoute, int sedsRequest, int sedsGender, int sedsBirthDate,
			int sedsUserColumn1, int sedsUserColumn2, int sedsUserColumn3, int sedsUserColumn4) {
		super();
		this.sedsPk = sedsPk;
		this.ssFk = ssFk;
		this.sedsBuyerId = sedsBuyerId;
		this.sedsBuyerName = sedsBuyerName;
		this.sedsBuyerContractNumber1 = sedsBuyerContractNumber1;
		this.sedsBuyerContractNumber2 = sedsBuyerContractNumber2;
		this.sedsReceiverName = sedsReceiverName;
		this.sedsProduct = sedsProduct;
		this.sedsProductType = sedsProductType;
		this.sedsProductOption = sedsProductOption;
		this.sedsQuantity = sedsQuantity;
		this.sedsDeliveryMessage = sedsDeliveryMessage;
		this.sedsDeliveryCompany = sedsDeliveryCompany;
		this.sedsDeliveryType = sedsDeliveryType;
		this.sedsOrderNumber = sedsOrderNumber;
		this.sedsProductOrderNumber = sedsProductOrderNumber;
		this.sedsProductNumber = sedsProductNumber;
		this.sedsPaymentPositionType = sedsPaymentPositionType;
		this.sedsSettlementDay = sedsSettlementDay;
		this.sedsProductPrice = sedsProductPrice;
		this.sedsProductOptionPrice = sedsProductOptionPrice;
		this.sedsDiscountPrice = sedsDiscountPrice;
		this.sedsTotalPrice = sedsTotalPrice;
		this.sedsTotalCost = sedsTotalCost;
		this.sedsOrderCheckDay = sedsOrderCheckDay;
		this.sedsSendingDeadline = sedsSendingDeadline;
		this.sedsSendingDay = sedsSendingDay;
		this.sedsDeliveryChargeType = sedsDeliveryChargeType;
		this.sedsDeliveryNumber = sedsDeliveryNumber;
		this.sedsDeliveryPrice = sedsDeliveryPrice;
		this.sedsDeliveryDiscountPrice = sedsDeliveryDiscountPrice;
		this.sedsReceiverContractNumber1 = sedsReceiverContractNumber1;
		this.sedsReceiverContractNumber2 = sedsReceiverContractNumber2;
		this.sedsShippingAddressNumber = sedsShippingAddressNumber;
		this.sedsShippingAddress = sedsShippingAddress;
		this.sedsShippingAddressDetail = sedsShippingAddressDetail;
		this.sedsSpecialRegionFlag = sedsSpecialRegionFlag;
		this.sedsPaymentType = sedsPaymentType;
		this.sedsPaymentCommision = sedsPaymentCommision;
		this.sedsInflowRoute = sedsInflowRoute;
		this.sedsRequest = sedsRequest;
		this.sedsGender = sedsGender;
		this.sedsBirthDate = sedsBirthDate;
		this.sedsUserColumn1 = sedsUserColumn1;
		this.sedsUserColumn2 = sedsUserColumn2;
		this.sedsUserColumn3 = sedsUserColumn3;
		this.sedsUserColumn4 = sedsUserColumn4;
	}
	
	public int getSedsStartRow() {
		return sedsStartRow;
	}

	public void setSedsStartRow(int sedsStartRow) {
		this.sedsStartRow = sedsStartRow;
	}

	public int getSedsPk() {
		return sedsPk;
	}

	public void setSedsPk(int sedsPk) {
		this.sedsPk = sedsPk;
	}

	public int getSsFk() {
		return ssFk;
	}

	public void setSsFk(int ssFk) {
		this.ssFk = ssFk;
	}

	public int getSedsBuyerId() {
		return sedsBuyerId;
	}

	public void setSedsBuyerId(int sedsBuyerId) {
		this.sedsBuyerId = sedsBuyerId;
	}

	public int getSedsBuyerName() {
		return sedsBuyerName;
	}

	public void setSedsBuyerName(int sedsBuyerName) {
		this.sedsBuyerName = sedsBuyerName;
	}

	public int getSedsBuyerContractNumber1() {
		return sedsBuyerContractNumber1;
	}

	public void setSedsBuyerContractNumber1(int sedsBuyerContractNumber1) {
		this.sedsBuyerContractNumber1 = sedsBuyerContractNumber1;
	}

	public int getSedsBuyerContractNumber2() {
		return sedsBuyerContractNumber2;
	}

	public void setSedsBuyerContractNumber2(int sedsBuyerContractNumber2) {
		this.sedsBuyerContractNumber2 = sedsBuyerContractNumber2;
	}

	public int getSedsReceiverName() {
		return sedsReceiverName;
	}

	public void setSedsReceiverName(int sedsReceiverName) {
		this.sedsReceiverName = sedsReceiverName;
	}

	public int getSedsProduct() {
		return sedsProduct;
	}

	public void setSedsProduct(int sedsProduct) {
		this.sedsProduct = sedsProduct;
	}

	public int getSedsProductType() {
		return sedsProductType;
	}

	public void setSedsProductType(int sedsProductType) {
		this.sedsProductType = sedsProductType;
	}

	public int getSedsProductOption() {
		return sedsProductOption;
	}

	public void setSedsProductOption(int sedsProductOption) {
		this.sedsProductOption = sedsProductOption;
	}

	public int getSedsQuantity() {
		return sedsQuantity;
	}

	public void setSedsQuantity(int sedsQuantity) {
		this.sedsQuantity = sedsQuantity;
	}

	public int getSedsDeliveryMessage() {
		return sedsDeliveryMessage;
	}

	public void setSedsDeliveryMessage(int sedsDeliveryMessage) {
		this.sedsDeliveryMessage = sedsDeliveryMessage;
	}

	public int getSedsDeliveryCompany() {
		return sedsDeliveryCompany;
	}

	public void setSedsDeliveryCompany(int sedsDeliveryCompany) {
		this.sedsDeliveryCompany = sedsDeliveryCompany;
	}

	public int getSedsDeliveryType() {
		return sedsDeliveryType;
	}

	public void setSedsDeliveryType(int sedsDeliveryType) {
		this.sedsDeliveryType = sedsDeliveryType;
	}

	public int getSedsOrderNumber() {
		return sedsOrderNumber;
	}

	public void setSedsOrderNumber(int sedsOrderNumber) {
		this.sedsOrderNumber = sedsOrderNumber;
	}

	public int getSedsProductOrderNumber() {
		return sedsProductOrderNumber;
	}

	public void setSedsProductOrderNumber(int sedsProductOrderNumber) {
		this.sedsProductOrderNumber = sedsProductOrderNumber;
	}

	public int getSedsProductNumber() {
		return sedsProductNumber;
	}

	public void setSedsProductNumber(int sedsProductNumber) {
		this.sedsProductNumber = sedsProductNumber;
	}

	public int getSedsPaymentPositionType() {
		return sedsPaymentPositionType;
	}

	public void setSedsPaymentPositionType(int sedsPaymentPositionType) {
		this.sedsPaymentPositionType = sedsPaymentPositionType;
	}

	public int getSedsSettlementDay() {
		return sedsSettlementDay;
	}

	public void setSedsSettlementDay(int sedsSettlementDay) {
		this.sedsSettlementDay = sedsSettlementDay;
	}

	public int getSedsProductPrice() {
		return sedsProductPrice;
	}

	public void setSedsProductPrice(int sedsProductPrice) {
		this.sedsProductPrice = sedsProductPrice;
	}

	public int getSedsProductOptionPrice() {
		return sedsProductOptionPrice;
	}

	public void setSedsProductOptionPrice(int sedsProductOptionPrice) {
		this.sedsProductOptionPrice = sedsProductOptionPrice;
	}

	public int getSedsDiscountPrice() {
		return sedsDiscountPrice;
	}

	public void setSedsDiscountPrice(int sedsDiscountPrice) {
		this.sedsDiscountPrice = sedsDiscountPrice;
	}

	public int getSedsTotalPrice() {
		return sedsTotalPrice;
	}

	public void setSedsTotalPrice(int sedsTotalPrice) {
		this.sedsTotalPrice = sedsTotalPrice;
	}

	public int getSedsTotalCost() {
		return sedsTotalCost;
	}

	public void setSedsTotalCost(int sedsTotalCost) {
		this.sedsTotalCost = sedsTotalCost;
	}

	public int getSedsOrderCheckDay() {
		return sedsOrderCheckDay;
	}

	public void setSedsOrderCheckDay(int sedsOrderCheckDay) {
		this.sedsOrderCheckDay = sedsOrderCheckDay;
	}

	public int getSedsSendingDeadline() {
		return sedsSendingDeadline;
	}

	public void setSedsSendingDeadline(int sedsSendingDeadline) {
		this.sedsSendingDeadline = sedsSendingDeadline;
	}

	public int getSedsSendingDay() {
		return sedsSendingDay;
	}

	public void setSedsSendingDay(int sedsSendingDay) {
		this.sedsSendingDay = sedsSendingDay;
	}

	public int getSedsDeliveryChargeType() {
		return sedsDeliveryChargeType;
	}

	public void setSedsDeliveryChargeType(int sedsDeliveryChargeType) {
		this.sedsDeliveryChargeType = sedsDeliveryChargeType;
	}

	public int getSedsDeliveryNumber() {
		return sedsDeliveryNumber;
	}

	public void setSedsDeliveryNumber(int sedsDeliveryNumber) {
		this.sedsDeliveryNumber = sedsDeliveryNumber;
	}

	public int getSedsDeliveryPrice() {
		return sedsDeliveryPrice;
	}

	public void setSedsDeliveryPrice(int sedsDeliveryPrice) {
		this.sedsDeliveryPrice = sedsDeliveryPrice;
	}

	public int getSedsDeliveryDiscountPrice() {
		return sedsDeliveryDiscountPrice;
	}

	public void setSedsDeliveryDiscountPrice(int sedsDeliveryDiscountPrice) {
		this.sedsDeliveryDiscountPrice = sedsDeliveryDiscountPrice;
	}

	public int getSedsReceiverContractNumber1() {
		return sedsReceiverContractNumber1;
	}

	public void setSedsReceiverContractNumber1(int sedsReceiverContractNumber1) {
		this.sedsReceiverContractNumber1 = sedsReceiverContractNumber1;
	}

	public int getSedsReceiverContractNumber2() {
		return sedsReceiverContractNumber2;
	}

	public void setSedsReceiverContractNumber2(int sedsReceiverContractNumber2) {
		this.sedsReceiverContractNumber2 = sedsReceiverContractNumber2;
	}

	public int getSedsShippingAddressNumber() {
		return sedsShippingAddressNumber;
	}

	public void setSedsShippingAddressNumber(int sedsShippingAddressNumber) {
		this.sedsShippingAddressNumber = sedsShippingAddressNumber;
	}

	public int getSedsShippingAddress() {
		return sedsShippingAddress;
	}

	public void setSedsShippingAddress(int sedsShippingAddress) {
		this.sedsShippingAddress = sedsShippingAddress;
	}

	public int getSedsShippingAddressDetail() {
		return sedsShippingAddressDetail;
	}

	public void setSedsShippingAddressDetail(int sedsShippingAddressDetail) {
		this.sedsShippingAddressDetail = sedsShippingAddressDetail;
	}

	public int getSedsSpecialRegionFlag() {
		return sedsSpecialRegionFlag;
	}

	public void setSedsSpecialRegionFlag(int sedsSpecialRegionFlag) {
		this.sedsSpecialRegionFlag = sedsSpecialRegionFlag;
	}

	public int getSedsPaymentType() {
		return sedsPaymentType;
	}

	public void setSedsPaymentType(int sedsPaymentType) {
		this.sedsPaymentType = sedsPaymentType;
	}

	public int getSedsPaymentCommision() {
		return sedsPaymentCommision;
	}

	public void setSedsPaymentCommision(int sedsPaymentCommision) {
		this.sedsPaymentCommision = sedsPaymentCommision;
	}

	public int getSedsInflowRoute() {
		return sedsInflowRoute;
	}

	public void setSedsInflowRoute(int sedsInflowRoute) {
		this.sedsInflowRoute = sedsInflowRoute;
	}

	public int getSedsRequest() {
		return sedsRequest;
	}

	public void setSedsRequest(int sedsRequest) {
		this.sedsRequest = sedsRequest;
	}

	public int getSedsGender() {
		return sedsGender;
	}

	public void setSedsGender(int sedsGender) {
		this.sedsGender = sedsGender;
	}

	public int getSedsBirthDate() {
		return sedsBirthDate;
	}

	public void setSedsBirthDate(int sedsBirthDate) {
		this.sedsBirthDate = sedsBirthDate;
	}

	public int getSedsUserColumn1() {
		return sedsUserColumn1;
	}

	public void setSedsUserColumn1(int sedsUserColumn1) {
		this.sedsUserColumn1 = sedsUserColumn1;
	}

	public int getSedsUserColumn2() {
		return sedsUserColumn2;
	}

	public void setSedsUserColumn2(int sedsUserColumn2) {
		this.sedsUserColumn2 = sedsUserColumn2;
	}

	public int getSedsUserColumn3() {
		return sedsUserColumn3;
	}

	public void setSedsUserColumn3(int sedsUserColumn3) {
		this.sedsUserColumn3 = sedsUserColumn3;
	}

	public int getSedsUserColumn4() {
		return sedsUserColumn4;
	}

	public void setSedsUserColumn4(int sedsUserColumn4) {
		this.sedsUserColumn4 = sedsUserColumn4;
	}

	@Override
	public String toString() {
		return "StoreExcelDataSortingVO [sedsPk=" + sedsPk + ", ssFk=" + ssFk + ", sedsBuyerId=" + sedsBuyerId
				+ ", sedsBuyerName=" + sedsBuyerName + ", sedsBuyerContractNumber1=" + sedsBuyerContractNumber1
				+ ", sedsBuyerContractNumber2=" + sedsBuyerContractNumber2 + ", sedsReceiverName=" + sedsReceiverName
				+ ", sedsProduct=" + sedsProduct + ", sedsProductType=" + sedsProductType + ", sedsProductOption="
				+ sedsProductOption + ", sedsQuantity=" + sedsQuantity + ", sedsDeliveryMessage=" + sedsDeliveryMessage
				+ ", sedsDeliveryCompany=" + sedsDeliveryCompany + ", sedsDeliveryType=" + sedsDeliveryType
				+ ", sedsOrderNumber=" + sedsOrderNumber + ", sedsProductOrderNumber=" + sedsProductOrderNumber
				+ ", sedsProductNumber=" + sedsProductNumber + ", sedsPaymentPositionType=" + sedsPaymentPositionType
				+ ", sedsSettlementDay=" + sedsSettlementDay + ", sedsProductPrice=" + sedsProductPrice
				+ ", sedsProductOptionPrice=" + sedsProductOptionPrice + ", sedsDiscountPrice=" + sedsDiscountPrice
				+ ", sedsTotalPrice=" + sedsTotalPrice + ", sedsTotalCost=" + sedsTotalCost + ", sedsOrderCheckDay="
				+ sedsOrderCheckDay + ", sedsSendingDeadline=" + sedsSendingDeadline + ", sedsSendingDay="
				+ sedsSendingDay + ", sedsDeliveryChargeType=" + sedsDeliveryChargeType + ", sedsDeliveryNumber="
				+ sedsDeliveryNumber + ", sedsDeliveryPrice=" + sedsDeliveryPrice + ", sedsDeliveryDiscountPrice="
				+ sedsDeliveryDiscountPrice + ", sedsReceiverContractNumber1=" + sedsReceiverContractNumber1
				+ ", sedsReceiverContractNumber2=" + sedsReceiverContractNumber2 + ", sedsShippingAddressNumber="
				+ sedsShippingAddressNumber + ", sedsShippingAddress=" + sedsShippingAddress
				+ ", sedsShippingAddressDetail=" + sedsShippingAddressDetail + ", sedsSpecialRegionFlag="
				+ sedsSpecialRegionFlag + ", sedsPaymentType=" + sedsPaymentType + ", sedsPaymentCommision="
				+ sedsPaymentCommision + ", sedsInflowRoute=" + sedsInflowRoute + ", sedsRequest=" + sedsRequest
				+ ", sedsGender=" + sedsGender + ", sedsBirthDate=" + sedsBirthDate + ", sedsUserColumn1="
				+ sedsUserColumn1 + ", sedsUserColumn2=" + sedsUserColumn2 + ", sedsUserColumn3=" + sedsUserColumn3
				+ ", sedsUserColumn4=" + sedsUserColumn4 + "]";
	}
	
}
