package com.gogi.proj.product.cost_io.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;

@Repository
public class CostIoDAOMybatis extends SqlSessionDaoSupport implements CostIoDAO{

	private String namespace = "cost.cost_io";

	@Override
	public int insertCostInputData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertCostInputData", costIoVO);
	}

	@Override
	public CostIoVO selectCostIoData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectCostIoData", costIoVO);
	}

	@Override
	public int updateCostIoData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoData", costIoVO);
	}

	@Override
	public int updateCostDetailPrice(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostDetailPrice", costIoVO);
	}

	@Override
	public int deleteCostIo(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteCostIo", costIoVO);
	}

	@Override
	public List<CostIoVO> selectCostIOCanOutputProductList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostIOCanOutputProductList", osVO);
	}

	@Override
	public List<CostDetailVO> selectCostInputList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostInputList", osVO);
	}

	@Override
	public List<CostDetailVO> selectCostDetailCode() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostDetailCode");
	}

	@Override
	public int updateCostIoOutputFlagAllZero(CostIoVO ciVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoOutputFlagAllZero", ciVO);
	}

	@Override
	public int updateCostIoOutputFlagPosib(CostIoVO ciVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoOutputFlagPosib", ciVO);
	}
}
