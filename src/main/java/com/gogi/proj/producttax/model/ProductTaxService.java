package com.gogi.proj.producttax.model;

import java.util.List;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;
import com.gogi.proj.producttax.vo.TaxTableVO;

public interface ProductTaxService {

	
	/**
	 * 
	 * @MethodName : insertResCompany
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @return
	 * @메소드설명 : 거래처 추가하기
	 */
	public int insertResCompany(ResCompanyVO rcVO);
	
	
	/**
	 * 
	 * @MethodName : selectRecCompany
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 거래처 모든 목록 가져오기
	 */
	public List<ResCompanyVO> selectRecCompany(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectRecCompanyByRcPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @return
	 * @메소드설명 : 특정 거래처 정보 가져오기
	 */
	public ResCompanyVO selectRecCompanyByRcPk(ResCompanyVO rcVO);
	
	/**
	 * 
	 * @MethodName : selectRecCompanyCounting
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래처 모든 목록 카운팅
	 */
	public int selectRecCompanyCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : insertProductInfo
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 거래내역 추가하기
	 */
	public int insertProductInfo(ProductInfoVO piVO);
	
	
	/**
	 * 
	 * @MethodName : selectProductInfoList
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래내역 목록
	 */
	public List<ProductInfoVO> selectProductInfoList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectProductInfoListCounting
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래내역 목록에 필요한 페이징처리
	 */
	public int selectProductInfoListCounting(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : selectProductInfoByPiPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 거래내역서 상세조회
	 */
	public ProductInfoVO selectProductInfoByPiPk(ProductInfoVO piVO);
	
	
	/**
	 * 
	 * @MethodName : updateTaxbilFlag
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 세금계산서 받은지 여부 업데이트
	 */
	public int updateTaxbilFlag(ProductInfoVO piVO);
	
	
	/**
	 * 
	 * @MethodName : updateAccFlag
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 이체내역 여부
	 */
	public int updateAccFlag(ProductInfoVO piVO);
	
	
	/**
	 * 
	 * @MethodName : updateProductInfo
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 거래내역서 수정
	 */
	public int updateProductInfo(ProductInfoVO piVO);
	
	
	/**
	 * 
	 * @MethodName : deleteProductInfo
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 거래내역서 삭제
	 */
	public int deleteProductInfo(ProductInfoVO piVO);

	
	/**
	 * 
	 * @MethodName : updateResCompany
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @return
	 * @메소드설명 : 거래처 수정
	 */
	public int updateResCompany(ResCompanyVO rcVO);
	
	
	/**
	 * 
	 * @MethodName : deleteResCompany
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @return
	 * @메소드설명 : 거래처 삭제
	 */
	public int deleteResCompany(ResCompanyVO rcVO);
	
	
	/**
	 * 
	 * @MethodName : insertTaxTableData
	 * @date : 2021. 3. 10.
	 * @author : Jeon KiChan
	 * @param ttVO
	 * @return
	 * @메소드설명 : 테스트용
	 */
	public int insertTaxTableData(TaxTableVO ttVO);
	
	
	public List<TaxTableVO> taxZero();
	
	public List<TaxTableVO> dutyZero();
	
	public List<TaxTableVO> taxTableCount();
	
	public int deleteTaxTable();
	
	/**
	 * 
	 * @MethodName : insertTaxTableDataBatch
	 * @date : 2021. 3. 11.
	 * @author : Jeon KiChan
	 * @param ttList
	 * @return
	 * @메소드설명 :
	 */
	public int[] insertTaxTableDataBatch(List<TaxTableVO> ttList);
}
