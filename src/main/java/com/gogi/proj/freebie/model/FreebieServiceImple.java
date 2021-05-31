package com.gogi.proj.freebie.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.freebie.vo.FreebieCheckVO;
import com.gogi.proj.freebie.vo.FreebieVO;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Service
public class FreebieServiceImple implements FreebieService{

	@Autowired
	private FreebieDAO fbDao;
	
	@Autowired
	private OrdersDAO orDao;

	@Override
	public int insertFreebie(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return fbDao.insertFreebie(fbVO);
	}

	@Override
	public List<FreebieVO> selectFreebieList() {
		// TODO Auto-generated method stub
		return fbDao.selectFreebieList();
	}

	@Override
	public FreebieVO selectFreebieByFbpk(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return fbDao.selectFreebieByFbpk(fbVO);
	}

	@Override
	@Transactional
	public int selectFreebieTargetOrder(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		
		String now = sdf.format(ts);
		
		List<OrdersVO> orList = fbDao.selectFreebieTargetOrder(fbVO);
		int freebieCount = 0;
		
		FreebieCheckVO fcVO = null;
		
		int addResult = 0;
		
		int qty = 0;
		
		for(int i=0; i < orList.size(); i++) {
			
			orList.get(i).setOrSerialSpecialNumber(orList.get(i).getOrSerialSpecialNumber().split(",")[0]);
			//주문 개수 대로 들어감
			if(fbVO.getFbAddType() == 0) {
				qty = orList.get(i).getOrAmount();
				
				orList.get(i).setOrAmount(qty);
			
			// 임의로 정의한 개수대로 들어감
			}else if(fbVO.getFbAddType() == 1) {
				qty = fbVO.getFbAddQty();
				
				orList.get(i).setOrAmount(qty);
				
			//주문수량 X 개수로 들어감
			}else if((fbVO.getFbAddType() == 2)) {
				qty = orList.get(i).getOrAmount() * fbVO.getFbAddQty();
				
				orList.get(i).setOrAmount(qty);
				
			//조건이 맞을 경우 배수로 들어감
			}else if(fbVO.getFbAddType() == 3) {
				
				if(fbVO.isFbTotalQtyFlag() == false) {
					int targetPrice = orList.get(i).getOrTotalPrice();

					qty = (int)Math.floor(targetPrice / fbVO.getFbMinPrice())* fbVO.getFbAddQty();
					
				}else {
					int targetQty = orList.get(i).getOrAmount();
					
					qty = (int)Math.floor(targetQty / fbVO.getFbMinTotalQty() )* fbVO.getFbAddQty();
				}
				

				if(qty > 0) {					
					orList.get(i).setOrAmount(qty);
				}
				
			}
			
			orList.get(i).setOrProduct(fbVO.getProductName());
			orList.get(i).setOrProductType("사은품");
			orList.get(i).setOrProductOption(fbVO.getOptionName());
			orList.get(i).setOrProductOrderNumber("사은품-"+now+"-"+freebieCount);
			orList.get(i).setOrProductNumber("");
			orList.get(i).setOrProductPrice(0);
			orList.get(i).setOrProductOptionPrice(0);
			orList.get(i).setOrDiscountPrice(0);
			orList.get(i).setOrTotalPrice(0);
			orList.get(i).setOrPaymentCommision(0);
			orList.get(i).setOrAnotherPaymentCommision(0);
			
			
			
			if(qty > 0) {	
				fcVO = new FreebieCheckVO();
				fcVO.setFbFk(fbVO.getFbPk());
				fcVO.setFcOrderTarget(orList.get(i).getSsName());
				fcVO.setFcType(fbVO.getFbType());
				
				int dupliResult = fbDao.selectFreebieDupliCheck(fcVO);
				
				if( dupliResult == 0) {					
					int orInputResult = orDao.insertAddOrderData(orList.get(i));
					
					if(orInputResult != 0) {
						addResult+= fbDao.insertFreebieCheck(fcVO);
						
					}
				}
			}
			
			
			freebieCount++;
			qty=0;
		}
		
		return addResult;
	}

	@Override
	public int updateFreebieByPk(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return fbDao.updateFreebieByPk(fbVO);
	}

	@Override
	public List<FreebieVO> selectFreebies(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return fbDao.selectFreebies(osVO);
	}

	@Override
	public int selectFreebieCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return fbDao.selectFreebieCount(osVO);
	}

	@Override
	public int deleteFreebie(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return fbDao.deleteFreebie(fbVO);
	}
	
}
