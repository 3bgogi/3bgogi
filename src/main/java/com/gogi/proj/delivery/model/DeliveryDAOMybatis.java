package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DeliveryDAOMybatis extends SqlSessionDaoSupport implements DeliveryDAO{

	private String namespace = "delivery";
	private String sendingReq = "delivery.sending_request";

	@Override
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDelivTargetByOrDeliveryInvoiceNumber",orVO);
	}

	@Override
	public int updateOrderSendingDay(Map<String, Object> orderInfo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateOrderSendingDay", orderInfo);
	}

	@Override
	public int updateDeliveryOutPutOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateDeliveryOutPutOrder", orVO);
	}

	@Override
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateDeliveryOutPutCancled", osVO);
	}

	@Override
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectStoreSendingResultByDate", osVO);
	}

	@Override
	public int storeSendingFinished(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".storeSendingFinished", osVO);
	}
	
	@Override
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSendingExcel", ssVO);
	}

	@Override
	public List<Map<String, Object>> selectSendingExcelEmall(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSendingExcelEmall", ssVO);
	}
	
	@Override
	public List<OrderHistoryVO> selectDeliveryOutPutCancledTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDeliveryOutPutCancledTarget", osVO);
	}

	@Override
	public int insertSendingRequest(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(sendingReq+".insertSendingRequest", srVO);
	}

	@Override
	public int checkSendingRequest(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sendingReq+".checkSendingRequest", srVO);
	}

	@Override
	public int sendingRequestFinished(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sendingReq+".sendingRequestFinished", srVO);
	}

	@Override
	public List<SendingRequestVO> selectSendingRequestNotChecked() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sendingReq+".selectSendingRequestNotChecked");
	}

	@Override
	public List<SendingRequestVO> selectSendingRequestNotSending() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sendingReq+".selectSendingRequestNotSending");
	}

	@Override
	public List<SendingRequestVO> selectAllSedingRequest() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sendingReq+".selectAllSedingRequest");
	}

	@Override
	public int sendingOrders(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sendingReq+".sendingOrders", srVO);
	}

	@Override
	public int updateSendingDone(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sendingReq+".updateSendingDone", orVO);
	}

	@Override
	public int dupliSendingReq(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sendingReq+".dupliSendingReq", srVO);
	}

	@Override
	public int deleteSendingReq(RegDataVO regVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(sendingReq+".deleteSendingReq", regVO);
	}

	@Override
	public List<OrdersVO> selectSendingResults(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSendingResults", ssVO);
	}

	@Override
	public List<OrdersVO> godomallAutoSendingTarget(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".godomallAutoSendingTarget", ssVO);
	}

	@Override
	public int nonPickingCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".nonPickingCount");
	}

	@Override
	public List<OrderHistoryVO> selectOrderPkByInvoiceNumber(SendingRequestVO srVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrderPkByInvoiceNumber",srVO);
	}
}
