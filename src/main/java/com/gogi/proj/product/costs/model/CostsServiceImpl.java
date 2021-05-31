package com.gogi.proj.product.costs.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.product.cost.vo.CostsVO;

@Service
public class CostsServiceImpl implements CostsService{

	@Autowired
	private CostsDAO costsDao;
	
	@Override
	public List<CostsVO> selectCostsGroupBYTotalPriceResult() {
		// TODO Auto-generated method stub
		return costsDao.selectCostsGroupBYTotalPriceResult();
	}

	@Override
	public List<CostsVO> selectCostDetailByCostsData(CostsVO costsVO) {
		// TODO Auto-generated method stub
		return costsDao.selectCostDetailByCostsData(costsVO);
	}

}
