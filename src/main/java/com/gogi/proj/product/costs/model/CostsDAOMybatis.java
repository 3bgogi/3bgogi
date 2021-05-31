package com.gogi.proj.product.costs.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.product.cost.vo.CostsVO;

@Repository
public class CostsDAOMybatis extends SqlSessionDaoSupport implements CostsDAO{
	
	private String namespace = "cost.costs";

	@Override
	public List<CostsVO> selectCostsGroupBYTotalPriceResult() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostsGroupBYTotalPriceResult");
	}

	@Override
	public List<CostsVO> selectCostDetailByCostsData(CostsVO costsVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostDetailByCostsData", costsVO);
	}


}
