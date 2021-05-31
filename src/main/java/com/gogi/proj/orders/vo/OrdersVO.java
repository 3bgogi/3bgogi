package com.gogi.proj.orders.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.product.products.vo.ProductsVO;

public class OrdersVO implements Cloneable, Comparable<OrdersVO>{
	
	private int orPk; //주문서 고유번호
	private int pmFk; //매칭 고유 번호
	private int ssFk; //판매처 고유 번호
	private String orSerialSpecialNumber; //주문서 고유 특수번호
	private String orBuyerId; // 구매자 아이디
	private String orBuyerName; //구매자명
	private String orBuyerAnotherName; //구매자명 치환
	private String orBuyerContractNumber1; //구매자 연락처 1
	private String orBuyerContractNumber2; //구매자 연락처 2
	private String orReceiverName; //수취인명
	private String orProduct; //상품명
	private String orProductType; //상품종류
	private String orProductOption; //상품의 옵션명
	private int orAmount; // 수량
	private String orDeliveryMessage; //배송메세지
	private String orDeliveryType; //배송방법 예) 방문수령, 택배 등
	private String orDeliveryCompany; // 택배사
	private String orOrderNumber; //주문번호 -> 한번에 샀을 경우 묶을 수 있게 하는 묶음번호 역할
	private String orProductOrderNumber; //상품주문번호 -> 주문번호 중 산 상품의 고유 주문번호를 알 수 있게 하는 역할
	private String orProductNumber; //상품고유번호
	private String orPaymentPositionType; //결제위치
	private Timestamp orSettlementDay; //결제일
	private int orProductPrice;  //상품가격
	private int orProductOptionPrice; //옵션가격
	private int orDiscountPrice; //상품할인가
	private int orTotalPrice; //총 주문가격
	private int orTotalCost; // 총 원가 -> 이 부분은 상품 매칭하면서 직접 계산하여 전달 1원 단위 자르기
	private Date orCheckDay; //발주확인일
	private Date orSendingDeadline; //발송기한
	private Timestamp orSendingDay; //발송일
	private String orDeliveryChargeType; //배송비형태 -> 선불, 후불, 무료
	private String orDeliveryNumber; //배송묶음번호 -> 송장으로 대체될 것 같음
	private int orDeliveryPrice; //배송비
	private int orDeliveryDiscountPrice; //배송할인액 -> 기본 배송비가 3000원일 경우에 배송쿠폰이 2500원이면 2500원 할인하여 500원 남음
	private String orReceiverContractNumber1; //수취인 연락처1
	private String orReceiverContractNumber2; //수취인 연락처2
	private String orShippingAddressNumber; //우편번호
	private String orShippingProvince; //배송지 도 예) 경기도, 강원도, 인천시 등 큰 지역
	private String orShippingAddress; //배송지
	private String orShippingAddressDetail; //배송지 상세사항
	private boolean orSpecialRegionDetail;
	private String orSendingAddress; //출고지 -> 현 상황에선 삼형제고기로 고정
	private String orPaymentType; //결제수단
	private int orPaymentCommision; //결제수수료
	private int orAnotherPaymentCommision; //또 다른 결제수수료
	private String orInflowRoute; //유입경로
	private String orRequest; //특별요청사항
	private boolean orTaxFlag; // 면세, 과제 여부
	private boolean orDevideFlag; //주문 나눔 여부
	private int orRefunds; // 환불 개수 : 기본값 0
	private Timestamp orRegdate; //현 주문서 등록일
	private boolean orCancledFlag; // 주문취소여부
	private boolean orSpecialRegionFlag;
	private boolean orSpecialRegionCheckFlag;
	
	//뒤늦게 추가사항
	private String orDeliveryInvoiceNumber; // 송장번호
	private String orDeliveryChargePaymentType; // 배송비 유형
	private int orDeliverySpecialPrice; //특수지역 추가 배송비
	private String orUserColumn1; //사용자정의
	private String orUserColumn2;
	private String orUserColumn3;
	private String orUserColumn4;
	private String orUserColumn5;
	private String orUserColumn6;
	private String orUserColumn7; //사용자정의 끝
	private String orMerge; //묶음정리
	private List<String> orMergeList; //묶음정리
	
	private boolean orOutputFlag; // 스토어 발송 여부
	private Timestamp orOutputDate; //스토어 발송일
	private boolean orOutputPosibFlag; //발송 가능 여부 - 재고체크 후 풀림
	private boolean orInvFlag;
	private int orCancledQty; // 취소 주문 개수
	private boolean orRequestCombFlag; // 생고기일 경우 무게를 무시하고 한 번에 포장할 때
	private boolean orExcelDivFlag; //엑셀파일 삽입 여부
	private int orFk; //주문서 테이블 참조
	private String orInvoiceNumDate;
	private int edtFk;
	private String orDelivCount;	//송장 카운트
	private int orTotalExcelDiv;
	private String orDelivEnter;	//배송출입
	private int orExcelDivCount;
	private boolean orDepositFlag = true; //입금 여부
	
	//cs에서 묶음번호를 통해 값을 검색 할 수 있도록 함
	//null값일 경우 상품주문번호를 통해 가져올 수 있도록
	private String hidingSpecialNumber;
	
	//묶음번호가 없을 경우 주문번호를 기준으로 가져옴
	private boolean specialNumberType;
	
	//주문 건 전부 가져올 수 있도록
	private int totalOrderCount;
	
	//판매처명 가져오도록
	private String ssName;
	
	private boolean productMatchingChecking; //상품 매칭 여부
	private boolean optionMatchingChecking; //옵션 매칭 여부
	
