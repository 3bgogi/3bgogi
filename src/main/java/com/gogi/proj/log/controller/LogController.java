package com.gogi.proj.log.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.log.vo.ProdQtyLogVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/log")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	
	
	/**
	 * 
	 * @MethodName : checkOrderHistory
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 작업 내역 확인
	 */
	@RequestMapping(value="/order_history.do", method=RequestMethod.GET)
	public String checkOrderHistory(@ModelAttribute OrdersVO orVO, Model model) {
		
		List<OrderHistoryVO> ohList = logService.selectOrderHistoryByOrPk(orVO);
		
		model.addAttribute("ohList", ohList);
		
		return "logs/order_history";
	}
	
	
	/**
	 * 
	 * @MethodName : checkProdQtyLog
	 * @date : 2021. 2. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 생산 기록 확인
	 */
	@RequestMapping(value="/prod_qty_log.do", method=RequestMethod.GET)
	public String checkProdQtyLog(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		int totalRecord = logService.selectProdQtyLogCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(30);
			
		}
		
		List<ProdQtyLogVO> pqlList = logService.selectProdQtyLog(osVO);
		
		model.addAttribute("pqlList", pqlList);
		model.addAttribute("osVO", osVO);

		return "logs/prod_qty_log";
	}
	
}
