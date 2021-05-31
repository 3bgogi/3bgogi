package com.gogi.proj.product.cost.model;

import java.util.List;

import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;

public interface CostDetailService {
	
	public int selectCostsPkByCostNameAndInsertMatchingData(OptionsVO optionVO);
	
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info);
	
	public int insertCostDetail(CostDetailVO costDetailVO);

	public int selectCostDetailCount(PaginationInfo info);
	
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk);
	
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList();
	
	public int insertCostsData(CostsVO costsVO);
	
	public int countingCostsGroupByCostName();
	
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
	 * @MethodName : insertCarcassAndCostIo
	 * @date : 2020. 8. 26.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 및 부분육 입력하기
	 */
	public int insertCarcassAndCostIo(AdminVO adminVO, CarcassInputListVO cilVO);
	
	
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
	 * @MethodName : selsectCarcassCostCategoryCounting
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 원윱 입출고 카테고리 가져오기
	 */
	public List<CostDetailVO> selsectCarcassCostCategoryCounting(OrderSearchVO osVO);
}
