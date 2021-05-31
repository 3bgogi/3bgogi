package com.gogi.proj.matching.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.matching.model.MatchingService;
import com.gogi.proj.matching.vo.OptionMatchingVO;
import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.orders.controller.OrdersController;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.model.ProductsService;
import com.gogi.proj.product.products.vo.ProductsVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/order/matching")
public class MatchingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MatchingController.class);

	@Autowired
	private MatchingService matchingService;
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private OrdersService ordersService;
	
	
	/**
	 * @MethodName : matchingPage
	 * @date : 2019. 6. 11.
	 * @author : Jeon KiChan
	 * @메소드설명 : 주문서와 상품 매칭 페이지 보여주기
	 */
	@RequestMapping(value="/product_matching.do", method=RequestMethod.GET)
	public String matchingPageGet(@ModelAttribute OrdersVO ordersVO, 
									@RequestParam boolean editFlag,
									@ModelAttribute ProductMatchingVO pmVO,
									Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		if(editFlag == true && pmVO.getPmPk() != 0) {
			ProductMatchingVO pmVo = matchingService.selectProductMatchingByPmPk(pmVO);
			model.addAttribute("pmVO", pmVo);
		}
		
		model.addAttribute("editFlag", editFlag);
		model.addAttribute("ordersVO", ordersVO);
		model.addAttribute("ccList", ccList);
		
		return "orders/matching/product_matching";
	}
	
	/**
	 * @MethodName : matchingOptionPageGet
	 * @date : 2019. 6. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 주문서와 옵션 매칭 페이지 보여주기
	 */
	@RequestMapping(value="/options_matching.do", method=RequestMethod.GET)
	public String matchingOptionPageGet(@ModelAttribute OrdersVO ordersVO, Model model, boolean editFlag) {
		
		if(editFlag) {
			OptionMatchingVO omVO = new OptionMatchingVO();
			omVO.setOmStoreOptionName(ordersVO.getOrProductOption());
			omVO.setPmFk(ordersVO.getPmFk());
			
			List<OptionMatchingVO> omList = matchingService.selectOptionMatchings(omVO);
			
			model.addAttribute("omList", omList);
		}
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("editFlag", editFlag);
		model.addAttribute("ordersVO", ordersVO);
		model.addAttribute("ccList", ccList);
		
		return "orders/matching/option_matching";
	}
	
	
	@RequestMapping(value="/products_matching.do", method=RequestMethod.GET)
	public String orderProductMatchingCheck(HttpServletRequest request , @ModelAttribute OrderSearchVO orderSearchVO, Model model) {
		
		if(orderSearchVO.getDateType() == null) {
			orderSearchVO.setDateType("or_regdate");
			orderSearchVO.setDatePeriod(1);
		}
		
		if(orderSearchVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			orderSearchVO.setDateStart(sdf.format(sevenDays));
			orderSearchVO.setDateEnd(sdf.format(today));
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		matchingService.matchingsProductAndOrders(request.getRemoteAddr(), adminVo.getUsername());
		
		int NotmatchingOrdersResult = ordersService.countingNotMatchingedOrders(orderSearchVO);
		orderSearchVO.setTotalRecord(NotmatchingOrdersResult);
		orderSearchVO.setBlockSize(10);
		
		if(orderSearchVO.getRecordCountPerPage() == 0) {			
			orderSearchVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<OrdersVO> ordersList = ordersService.selectNotMatchingedOrders(orderSearchVO);
		
		model.addAttribute("NotmatchingOrdersResult", NotmatchingOrdersResult);
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("OrderSearchVO", orderSearchVO);
		model.addAttribute("order_process", 2);
		
		return "orders/checking_order_matching";
	}
	
	
	/**
	 * @MethodName : matchingBeforeOptionPageGet
	 * @date : 2019. 6. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 옵션 미매칭 페이지 보여주기
	 */
	@RequestMapping(value="/option_matching.do", method=RequestMethod.GET)
	public String matchingBeforeOptionPageGet(HttpServletRequest request, @ModelAttribute OrderSearchVO orderSearchVO, Model model) {
		
		if(orderSearchVO.getDateType() == null) {
			orderSearchVO.setDateType("or_regdate");
			orderSearchVO.setDatePeriod(1);
		}
		
		if(orderSearchVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			orderSearchVO.setDateStart(sdf.format(sevenDays));
			orderSearchVO.setDateEnd(sdf.format(today));
			
		}
		
		int notMatchingOptionOrdersResult = matchingService.countingNotOptionMatchingedOrders(orderSearchVO);
		
		orderSearchVO.setTotalRecord(notMatchingOptionOrdersResult);
		orderSearchVO.setBlockSize(10);
		
		if(orderSearchVO.getRecordCountPerPage() == 0) {
			orderSearchVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<OrdersVO> ordersList = matchingService.selectOrdersOptionNotMatchinged(orderSearchVO);
		
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("notMatchingOptionOrdersResult", notMatchingOptionOrdersResult);
		model.addAttribute("orderSearchVO", orderSearchVO);
		model.addAttribute("order_process", 3);
		
		//매칭 및 원가 적용
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		matchingService.matchingsProductAndOrders(request.getRemoteAddr(), adminVo.getUsername());*/
		
		return "orders/checking_order_option_matching";
	}
	
	/**
	 * @MethodName : matchingResultResponse
	 * @date : 2019. 6. 11.
	 * @author : Jeon KiChan
	 * @메소드설명 : 상품 매칭에 필요한 상품 목록 가져오기 :: ajax 형태로 : matchingPage 부분
	 */
	@RequestMapping(value="/product_list_result.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProductsVO> matchingResultResponse(@ModelAttribute ProductsVO productsVO){
		
		return productsService.selectProductOptionsByCodeAndName(productsVO);
	}
	
	
	/**
	 * @MethodName : matchingPagePost
	 * @date : 2019. 6. 14.
	 * @author : Jeon KiChan
	 * @메소드설명 : 상품 매칭 등록 후 자동으로 매칭이 되지 않은 상품들 매칭시키기
	 */
	@RequestMapping(value="/product_matching.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean matchingPagePost(HttpServletRequest request,@ModelAttribute ProductMatchingVO pmVO ) {
		
		logger.info("pmVO result = {}", pmVO.toString());
		boolean sendMessage = false;
		int result = matchingService.insertProductMatching(pmVO);
		
		if(result == 0) {
			sendMessage = false;
		}else {
			sendMessage = true;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			AdminVO adminVo = (AdminVO)auth.getPrincipal();
			matchingService.matchingsProductAndOrders(request.getRemoteAddr(), adminVo.getUsername());
		}
		
		return sendMessage;
	}
	
	@RequestMapping(value="/product_sequence.do", method=RequestMethod.GET)
	@ResponseBody
	public boolean allProductMatchingSequence(HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		matchingService.matchingsProductAndOrders(request.getRemoteAddr(), adminVo.getUsername());
		return true;
	}
	
	@RequestMapping(value="/option_matching.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public boolean matchingOptionPagePost(@ModelAttribute ProductMatchingVO pmVO) {
		
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		System.out.println("관리자 아이디 => "+adminVo.getUsername());
		logger.info("pmVO result = {}", pmVO.toString());
		
		int result = matchingService.insertOptionMatchingListData(pmVO.getOptionMatchingVOList());
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@RequestMapping(value="/matching_list.do", method=RequestMethod.GET)
	public String matchingListPage(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}

		model.addAttribute("osVO", osVO);
		
		return "orders/matching/list/product_option_matching_list";
	}
	
	
	/**
	 * 
	 * @MethodName : editProductMatching
	 * @date : 2020. 7. 23.
	 * @author : Jeon KiChan
	 * @param pmVO
	 * @return
	 * @메소드설명 : 스토어 재매칭하기
	 */
	@RequestMapping(value="/edit_product_matching.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean editProductMatching(@ModelAttribute ProductMatchingVO pmVO) {
		
		int result = matchingService.editProductMatching(pmVO);
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * @MethodName : insertOptionMatching
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션매칭 추가하기
	 */
	@RequestMapping(value="/add_option_matching.do", method=RequestMethod.POST)
	@ResponseBody
	public int insertOptionMatching(@ModelAttribute OptionMatchingVO omVO) {
		
		int result = matchingService.insertOptionMatching(omVO);
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : deleteOptionMatching
	 * @date : 2020. 7. 24.
	 * @author : Jeon KiChan
	 * @param omVO
	 * @return
	 * @메소드설명 : 옵션 매칭 삭제하기
	 */
	@RequestMapping(value="/delete_option_matching.do", method=RequestMethod.POST)
	@ResponseBody
	public int deleteOptionMatching(@ModelAttribute OptionMatchingVO omVO) {
		
		int result = matchingService.deleteOptionMatching(omVO);
		
		return result;
	}
}
