package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.analytics.vo.LocalAreaVO;
import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{

	@Autowired
	private AnalyticsDAO analyDAO;
	
	@Override
	public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysTotalSalesWithoutCommision(osVO);
	}

	@Override
	public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysTotalSales(osVO);
	}

	@Override
	public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysSendingOut(osVO);
	}

	@Override
	public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sixMonthTotalSales(osVO);
	}

	@Override
	public List<OrdersVO> selectSevenDaysOutPutProductQty() {
		// TODO Auto-generated method stub
		return analyDAO.selectSevenDaysOutPutProductQty();
	}

	@Override
	public List<Map<String, Object>> selectTodayDeliveryCount() {
		// TODO Auto-generated method stub
		return analyDAO.selectTodayDeliveryCount();
	}

	@Override
	public List<Map<String, Object>> selectMainDeliveryResult() {
		// TODO Auto-generated method stub
		return analyDAO.selectMainDeliveryResult();
	}

	@Override
	public List<Map<String, Object>> selectPriceChartInsert() {
		// TODO Auto-generated method stub
		return analyDAO.selectPriceChartInsert();
	}

	@Override
	public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectAnalyDataList(osVO);
	}

	@Override
	public List<Map<String, Object>> selectReservProductQty(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectReservProductQty(osVO);
	}

	@Override
	public List<Map<String, Object>> selectReservProductQtyInMonth(DatesVO datesVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectReservProductQtyInMonth(datesVO);
	}

	@Override
	public List<OrdersVO> selectTotalSalesByDates(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectTotalSalesByDates(osVO);
	}

	@Override
	public List<OrdersVO> selectCancledSalesByDates(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectCancledSalesByDates(osVO);
	}

	@Override
	public List<OrdersVO> selectLocalAreaAnalytics(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectLocalAreaAnalytics(osVO);
	}

	@Override
	public List<LocalAreaVO> sleectLocalAreaTopProducts(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sleectLocalAreaTopProducts(osVO);
	}

	@Override
	public LocalAreaVO localAreaAnlayDetail(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.localAreaAnlayDetail(osVO);
	}

	@Override
	public List<LocalAreaVO> selectLocalAreaInflowRoute(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectLocalAreaInflowRoute(osVO);
	}

}
