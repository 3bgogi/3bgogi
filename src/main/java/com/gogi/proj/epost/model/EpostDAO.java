package com.gogi.proj.epost.model;

import java.util.List;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface EpostDAO {

	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO);
	
	public int grantRegiNoByOrPk(RegDataVO regData);
	
	public RegDataVO selectEpostInfoByOrserialspecialnumber(String orSerialSpecialNumber);
	
	public int deleteDelivInfo(String orSerialSpecialNumber);
	
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO);
	
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : deliveryPrintTarget
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 자체 프린트 타겟
	 */
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : grantDeliveryInvoiceNumber
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 부여된 송장 정보 입력
	 */
	public int grantDeliveryInvoiceNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : deliveryReprinting
	 * @date : 2020. 9. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 송장 재출력
	 */
	public OrdersVO deliveryInvoiceNumberReprinting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDeliveryInvoiceNumberByDate
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 발송기한을 기준으로 주문자,수령자,주소,송장번호 가져오기
	 */
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : freshSolutionDelivExcel
	 * @date : 2020. 11. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 프레시솔루션 송장 출력 대상자 가져오기
	 */
	public List<OrdersVO> freshSolutionDelivExcel(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : updateFreshSolutionTarget
	 * @date : 2020. 11. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 임시로 프레시솔루션 입력
	 */
	public int updateFreshSolutionTarget(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateFreshSolutionInvoiceNumber
	 * @date : 2020. 11. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 프레시솔루션 송장 입력하기
	 */
	public int updateFreshSolutionInvoiceNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : deleteDelivInfoByPk
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : orPk로 송장 삭제
	 */
	public int deleteDelivInfoByPk(OrdersVO osVO);
	
	
	/**
	 * 
	 * @MethodName : gtranReceiverPickUp
	 * @date : 2021. 2. 15.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 방문수령 처리하기
	 */
	public int gtranReceiverPickUp(OrdersVO osVO); 
}
