package com.gogi.proj.delivery.config.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.delivery.config.vo.DelivImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivCommonImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Service
public class DeliveryConfigServiceImple implements DeliveryConfigService{

	private static final Logger logger = LoggerFactory.getLogger(DeliveryConfigServiceImple.class);
	
	@Autowired
	private DeliveryConfigDAO dcDao;

	@Override
	public List<EarlyDelivTypeVO> earlyDelivType() {
		// TODO Auto-generated method stub
		return dcDao.earlyDelivType();
	}

	@Override
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectDelivNumCheckTarget(osVO);
	}

	@Override
	public List<EarlyDelivTypeVO> searchDelivArea(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		int totalRecord = dcDao.delivAreaCount(osVO);
		
		/*페이징 처리 설정 시작*/
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		/*페이징 처리 설정 끝*/
		
		
		return dcDao.delivArea(osVO);
	}

	@Override
	public int insertEarlyAreaZipcCode(EarlyDelivAreaVO eda) {
		// TODO Auto-generated method stub
		boolean dupli = dcDao.earlyAreaZipcodeDupliCheck(eda);
		
		int result = 0;
		
		// 중복값이 없다면
		if(!dupli) {
			result += dcDao.insertEarlyAreaZipcCode(eda);
			
		}
		
		return result; 
	}

	@Override
	public List<EarlyDelivAreaVO> selectDelivPosArea(EarlyDelivAreaVO eda) {
		// TODO Auto-generated method stub
		return dcDao.selectDelivPosArea(eda);
	}

	@Override
	public int insertDelivImposKeyword(DelivImposVO diVO) {
		// TODO Auto-generated method stub
		return dcDao.insertDelivImposKeyword(diVO);
	}

	@Override
	public int deleteDelivImpos(DelivImposVO diVO) {
		// TODO Auto-generated method stub
		return dcDao.deleteDelivImpos(diVO);
	}

	@Override
	public List<EarlyDelivCommonImposVO> selectEarlyDelivCommonImposList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectEarlyDelivCommonImposList(osVO);
	}

	@Override
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return dcDao.deleteEarlyDelivArea(edaVO);
	}
	
	
}
