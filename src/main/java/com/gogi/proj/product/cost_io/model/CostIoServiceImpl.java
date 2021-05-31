package com.gogi.proj.product.cost_io.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;

@Service
public class CostIoServiceImpl implements CostIoService{

	@Autowired
	private CostIoDAO costIoDao;

	@Override
	@Transactional
	public int insertCostInputData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		int result = costIoDao.updateCostDetailPrice(costIoVO);
		
		if(result > 0) {
			result = costIoDao.insertCostInputData(costIoVO);
			
		}else {
			result = 0;
			
		}
		
		return result;
	}

	@Override
	public CostIoVO selectCostIoData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return costIoDao.selectCostIoData(costIoVO);
	}

	@Override
	public int updateCostIoData(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return costIoDao.updateCostIoData(costIoVO);
	}

	@Override
	public int deleteCostIo(CostIoVO costIoVO) {
		// TODO Auto-generated method stub
		return costIoDao.deleteCostIo(costIoVO);
	}

	@Override
	public List<CostIoVO> selectCostIOCanOutputProductList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costIoDao.selectCostIOCanOutputProductList(osVO);
	}

	@Override
	public List<CostDetailVO> selectCostInputList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costIoDao.selectCostInputList(osVO);
	}

	@Override
	public List<CostDetailVO> selectCostDetailCode() {
		// TODO Auto-generated method stub
		return costIoDao.selectCostDetailCode();
	}

	@Override
	@Transactional
	public int chooseCostIo(CostIoVO ciVO) {
		// TODO Auto-generated method stub
		
		int result = costIoDao.updateCostIoOutputFlagAllZero(ciVO);
		
		if(result > 0) {
			return costIoDao.updateCostIoOutputFlagPosib(ciVO);
			
		}else {
			return 0;
		}
	}
}
