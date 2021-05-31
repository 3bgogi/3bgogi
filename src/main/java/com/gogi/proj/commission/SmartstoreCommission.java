package com.gogi.proj.commission;

public class SmartstoreCommission {

	//신용카드, 신용카드 간편결제
	private static final double CREDIT_CARD = 2.86;
	
	//포인트 결제
	private static final double POINT = 3.74;
	
	//계좌이체
	private static final double TRANSFER = 1.65;
	
	//무통장입금
	private static final double WITHOUT_BANKBOOK = 0.95;
	
	//휴대폰결제
	private static final double DEPOSIT_WITH_CELLPHONE = 3.85;
	
	//보조결제
	private static final double ANOTHER_PAYMENT = 3.71;
	
	//유입으로 인한 결제
	private static final double INFLOW_PAYMENT = 2.0;
	
	public int matchingPaymentCommission(String payType, int totalPrice) {
		int result = 0;
		
		if(payType.equals("신용카드") || payType.equals("신용카드 간편결제")) {
			result = -((int)(totalPrice * CREDIT_CARD / 100));
			
		}else if(payType.equals("포인트결제")) {
			result = -((int)(totalPrice * POINT / 100));
			
		}else if(payType.equals("실시간계좌이체")) {
			result = -((int)(totalPrice * TRANSFER / 100));
			
		}else if(payType.equals("무통장입금")) {
			result = -((int)(totalPrice * WITHOUT_BANKBOOK / 100));
			
		}else if(payType.equals("휴대폰") || payType.equals("휴대폰 간편결제")) {
			result = -((int)(totalPrice * DEPOSIT_WITH_CELLPHONE / 100));
			
		}
		
		return result;
	}
	
	public int matchingInflowCommission(String inflowType, int totalPrice) {
		int result = 0;
		
		if(inflowType.equals("네이버쇼핑 외") || inflowType.equals("검색>쇼핑검색(네이버쇼핑 외)") || inflowType.equals("광고>쇼핑검색광고") || inflowType.equals("광고>쇼핑검색광고>쇼핑몰상품형") ||
				inflowType.equals("쇼핑MY>장바구니(네이버쇼핑)") || inflowType.equals("쇼핑MY>최근본상품(네이버쇼핑)") || inflowType.equals("톡톡>스토어팜 주문서(톡톡)") || inflowType.equals("알림>상품문의답변") ||
				inflowType.equals("바로가기(네이버쇼핑 외)") || inflowType.equals("럭키투데이(네이버쇼핑 외)")) {
			result = 0;
			
		}else {
			result = -((int)(totalPrice * INFLOW_PAYMENT / 100));
			
		}
		
		return result;
	}
}