	//매칭 추가사항
	private List<ProductMatchingVO> productMatchingVOList;

	//수령방식 추가사항
	private int orRecType; // 0 = 택배수령, 1 = 퀵서비스, 2 = 방문수령
	private String orRecMemo; // 시간, 유의사항 등
	private String orRecStoragePlace; // 보관 장소 = 야채냉장고, 1번냉동고 등
	
	//주문생성 추가사항
	private List<Integer> orAmountList;
	private List<String> orProductList;
	private List<String> orProductOptionList;
	private List<Integer> orTotalPriceList;
	
	//응답메세지
	private String reqNo;						//우체국택배신청번호(건당부여)
	private String resNo;						//예약번호(일자당 부여)
	private String regiNo;						//운송장번호
	private String regiPoNm;					//접수우체국
	private String resDate;						//예약일시
	private int price;							//예상접수요금
	private String vTelNo;						//가상전화번호
	private String arrCnpoNm;					//도착집중국명
	private String delivPoNm;					//배달우체국명
	private String delivAreaCd;					//배달구구분코드
	private int ediPk;	
	
	private String cancelregino;				//취소(삭제) 운송장번호
	private String cancelDate;					//취소일시
	private String canceledyn;					//취소결과 여부 Y:취소, N:미취소, D:삭제
	private String notcancelReason;				//미 취소 사유
	private String resno;
	private String reqno;
	private String error_code;
	private String message;
	
	private String ip;
	private String adminId;

	// 우체국 발송용
	private List<ProductOptionVO> productOptionList;
	
	public OrdersVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrdersVO copy() throws CloneNotSupportedException {
		OrdersVO copyOrder = (OrdersVO)this.clone();
		
		return copyOrder;
	}

