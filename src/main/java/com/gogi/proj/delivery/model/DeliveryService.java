package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryService {

	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO);
	
	public int updateOrderSendingDay(List<String> orPkList, String ip, String adminId);
	
	public int updateDeliveryOutPutOrder(List<Integer> orPkList, String ip, String adminId);
	
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO, String ip, String adminId);
	
	/**
	 * 
	 * @MethodName : selectStoreSendingResultByDate
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 스토어 발송 처리 목록 가져오기
	 */
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO);
	
	public int storeSendingFinished(OrderSearchVO osVO);
	
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO); 
	public List<Map<String, Object>> selectSendingExcelEmall(StoreSectionVO ssVO);
	
	
	/**
	 * 
	 * @MethodName : insertSendingRequest
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 출고 요청 데이터 넣기
	 */
	public int insertSendingRequest(SendingRequestVO srVO, String ip);
	
	
	/**
	 * 
	 * @MethodName : checkSendingRequest
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 출고 요청 메세지 확인 처리
	 */
	public int checkSendingRequest(SendingRequestVO srVO);
	
	
	/**
	 * 
	 * @MethodName : sendingRequestFinished
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 출고처리 완료하기
	 */
	public int sendingRequestFinished(SendingRequestVO srVO);
	
	
	/**
	 * 
	 * @MethodName : selectSendingRequestNotChecked
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 확인 되지 않은 출고처리 요청 메세지 목록
	 */
	public List<SendingRequestVO> selectSendingRequestNotChecked();
	
	
	/**
	 * 
	 * @MethodName : selectSendingRequestNotSending
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 발송처리가 이뤄지지 않은 출고요청 메세지 목록
	 */
	public List<SendingRequestVO> selectSendingRequestNotSending();
	
	
	/**
	 * 
	 * @MethodName : selectAllSedingRequest
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 모든 출고요청 메세지 목록
	 */
	public List<SendingRequestVO> selectAllSedingRequest();
	
	
	/**
	 * 
	 * @MethodName : selectSendingResults
	 * @date : 2020. 10. 29.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 송장부여페이지에서 발송 결과보기
	 */
	public List<OrdersVO> selectSendingResults(StoreSectionVO ssVO);
	
	/**
	 * 
	 * @MethodName : godomallAutoSendingTarget
	 * @date : 2021. 1. 12.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 고도몰 자동 발송 대상
	 */
	public List<OrdersVO> godomallAutoSendingTarget(StoreSectionVO ssVO);
	
	
	/**
	 * 
	 * @MethodName : nonPickingCount
	 * @date : 2021. 2. 23.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 분류되지 않은 송장 개수
	 */
	public int nonPickingCount();
}
