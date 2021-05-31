package com.gogi.proj.matching.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.matching.vo.OptionMatchingVO;
import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class MatchingDAOMybatis extends SqlSessionDaoSupport implements MatchingDAO{

	private String namespace = "order.matching";

	@Override
	public List<Integer> selectOrdersForMatchingByStoreproductname(String StoreProductName) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrdersForMatchingByStoreproductname", StoreProductName);
	}

	@Override
	public int selectProductMatchingPk(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProductMatchingPk", ordersVO);
	}

	@Override
	public int matchingProductForOrders(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".matchingProductForOrders", ordersVO);
	}

	@Override
	public int insertProductMatching(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertProductMatching", pmVO);
	}

	@Override
	public List<OrdersVO> selectOrdersNotMatchinged() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrdersNotMatchinged");
	}

	@Override
	public List<OrdersVO> selectOrdersOptionNotMatchinged(OrderSearchVO osc) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrdersOptionNotMatchinged", osc);
	}

	@Override
	public int countingNotOptionMatchingedOrders(OrderSearchVO osc) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".countingNotOptionMatchingedOrders", osc);
	}

	@Override
	public int insertOptionMatchingData(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertOptionMatchingData", omVO);
	}

	@Override
	public ProductMatchingVO selectProductMatchingByPmPk(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProductMatchingByPmPk", pmVO);
	}

	@Override
	public int editProductMatching(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".editProductMatching", pmVO);
	}

	@Override
	public List<OptionMatchingVO> selectOptionMatchings(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOptionMatchings", omVO);
	}

	@Override
	public int deleteOptionMatching(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteOptionMatching", omVO);
	}

	@Override
	public int optionMatchingDupliCheck(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".optionMatchingDupliCheck", omVO);
	}

	@Override
	public int selectOrderMatchingCounting(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectOrderMatchingCounting", orVO);
	}

	@Override
	public int selectOrderMatchingIncMeatCounting(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectOrderMatchingIncMeatCounting", orVO);
	}
	
}
