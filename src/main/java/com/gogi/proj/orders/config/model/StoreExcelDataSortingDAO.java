package com.gogi.proj.orders.config.model;

import java.util.List;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.vo.StoreCancleExcelDataSortingVO;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;

public interface StoreExcelDataSortingDAO {

	public int insertStoreExcelDataSorting(StoreSectionVO ssVO);
	
	public StoreExcelDataSortingVO selectStoreExcelDataSorting(StoreSectionVO ssVO);
	
	public int updateStoreExcelDataSorting(StoreExcelDataSortingVO sedsVO);
	
	
	/**
	 * 
	 * @MethodName : insertStoreCancleExcelDataSorting
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 판매처 취소열 엑셀 데이터 기본 값 삽입
	 */
	public int insertStoreCancleExcelDataSorting(StoreSectionVO ssVO);
	
	
	/**
	 * 
	 * @MethodName : selectStoreCancleExcelDataSorting
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 판매처 취소열 엑셀 데이터 값 가져오기
	 */
	public StoreCancleExcelDataSortingVO selectStoreCancleExcelDataSorting(StoreSectionVO ssVO);
	
	
	/**
	 * 
	 * @MethodName : updateStoreCancleExcelDataSorting
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param scedsVO
	 * @return
	 * @메소드설명 : 판매처 취소열 엑셀 데이터 수정하기
	 */
	public int updateStoreCancleExcelDataSorting(StoreCancleExcelDataSortingVO scedsVO);
	
	
	/**
	 * 
	 * @MethodName : cancledOrderSearch
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 취소 요청된 주문서 리스트 가져오기
	 */
	public List<OrdersVO> cancledOrderSearch(OrdersVOList orVO);
	
	
	/**
	 * 
	 * @MethodName : updateCancledOrder
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param orList
	 * @return
	 * @메소드설명 : 취소 주문으로 수정 하기
	 */
	public int updateCancledOrder(OrdersVO orVO);
}
