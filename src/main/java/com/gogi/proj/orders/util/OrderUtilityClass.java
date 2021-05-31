package com.gogi.proj.orders.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

public class OrderUtilityClass {

	
	/**
	 * 
	 * @MethodName : returnDevideOrdersData
	 * @date : 2019. 12. 5.
	 * @author : Jeon KiChan
	 * @param ordersVO
	 * @param devideValue
	 * @param originalOrderFlag
	 * @param orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 하나의 주문서를 여러개로 분리할 때에사용
	 */
	public OrdersVO returnDevideOrdersData(OrdersVO ordersVO, int devideValue, boolean originalOrderFlag, String orSerialSpecialNumber) {
		int productQuantity = ordersVO.getOrAmount();
		
		ordersVO.setOrAmount(ordersVO.getOrAmount()/productQuantity * devideValue);
		
			if(originalOrderFlag == true) {
				ordersVO.setOrProductPrice( Math.round(ordersVO.getOrProductPrice()/productQuantity * devideValue) );
				ordersVO.setOrProductOptionPrice( Math.round(ordersVO.getOrProductOptionPrice()/productQuantity * devideValue) );
				ordersVO.setOrDiscountPrice( Math.round(ordersVO.getOrDiscountPrice()/productQuantity * devideValue) );
				ordersVO.setOrTotalPrice( Math.round(ordersVO.getOrTotalPrice()/productQuantity * devideValue) );
				ordersVO.setOrTotalCost( Math.round(ordersVO.getOrTotalCost()/productQuantity * devideValue) );
				ordersVO.setOrPaymentCommision( Math.round(ordersVO.getOrPaymentCommision()/productQuantity * devideValue) );
				ordersVO.setOrAnotherPaymentCommision( Math.round(ordersVO.getOrAnotherPaymentCommision()/productQuantity * devideValue) );
				ordersVO.setOrDevideFlag(false);
				
			}else {
				ordersVO.setOrSerialSpecialNumber(orSerialSpecialNumber);
				ordersVO.setOrProductOrderNumber("삼-"+ordersVO.getOrProductOrderNumber());
				ordersVO.setOrProductPrice( Math.abs(ordersVO.getOrProductPrice()/productQuantity * devideValue) );
				ordersVO.setOrProductOptionPrice( Math.abs(ordersVO.getOrProductOptionPrice()/productQuantity * devideValue) );
				ordersVO.setOrDiscountPrice( Math.abs(ordersVO.getOrDiscountPrice()/productQuantity * devideValue) );
				ordersVO.setOrDeliveryDiscountPrice(0);
				ordersVO.setOrDeliveryPrice(0);
				ordersVO.setOrTotalPrice( Math.abs(ordersVO.getOrTotalPrice()/productQuantity * devideValue) );
				ordersVO.setOrTotalCost( Math.abs(ordersVO.getOrTotalCost()/productQuantity * devideValue) );
				ordersVO.setOrPaymentCommision( Math.abs(ordersVO.getOrPaymentCommision()/productQuantity * devideValue) );
				ordersVO.setOrAnotherPaymentCommision( Math.abs(ordersVO.getOrAnotherPaymentCommision()/productQuantity * devideValue) );
				ordersVO.setOrDevideFlag(true);
				ordersVO.setOrInvFlag(false);
			}
		
		return ordersVO;
	}
	
	
	public List<OrdersVO> devideMultiMatchingProduct(OrdersVO ordersVO, List<ProductOptionVO> matchingProducts) throws CloneNotSupportedException {

			List<OrdersVO> result = new ArrayList<OrdersVO>();
			
			for(int i = 0; i < matchingProducts.size(); i++) {
				OrdersVO divOd = ordersVO.copy();
				
				//원본 주문서는 데이터를 그대로 남김
				if(i == 0) {
					divOd.setOrProduct(matchingProducts.get(i).getProductName());
					divOd.setOrProductOption(matchingProducts.get(i).getOptionName());
					divOd.setOrDevideFlag(false);
					divOd.setOrInvFlag(false);
					divOd.setOrAmount(matchingProducts.get(i).getOptionSeq());
					divOd.setOrTotalCost(0);
					result.add(divOd);
					
				//복수 매칭 나누기로 인한 추가 주문서
				}else {
					divOd.setOrProductOrderNumber("삼-"+ordersVO.getOrProductOrderNumber());
					divOd.setOrProduct(matchingProducts.get(i).getProductName());
					divOd.setOrProductOption(matchingProducts.get(i).getOptionName());
					divOd.setOrProductPrice(0);
					divOd.setOrProductOptionPrice(0);
					divOd.setOrDiscountPrice(0);
					divOd.setOrAmount(matchingProducts.get(i).getOptionSeq());
					divOd.setOrTotalPrice(0 );
					divOd.setOrTotalCost(0);
					divOd.setOrPaymentCommision(0);
					divOd.setOrAnotherPaymentCommision( 0);
					divOd.setOrDevideFlag(true);
					divOd.setOrInvFlag(false);
					divOd.setOrInvFlag(false);
					result.add(divOd);
				}
			}
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : returnAddProductOrdersData
	 * @date : 2019. 12. 5.
	 * @author : Jeon KiChan
	 * @param insertOrdersVO
	 * @param originalCloneOrderVO
	 * @return
	 * @메소드설명 : 상품을 임의로 추가할 때에 사용
	 */
	public OrdersVO returnAddProductOrdersData(OrdersVO insertOrdersVO, OrdersVO originalCloneOrderVO) {

		Date today = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		originalCloneOrderVO.setOrProductOrderNumber("삼-"+sdf.format(today));
		originalCloneOrderVO.setOrProductPrice(insertOrdersVO.getOrProductPrice());
		originalCloneOrderVO.setOrProductOptionPrice(0);
		originalCloneOrderVO.setOrTotalPrice( insertOrdersVO.getOrTotalPrice());
		originalCloneOrderVO.setOrTotalCost(0);
		originalCloneOrderVO.setOrAmount(insertOrdersVO.getOrAmount());
		originalCloneOrderVO.setOrProduct(insertOrdersVO.getOrProduct());
		originalCloneOrderVO.setOrProductOption(insertOrdersVO.getOrProductOption());
		originalCloneOrderVO.setOrPaymentCommision(0);
		originalCloneOrderVO.setOrAnotherPaymentCommision(0);
		originalCloneOrderVO.setOrDevideFlag(true);
		originalCloneOrderVO.setPmFk(0);
		
		return originalCloneOrderVO;
	}
	
}
