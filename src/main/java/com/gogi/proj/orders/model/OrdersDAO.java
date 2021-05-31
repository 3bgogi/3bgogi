package com.gogi.proj.orders.model;

import java.util.List;

import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.AdminOrderRecordVO;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

public interface OrdersDAO {

	/*고객 필터링 시작*/
	
	//고객 필터링 추가
	public int addIrregularOrders(IrregularOrderVO iroVO);
	
	//필터링 되지 않은 대기상태 상태 값 목록
	public List<IrregularOrderVO> selectIrregularOrdersNotFiltered();
	
	//모든 고객필터링 값 가져오기
	public List<IrregularOrderVO> selectIrregularOrders();
	
	//필터링 확인처리 하기
	public int successedFiltering(IrregularOrderVO iroVO);
	
	//필터링 확인사항 수정하기
	public int updateFilteringData(IrregularOrderVO iroVO);
	
	//필터링 데이터 삭제하기
	public int deleteFilteringData(IrregularOrderVO iroVO);
	
	/*고객 필터링 끝*/
	/*주문서 묶음 정리 시작*/
	
	 /*최근 일주일간 들어온 주문서 중 묶음 정리가 안된 데이터를 주문번호로 묶어서 가져옴*/
	public List<OrdersVO> selectNotMergedOrders(StoreSectionVO ssVO);
	
	/*주문서 묶음번호 부여하기*/
	public int grantOrSerialSpecialNumber(OrdersVO ordersVO);
	
	public int grantOrSerialSpecialNumberByOrPk(OrdersVO ordersVO);
	
	public List<OrdersVO> selectOrdersByOrderNumber(String orderNumber);
	
	public OrdersVO selectOrdersByPk(int orPk);
	
	//나누기 주문서 데이터 넣기
	public int insertDevideOrderData(OrdersVO ordersVO);
	
	//나눈 주문서 데이터 업데이트
	public int updateDevideOrderData(OrdersVO ordersVO);
	/*주문서 묶음 정리 끝*/
	/*cs 시작*/
	
	//주문고객 검색하기
	public List<OrdersVO> searchCustomerOrderInfo(OrderSearchVO osVO);
	
	//주문고객 총 수 검색
	public int searchCustomerOrderInfoCounting(OrderSearchVO osVO);
	
	//주문서의 상품 상세사항 검색
	public List<OrdersVO> selectCustomerOrderProductInfoDetail(OrdersVO orVO);
	
	//묶음번호로 or_pk 값 가져오기
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumber(String orSerialSpecialNumber);
	
	//or_pk로 주문서 삭제처리하기
	public int deleteOrdersByOrPk(OrdersVO orVO);
	
	//고유번호로 합포할 주문서 목록 가져오기
	public List<OrdersVO> selectCombineInfoBySerialSpecialNumber(List<String> orSerialSpecialNumber);
	
	//합포하기
	public int updateCombineOrders(OrdersVO orVO);
	
	//주문서 고유값으로 상품변경하기
	public int changeProductAndOptionByOrPk(OrdersVO orVO);
	
	//묶음번호로 한 가지의 값만 가져오기
	public OrdersVO selectOnlyOneOrdersAllInfoBySerialNumber(String orSerialSpecialNumber);
	
	//추가 상품 넣을 때
	public int insertAddOrderData(OrdersVO orVO);
	
	/*cs 끝*/
	
	//스마트 스토어 주문서 넣기
	public int insertOrderData(OrdersVO ordersVO);
	
	/*오늘 들어온 날짜로 주문건, 입력일 조회하기*/
	public List<OrdersVO> selectOrdersCountingByInputDate();
	
	/*입력일로 주문서를 조회하여 일괄 삭제하기*/
	public int deleteOrdersByDate(OrdersVO ordersVO);
	
	//오늘자 주문서 조회하기
	public List<OrdersVO> selectTotalOrderInToday();
	
	//주문서 삭제하기
	public int deleteOrders(int orPk);
	
	public List<OrdersVO> selectOrderByOrOrderNumber(OrdersVO ordersVO);
	
	public List<OrdersVO> selectNotMatchingedOrders(OrderSearchVO orderSearchVO);
	
	public int countingNotMatchingedOrders(OrderSearchVO orderSearchVO);
	
	
	//배송중 데이터로 업데이트하기
	public int updateOrderDeliveryInvoiceNumber(OrdersVO ordersVO);
	
	//원가 데이터 조회하기
	public List<OrdersVO> selectNotMatchingedCostData();
	
	//원가 데이터 입력하기
	public int updateOrderCostsData(OrdersVO ordersVO);
	
	
	
	//임시 주문서 뽑기 기능
	public List<OrdersVO> selectedOrderExcelByOrderSerachVO(OrderSearchVO osVO);
	
	// 발송 취소
	public int outputCancledBySerialNumber(OrdersVOList orVOList);
	
	
	//발송일 변경
	public int changeSendingDeadline(OrderSearchVO osVO);
	
	//분리된 주문서 처리하기
	
