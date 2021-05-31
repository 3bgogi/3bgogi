package com.gogi.proj.orders.config.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.config.vo.ExceptAddressKeywordVO;
import com.gogi.proj.orders.config.vo.OrdersDeleteVO;
import com.gogi.proj.orders.config.vo.ReqFilterKeywordVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class OrderConfigDAOMybatis extends SqlSessionDaoSupport implements OrderConfigDAO{

	// 주문서에서 특정 키워드가 포함된 주소를 걸러냄
	private String exceptAddressKeywordNamespace = "order.config.except_address_keyword";
	private String reqFilterKeywordNamespace = "order.config.req_filter_keyword";
	private String orderConfigDivNamespace = "order.config.div";
	private String orderConfigDelete = "order.config.delete";

	@Override
	public int insertExceptAddressKeyword(ExceptAddressKeywordVO eakVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(exceptAddressKeywordNamespace+".insertExceptAddressKeyword", eakVO);
	}

	@Override
	public List<ExceptAddressKeywordVO> selectExceptAddressKeyword() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(exceptAddressKeywordNamespace+".selectExceptAddressKeyword");
	}

	@Override
	public int deleteExceptAddressKeyword(ExceptAddressKeywordVO eakVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(exceptAddressKeywordNamespace+".deleteExceptAddressKeyword", eakVO);
	}

	@Override
	public List<OrdersVO> exceptAddrTargetOrder(List<ExceptAddressKeywordVO> eakList) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(exceptAddressKeywordNamespace+".exceptAddrTargetOrder", eakList);
	}

	@Override
	public int updateSpecialRegionOrder(List<Integer> orList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(exceptAddressKeywordNamespace+".updateSpecialRegionOrder", orList);
	}

	@Override
	public int searchEceptAddrAndUpdateCheckFlag(List<ExceptAddressKeywordVO> eakList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(exceptAddressKeywordNamespace+".searchEceptAddrAndUpdateCheckFlag", eakList);
	}

	@Override
	public int insertReqFilterKeyword(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(reqFilterKeywordNamespace+".insertReqFilterKeyword", rfkVO);
	}

	@Override
	public List<ReqFilterKeywordVO> selectAllReqFilterKeywordList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(reqFilterKeywordNamespace+".selectAllReqFilterKeywordList");
	}

	@Override
	public List<ReqFilterKeywordVO> selectUseReqFilterKeywordList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(reqFilterKeywordNamespace+".selectUseReqFilterKeywordList");
	}

	@Override
	public int deleteReqFilterKeywordByPk(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(reqFilterKeywordNamespace+".deleteReqFilterKeywordByPk", rfkVO);
	}

	@Override
	public int updateReqFilterKeywordUseOrUnuse(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(reqFilterKeywordNamespace+".updateReqFilterKeywordUseOrUnuse", rfkVO);
	}

	@Override
	public List<OrdersVO> selectPackingIrreTargetOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderConfigDivNamespace+".selectPackingIrreTargetOrder");
	}

	@Override
	public List<OrdersVOList> selectPackingIrreTargetOrderList(List<OrdersVO> orVoList) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderConfigDivNamespace+".selectPackingIrreTargetOrderList", orVoList);
	}

	@Override
	public int selectPackingIrreTargetOrderCounting() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderConfigDivNamespace+".selectPackingIrreTargetOrderCounting");
	}

	@Override
	public int insertDeleteOrders(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderConfigDelete+".insertDeleteOrders", orVO);
	}

	@Override
	public List<OrdersDeleteVO> selectOrdersDeleteList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderConfigDelete+".selectOrdersDeleteList", osVO);
	}

	@Override
	public int selectOrdersDeleteListCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderConfigDelete+".selectOrdersDeleteListCounting", osVO);
	}
	
	
	
}
