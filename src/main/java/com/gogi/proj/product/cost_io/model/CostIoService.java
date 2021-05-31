package com.gogi.proj.product.cost_io.model;

import java.util.List;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;

public interface CostIoService {

	/**
	 * 
	 * @MethodName : insertCostInputData
	 * @date : 2020. 4. 8.
	 * @author : Jeon KiChan
	 * @param costIoVO
	 * @return
	 * @메소드설명 : 원육, 원가상품 입고 처리
	 */
	public int insertCostInputData(CostIoVO costIoVO);
	
	/**
	 * 
	 * @MethodName : selectCostIoData
	 * @date : 2020. 4. 9.
	 * @author : Jeon KiChan
	 * @param costIoVO
	 * @return
	 * @메소드설명 : 입고 상품 상세사항 불러오기
	 */
	public CostIoVO selectCostIoData(CostIoVO costIoVO);
	
	
	/**
	 * 
	 * @MethodName : updateCostIoData
	 * @date : 2020. 4. 9.
	 * @author : Jeon KiChan
	 * @param costIoVO
	 * @return
	 * @메소드설명 : 입고 상품 수정하기
	 */
	public int updateCostIoData(CostIoVO costIoVO);
	
	/**
	 * 
	 * @MethodName : deleteCostIo
	 * @date : 2020. 4. 9.
	 * @author : Jeon KiChan
	 * @param costIoVO
	 * @return
	 * @메소드설명 : 입고 상품 정보 삭제 처리
	 */
	public int deleteCostIo(CostIoVO costIoVO);
	
	/**
	 * 
	 * @MethodName : selectCostIOCanOutputProductList
	 * @date : 2020. 4. 21.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 출고되는 상품 테스트로 생고기계열 무게, 개수 계산해서 가져오기 
	 */
	public List<CostIoVO> selectCostIOCanOutputProductList(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : selectCostInputList
	 * @date : 2020. 5. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 메인 페이지에 들어갈 최근 입고된 상품 조회
	 */
	public List<CostDetailVO> selectCostInputList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCostDetailCode
	 * @date : 2020. 10. 16.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 원재료 입고에 필요한 원재료 코드
	 */
	public List<CostDetailVO> selectCostDetailCode();
	
	
	/**
	 * 
	 * @MethodName : chooseCostIo
	 * @date : 2020. 10. 22.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 라벨지에 표기될 입고 원육 선택하기
	 */
	public int chooseCostIo(CostIoVO ciVO);
}
