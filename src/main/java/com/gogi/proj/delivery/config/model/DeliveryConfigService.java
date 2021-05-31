package com.gogi.proj.delivery.config.model;

import java.util.List;

import com.gogi.proj.delivery.config.vo.DelivImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivCommonImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryConfigService {

	
	/**
	 * 
	 * @MethodName : earlyDelivType
	 * @date : 2020. 11. 27.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송 구분 목록 가져오기
	 */
	public List<EarlyDelivTypeVO> earlyDelivType();
	
	
	/**
	 * 
	 * @MethodName : selectDelivNumCheckTarget
	 * @date : 2020. 12. 1.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 송장 나온지 체크하기
	 */
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO); 
	
	
	/**
	 * 
	 * @MethodName : searchDelivArea
	 * @date : 2020. 12. 4.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송 타입에 따른 배송 가능 지역 조회
	 */
	public List<EarlyDelivTypeVO> searchDelivArea(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : insertEarlyAreaZipcCode
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param eda
	 * @return
	 * @메소드설명 : 우편번호 새로 입력
	 */
	public int insertEarlyAreaZipcCode(EarlyDelivAreaVO eda);
	
	
	/**
	 * 
	 * @MethodName : selectDelivPosArea
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param eda
	 * @return
	 * @메소드설명 : 우편번호에 따른 배송불가 키워드 리스트
	 */
	public List<EarlyDelivAreaVO> selectDelivPosArea(EarlyDelivAreaVO eda);
	
	
	/**
	 * 
	 * @MethodName : insertDelivImposKeyword
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param diVO
	 * @return
	 * @메소드설명 : 배송불가 키워드 입력하기
	 */
	public int insertDelivImposKeyword(DelivImposVO diVO);
	
	
	/**
	 * 
	 * @MethodName : deleteDelivImpos
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param diVO
	 * @return
	 * @메소드설명 : 배송불가 키워드 삭제하기
	 */
	public int deleteDelivImpos(DelivImposVO diVO);
	
	
	/**
	 * 
	 * @MethodName : selectEarlyDelivCommonImposList
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송불가 지역 공통 키워드
	 */
	public List<EarlyDelivCommonImposVO> selectEarlyDelivCommonImposList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : deleteEarlyDelivArea
	 * @date : 2020. 12. 8.
	 * @author : Jeon KiChan
	 * @param edaVO
	 * @return
	 * @메소드설명 : 빠른 배송 우편번호 삭제하기
	 */
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO);
}