	public int writeDevideOrderFlag(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateOutputDateBySerialNumber
	 * @date : 2020. 7. 14.
	 * @author : Jeon KiChan
	 * @param orVOList
	 * @return
	 * @메소드설명 : 발송 처리하기 임의로 orSerialSpecialNumber에 string값 날짜를 집어넣어 업데이트함
	 */
	public int updateOutputDateBySerialNumber(OrdersVOList orVOList);
	
	
	/**
	 * 
	 * @MethodName : selectedOrderExcelByOrderSerachVOForVegit
	 * @date : 2020. 7. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 야채 한사람당으로 목록 가져오기
	 */
	public List<OrdersVOList> selectedOrderExcelByOrderSerachVOForVegit(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrderInHowManyProducts
	 * @date : 2020. 7. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 한 주문서에 얼마나 많은 상품이 들었는지 검색
	 */
	public List<ProductOptionVO> selectOrderInHowManyProducts(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : searchRefundOrder
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 환불 처리에 필요한 정보 가져오기
	 */
	public OrdersVO searchRefundOrder(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : orderRefundsEdit
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 데이터상 환불 처리하기
	 */
	public int orderRefundsEdit(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : addCustomerSpecialRequest
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고객요청사항 저장하기
	 */
	public int addCustomerSpecialRequest(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectCustomerSpecialRequest
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고객요청사항 불러오기
	 */
	public OrdersVO selectCustomerSpecialRequest(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectDeliveryMsg
	 * @date : 2020. 7. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송메세지 일괄 확인하기 ( 완전 매칭된 주문서만 확인가능 )
	 */
	public List<OrdersVO> selectDeliveryMsg(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : editDelivNum
	 * @date : 2020. 7. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 묶음 고유 값으로 송장번호 업데이트하기
	 */
	public int editDelivNum(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : selectOrderQtyByPk
	 * @date : 2020. 8. 3.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고유값으로 주문서의 상품 수량 가져오기
	 */
	public OrdersVO selectOrderQtyByPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateMultiMatchingProductOriginalOrder
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 복수 상품이 매칭된 원본 주문서 수정하기
	 */
	public int updateMultiMatchingProductOriginalOrder(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrdersMatchingProductByOrPk
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 복수 상품이 매칭된 주문서 상품 목록 및 수량 가져오기
	 */
	public List<ProductOptionVO> selectOrdersMatchingProductByOrPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateExcelDivOrders
	 * @date : 2020. 9. 28.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 대량 엑셀파일로 주문서를 넣은 후 취소 및 확인 작업
	 */
	public int updateExcelDivOrders(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : newSearchCustomerOrderInfo
	 * @date : 2020. 10. 13.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 새로운 cs 검색 방법
	 */
	public List<OrdersVO> newSearchCustomerOrderInfo(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : newSearchCustomerOrderInfoCounting
	 * @date : 2020. 10. 13.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 새로운 cs 검색 방법
	 */
	public int newSearchCustomerOrderInfoCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : newSearchCustomerOrderInfoToExcelFile
	 * @date : 2020. 10. 13.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cs 검색 결과 엑셀 파일 다운로드
	 */
	public List<OrdersVO> newSearchCustomerOrderInfoToExcelFile(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCreateInvoiceNum
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 송장 생성 차수 가져오기
	 */
	public List<OrdersVO> selectCreateInvoiceNum();
	
	
	/**
	 * 
	 * @MethodName : selectBuyerAddrInfo
	 * @date : 2021. 1. 13.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 과거 주문 내역으로 새로운 주문 정보 기입하기
	 */
	public OrdersVO selectBuyerAddrInfo(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : checkDepositOrder
	 * @date : 2021. 2. 9.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 입금 확인 처리하기
	 */
	public int checkDepositOrder(OrdersVO orVO);
	
	
	
	/**
	 * 
	 * @MethodName : receiverPickUp
	 * @date : 2021. 2. 15.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 픽업 처리하기 (퀵서비스, 방문수령)
	 */
	public int receiverPickUp(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : deleteExcelGiftOrderByOrFk
	 * @date : 2021. 2. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 대량주소 중복값 삭제하기
	 */
	public int deleteExcelGiftOrderByOrFk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : insertAdminOrderRecord
	 * @date : 2021. 3. 8.
	 * @author : Jeon KiChan
	 * @param aorVO
	 * @return
	 * @메소드설명 : 주문서 별로 특이사항 혹은 메모 기록하기 (cs 내역)
	 */
	public int insertAdminOrderRecord(AdminOrderRecordVO aorVO);
	
	
	/**
	 * 
	 * @MethodName : searchAdminOrderRecordBySerialSpecialNumber
	 * @date : 2021. 3. 8.
	 * @author : Jeon KiChan
	 * @param orVO - orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 주문서의 특이사항 혹은 메모 기록 가져오기(cs 내역)
	 */
	public List<AdminOrderRecordVO> searchAdminOrderRecordBySerialSpecialNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : insertOrderDataBatch
	 * @date : 2021. 3. 11.
	 * @author : Jeon KiChan
	 * @param orList
	 * @return
	 * @메소드설명 : batch로 주문서 대량 일괄 처리하기
	 */
	public int [] insertOrderDataBatch(List<OrdersVO> orList);
	
}
