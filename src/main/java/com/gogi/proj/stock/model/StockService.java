package com.gogi.proj.stock.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.stock.vo.ProductInputListVO;

public interface StockService {

	public void stockChecking(OrderSearchVO osVO, String ip, String adminId);
	
	/**
	 * 
	 * @MethodName : stockSearchList
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 재고할당에 따른 검색 결과를 int 배열로 가져옴
	 *  0 = 출고 가능 주문서의 개수
	 *  1 = 예약 건의 개수 
	 *  2 = 재고 미할당의 개수
	 */
	public int[] stockSearchList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : searchOutputListByOutputType
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 재고 할당 페이지에서 타입별로 검색 결과 가져오기
	 */
	public List<OrdersVO> searchOutputListByOutputType(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectStockResult
	 * @date : 2020. 5. 22.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 재고 결과값 가져오기 dao쪽이랑 같은 기능
	 */
	public List<Map<String, String>> selectStockResult(OrderSearchVO osVO);
	
	public List<PrintDataSetVO> selectProductLabel(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : updateProductChangeValues
	 * @date : 2020. 6. 19.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 상품 변경 혹은 삭제할 경우 재고 증가 및 입출고내역 삭제하기
	 */
	public boolean updateProductChangeValues(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : productOptionStockAlarm
	 * @date : 2020. 7. 1.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 상품이 최소 재고 보유치로 내려갈 경우 해당 상품을 보여줌
	 */
	public List<ProductOptionVO> productOptionStockAlarm();
	
	
	/**
	 * 
	 * @MethodName : selectOptionStockByNameOrBarcodeNum
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 상품명 혹은 바코드번호로 재고 검색하기
	 */
	public List<ProductOptionVO> selectOptionStockByNameOrBarcodeNum(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : insertProductInputList
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @메소드설명 : 상품 입고 처리 (관리자는 그냥 입고 처리되고, 관리자 권한이 없으면 승인을 받아야 함)
	 */
	public int insertProductInputList(AdminVO adminVO, ProductInputListVO pilVO);
	
	/**
	 * 
	 * @MethodName : updateProductInputList
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @메소드설명 : 상품 입고 처리 허가 하기
	 */
	public int updateProductInputList(AdminVO adminVOm, ProductInputListVO pilVO);
	
	
	/**
	 * 
	 * @MethodName : selectProductInputLists
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param paging
	 * @return
	 * @메소드설명 : 입고 요청 내역 목록
	 */
	public List<ProductInputListVO> selectProductInputLists(PaginationInfo paging);
	
	
	/**
	 * 
	 * @MethodName : selectProductInputListsCount
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param paging
	 * @return
	 * @메소드설명 : 입고 요청 내역 목록에 필요한 페이징 처리 개수
	 */
	public int selectProductInputListsCount(PaginationInfo paging);
	
	/**
	 * 
	 * @MethodName : selectProductInputListLimitTen
	 * @date : 2020. 7. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 최근 입고순으로 10개까지 보이기
	 */
	public List<ProductInputListVO> selectProductInputListLimitTen();
	
	
	/**
	 * 
	 * @MethodName : productInputDontPerm
	 * @date : 2020. 7. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 상품 입고 처리 승인이 되지 않은 항목 개수 조회
	 */
	public int productInputDontPerm();
	
	/**
	 * 
	 * @MethodName : changeOrderInvFlag
	 * @date : 2020. 6. 19.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 재고할당 초기화 시키기
	 */
	public int changeOrderInvFlag(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : checkOptionBarcodeDupli
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 바코드 중복 번호 검색하기
	 */
	public List<ProductOptionVO> checkOptionBarcodeDupli(OrderSearchVO osVO);
}
