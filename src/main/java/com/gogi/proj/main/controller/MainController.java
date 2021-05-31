package com.gogi.proj.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.analytics.model.AnalyticsService;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;

@Controller
@RequestMapping("/main")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private CostIoService ciService;
	
	@Autowired
	private AnalyticsService analyService;
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String Home( Model model) {
		
		OrderSearchVO osVO = new OrderSearchVO();
		
		osVO.setMinNum(0);
		osVO.setMaxNum(20);
		
		List<CostDetailVO> costInputList = ciService.selectCostInputList(osVO);
		List<Map<String, Object>> deliveryList = analyService.selectTodayDeliveryCount();
		List<Map<String, Object>> deliveryResult = analyService.selectMainDeliveryResult();
		List<ProductOptionVO> productOptionList = stockService.productOptionStockAlarm();
		
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("costInputList", costInputList);
		model.addAttribute("deliveryResult", deliveryResult);
		model.addAttribute("productOptionList", productOptionList);
		
		return "menu/main";
		
	}
}
