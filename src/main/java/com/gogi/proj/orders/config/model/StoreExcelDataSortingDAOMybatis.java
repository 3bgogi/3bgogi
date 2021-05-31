package com.gogi.proj.orders.config.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.vo.StoreCancleExcelDataSortingVO;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;

@Repository
public class StoreExcelDataSortingDAOMybatis extends SqlSessionDaoSupport implements StoreExcelDataSortingDAO{

	private String namespace = "order.config.store_excel_data_sorting";
	private String cancleNamespace = "order.config.store_cancle_excel_data_sorting";

	@Override
	public int insertStoreExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertStoreExcelDataSorting", ssVO);
	}

	@Override
	public StoreExcelDataSortingVO selectStoreExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStoreExcelDataSorting", ssVO);
	}

	@Override
	public int updateStoreExcelDataSorting(StoreExcelDataSortingVO sedsVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreExcelDataSorting", sedsVO);
	}

	@Override
	public int insertStoreCancleExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(cancleNamespace+".insertStoreCancleExcelDataSorting", ssVO);
	}

	@Override
	public StoreCancleExcelDataSortingVO selectStoreCancleExcelDataSorting(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(cancleNamespace+".selectStoreCancleExcelDataSorting", ssVO);
	}

	@Override
	public int updateStoreCancleExcelDataSorting(StoreCancleExcelDataSortingVO scedsVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(cancleNamespace+".updateStoreCancleExcelDataSorting", scedsVO);
	}

	@Override
	public List<OrdersVO> cancledOrderSearch(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(cancleNamespace+".cancledOrderSearch", orVO);
	}

	@Override
	public int updateCancledOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(cancleNamespace+".updateCancledOrder", orVO);
	}
}
