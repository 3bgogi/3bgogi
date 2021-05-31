package com.gogi.proj.orders.config.model;

import java.util.List;

import com.gogi.proj.orders.config.vo.ExceptAddressKeywordVO;
import com.gogi.proj.orders.config.vo.OrdersDeleteVO;
import com.gogi.proj.orders.config.vo.ReqFilterKeywordVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface OrderConfigDAO {

	
	/**
	 * 
	 * @MethodName : insertExceptAddressKeyword
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param eakVO
	 * @return
	 * @메소드설명 : 제외 주소 키워드 값 넣기
	 */
	public int insertExceptAddressKeyword(ExceptAddressKeywordVO eakVO);
	
	
	/**
	 * 
	 * @MethodName : selectExceptAddressKeyword
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 제외 주소 키워드 값 목록 불러오기
	 */
	public List<ExceptAddressKeywordVO> selectExceptAddressKeyword();
	
	
	/**
	 * 
	 * @MethodName : deleteExceptAddressKeyword
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param eakVO
	 * @return
	 * @메소드설명 : 제외 주소 키워드 삭제하기
	 */
	public int deleteExceptAddressKeyword(ExceptAddressKeywordVO eakVO); 
	
	
	/**
	 * 
	 * @MethodName : exceptAddrTargetOrder
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param eakList
	 * @return
	 * @메소드설명 : 특정 제외 키워드에 속한 주문서 전부 가져오기 (확인된 주문서는 제외)
	 */
	public List<OrdersVO> exceptAddrTargetOrder(List<ExceptAddressKeywordVO> eakList);
	
	/**
	 * 
	 * @MethodName : updateSpecialRegionOrder
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param orList
	 * @return
	 * @메소드설명 : 특정 제외 키워드로 확인 한 주문서로 수정하기
	 */
	public int updateSpecialRegionOrder(List<Integer> orList);
	
	
	/**
	 * 
	 * @MethodName : searchEceptAddrAndUpdateCheckFlag
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param eakList
	 * @return
	 * @메소드설명 : 특정 제외 키워드에 속한 주문서 특수 지역, 특수 지역 체크하지 않음으로 수정하기
	 */
	public int searchEceptAddrAndUpdateCheckFlag(List<ExceptAddressKeywordVO> eakList);
	
	
	/**
	 * 
	 * @MethodName : insertReqFilterKeyword
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 필터링 입력
	 */
	public int insertReqFilterKeyword(ReqFilterKeywordVO rfkVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectAllReqFilterKeywordList
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 단어 전부 불러오기
	 */
	public List<ReqFilterKeywordVO> selectAllReqFilterKeywordList();
	
	
	
	/**
	 * 
	 * @MethodName : selectUseReqFilterKeywordList
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 단어 사용하는 것만 불러오기
	 */
	public List<ReqFilterKeywordVO> selectUseReqFilterKeywordList();
	
	
	
	/**
	 * 
	 * @MethodName : deleteReqFilterKeywordByPk
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 단어 삭제하기
	 */
	public int deleteReqFilterKeywordByPk(ReqFilterKeywordVO rfkVO);
	
	
	/**
	 * 
	 * @MethodName : updateReqFilterKeywordUseOrUnuse
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @return
	 * @메소드설명 : 배송메세지 요구 사항 단어 사용 여부 수정하기
	 */
	public int updateReqFilterKeywordUseOrUnuse(ReqFilterKeywordVO rfkVO);
	
	
	/**
	 * 
	 * @MethodName : selectPackingIrreTargetOrder
	 * @date : 2020. 8. 6.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 묶음정리가 끝난 상품 주문서 중 합쳐도 무관한 주문서 목록 검색하기
	 */
	public List<OrdersVO> selectPackingIrreTargetOrder();
	
	
	/**
	 * 
	 * @MethodName : selectPackingIrreTargetOrderList
	 * @date : 2020. 8. 6.
	 * @author : Jeon KiChan
	 * @param orVoList
	 * @return
	 * @메소드설명 : selectPackingIrreTargetOrder의 목록으로 상세사항을 검색하여 나열하기
	 */
	public List<OrdersVOList> selectPackingIrreTargetOrderList(List<OrdersVO> orVoList);
	
	
	/**
	 * 
	 * @MethodName : selectPackingIrreTargetOrderCounting
	 * @date : 2020. 8. 7.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 묶음정리가 끝난 상품 주문서 중 합쳐도 무관한 주문서 목록 개수 검색
	 */
	public int selectPackingIrreTargetOrderCounting();
	
	
	/**
	 * 
	 * @MethodName : insertDeleteOrders
	 * @date : 2021. 2. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 삭제 후 파기정보 남기기
	 */
	public int insertDeleteOrders(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : selectOrdersDeleteList
	 * @date : 2021. 3. 4.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 주문서 삭제 내역 가져오기
	 */
	public List<OrdersDeleteVO> selectOrdersDeleteList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrdersDeleteListCounting
	 * @date : 2021. 3. 4.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 주문서 삭제 내역에 필요한 페이징 개수
	 */
	public int selectOrdersDeleteListCounting(OrderSearchVO osVO);
	
}
