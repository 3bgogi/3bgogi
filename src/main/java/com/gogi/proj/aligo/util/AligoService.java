package com.gogi.proj.aligo.util;

import java.util.List;

public interface AligoService {

	/**
	 * 
	 * @MethodName : insertAligoSendingForm
	 * @date : 2020. 7. 8.
	 * @author : Jeon KiChan
	 * @param asfVO
	 * @return
	 * @메소드설명 : 알리고 문자 폼 입력
	 */
	public int insertAligoSendingForm(AligoSendingFormVO asfVO);
	
	
	/**
	 * 
	 * @MethodName : selectAligoSendingFormList
	 * @date : 2020. 7. 8.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 알리고 폼 리스트 전부 불러오기
	 */
	public List<AligoSendingFormVO> selectAligoSendingFormList();
	
	
	/**
	 * 
	 * @MethodName : selectAligoSendingFormByAsfPk
	 * @date : 2020. 7. 8.
	 * @author : Jeon KiChan
	 * @param asfVO
	 * @return
	 * @메소드설명 : 알리고 폼 Pk 값으로 불러오기
	 */
	public AligoSendingFormVO selectAligoSendingFormByAsfPk(AligoSendingFormVO asfVO);
	
	
	/**
	 * 
	 * @MethodName : deleteAligoSendingFormByAsfPk
	 * @date : 2020. 7. 8.
	 * @author : Jeon KiChan
	 * @param asfVO
	 * @return
	 * @메소드설명 : 알리고 폼 Pk 값으로 삭제하기
	 */
	public int deleteAligoSendingFormByAsfPk(AligoSendingFormVO asfVO);
}
