package com.gogi.proj.configurations.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.configurations.vo.BlockSendingListVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.model.StoreExcelDataSortingDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);
	
	@Autowired
	private ConfigurationDAO configurationDao;
	
	@Autowired
	private StoreExcelDataSortingDAO sedsDao;

	@Override
	@Transactional
	public int addStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		result += configurationDao.addStoreSection(ssVO);
		
		if(ssVO.getSsPk() > 0) {
			result += sedsDao.insertStoreExcelDataSorting(ssVO);
			result += sedsDao.insertStoreCancleExcelDataSorting(ssVO);
		}
		
		return result;
	}

	@Override
	public List<StoreSectionVO> selectStoreSectionList() {
		// TODO Auto-generated method stub
		return configurationDao.selectStoreSectionList();
		
	}

	@Override
	public StoreSectionVO selectStoreSectionBySspk(int ssPk) {
		// TODO Auto-generated method stub
		return configurationDao.selectStoreSectionBySspk(ssPk);
	}

	@Override
	public int updateStoreSendingForm(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return configurationDao.updateStoreSendingForm(ssVO);
	}

	@Override
	public int updateStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return configurationDao.updateStoreSection(ssVO);
	}

	@Override
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectStoreMerge(ssVO);
	}

	@Override
	public int updateStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return configurationDao.updateStoreMerge(ssVO);
	}

	@Override
	public int insertBlockSendingList(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return configurationDao.insertBlockSendingList(bslVO);
	}

	@Override
	public List<BlockSendingListVO> selectBlockSendingList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectBlockSendingList(osVO);
	}

	@Override
	public int deleteBlockSendingList(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return configurationDao.deleteBlockSendingList(bslVO);
	}

	@Override
	public int selectBlockSendingListCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectBlockSendingListCount(osVO);
	}

	@Override
	public int selectBlockSendingListDupli(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectBlockSendingListDupli(bslVO);
	}

	@Override
	public List<OrdersVO> selectEventMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectEventMsgTarget(osVO);
	}

	@Override
	public List<BlockSendingListVO> selectAllBlockSendingList() {
		// TODO Auto-generated method stub
		return configurationDao.selectAllBlockSendingList();
	}

	@Override
	public int selectEventMsgTargetCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectEventMsgTargetCounting(osVO);
	}

	@Override
	public List<OrdersVO> selectAllEventMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectAllEventMsgTarget(osVO);
	}

	@Override
	public List<OrdersVO> selectEventMsgProductKeyword(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return configurationDao.selectEventMsgProductKeyword(osVO);
	}

	@Override
	public List<StoreSectionVO> storeListOrderInTwoMonth() {
		// TODO Auto-generated method stub
		return configurationDao.storeListOrderInTwoMonth();
	}
	
}
