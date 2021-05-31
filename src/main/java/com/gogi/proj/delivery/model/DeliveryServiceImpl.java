package com.gogi.proj.delivery.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired
	private DeliveryDAO deliDao;

	@Autowired
	private LogService logService;
	
	@Override
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return deliDao.selectDelivTargetByOrDeliveryInvoiceNumber(orVO);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int updateOrderSendingDay(List<String> orPkList, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		String dates = sdf.format(today);
		Map<String, Object> orderInfo = new HashMap<>();
		
		
		Timestamp now = new Timestamp(new Date().getTime());
		
		orderInfo.put("orPkList", orPkList);
		orderInfo.put("orSendingDay", now);
		
		int result = deliDao.updateOrderSendingDay(orderInfo);
		
		OrderHistoryVO ohVO = null;
		
		int orPkSize = orPkList.size();
		for(int i=0; i< orPkSize; i++) {

			ohVO = new OrderHistoryVO();
			 
			ohVO.setOrFk(Integer.parseInt(orPkList.get(i)));
			ohVO.setOhIp(ip);
			ohVO.setOhAdmin(adminId);
			ohVO.setOhEndPoint("출고 - 발송처리");
			ohVO.setOhRegdate(dates);
			ohVO.setOhDetail("분류가 되어 발송처리");
			
			logService.insertOrderHistory(ohVO);
			
		}
		
		orderInfo = null;
		
		return result;
	}

	@Override
	@Transactional
	public int updateDeliveryOutPutOrder(List<Integer> orPkList, String ip, String adminId) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		String dates = sdf.format(today);
		OrdersVO orVO = new OrdersVO();
		OrderHistoryVO ohVO = null;
		
		Timestamp sendingTime = new Timestamp(new Date().getTime());
		
		int result = 0;
		
		for(int i = 0; i < orPkList.size(); i++) {
			orVO.setOrPk(orPkList.get(i));
			orVO.setOrOutputDate(sendingTime);
			result += deliDao.updateDeliveryOutPutOrder(orVO);
			
			ohVO = new OrderHistoryVO();
			 
			ohVO.setOrFk(orVO.getOrPk());
			ohVO.setOhIp(ip);
			ohVO.setOhAdmin(adminId);
			ohVO.setOhEndPoint("출고 - 발송처리");
			ohVO.setOhRegdate(dates);
			ohVO.setOhDetail("판매처로의 발송처리");
			
			logService.insertOrderHistory(ohVO);
			
		}
		
		return result;
	}

	@Override
	@Transactional
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdf.format(new Date());
		
		int result = deliDao.updateDeliveryOutPutCancled(osVO);
		
		List<OrderHistoryVO> ohList = deliDao.selectDeliveryOutPutCancledTarget(osVO);
		
		for(OrderHistoryVO ohVO : ohList) {
			ohVO.setOhIp(ip);
			ohVO.setOhAdmin(adminId);
			ohVO.setOhEndPoint("발송 취소");
			ohVO.setOhRegdate(today);
			ohVO.setOhDetail("판매처 송장부여에서 일괄 발송 취소");
			
			logService.insertOrderHistory(ohVO);
		}
		
		return result;
	}

	@Override
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return deliDao.selectStoreSendingResultByDate(osVO);
	}

	@Override
	@Transactional
	public int storeSendingFinished(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return deliDao.storeSendingFinished(osVO);
	}

	@Override
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return deliDao.selectSendingExcel(ssVO);
	}

	@Override
	public List<Map<String, Object>> selectSendingExcelEmall(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return deliDao.selectSendingExcelEmall(ssVO);
	}
	
	@Transactional
	@Override
	public int insertSendingRequest(SendingRequestVO srVO, String ip) {
		// TODO Auto-generated method stub
		
		int dupli = deliDao.dupliSendingReq(srVO);
		
		if(dupli > 0) {
			return 0;
			
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = sdf.format(new Date());
			
			List<OrderHistoryVO> ohList = deliDao.selectOrderPkByInvoiceNumber(srVO);
			
			for(OrderHistoryVO ohVO : ohList) {
				ohVO.setOhIp(ip);
				ohVO.setOhAdmin(srVO.getSrAdminId());
				ohVO.setOhEndPoint("출고 - 발송처리");
				ohVO.setOhRegdate(today);
				ohVO.setOhDetail("강제출고 요청 [ "+srVO.getSrReason()+" ]");
				
				logService.insertOrderHistory(ohVO);
			}
			
			
			return deliDao.insertSendingRequest(srVO);
			
		}
		
	}

	@Override
	public int checkSendingRequest(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return deliDao.checkSendingRequest(srVO);
	}

	@Override
	@Transactional
	public int sendingRequestFinished(SendingRequestVO srVO) {
		// TODO Auto-generated method stub

			int result = deliDao.sendingOrders(srVO);
			
			if(result > 0) {
				
				return 0;
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				srVO.setSrRegdate(sdf.format(new Date()));
				
				return deliDao.sendingRequestFinished(srVO);
			}
		
	}

	@Override
	public List<SendingRequestVO> selectSendingRequestNotChecked() {
		// TODO Auto-generated method stub
		return deliDao.selectSendingRequestNotChecked();
	}

	@Override
	public List<SendingRequestVO> selectSendingRequestNotSending() {
		// TODO Auto-generated method stub
		return deliDao.selectSendingRequestNotSending();
	}

	@Override
	public List<SendingRequestVO> selectAllSedingRequest() {
		// TODO Auto-generated method stub
		return deliDao.selectAllSedingRequest();
	}

	@Override
	public List<OrdersVO> selectSendingResults(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return deliDao.selectSendingResults(ssVO);
	}

	@Override
	public List<OrdersVO> godomallAutoSendingTarget(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return deliDao.godomallAutoSendingTarget(ssVO);
	}

	@Override
	public int nonPickingCount() {
		// TODO Auto-generated method stub
		return deliDao.nonPickingCount();
	}
	
}
