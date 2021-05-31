package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryDAO {

	
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : updateOrderSendingDay
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 발송처리하기
	 */
	public int updateOrderSendingDay(Map<String, Object> orderInfo);
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryOutPutOrder
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 :스토어 발송처리 기능, 한 번만 발송처리 가능
	 */
	public int updateDeliveryOutPutOrder(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryOutPutCancled
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 스토어 발송처리 취소 기능
	 */
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO);
	
	
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
	
	
	/**
	 * 
	 * @MethodName : storeSendingFinished
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 :  스토어 미발송 주문 건 발송 처리
	 */
	public int storeSendingFinished(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectSendingExcel
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 스토어 발송엑셀파일 데이터 가져오기
	 */
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO);
	public List<Map<String, Object>> selectSendingExcelEmall(StoreSectionVO ssVO);
	
	/**
	 * 
	 * @MethodName : selectDeliveryOutPutCancledTarget
	 * @date : 2020. 10. 8.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 발송 취소 처리 기록에 필요한 주문서 고유값 획득
	 */
	public List<OrderHistoryVO> selectDeliveryOutPutCancledTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : insertSendingRequest
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 출고 요청 데이터 넣기
	 */
	public int insertSendingRequest(SendingRequestVO srVO);
	
	
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
	 * @MethodName : sendingOrders
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 주문서 출고처리하기
	 */
	public int sendingOrders(SendingRequestVO srVO);
	
	
	/**
	 * 
	 * @MethodName : updateSendingDone
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : cs 에서 발송처리할 경우 강제출고요청에 있는 값도 찾아서 변경처리
	 */
	public int updateSendingDone(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : dupliSendingReq
	 * @date : 2020. 10. 22.
	 * @author : Jeon KiChan
	 * @param srVO
	 * @return
	 * @메소드설명 : 중복 여부 확인
	 */
	public int dupliSendingReq(SendingRequestVO srVO);
	
	/**
	 * 
	 * @MethodName : deleteSendingReq
	 * @date : 2020. 10. 22.
	 * @author : Jeon KiChan
	 * @param regVO
	 * @return
	 * @메소드설명 : 강제출고요청 삭제하기
	 */
	public int deleteSendingReq(RegDataVO regVO);
	
	
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
	
	
	/**
	 * 
	 * @MethodName : selectOrderPkByInvoiceNumber
	 * @date : 2021. 3. 3.
	 * @author : Jeon KiChan
	 * @param srVO 송장번호
	 * @return
	 * @메소드설명 : 강제출고요청의 주문서 고유값 가져오기 
	 */
	public List<OrderHistoryVO> selectOrderPkByInvoiceNumber(SendingRequestVO srVO);
	
}