	public OrdersVO(int orPk, int pmFk, int ssFk, String orSerialSpecialNumber, String orBuyerId, String orBuyerName,
			String orBuyerAnotherName, String orBuyerContractNumber1, String orBuyerContractNumber2,
			String orReceiverName, String orProduct, String orProductType, String orProductOption, int orAmount,
			String orDeliveryMessage, String orDeliveryType, String orDeliveryCompany, String orOrderNumber,
			String orProductOrderNumber, String orProductNumber, String orPaymentPositionType,
			Timestamp orSettlementDay, int orProductPrice, int orProductOptionPrice, int orDiscountPrice,
			int orTotalPrice, int orTotalCost, Date orCheckDay, Date orSendingDeadline, Timestamp orSendingDay,
			String orDeliveryChargeType, String orDeliveryNumber, int orDeliveryPrice, int orDeliveryDiscountPrice,
			String orReceiverContractNumber1, String orReceiverContractNumber2, String orShippingAddressNumber,
			String orShippingProvince, String orShippingAddress, String orShippingAddressDetail,
			boolean orSpecialRegionDetail, String orSendingAddress, String orPaymentType, int orPaymentCommision,
			int orAnotherPaymentCommision, String orInflowRoute, String orRequest, boolean orTaxFlag,
			boolean orDevideFlag, int orRefunds, Timestamp orRegdate, boolean orCancledFlag,
			boolean orSpecialRegionFlag, boolean orSpecialRegionCheckFlag, String orDeliveryInvoiceNumber,
			String orDeliveryChargePaymentType, int orDeliverySpecialPrice, String orUserColumn1, String orUserColumn2,
			String orUserColumn3, String orUserColumn4, String orUserColumn5, String orUserColumn6,
			String orUserColumn7, String orMerge, List<String> orMergeList, boolean orOutputFlag,
			Timestamp orOutputDate, boolean orOutputPosibFlag, boolean orInvFlag, int orCancledQty,
			boolean orRequestCombFlag, boolean orExcelDivFlag, int orFk, String orInvoiceNumDate,
			String hidingSpecialNumber, boolean specialNumberType, int totalOrderCount, String ssName,
			boolean productMatchingChecking, boolean optionMatchingChecking,
			List<ProductMatchingVO> productMatchingVOList, List<Integer> orAmountList, List<String> orProductList,
			List<String> orProductOptionList, List<Integer> orTotalPriceList, String reqNo, String resNo, String regiNo,
			String regiPoNm, String resDate, int price, String vTelNo, String arrCnpoNm, String delivPoNm,
			String delivAreaCd, int ediPk, String cancelregino, String cancelDate, String canceledyn,
			String notcancelReason, String resno2, String reqno2, String error_code, String message,
			List<ProductOptionVO> productOptionList) {
		super();
		this.orPk = orPk;
		this.pmFk = pmFk;
		this.ssFk = ssFk;
		this.orSerialSpecialNumber = orSerialSpecialNumber;
		this.orBuyerId = orBuyerId;
		this.orBuyerName = orBuyerName;
		this.orBuyerAnotherName = orBuyerAnotherName;
		this.orBuyerContractNumber1 = orBuyerContractNumber1;
		this.orBuyerContractNumber2 = orBuyerContractNumber2;
		this.orReceiverName = orReceiverName;
		this.orProduct = orProduct;
		this.orProductType = orProductType;
		this.orProductOption = orProductOption;
		this.orAmount = orAmount;
		this.orDeliveryMessage = orDeliveryMessage;
		this.orDeliveryType = orDeliveryType;
		this.orDeliveryCompany = orDeliveryCompany;
		this.orOrderNumber = orOrderNumber;
		this.orProductOrderNumber = orProductOrderNumber;
		this.orProductNumber = orProductNumber;
		this.orPaymentPositionType = orPaymentPositionType;
		this.orSettlementDay = orSettlementDay;
		this.orProductPrice = orProductPrice;
		this.orProductOptionPrice = orProductOptionPrice;
		this.orDiscountPrice = orDiscountPrice;
		this.orTotalPrice = orTotalPrice;
		this.orTotalCost = orTotalCost;
		this.orCheckDay = orCheckDay;
		this.orSendingDeadline = orSendingDeadline;
		this.orSendingDay = orSendingDay;
		this.orDeliveryChargeType = orDeliveryChargeType;
		this.orDeliveryNumber = orDeliveryNumber;
		this.orDeliveryPrice = orDeliveryPrice;
		this.orDeliveryDiscountPrice = orDeliveryDiscountPrice;
		this.orReceiverContractNumber1 = orReceiverContractNumber1;
		this.orReceiverContractNumber2 = orReceiverContractNumber2;
		this.orShippingAddressNumber = orShippingAddressNumber;
		this.orShippingProvince = orShippingProvince;
		this.orShippingAddress = orShippingAddress;
		this.orShippingAddressDetail = orShippingAddressDetail;
		this.orSpecialRegionDetail = orSpecialRegionDetail;
		this.orSendingAddress = orSendingAddress;
		this.orPaymentType = orPaymentType;
		this.orPaymentCommision = orPaymentCommision;
		this.orAnotherPaymentCommision = orAnotherPaymentCommision;
		this.orInflowRoute = orInflowRoute;
		this.orRequest = orRequest;
		this.orTaxFlag = orTaxFlag;
		this.orDevideFlag = orDevideFlag;
		this.orRefunds = orRefunds;
		this.orRegdate = orRegdate;
		this.orCancledFlag = orCancledFlag;
		this.orSpecialRegionFlag = orSpecialRegionFlag;
		this.orSpecialRegionCheckFlag = orSpecialRegionCheckFlag;
		this.orDeliveryInvoiceNumber = orDeliveryInvoiceNumber;
		this.orDeliveryChargePaymentType = orDeliveryChargePaymentType;
		this.orDeliverySpecialPrice = orDeliverySpecialPrice;
		this.orUserColumn1 = orUserColumn1;
		this.orUserColumn2 = orUserColumn2;
		this.orUserColumn3 = orUserColumn3;
		this.orUserColumn4 = orUserColumn4;
		this.orUserColumn5 = orUserColumn5;
		this.orUserColumn6 = orUserColumn6;
		this.orUserColumn7 = orUserColumn7;
		this.orMerge = orMerge;
		this.orMergeList = orMergeList;
		this.orOutputFlag = orOutputFlag;
		this.orOutputDate = orOutputDate;
		this.orOutputPosibFlag = orOutputPosibFlag;
		this.orInvFlag = orInvFlag;
		this.orCancledQty = orCancledQty;
		this.orRequestCombFlag = orRequestCombFlag;
		this.orExcelDivFlag = orExcelDivFlag;
		this.orFk = orFk;
		this.orInvoiceNumDate = orInvoiceNumDate;
		this.hidingSpecialNumber = hidingSpecialNumber;
		this.specialNumberType = specialNumberType;
		this.totalOrderCount = totalOrderCount;
		this.ssName = ssName;
		this.productMatchingChecking = productMatchingChecking;
		this.optionMatchingChecking = optionMatchingChecking;
		this.productMatchingVOList = productMatchingVOList;
		this.orAmountList = orAmountList;
		this.orProductList = orProductList;
		this.orProductOptionList = orProductOptionList;
		this.orTotalPriceList = orTotalPriceList;
		this.reqNo = reqNo;
		this.resNo = resNo;
		this.regiNo = regiNo;
		this.regiPoNm = regiPoNm;
		this.resDate = resDate;
		this.price = price;
		this.vTelNo = vTelNo;
		this.arrCnpoNm = arrCnpoNm;
		this.delivPoNm = delivPoNm;
		this.delivAreaCd = delivAreaCd;
		this.ediPk = ediPk;
		this.cancelregino = cancelregino;
		this.cancelDate = cancelDate;
		this.canceledyn = canceledyn;
		this.notcancelReason = notcancelReason;
		resno = resno2;
		reqno = reqno2;
		this.error_code = error_code;
		this.message = message;
		this.productOptionList = productOptionList;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public int getOrRecType() {
		return orRecType;
	}

	public void setOrRecType(int orRecType) {
		this.orRecType = orRecType;
	}

	public String getOrRecMemo() {
		return orRecMemo;
	}

	public void setOrRecMemo(String orRecMemo) {
		this.orRecMemo = orRecMemo;
	}

	public String getOrRecStoragePlace() {
		return orRecStoragePlace;
	}

	public void setOrRecStoragePlace(String orRecStoragePlace) {
		this.orRecStoragePlace = orRecStoragePlace;
	}

	public boolean isOrDepositFlag() {
		return orDepositFlag;
	}

	public void setOrDepositFlag(boolean orDepositFlag) {
		this.orDepositFlag = orDepositFlag;
	}

	public int getOrExcelDivCount() {
		return orExcelDivCount;
	}

	public void setOrExcelDivCount(int orExcelDivCount) {
		this.orExcelDivCount = orExcelDivCount;
	}

	public String getOrDelivEnter() {
		return orDelivEnter;
	}

	public void setOrDelivEnter(String orDelivEnter) {
		this.orDelivEnter = orDelivEnter;
	}

	public int getOrTotalExcelDiv() {
		return orTotalExcelDiv;
	}

	public void setOrTotalExcelDiv(int orTotalExcelDiv) {
		this.orTotalExcelDiv = orTotalExcelDiv;
	}

	public String getOrDelivCount() {
		return orDelivCount;
	}

	public void setOrDelivCount(String orDelivCount) {
		this.orDelivCount = orDelivCount;
	}

	public int getEdtFk() {
		return edtFk;
	}

	public void setEdtFk(int edtFk) {
		this.edtFk = edtFk;
	}

	public String getOrInvoiceNumDate() {
		return orInvoiceNumDate;
	}

	public void setOrInvoiceNumDate(String orInvoiceNumDate) {
		this.orInvoiceNumDate = orInvoiceNumDate;
	}

	public boolean isOrSpecialRegionCheckFlag() {
		return orSpecialRegionCheckFlag;
	}

	public void setOrSpecialRegionCheckFlag(boolean orSpecialRegionCheckFlag) {
		this.orSpecialRegionCheckFlag = orSpecialRegionCheckFlag;
	}

	public boolean isOrSpecialRegionFlag() {
		return orSpecialRegionFlag;
	}

	public void setOrSpecialRegionFlag(boolean orSpecialRegionFlag) {
		this.orSpecialRegionFlag = orSpecialRegionFlag;
	}

	public boolean isOrExcelDivFlag() {
		return orExcelDivFlag;
	}

	public void setOrExcelDivFlag(boolean orExcelDivFlag) {
		this.orExcelDivFlag = orExcelDivFlag;
	}

	public int getOrFk() {
		return orFk;
	}

	public void setOrFk(int orFk) {
		this.orFk = orFk;
	}

	public String getCancelregino() {
		return cancelregino;
	}

	public void setCancelregino(String cancelregino) {
		this.cancelregino = cancelregino;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCanceledyn() {
		return canceledyn;
	}

	public void setCanceledyn(String canceledyn) {
		this.canceledyn = canceledyn;
	}

	public String getNotcancelReason() {
		return notcancelReason;
	}

	public void setNotcancelReason(String notcancelReason) {
		this.notcancelReason = notcancelReason;
	}

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getReqno() {
		return reqno;
	}

	public void setReqno(String reqno) {
		this.reqno = reqno;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ProductOptionVO> getProductOptionList() {
		return productOptionList;
	}

	public void setProductOptionList(List<ProductOptionVO> productOptionList) {
		this.productOptionList = productOptionList;
	}

	public boolean getOrRequestCombFlag() {
		return orRequestCombFlag;
	}

	public void setOrRequestCombFlag(boolean orRequestCombFlag) {
		this.orRequestCombFlag = orRequestCombFlag;
	}

	public int getOrCancledQty() {
		return orCancledQty;
	}

	public void setOrCancledQty(int orCancledQty) {
		this.orCancledQty = orCancledQty;
	}

	public List<Integer> getOrAmountList() {
		return orAmountList;
	}

	public void setOrAmountList(List<Integer> orAmountList) {
		this.orAmountList = orAmountList;
	}

	public List<String> getOrProductList() {
		return orProductList;
	}

	public void setOrProductList(List<String> orProductList) {
		this.orProductList = orProductList;
	}

	public List<String> getOrProductOptionList() {
		return orProductOptionList;
	}

	public void setOrProductOptionList(List<String> orProductOptionList) {
		this.orProductOptionList = orProductOptionList;
	}

	public List<Integer> getOrTotalPriceList() {
		return orTotalPriceList;
	}

	public void setOrTotalPriceList(List<Integer> orTotalPriceList) {
		this.orTotalPriceList = orTotalPriceList;
	}

	public boolean getOrInvFlag() {
		return orInvFlag;
	}

	public void setOrInvFlag(boolean orInvFlag) {
		this.orInvFlag = orInvFlag;
	}

	public boolean isOrOutputPosibFlag() {
		return orOutputPosibFlag;
	}

	public void setOrOutputPosibFlag(boolean orOutputPosibFlag) {
		this.orOutputPosibFlag = orOutputPosibFlag;
	}

	public int getOrPk() {
		return orPk;
	}

	public void setOrPk(int orPk) {
		this.orPk = orPk;
	}

	public int getPmFk() {
		return pmFk;
	}

	public void setPmFk(int pmFk) {
		this.pmFk = pmFk;
	}

	public int getSsFk() {
		return ssFk;
	}

	public void setSsFk(int ssFk) {
		this.ssFk = ssFk;
	}

	public String getOrSerialSpecialNumber() {
		return orSerialSpecialNumber;
	}

	public void setOrSerialSpecialNumber(String orSerialSpecialNumber) {
		this.orSerialSpecialNumber = orSerialSpecialNumber;
	}

	public String getOrBuyerId() {
		return orBuyerId;
	}

	public void setOrBuyerId(String orBuyerId) {
		this.orBuyerId = orBuyerId;
	}

	public String getOrBuyerName() {
		return orBuyerName;
	}

	public void setOrBuyerName(String orBuyerName) {
		this.orBuyerName = orBuyerName;
	}

	public String getOrBuyerAnotherName() {
		return orBuyerAnotherName;
	}

	public void setOrBuyerAnotherName(String orBuyerAnotherName) {
		this.orBuyerAnotherName = orBuyerAnotherName;
	}

	public String getOrBuyerContractNumber1() {
		return orBuyerContractNumber1;
	}

	public void setOrBuyerContractNumber1(String orBuyerContractNumber1) {
		this.orBuyerContractNumber1 = orBuyerContractNumber1;
	}

	public String getOrBuyerContractNumber2() {
		return orBuyerContractNumber2;
	}

	public void setOrBuyerContractNumber2(String orBuyerContractNumber2) {
		this.orBuyerContractNumber2 = orBuyerContractNumber2;
	}

	public String getOrReceiverName() {
		return orReceiverName;
	}

	public void setOrReceiverName(String orReceiverName) {
		this.orReceiverName = orReceiverName;
	}

	public String getOrProduct() {
		return orProduct;
	}

	public void setOrProduct(String orProduct) {
		this.orProduct = orProduct;
	}

	public String getOrProductType() {
		return orProductType;
	}

	public void setOrProductType(String orProductType) {
		this.orProductType = orProductType;
	}

	public String getOrProductOption() {
		return orProductOption;
	}

	public void setOrProductOption(String orProductOption) {
		this.orProductOption = orProductOption;
	}

	public int getOrAmount() {
		return orAmount;
	}

	public void setOrAmount(int orAmount) {
		this.orAmount = orAmount;
	}

	public String getOrDeliveryMessage() {
		return orDeliveryMessage;
	}

	public void setOrDeliveryMessage(String orDeliveryMessage) {
		this.orDeliveryMessage = orDeliveryMessage;
	}

	public String getOrDeliveryType() {
		return orDeliveryType;
	}

	public void setOrDeliveryType(String orDeliveryType) {
		this.orDeliveryType = orDeliveryType;
	}

	public String getOrDeliveryCompany() {
		return orDeliveryCompany;
	}

	public void setOrDeliveryCompany(String orDeliveryCompany) {
		this.orDeliveryCompany = orDeliveryCompany;
	}

	public String getOrOrderNumber() {
		return orOrderNumber;
	}

	public void setOrOrderNumber(String orOrderNumber) {
		this.orOrderNumber = orOrderNumber;
	}

	public String getOrProductOrderNumber() {
		return orProductOrderNumber;
	}

	public void setOrProductOrderNumber(String orProductOrderNumber) {
		this.orProductOrderNumber = orProductOrderNumber;
	}

	public String getOrProductNumber() {
		return orProductNumber;
	}

	public void setOrProductNumber(String orProductNumber) {
		this.orProductNumber = orProductNumber;
	}

	public String getOrPaymentPositionType() {
		return orPaymentPositionType;
	}

	public void setOrPaymentPositionType(String orPaymentPositionType) {
		this.orPaymentPositionType = orPaymentPositionType;
	}

	public Timestamp getOrSettlementDay() {
		return orSettlementDay;
	}

	public void setOrSettlementDay(Timestamp orSettlementDay) {
		this.orSettlementDay = orSettlementDay;
	}

	public int getOrProductPrice() {
		return orProductPrice;
	}

	public void setOrProductPrice(int orProductPrice) {
		this.orProductPrice = orProductPrice;
	}

	public int getOrProductOptionPrice() {
		return orProductOptionPrice;
	}

	public void setOrProductOptionPrice(int orProductOptionPrice) {
		this.orProductOptionPrice = orProductOptionPrice;
	}

	public int getOrDiscountPrice() {
		return orDiscountPrice;
	}

	public void setOrDiscountPrice(int orDiscountPrice) {
		this.orDiscountPrice = orDiscountPrice;
	}

	public int getOrTotalPrice() {
		return orTotalPrice;
	}

	public void setOrTotalPrice(int orTotalPrice) {
		this.orTotalPrice = orTotalPrice;
	}

	public int getOrTotalCost() {
		return orTotalCost;
	}

	public void setOrTotalCost(int orTotalCost) {
		this.orTotalCost = orTotalCost;
	}

	public Date getOrCheckDay() {
		return orCheckDay;
	}

	public void setOrCheckDay(Date orCheckDay) {
		this.orCheckDay = orCheckDay;
	}

	public Date getOrSendingDeadline() {
		return orSendingDeadline;
	}

	public void setOrSendingDeadline(Date orSendingDeadline) {
		this.orSendingDeadline = orSendingDeadline;
	}

	public Timestamp getOrSendingDay() {
		return orSendingDay;
	}

	public void setOrSendingDay(Timestamp orSendingDay) {
		this.orSendingDay = orSendingDay;
	}

	public String getOrDeliveryChargeType() {
		return orDeliveryChargeType;
	}

	public void setOrDeliveryChargeType(String orDeliveryChargeType) {
		this.orDeliveryChargeType = orDeliveryChargeType;
	}

	public String getOrDeliveryNumber() {
		return orDeliveryNumber;
	}

	public void setOrDeliveryNumber(String orDeliveryNumber) {
		this.orDeliveryNumber = orDeliveryNumber;
	}

	public int getOrDeliveryPrice() {
		return orDeliveryPrice;
	}

	public void setOrDeliveryPrice(int orDeliveryPrice) {
		this.orDeliveryPrice = orDeliveryPrice;
	}

	public int getOrDeliveryDiscountPrice() {
		return orDeliveryDiscountPrice;
	}

	public void setOrDeliveryDiscountPrice(int orDeliveryDiscountPrice) {
		this.orDeliveryDiscountPrice = orDeliveryDiscountPrice;
	}

	public String getOrReceiverContractNumber1() {
		return orReceiverContractNumber1;
	}

	public void setOrReceiverContractNumber1(String orReceiverContractNumber1) {
		this.orReceiverContractNumber1 = orReceiverContractNumber1;
	}

	public String getOrReceiverContractNumber2() {
		return orReceiverContractNumber2;
	}

	public void setOrReceiverContractNumber2(String orReceiverContractNumber2) {
		this.orReceiverContractNumber2 = orReceiverContractNumber2;
	}

	public String getOrShippingAddressNumber() {
		return orShippingAddressNumber;
	}

	public void setOrShippingAddressNumber(String orShippingAddressNumber) {
		this.orShippingAddressNumber = orShippingAddressNumber;
	}

	public String getOrShippingProvince() {
		return orShippingProvince;
	}

	public void setOrShippingProvince(String orShippingProvince) {
		this.orShippingProvince = orShippingProvince;
	}

	public String getOrShippingAddress() {
		return orShippingAddress;
	}

	public void setOrShippingAddress(String orShippingAddress) {
		this.orShippingAddress = orShippingAddress;
	}

	public String getOrShippingAddressDetail() {
		return orShippingAddressDetail;
	}

	public void setOrShippingAddressDetail(String orShippingAddressDetail) {
		this.orShippingAddressDetail = orShippingAddressDetail;
	}

	public boolean isOrSpecialRegionDetail() {
		return orSpecialRegionDetail;
	}

	public void setOrSpecialRegionDetail(boolean orSpecialRegionDetail) {
		this.orSpecialRegionDetail = orSpecialRegionDetail;
	}

	public String getOrSendingAddress() {
		return orSendingAddress;
	}

	public void setOrSendingAddress(String orSendingAddress) {
		this.orSendingAddress = orSendingAddress;
	}

	public String getOrPaymentType() {
		return orPaymentType;
	}

	public void setOrPaymentType(String orPaymentType) {
		this.orPaymentType = orPaymentType;
	}

	public int getOrPaymentCommision() {
		return orPaymentCommision;
	}

	public void setOrPaymentCommision(int orPaymentCommision) {
		this.orPaymentCommision = orPaymentCommision;
	}

	public int getOrAnotherPaymentCommision() {
		return orAnotherPaymentCommision;
	}

	public void setOrAnotherPaymentCommision(int orAnotherPaymentCommision) {
		this.orAnotherPaymentCommision = orAnotherPaymentCommision;
	}

	public String getOrInflowRoute() {
		return orInflowRoute;
	}

	public void setOrInflowRoute(String orInflowRoute) {
		this.orInflowRoute = orInflowRoute;
	}

	public String getOrRequest() {
		return orRequest;
	}

	public void setOrRequest(String orRequest) {
		this.orRequest = orRequest;
	}

	public boolean isOrTaxFlag() {
		return orTaxFlag;
	}

	public void setOrTaxFlag(boolean orTaxFlag) {
		this.orTaxFlag = orTaxFlag;
	}

	public boolean isOrDevideFlag() {
		return orDevideFlag;
	}

	public void setOrDevideFlag(boolean orDevideFlag) {
		this.orDevideFlag = orDevideFlag;
	}

	public int getOrRefunds() {
		return orRefunds;
	}

	public void setOrRefunds(int orRefunds) {
		this.orRefunds = orRefunds;
	}

	public Timestamp getOrRegdate() {
		return orRegdate;
	}

	public void setOrRegdate(Timestamp orRegdate) {
		this.orRegdate = orRegdate;
	}

	public boolean isOrCancledFlag() {
		return orCancledFlag;
	}

	public void setOrCancledFlag(boolean orCancledFlag) {
		this.orCancledFlag = orCancledFlag;
	}

	public String getOrDeliveryInvoiceNumber() {
		return orDeliveryInvoiceNumber;
	}

	public void setOrDeliveryInvoiceNumber(String orDeliveryInvoiceNumber) {
		this.orDeliveryInvoiceNumber = orDeliveryInvoiceNumber;
	}

	public String getOrDeliveryChargePaymentType() {
		return orDeliveryChargePaymentType;
	}

	public void setOrDeliveryChargePaymentType(String orDeliveryChargePaymentType) {
		this.orDeliveryChargePaymentType = orDeliveryChargePaymentType;
	}

	public int getOrDeliverySpecialPrice() {
		return orDeliverySpecialPrice;
	}

	public void setOrDeliverySpecialPrice(int orDeliverySpecialPrice) {
		this.orDeliverySpecialPrice = orDeliverySpecialPrice;
	}

	public String getOrUserColumn1() {
		return orUserColumn1;
	}

	public void setOrUserColumn1(String orUserColumn1) {
		this.orUserColumn1 = orUserColumn1;
	}

	public String getOrUserColumn2() {
		return orUserColumn2;
	}

	public void setOrUserColumn2(String orUserColumn2) {
		this.orUserColumn2 = orUserColumn2;
	}

	public String getOrUserColumn3() {
		return orUserColumn3;
	}

	public void setOrUserColumn3(String orUserColumn3) {
		this.orUserColumn3 = orUserColumn3;
	}

	public String getOrUserColumn4() {
		return orUserColumn4;
	}

	public void setOrUserColumn4(String orUserColumn4) {
		this.orUserColumn4 = orUserColumn4;
	}

	public String getOrUserColumn5() {
		return orUserColumn5;
	}

	public void setOrUserColumn5(String orUserColumn5) {
		this.orUserColumn5 = orUserColumn5;
	}

	public String getOrUserColumn6() {
		return orUserColumn6;
	}

	public void setOrUserColumn6(String orUserColumn6) {
		this.orUserColumn6 = orUserColumn6;
	}

	public String getOrUserColumn7() {
		return orUserColumn7;
	}

	public void setOrUserColumn7(String orUserColumn7) {
		this.orUserColumn7 = orUserColumn7;
	}

	public String getOrMerge() {
		return orMerge;
	}

	public void setOrMerge(String orMerge) {
		this.orMerge = orMerge;
	}

	public List<String> getOrMergeList() {
		return orMergeList;
	}

	public void setOrMergeList(List<String> orMergeList) {
		this.orMergeList = orMergeList;
	}

	public boolean isOrOutputFlag() {
		return orOutputFlag;
	}

	public void setOrOutputFlag(boolean orOutputFlag) {
		this.orOutputFlag = orOutputFlag;
	}

	public Timestamp getOrOutputDate() {
		return orOutputDate;
	}

	public void setOrOutputDate(Timestamp orOutputDate) {
		this.orOutputDate = orOutputDate;
	}

	public String getHidingSpecialNumber() {
		return hidingSpecialNumber;
	}

	public void setHidingSpecialNumber(String hidingSpecialNumber) {
		this.hidingSpecialNumber = hidingSpecialNumber;
	}

	public boolean isSpecialNumberType() {
		return specialNumberType;
	}

	public void setSpecialNumberType(boolean specialNumberType) {
		this.specialNumberType = specialNumberType;
	}

	public int getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public boolean isProductMatchingChecking() {
		return productMatchingChecking;
	}

	public void setProductMatchingChecking(boolean productMatchingChecking) {
		this.productMatchingChecking = productMatchingChecking;
	}

	public boolean isOptionMatchingChecking() {
		return optionMatchingChecking;
	}

	public void setOptionMatchingChecking(boolean optionMatchingChecking) {
		this.optionMatchingChecking = optionMatchingChecking;
	}

	public List<ProductMatchingVO> getProductMatchingVOList() {
		return productMatchingVOList;
	}

	public void setProductMatchingVOList(List<ProductMatchingVO> productMatchingVOList) {
		this.productMatchingVOList = productMatchingVOList;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getRegiNo() {
		return regiNo;
	}

	public void setRegiNo(String regiNo) {
		this.regiNo = regiNo;
	}

	public String getRegiPoNm() {
		return regiPoNm;
	}

	public void setRegiPoNm(String regiPoNm) {
		this.regiPoNm = regiPoNm;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getvTelNo() {
		return vTelNo;
	}

	public void setvTelNo(String vTelNo) {
		this.vTelNo = vTelNo;
	}

	public String getArrCnpoNm() {
		return arrCnpoNm;
	}

	public void setArrCnpoNm(String arrCnpoNm) {
		this.arrCnpoNm = arrCnpoNm;
	}

	public String getDelivPoNm() {
		return delivPoNm;
	}

	public void setDelivPoNm(String delivPoNm) {
		this.delivPoNm = delivPoNm;
	}

	public String getDelivAreaCd() {
		return delivAreaCd;
	}

	public void setDelivAreaCd(String delivAreaCd) {
		this.delivAreaCd = delivAreaCd;
	}

	public int getEdiPk() {
		return ediPk;
	}

	public void setEdiPk(int ediPk) {
		this.ediPk = ediPk;
	}
	
	private String byteSplit(String splitString, int ByteLength, int maxLength) {
		
		if(splitString == null) return "";
		
		byte [] inputByte = splitString.getBytes();
		int resultByte = 0;
		for(int i = 0; i < splitString.length() - 1; i++) {
			
			if(isIncHangul(splitString.substring(i, i+1))) {
				if(resultByte + ByteLength > maxLength) {
					break;
				}
				resultByte+=ByteLength;
			}else {
				if(resultByte + 1 > maxLength) {
					break;
				}
				resultByte+=1;
			}
		}
		
		return new String(inputByte, 0, resultByte);
	}
	
	private boolean isIncHangul(String splitString) {
		for(int i = 0; i < splitString.length(); i++) {
			if(Character.getType(splitString.charAt(i)) == Character.OTHER_LETTER) {
				return true;
			}
		}
		
		return false;
	}
	
	public String epostDelivSelfPrintToString() {
		return "custNo=0004610509&reqType=1&officeSer=01&weight=2"
				+ "&volume=30&ordCompNm=" + ssName + "&ordNm=삼형제고기&ordZip=21126" 
				+ "&ordAddr1=인천광역시 계양구 효서로 388&ordAddr2=(작전동) 3층 삼형제고기&ordTel=050713121620&ordMob=050713121620"
				+ "&recNm=" + orReceiverName + "&recZip=" + orShippingAddressNumber 
				+ "&recAddr1=" + orShippingAddress 
				+ "&recAddr2=" + (orShippingAddressDetail == null || orShippingAddressDetail.equals("") ? ". " : orShippingAddressDetail)
				+ "&recTel=" + (orReceiverContractNumber2 == null ? "" : orReceiverContractNumber2.replaceAll("-", "")) 
				+ "&recMob=" + (orReceiverContractNumber1 == null ? "" : orReceiverContractNumber1.replaceAll("-", "")) 
				+ "&apprNo=4003181560&payType=1"
				+ "&microYn=N&contCd=022&goodsNm=" + (productOptionList.size() > 1 ? productOptionList.get(0).getProductName()+productOptionList.get(0).getOptionName()+" 외 "+productOptionList.size() : productOptionList.get(0).getProductName()+productOptionList.get(0).getOptionName()) 
				+ "&goodsCd=" 
				+ "&goosMdl=&goodsSize=&goodsColor=&qty=" + productOptionList.size()
				+ "&orderNo=" + orSerialSpecialNumber + "&delivMsg=" + byteSplit(orDeliveryMessage, 2, 180) + "&retReason=&retVisitYmd="
				+ "&testYn=N&printYn=Y";
	}

	@Override
	public String toString() {
		return "OrdersVO [orPk=" + orPk + ", pmFk=" + pmFk + ", ssFk=" + ssFk + ", orSerialSpecialNumber="
				+ orSerialSpecialNumber + ", orBuyerId=" + orBuyerId + ", orBuyerName=" + orBuyerName
				+ ", orBuyerAnotherName=" + orBuyerAnotherName + ", orBuyerContractNumber1=" + orBuyerContractNumber1
				+ ", orBuyerContractNumber2=" + orBuyerContractNumber2 + ", orReceiverName=" + orReceiverName
				+ ", orProduct=" + orProduct + ", orProductType=" + orProductType + ", orProductOption="
				+ orProductOption + ", orAmount=" + orAmount + ", orDeliveryMessage=" + orDeliveryMessage
				+ ", orDeliveryType=" + orDeliveryType + ", orDeliveryCompany=" + orDeliveryCompany + ", orOrderNumber="
				+ orOrderNumber + ", orProductOrderNumber=" + orProductOrderNumber + ", orProductNumber="
				+ orProductNumber + ", orPaymentPositionType=" + orPaymentPositionType + ", orSettlementDay="
				+ orSettlementDay + ", orProductPrice=" + orProductPrice + ", orProductOptionPrice="
				+ orProductOptionPrice + ", orDiscountPrice=" + orDiscountPrice + ", orTotalPrice=" + orTotalPrice
				+ ", orTotalCost=" + orTotalCost + ", orCheckDay=" + orCheckDay + ", orSendingDeadline="
				+ orSendingDeadline + ", orSendingDay=" + orSendingDay + ", orDeliveryChargeType="
				+ orDeliveryChargeType + ", orDeliveryNumber=" + orDeliveryNumber + ", orDeliveryPrice="
				+ orDeliveryPrice + ", orDeliveryDiscountPrice=" + orDeliveryDiscountPrice
				+ ", orReceiverContractNumber1=" + orReceiverContractNumber1 + ", orReceiverContractNumber2="
				+ orReceiverContractNumber2 + ", orShippingAddressNumber=" + orShippingAddressNumber
				+ ", orShippingProvince=" + orShippingProvince + ", orShippingAddress=" + orShippingAddress
				+ ", orShippingAddressDetail=" + orShippingAddressDetail + ", orSpecialRegionDetail="
				+ orSpecialRegionDetail + ", orSendingAddress=" + orSendingAddress + ", orPaymentType=" + orPaymentType
				+ ", orPaymentCommision=" + orPaymentCommision + ", orAnotherPaymentCommision="
				+ orAnotherPaymentCommision + ", orInflowRoute=" + orInflowRoute + ", orRequest=" + orRequest
				+ ", orTaxFlag=" + orTaxFlag + ", orDevideFlag=" + orDevideFlag + ", orRefunds=" + orRefunds
				+ ", orRegdate=" + orRegdate + ", orCancledFlag=" + orCancledFlag + ", orDeliveryInvoiceNumber="
				+ orDeliveryInvoiceNumber + ", orDeliveryChargePaymentType=" + orDeliveryChargePaymentType
				+ ", orDeliverySpecialPrice=" + orDeliverySpecialPrice + ", orUserColumn1=" + orUserColumn1
				+ ", orUserColumn2=" + orUserColumn2 + ", orUserColumn3=" + orUserColumn3 + ", orUserColumn4="
				+ orUserColumn4 + ", orUserColumn5=" + orUserColumn5 + ", orUserColumn6=" + orUserColumn6
				+ ", orUserColumn7=" + orUserColumn7 + ", orMerge=" + orMerge + ", orMergeList=" + orMergeList
				+ ", orOutputFlag=" + orOutputFlag + ", orOutputDate=" + orOutputDate + ", orOutputPosibFlag="
				+ orOutputPosibFlag + ", hidingSpecialNumber=" + hidingSpecialNumber + ", specialNumberType="
				+ specialNumberType + ", totalOrderCount=" + totalOrderCount + ", ssName=" + ssName
				+ ", productMatchingChecking=" + productMatchingChecking + ", optionMatchingChecking="
				+ optionMatchingChecking + ", productMatchingVOList=" + productMatchingVOList + ", reqNo=" + reqNo
				+ ", resNo=" + resNo + ", regiNo=" + regiNo + ", regiPoNm=" + regiPoNm + ", resDate=" + resDate
				+ ", price=" + price + ", vTelNo=" + vTelNo + ", arrCnpoNm=" + arrCnpoNm + ", delivPoNm=" + delivPoNm
				+ ", delivAreaCd=" + delivAreaCd + ", ediPk=" + ediPk + "]";
	}

	@Override
	public int compareTo(OrdersVO orVO) {
		// TODO Auto-generated method stub
		int targetProdSeq = orVO.getProductOptionList().get(0).getProdSorting();
		int prodSize = orVO.getProductOptionList().size();
		
		
		
		if(this.productOptionList.get(0).getProdSorting() == targetProdSeq) {
			
			if(targetProdSeq == 1 && prodSize > 1) {
				for(int i = 1; i < prodSize; i++) {
					if(orVO.getProductOptionList().get(i).getProdSorting() == 2) {
						return -1;
					}
				}
				
				return 1;
				
			}
			
			return 0;
		}
        else if(this.productOptionList.get(0).getProdSorting() > targetProdSeq) return 1;
        else return -1;
	}

	
}

