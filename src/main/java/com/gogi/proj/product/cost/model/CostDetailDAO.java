package com.gogi.proj.product.cost.model;

import java.util.List;

import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;

public interface CostDetailDAO {

	public List<CostsVO> selectCostsPkByCostName(OptionsCostsMatchingVO ocmVO);
	
	/*원가 세부사항 가져오기*/
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info);
	
	/*원가 세부사항 삽입하기*/
	public int insertCostDetail(CostDetailVO costDetailVO);
	
	/*원가 세부사항 총 개수 파악하기*/
	public int selectCostDetailCount(PaginationInfo info);
	
	/*원가 세부사항을 원가 분류코드로 가져오기*/
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk);
	
	/*원가 세부사항과 원가 분류코드 조인하여 전부 가져오기 */
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList();
	
	/*원가 데이터 삽입*/
	public int insertCostsData(CostsVO costsVO);
	
	/*원가 개수 카운팅*/
	public int countingCostsGroupByCostName();
	
	/* 원가 사항에서 pk를 이용하여 원가사항 및 원가변동사항 전부 가져오기  */
	public CostDetailVO selectCostDetailByCcfk(CostDetailVO cdVO);
	
	/**
	 * 
	 * @MethodName : selectCostdetailWightCostcodeByCcPk
	 * @date : 2020. 8. 24.
	 * @author : Jeon KiChan
	 * @param ccVO
	 * @return
	 * @메소드설명 : cost_code의 pk 값으로 cost_detail 목록 가져오기
	 */
	public List<CostDetailVO> selectCostdetailWightCostcodeByCcPk(CostCodeVO ccVO);
	
	
	/**
	 * 
	 * @MethodName : insertCarcass
	 * @date : 2020. 8. 26.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 입력하기
	 */
	public int insertCarcass(CarcassInputListVO cilVO);
	
	
	/**
	 * 
	 * @MethodName : selectCarcassInputList
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 도체 입력 내역 가져오기
	 */
	public List<CarcassInputListVO> selectCarcassInputList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCarcassInputListDetail
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 입력 상세사항
	 */
	public CarcassInputListVO selectCarcassInputListDetail(CarcassInputListVO cilVO);
	
	
	/**
	 * 
	 * @MethodName : updateCarcassInputList
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 수정하기
	 */
	public int updateCarcassInputList(CarcassInputListVO cilVO);
	
	
	/**
	 * 
	 * @MethodName : deleteCarcassInputList
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 삭제하기
	 */
	public int deleteCarcassInputList(CarcassInputListVO cilVO);
	
	
	/**
	 * 
	 * @MethodName : selectCostIoHistory
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 등록된 부분육 목록 가져오기
	 */
	public List<CostIoVO> selectCostIoHistory(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCostIoHistoryCounting
	 * @date : 2020. 10. 16.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 부분육 목록 전체 개수
	 */
	public int selectCostIoHistoryCounting(OrderSearchVO osVO); 
	
	
	/**
	 * 
	 * @MethodName : selectCarcassInputListCount
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 등록된 도체 개수 페이징
	 */
	public int selectCarcassInputListCount(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCarcassCostManage
	 * @date : 2020. 10. 26.
	 * @author : Jeon KiChan
	 * @param OrderSearchVO osVO
	 * @return
	 * @메소드설명 : 원육 입출고 데이터 가져오기
	 */
	public List<CostDetailVO> selectCarcassCostManage(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectCarcassCostManageCounting
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 원육 입출고 데이터 페이징처리
	 */
	public int selectCarcassCostManageCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selsectCarcassCostCategoryCounting
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 원윱 입출고 카테고리 가져오기
	 */
	public List<CostDetailVO> selsectCarcassCostCategoryCounting(OrderSearchVO osVO);
}
