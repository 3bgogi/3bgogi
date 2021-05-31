package com.gogi.proj.log.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.log.util.LogUtil;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.log.vo.ProdQtyLogVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private LogDAO logDao;

	@Transactional
	@Override
	public int changeOrderHistory(OrdersVO updateOrder, boolean orPkExist, String accessIp, String adminId, String accessPage, String regdate) {
		// TODO Auto-generated method stub
		int result = 0;
		
			//orPk가 들어가 있는 주문서라면
		if(orPkExist == true) {
			
			OrdersVO originalVO = logDao.selectOrdersChangeBeforeValueByOrPk(updateOrder);
			
			OrderHistoryVO ohVO = new OrderHistoryVO();
			ohVO.setOrFk(updateOrder.getOrPk());
			ohVO.setOhIp(accessIp);
			ohVO.setOhAdmin(adminId);
			ohVO.setOhEndPoint(accessPage);
			ohVO.setOhRegdate(regdate);
			ohVO.setOhDetail(LogUtil.logChangeCheck(originalVO, updateOrder));
			
			result += logDao.insertOrderHistory(ohVO);
			
			//orPk가 없고 주문분리번호값이 있는 경우
		}else {
			// List<OrderHistoryVO> ohList = logDao.selectOrderPkBySerialSpecialNumber(orVO);
			OrderHistoryVO ohVO = null;
			List<OrdersVO> changeOrderList = logDao.selectOrdersChangeBeforeValueBySerialSpecialNumber(updateOrder);
			for(OrdersVO changeOrder : changeOrderList) {
				ohVO = new OrderHistoryVO();
				 
				ohVO.setOrFk(changeOrder.getOrPk());
				ohVO.setOhIp(accessIp);
				ohVO.setOhAdmin(adminId);
				ohVO.setOhEndPoint(accessPage);
				ohVO.setOhRegdate(regdate);
				ohVO.setOhDetail(LogUtil.logChangeCheck(changeOrder, updateOrder));
				
				result += logDao.insertOrderHistory(ohVO);
			}
		}
		
		return result;
	}

	@Override
	public List<OrderHistoryVO> selectOrderHistoryByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return logDao.selectOrderHistoryByOrPk(orVO);
	}

	@Override
	public int insertOrderHistory(OrderHistoryVO ohVO) {
		// TODO Auto-generated method stub
		return logDao.insertOrderHistory(ohVO);
	}

	@Override
	public List<ProdQtyLogVO> selectProdQtyLog(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return logDao.selectProdQtyLog(osVO);
	}

	@Override
	public int selectProdQtyLogCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return logDao.selectProdQtyLogCount(osVO);
	}
}
