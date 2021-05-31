jQuery(document).ready(function($) {
	
	/**
	  *  상품 정보 클레스
	  */
	 class productInfo{
		 constructor(productName, qty){
			 	this.productName = productName;
			 	this.qty = qty;
		 }
	 }
	 
	 /**
	  *  송장 정보 클레스
	  *  delivInfo 송장 정보
	  *  top 상단 위치
	  *  left 좌측 위치
	  */
	 class delivInfoLoc{
		 constructor(delivInfo, top, left){
			 this.delivInfo = delivInfo;
			 this.top = top;
			 this.left = left;
		 }
	 }
	 
	//우체국송장 좌측 필수 영역
	 /**
	  *  접수국
	  */
	 var delivPoNm;
	 	
	 /**
	  *  예약일 / 접수일
	  */
	 var delivReservDate;
	 
	 /**
	  * 주문자명
	  */
	 var orBuyerName;
	 
	 /**
	  * 접수 판매처
	  */
	 var ssName;
	 
	 /**
	  * 주문번호
	  */
	 var orOrderNumber;
	 
	 /**
	  * 중량
	  */
	 var boxWeight;
	 
	 /**
	  * 계약 요금
	  */
	 var sendingPrice;
	 
	 /**
	  * 우편 번호 
	  * 특이 사항 : 바코드 이미지 따와야함
	  */
	 var orShippingAddressNumber;
	 
	 /**
	  * 배송메세지
	  */
	 var orDeliveryMesssage;
	 
	 
	 /**
	  *  우체국 송장 상단 필수 영역 입력 폼
	  *  [B8]  [부평물]   [409]  [인천계양]  [55] [41]
	  *  지역    물류센터           접수국
	  */
	 //우체국송장 상단 필수 영역
	 var delivAreaCd;										//예 : B8 409 55 41 <= 이런식으로 짤라내야함
	 var arrCnpoNm;											// 예 : 부평물
	 var arrCnpoNmBefore = delivAreaCd.substring(0,2);		// B8
	 var arrCnpoNmAfter = delivAreaCd.substring(2,5);		// 409
	 
	 var delivPoNm;											// 예 : 인천계약
	 var delivPoNmBefore = delivAreaCd.substring(5,7);		// 55
	 var delivPoNmAfter = delivAreaCd.substring(7);			// 41
	 
	 
	 
	 /**
	  * 우체국송장 가운데 필수 정보
	  * 보낸분, 받는분 정보, 운송장 송장번호 
	  */
	 //보내는 사람 정보
	 var sender;					//보내는 사람
	 var sendergAddr;				//보내는 사람 주소
	 var senderZipNum;				//보내는 사람 우편번호
	 var senderTelNum;				//보내는 사람 전화번호
	 var senderMobNum;				//보내는 사람 휴대번호
	 
	 //받는 사람 정보
	 var receiver;					//받는 사람
	 var recAddr					//받는 사람 주소
	 var recZipNum;					//받는 사람 우편번호
	 var recTelNum;					//받는 사람 전화번호
	 var recMobNum;					//받는 사람 휴대번호
	 
	 var delivInvoiceNum;			//운송장 번호 - 바코드 이미지 출력해야함
	 

}); // AND OF JQUERY