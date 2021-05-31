package com.gogi.proj.log.util;

import com.gogi.proj.orders.vo.OrdersVO;

public class LogUtil {

	public static String logChangeCheck(OrdersVO originalOrder, OrdersVO changeOrder) {
		
		StringBuffer sb = new StringBuffer("");
		
			//발송일 변경 이력 체크
		if(!originalOrder.getOrSendingDeadline().equals(changeOrder.getOrSendingDeadline())) {
			sb.append("발송일 변경 [ "+originalOrder.getOrSendingDeadline()+" => "+changeOrder.getOrSendingDeadline()+" ]\n");
			
			//구매자명
		}if(!originalOrder.getOrBuyerName().equals(changeOrder.getOrBuyerName())) {
			sb.append("구매자명 변경 [ "+originalOrder.getOrBuyerName()+" => "+changeOrder.getOrBuyerName()+" ]\n");
			
			//보내는 사람
		}if(originalOrder.getOrBuyerAnotherName() == null ? 
				!"".equals(changeOrder.getOrBuyerAnotherName()) :
				!originalOrder.getOrBuyerAnotherName().equals(changeOrder.getOrBuyerAnotherName())) {
			sb.append("보내는사람 변경 [ "+(originalOrder.getOrBuyerAnotherName() == null ? "없음" : originalOrder.getOrBuyerAnotherName() )+" => "+changeOrder.getOrBuyerAnotherName()+" ]\n");
			
			//받는 사람
		}if(!originalOrder.getOrReceiverName().equals(changeOrder.getOrReceiverName())) {
			sb.append("받는 사람 변경 [ "+originalOrder.getOrReceiverName()+" => "+changeOrder.getOrReceiverName()+" ]\n");
			
			//우편번호
		}if(!originalOrder.getOrShippingAddressNumber().equals(changeOrder.getOrShippingAddressNumber())) {
			sb.append("우편번호  변경 [ "+originalOrder.getOrShippingAddressNumber()+" => "+changeOrder.getOrShippingAddressNumber()+" ]\n");
			
			//주소
		}if(!originalOrder.getOrShippingAddress().equals(changeOrder.getOrShippingAddress())) {
			sb.append("주소  변경 [ "+originalOrder.getOrShippingAddress()+" => "+changeOrder.getOrShippingAddress()+" ]\n");
			
			//주소상세사항
		}if(originalOrder.getOrShippingAddressDetail() == null ? 
				!"".equals(changeOrder.getOrShippingAddressDetail()) :
				!originalOrder.getOrShippingAddressDetail().equals(changeOrder.getOrShippingAddressDetail())) {
			sb.append("상세주소  변경 [ "+(originalOrder.getOrShippingAddressDetail() == null ? "" : originalOrder.getOrShippingAddressDetail())+" => "+changeOrder.getOrShippingAddressDetail()+" ]\n");
			
			//구매자번호1
		}if(!originalOrder.getOrBuyerContractNumber1().equals(changeOrder.getOrBuyerContractNumber1())) {
			sb.append("구매자 연락처1  변경 [ "+originalOrder.getOrBuyerContractNumber1()+" => "+changeOrder.getOrBuyerContractNumber1()+" ]\n");
			
			//구매자번호2
		}if( originalOrder.getOrBuyerContractNumber2() == null ?
				!"".equals(changeOrder.getOrBuyerContractNumber2()) :
				!originalOrder.getOrBuyerContractNumber2().equals(changeOrder.getOrBuyerContractNumber2())) {
			sb.append("구매자 연락처2  변경 [ "+(originalOrder.getOrBuyerContractNumber2() == null ? "" : originalOrder.getOrBuyerContractNumber2())+" => "+changeOrder.getOrBuyerContractNumber2()+" ]\n");
			
			//수령자번호1
		}if(!originalOrder.getOrReceiverContractNumber1().equals(changeOrder.getOrReceiverContractNumber1())) {
			sb.append("수령자 연락처1  변경 [ "+originalOrder.getOrReceiverContractNumber1()+" => "+changeOrder.getOrReceiverContractNumber1()+" ]\n");
			
			//수령자번호2
		}if( originalOrder.getOrReceiverContractNumber2() == null ?
				!"".equals(changeOrder.getOrReceiverContractNumber2()) :
				!originalOrder.getOrReceiverContractNumber2().equals(changeOrder.getOrReceiverContractNumber2())) {
			sb.append("수령자 연락처2  변경 [ "+(originalOrder.getOrReceiverContractNumber2() ==  null ? "" : originalOrder.getOrReceiverContractNumber2())+" => "+changeOrder.getOrReceiverContractNumber2()+" ]\n");
			
			//요청사항
		}if( originalOrder.getOrDeliveryMessage() == null ? 
				!"".equals(changeOrder.getOrDeliveryMessage()) :
				!originalOrder.getOrDeliveryMessage().equals(changeOrder.getOrDeliveryMessage())) {
			sb.append("요청사항  변경 [ "+(originalOrder.getOrDeliveryMessage() == null ? "" : originalOrder.getOrDeliveryMessage())+" => "+changeOrder.getOrDeliveryMessage()+" ]\n");
		}
		
		
		return sb.toString();
	}
}
