package com.gogi.proj.stock.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoOrderVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.stock.vo.ProductInputListVO;

public interface StockDAO {

	/**
	 * 
	 * @MethodName : selectUpdateCostIoTarget
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 재고 차감 타겟 목록
	 */
	public List<OrdersVO> selectUpdateCostIoTarget(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : updateOrderStockComplete
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 재고 할당 완료 처리
	 */
	public int updateOrderStockComplete(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : selectOptionStockCheck
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param opVO
	 * @return
	 * @메소드설명 : 옵션 재고 조회하기
	 */
	public OptionsVO selectOptionStockCheck(OptionsVO opVO);
	
	/**
	 * 
	 * @MethodName : updateOptionStockSubtract
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param opVO
	 * @return
	 * @메소드설명 : 옵션 재고 차감하기
	 */
	public int updateOptionStockSubtract(OptionsVO opVO);
	
	
	/**
	 * 
	 * @MethodName : selectCostIoStockChecking
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param cdVO
	 * @return
	 * @메소드설명 : 원육 입출고 재고 확인
	 */
	public List<CostIoVO> selectCostIoStockChecking(CostDetailVO cdVO);
	
	
	/**
	 * 
	 * @MethodName : updateCostIoStockSubtract
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @return
	 * @메소드설명 : 원육 출고하기
	 */
	public int updateCostIoStockSubtract(CostIoVO ciVO); 
	
	
	/**
	 * 
	 * @MethodName : updateCostIoStockSoldout
	 * @date : 2020. 5. 8.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @return
	 * @메소드설명 : 원육이 전부 차감되었을 경우 로그만 기록 후 차감 불가 처리하기
	 */
	public int updateCostIoStockSoldout(CostIoVO ciVO);
	
	
	/**
	 * 
	 * @MethodName : updateCostIoQtyInit
	 * @date : 2020. 5. 11.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 원육 출고 개수 초기화 시키기(후에 원육 출고의 테이블과 대조 후 없앨 예정)
	 */
	public int updateCostIoQtyInit();
	
	
	/**
	 * 
	 * @MethodName : insertCio
	 * @date : 2020. 5. 12.
	 * @author : Jeon KiChan
	 * @param cioVO
	 * @return
	 * @메소드설명 : 원육 출고 등록하기
	 */
	public int insertCio(CostIoOrderVO cioVO);
	
	
	/**
	 * 
	 * @MethodName : outputPosOrderCouning
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 출고 가능 주문서 개수 가져오기
	 */
	public int outputPosOrderCouning(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : outputReservOrderCounting
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 예약 건 개수 가져오기
	 */
	public int outputReservOrderCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : notOutputOrderCounting
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 재고 미할당으로 나갈 수 없는 주문서 개수 가져오기
	 */
	public int notOutputOrderCounting(OrderSearchVO osVO);
	
	
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
	 * @메소드설명 : 재고처리 결과값 가져오기
	 */
	public List<Map<String, String>> selectStockResult(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectProductLabel
	 * @date : 2020. 6. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 생고기 라벨 뽑기
	 */
	public List<PrintDataSetVO> selectProductLabel(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : addOptionStock
	 * @date : 2020. 6. 19.
	 * @author : Jeon KiChan
	 * @param opVO
	 * @return
	 * @메소드설명 : 옵션 재고 증가시키기
	 */
	public int addOptionStock(OptionsVO opVO);
	
	
	/**
	 * 
	 * @MethodName : deleteCostIoOrderByOrpk
	 * @date : 2020. 6. 19.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 원육 입출고 삭제시키기
	 */
	public int deleteCostIoOrderByOrpk(OrdersVO orVO);
	
	
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
	 * @MethodName : selectStockChangeOrderByOrPk
	 * @date : 2020. 7. 1.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 상품 재고를 변화시킬 때 해당 주문서값을 가져옴
	 */
	public OrdersVO selectStockChangeOrderByOrPk(OrdersVO orVO);
	
	
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
	public int insertProductInputList(ProductInputListVO pilVO);
	
	/**
	 * 
	 * @MethodName : updateProductInputList
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @메소드설명 : 상품 입고 처리 허가 하기
	 */
	public int updateProductInputList(ProductInputListVO pilVO);
	
	
	
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
	 * @MethodName : selectProductInputListFlag
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @메소드설명 : 입고 승인 중복 방지
	 */
	public boolean selectProductInputListFlag(ProductInputListVO pilVO);
	
	
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
	 * @MethodName : checkOptionBarcodeDupli
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 바코드 중복 번호 검색하기
	 */
	public List<ProductOptionVO> checkOptionBarcodeDupli(OrderSearchVO osVO);
	
}
