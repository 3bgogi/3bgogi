package com.gogi.proj.orders.config.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.vo.StoreCancleExcelDataSortingVO;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.stock.model.StockService;

@Service
public class StoreExcelDataSortingServiceImpl implements StoreExcelDataSortingService{

	@Autowired
	private StoreExcelDataSortingDAO sedsDao;
	
	@Autowired
	private StockService stockService;

	@Override
	public StoreExcelDataSortingVO selectStoreExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return sedsDao.selectStoreExcelDataSorting(ssVO);
	}

	@Override
	public int updateStoreExcelDataSorting(StoreExcelDataSortingVO sedsVO) {
		// TODO Auto-generated method stub
		return sedsDao.updateStoreExcelDataSorting(sedsVO);
	}

	@Override
	public StoreCancleExcelDataSortingVO selectStoreCancleExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return sedsDao.selectStoreCancleExcelDataSorting(ssVO);
	}

	@Override
	public int updateStoreCancleExcelDataSorting(StoreCancleExcelDataSortingVO scedsVO) {
		// TODO Auto-generated method stub
		return sedsDao.updateStoreCancleExcelDataSorting(scedsVO);
	}

	@Override
	public List<OrdersVO> cancledOrderSearch(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		return sedsDao.cancledOrderSearch(orVO);
	}

	@Override
	@Transactional
	public int updateCancledOrder(List<OrdersVO> orList) {
		// TODO Auto-generated method stub
		
		int updateResult = 0;
		boolean result = false;
		for( OrdersVO orVO : orList) {
			updateResult += sedsDao.updateCancledOrder(orVO);
			result = stockService.updateProductChangeValues(orVO);
			
			if(result == false) return 0;
		}
		
		return updateResult;
	}
	
}
