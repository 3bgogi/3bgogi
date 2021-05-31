package com.gogi.proj.matching.model;

import java.util.List;

import com.gogi.proj.matching.vo.OptionMatchingVO;
import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface MatchingService {

	public int[] matchingsProductAndOrders(String ip, String adminId);
	
	public int insertProductMatching(ProductMatchingVO pmVO);
	
	public List<OrdersVO> selectOrdersOptionNotMatchinged(OrderSearchVO osc);
	
	public int countingNotOptionMatchingedOrders(OrderSearchVO osc);
	
	public int insertOptionMatchingListData(List<OptionMatchingVO> optionMatchingVOList);
	
	
	/**
	 * 
	 * @MethodName : selectProductMatchingByPmPk
	 * @date : 2020. 7. 23.
	 * @author : Jeon KiChan
	 * @param pmVO
	 * @return
	 * @메소드설명 : pmPk로 상품 매칭값 가져오기
	 */
	public ProductMatchingVO selectProductMatchingByPmPk(ProductMatchingVO pmVO); 
	
	
	/**
	 * 
	 * @MethodName : editProductMatching
	 * @date : 2020. 7. 23.
	 * @author : Jeon KiChan
	 * @param pmVO
	 * @return
	 * @메소드설명 : 스토어 매칭을 다른 상품으로 재매칭하기
	 */
	public int editProductMatching(ProductMatchingVO pmVO);
	
	/**
	 * 
	 * @MethodName : selectOptionMatchings
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션 매칭 조회
	 */
	public List<OptionMatchingVO> selectOptionMatchings(OptionMatchingVO omVO);
	
	
	/**
	 * 
	 * @MethodName : deleteOptionMatching
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션 매칭 삭제
	 */
	public int deleteOptionMatching(OptionMatchingVO omVO);
	
	
	/**
	 * 
	 * @MethodName : insertOptionMatching
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션 매칭 추가
	 */
	public int insertOptionMatching(OptionMatchingVO omVO);
	
	
	/**
	 * 
	 * @MethodName : optionMatchingDupliCheck
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션 등록 시 중복여부 체크하기
	 */
	public int optionMatchingDupliCheck(OptionMatchingVO omVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrderMatchingCounting
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 매칭된 옵션 개수 파악하기
	 */
	public int selectOrderMatchingCounting(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrderMatchingIncMeatCounting
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 매칭된 옵션 중 합포가 가능한 주문서 개수 파악
	 */
	public int selectOrderMatchingIncMeatCounting(OrdersVO orVO);
}
