package com.gogi.proj.epost.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class EpostDAOMybatis extends SqlSessionDaoSupport implements EpostDAO{

	private String epostNameSpace = "epost";

	@Override
	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(epostNameSpace+".selectEpostSendingData", osVO);
	}

	@Override
	public int grantRegiNoByOrPk(RegDataVO regData) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".grantRegiNoByOrPk", regData);
	}

	@Override
	public RegDataVO selectEpostInfoByOrserialspecialnumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(epostNameSpace+".selectEpostInfoByOrserialspecialnumber", orSerialSpecialNumber);
	}

	@Override
	public int deleteDelivInfo(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".deleteDelivInfo", orSerialSpecialNumber);
	}

	@Override
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(epostNameSpace+".selectDontGrantDelivOrderListInMonth", osVO);
	}

	@Override
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(epostNameSpace+".selectDontGrantDelivOrderListInMonthCounting", osVO);
	}

	@Override
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(epostNameSpace+".deliveryPrintTarget", osVO);
	}

	@Override
	public int grantDeliveryInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".grantDeliveryInvoiceNumber", orVO);
	}

	@Override
	public OrdersVO deliveryInvoiceNumberReprinting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(epostNameSpace+".deliveryInvoiceNumberReprinting", osVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(epostNameSpace+".selectDeliveryInvoiceNumberByDate", osVO);
	}

	@Override
	public List<OrdersVO> freshSolutionDelivExcel(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(epostNameSpace+".freshSolutionDelivExcel", osVO);
	}

	@Override
	public int updateFreshSolutionInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".updateFreshSolutionInvoiceNumber", orVO);
	}

	@Override
	public int updateFreshSolutionTarget(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".updateFreshSolutionTarget", orVO);
	}

	@Override
	public int deleteDelivInfoByPk(OrdersVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".deleteDelivInfoByPk", osVO);
	}

	@Override
	public int gtranReceiverPickUp(OrdersVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(epostNameSpace+".gtranReceiverPickUp", osVO);
	}
}
