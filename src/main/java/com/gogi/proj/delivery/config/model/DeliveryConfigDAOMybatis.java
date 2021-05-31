package com.gogi.proj.delivery.config.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.delivery.config.vo.DelivImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivCommonImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DeliveryConfigDAOMybatis extends SqlSessionDaoSupport implements DeliveryConfigDAO {

	private String namespace = "delivery.config";
	private String deliveryCheck = "delivery.check";

	@Override
	public List<EarlyDelivTypeVO> earlyDelivType() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".earlyDelivType");
	}

	@Override
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(deliveryCheck+".selectDelivNumCheckTarget", osVO);
	}

	@Override
	public int delivAreaCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".delivAreaCount", osVO);
	}

	@Override
	public List<EarlyDelivTypeVO> delivArea(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".delivArea", osVO);
	}

	@Override
	public boolean earlyAreaZipcodeDupliCheck(EarlyDelivAreaVO eda) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".earlyAreaZipcodeDupliCheck", eda);
	}

	@Override
	public int insertEarlyAreaZipcCode(EarlyDelivAreaVO eda) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertEarlyAreaZipcCode", eda);
	}

	@Override
	public List<EarlyDelivAreaVO> selectDelivPosArea(EarlyDelivAreaVO eda) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDelivPosArea", eda);
	}

	@Override
	public int insertDelivImposKeyword(DelivImposVO diVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertDelivImposKeyword", diVO);
	}

	@Override
	public int deleteDelivImpos(DelivImposVO diVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteDelivImpos", diVO);
	}

	@Override
	public List<EarlyDelivCommonImposVO> selectEarlyDelivCommonImposList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectEarlyDelivCommonImposList", osVO);
	}

	@Override
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteEarlyDelivArea", edaVO);
	}
	
	
}
