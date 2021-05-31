package com.gogi.proj.log.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.log.vo.ProdQtyLogVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class LogDAOMybatis extends SqlSessionDaoSupport implements LogDAO{

	private String orderHistoryNs = "log.order_history";
	private String prodQtyLogNs = "log.prod_qty_log";

	@Override
	public int insertOrderHistory(OrderHistoryVO ohVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderHistoryNs+".insertOrderHistory",ohVO);
	}

	@Override
	public List<OrderHistoryVO> selectOrderHistoryByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderHistoryNs+".selectOrderHistoryByOrPk", orVO);
	}

	@Override
	public List<OrderHistoryVO> selectOrderPkBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderHistoryNs+".selectOrderPkBySerialSpecialNumber", orVO);
	}

	@Override
	public OrdersVO selectOrdersChangeBeforeValueByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderHistoryNs+".selectOrdersChangeBeforeValueByOrPk", orVO);
	}

	@Override
	public List<OrdersVO> selectOrdersChangeBeforeValueBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderHistoryNs+".selectOrdersChangeBeforeValueBySerialSpecialNumber", orVO);
	}

	@Override
	public List<ProdQtyLogVO> selectProdQtyLog(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(prodQtyLogNs+".selectProdQtyLog", osVO);
	}

	@Override
	public int selectProdQtyLogCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(prodQtyLogNs+".selectProdQtyLogCount", osVO);
	}
	
	
}
