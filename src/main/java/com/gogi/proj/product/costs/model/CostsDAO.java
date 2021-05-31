package com.gogi.proj.product.costs.model;

import java.util.List;

import com.gogi.proj.product.cost.vo.CostsVO;

public interface CostsDAO {

	public List<CostsVO> selectCostsGroupBYTotalPriceResult();
	
	public List<CostsVO> selectCostDetailByCostsData(CostsVO costsVO);
}
