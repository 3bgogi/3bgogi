package com.gogi.proj.configurations.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.BlockSendingListVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface ConfigurationDAO {

	public int addStoreSection(StoreSectionVO ssVO);
	
	public List<StoreSectionVO> selectStoreSectionList();
	
	public int increaseStoreSectionSpecialNumber(StoreSectionVO ssVO);
	
	public StoreSectionVO selectStoreSectionBySspk(int ssPk);
	
	/*public StoreExcelDataSortingVO */
	
	public int updateStoreSendingForm(StoreSectionVO ssVO);
	
	public int updateStoreSection(StoreSectionVO ssVO);
	
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO);
	
	public int updateStoreMerge(StoreSectionVO ssVO);

	
	/**
	 * 
	 * @MethodName : insertBlockSendingList
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param bslVO
	 * @return
	 * @메소드설명 : 문자발송금지명단 입력
	 */
	public int insertBlockSendingList(BlockSendingListVO bslVO);
	
	
	/**
	 * 
	 * @MethodName : selectBlockSendingList
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 문자발송금지명단 불러오기
	 */
	public List<BlockSendingListVO> selectBlockSendingList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : deleteBlockSendingList
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param bslVO
	 * @return
	 * @메소드설명 : 문자발송금지명단 삭제
	 */
	public int deleteBlockSendingList(BlockSendingListVO bslVO);
	
	
	/**
	 * 
	 * @MethodName : selectBlockSendingListCount
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 문자발송금지명단 페이징처리
	 */
	public int selectBlockSendingListCount(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectBlockSendingListDupli
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param bslVO
	 * @return
	 * @메소드설명 : 중복값 체크하기
	 */
	public int selectBlockSendingListDupli(BlockSendingListVO bslVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectEventMsgTarget
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 이벤트 문자 발송 명단 뽑기
	 */
	public List<OrdersVO> selectEventMsgTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllBlockSendingList
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 문자발송금지명단 전부 가져오기
	 */
	public List<BlockSendingListVO> selectAllBlockSendingList();
	
	
	/**
	 * 
	 * @MethodName : selectEventMsgTargetCounting
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 이벤트 문자 발송 명단 뽑기 페이징 처리
	 */
	public int selectEventMsgTargetCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllEventMsgTarget
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return 
	 * @메소드설명 : 문자발송금지명단 엑셀로 변환
	 */
	public List<OrdersVO> selectAllEventMsgTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectEventMsgProductKeyword
	 * @date : 2020. 10. 20.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 문자발송금지 명단 검색 시 대응되는 키워드값 가져오기
	 */
	public List<OrdersVO> selectEventMsgProductKeyword(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : storeListOrderInTwoMonth
	 * @date : 2021. 3. 2.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 2개월 이내에 들어온 주문서의 판매처 정보 가져오기
	 */
	public List<StoreSectionVO> storeListOrderInTwoMonth();
}
