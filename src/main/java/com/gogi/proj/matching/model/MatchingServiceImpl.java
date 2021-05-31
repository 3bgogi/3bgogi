package com.gogi.proj.matching.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.matching.vo.OptionMatchingVO;
import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class MatchingServiceImpl implements MatchingService{

	private static final Logger logger = LoggerFactory.getLogger(MatchingServiceImpl.class);
	
	@Autowired
	private MatchingDAO matchingDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private LogService logService;

	//미매칭 주문 건 매칭쪽에서 상품명을 검색하여 고유값 획득 뒤 매칭시켜주기
	
	@Transactional
	@Override
	public int[] matchingsProductAndOrders(String ip, String adminId) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdf.format(new Date());
		
		int successedResult = 0;
		int notMatchingedResult = 0;
		int pmPk = 0;
		List<OrdersVO> notMatchingedOrderList = matchingDAO.selectOrdersNotMatchinged();
		OrderHistoryVO ohVO = null;
		
		for(int i = 0; i < notMatchingedOrderList.size(); i++) {
			
			try {
				pmPk = matchingDAO.selectProductMatchingPk(notMatchingedOrderList.get(i));
			}catch(NullPointerException e) {
				pmPk = 0;
			}
			
			
			if(pmPk == 0) {
				notMatchingedResult++;
				continue;
				
			}else {
				notMatchingedOrderList.get(i).setPmFk(pmPk);
				int results = matchingDAO.matchingProductForOrders(notMatchingedOrderList.get(i));
				ohVO = new OrderHistoryVO();
				ohVO.setOhAdmin(adminId);
				ohVO.setOhIp(ip);
				ohVO.setOrFk(notMatchingedOrderList.get(i).getOrPk());
				ohVO.setOhEndPoint("매칭");
				ohVO.setOhRegdate(today);
				ohVO.setOhDetail("상품 매칭 완료");
				successedResult+=logService.insertOrderHistory(ohVO);
			}
			
		}
		
		int [] result = {successedResult, notMatchingedResult};
		
		
		/*//원가 매칭
		List<OrdersVO> notMatchinedCostList = ordersDAO.selectNotMatchingedCostData();
		
		if(notMatchinedCostList.size() != 0) {
			for(int i= 0; i < notMatchinedCostList.size(); i++) {
				ordersDAO.updateOrderCostsData(notMatchinedCostList.get(i));
			}
		}*/
		
		
		return result;
	}

	@Override
	public int insertProductMatching(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return matchingDAO.insertProductMatching(pmVO);
	}

	@Override
	public List<OrdersVO> selectOrdersOptionNotMatchinged(OrderSearchVO osc) {
		// TODO Auto-generated method stub
		return matchingDAO.selectOrdersOptionNotMatchinged(osc);
	}

	@Override
	public int countingNotOptionMatchingedOrders(OrderSearchVO osc) {
		// TODO Auto-generated method stub
		return matchingDAO.countingNotOptionMatchingedOrders(osc);
	}

	@Override
	public int insertOptionMatchingListData(List<OptionMatchingVO> optionMatchingVOList) {
		// TODO Auto-generated method stub
		int insertResult = 0;
		
		for( OptionMatchingVO vo : optionMatchingVOList) {
			
			if(vo.getPmFk() != 0) {
				
				//중복 여부 확인하기
				int dupli = matchingDAO.optionMatchingDupliCheck(vo);
				if(dupli == 0) {
					matchingDAO.insertOptionMatchingData(vo);
					insertResult++;
					
				}
				
			}
		}
		
		return insertResult;
	}

	@Override
	public ProductMatchingVO selectProductMatchingByPmPk(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return matchingDAO.selectProductMatchingByPmPk(pmVO);
	}

	@Override
	public int editProductMatching(ProductMatchingVO pmVO) {
		// TODO Auto-generated method stub
		return matchingDAO.editProductMatching(pmVO);
	}

	@Override
	public List<OptionMatchingVO> selectOptionMatchings(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return matchingDAO.selectOptionMatchings(omVO);
	}

	@Override
	public int deleteOptionMatching(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return matchingDAO.deleteOptionMatching(omVO);
	}

	@Override
	public int insertOptionMatching(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		
		//중복 여부 체크
		int dupli = matchingDAO.optionMatchingDupliCheck(omVO);
		
		int result = 0;
		
		if(dupli == 0) {
			result = matchingDAO.insertOptionMatchingData(omVO);
			
		}else {
			result= -1;
			
		}
		
		return result;
	}

	@Override
	public int optionMatchingDupliCheck(OptionMatchingVO omVO) {
		// TODO Auto-generated method stub
		return matchingDAO.optionMatchingDupliCheck(omVO);
	}

	@Override
	public int selectOrderMatchingCounting(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return matchingDAO.selectOrderMatchingCounting(orVO);
	}

	@Override
	public int selectOrderMatchingIncMeatCounting(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return matchingDAO.selectOrderMatchingIncMeatCounting(orVO);
	}

}
