package com.gogi.proj.configurations.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.BlockSendingListVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class ConfigurationDAOMybatis extends SqlSessionDaoSupport implements ConfigurationDAO{

	private String namespace = "configuration.store";

	private String blockSendingList = "order.config.block_sending_list";
	
	private String eventMsg = "order.config.event_msg";
	
	@Override
	public int addStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".addStoreSection", ssVO);
	}

	@Override
	public List<StoreSectionVO> selectStoreSectionList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectStoreSectionList");
	}

	@Override
	public int increaseStoreSectionSpecialNumber(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".increaseStoreSectionSpecialNumber", ssVO);
	}

	@Override
	public StoreSectionVO selectStoreSectionBySspk(int ssPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStoreSectionBySspk", ssPk);
	}

	@Override
	public int updateStoreSendingForm(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreSendingForm", ssVO);
	}

	@Override
	public int updateStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreSection", ssVO);
	}

	@Override
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStoreMerge", ssVO);
	}

	@Override
	public int updateStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreMerge", ssVO);
	}

	@Override
	public int insertBlockSendingList(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(blockSendingList+".insertBlockSendingList", bslVO);
	}

	@Override
	public List<BlockSendingListVO> selectBlockSendingList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(blockSendingList+".selectBlockSendingList", osVO);
	}

	@Override
	public int deleteBlockSendingList(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(blockSendingList+".deleteBlockSendingList", bslVO);
	}

	@Override
	public int selectBlockSendingListCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(blockSendingList+".selectBlockSendingListCount", osVO);
	}

	@Override
	public int selectBlockSendingListDupli(BlockSendingListVO bslVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(blockSendingList+".selectBlockSendingListDupli", bslVO);
	}

	@Override
	public List<OrdersVO> selectEventMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(eventMsg+".selectEventMsgTarget", osVO);
	}

	@Override
	public List<BlockSendingListVO> selectAllBlockSendingList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(blockSendingList+".selectAllBlockSendingList");
	}

	@Override
	public int selectEventMsgTargetCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(eventMsg+".selectEventMsgTargetCounting", osVO);
	}

	@Override
	public List<OrdersVO> selectAllEventMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(eventMsg+".selectAllEventMsgTarget", osVO);
	}

	@Override
	public List<OrdersVO> selectEventMsgProductKeyword(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(eventMsg+".selectEventMsgProductKeyword", osVO);
	}

	@Override
	public List<StoreSectionVO> storeListOrderInTwoMonth() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".storeListOrderInTwoMonth");
	}
	
}
