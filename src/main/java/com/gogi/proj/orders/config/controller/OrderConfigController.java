package com.gogi.proj.orders.config.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.orders.config.model.OrderConfigService;
import com.gogi.proj.orders.config.vo.ExceptAddressKeywordVO;
import com.gogi.proj.orders.config.vo.OrdersDeleteVO;
import com.gogi.proj.orders.config.vo.ReqFilterKeywordVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/order/config")
public class OrderConfigController {

	private static final Logger logger = LoggerFactory.getLogger(OrderConfigController.class);
	
	@Autowired
	private OrderConfigService orderConfigService;
	
	
	/**
	 * 
	 * @MethodName : insertingExceptAdderssKeywordGet
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 제외 키워드 추가하는 페이지 들어가기
	 */
	@RequestMapping(value="/except_address_keyword.do", method=RequestMethod.GET)
	public String insertingExceptAdderssKeywordGet(Model model) {
		
		List<ExceptAddressKeywordVO> eakList = orderConfigService.selectExceptAddressKeyword();
		
		model.addAttribute("eakList", eakList);
		
		return "orders/config/except_addr/insert_except_address_keyword";
	}
	
	
	/**
	 * 
	 * @MethodName : insertingExceptAdderssKeywordPost
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param eakVO
	 * @param model
	 * @return
	 * @메소드설명 : 제외 키워드 추가하기
	 */
	@RequestMapping(value="/except_address_keyword.do", method=RequestMethod.POST)
	public String insertingExceptAdderssKeywordPost(@ModelAttribute ExceptAddressKeywordVO eakVO, Model model) {
		
		String msg = "";
		String url = "/order/config/except_address_keyword.do";
		
		int result = orderConfigService.insertExceptAddressKeyword(eakVO);
		
		if(result > 0) {
			msg="추가 완료";
		}else {
			msg="추가 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : deleteExceptAddrKeyword
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param eakVO
	 * @param model
	 * @return
	 * @메소드설명 : 제외 키워드 삭제
	 */
	@RequestMapping(value="/delete_addr_keyword.do", method=RequestMethod.GET)
	public String deleteExceptAddrKeyword(@ModelAttribute ExceptAddressKeywordVO eakVO, Model model) {
		
		String msg = "";
		String url = "/order/config/except_address_keyword.do";
		
		int result = orderConfigService.deleteExceptAddressKeyword(eakVO);
		
		if(result > 0) {
			msg="삭제 완료";
		}else {
			msg="삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : searchExceptAddrOrderGet
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 제외 키워드에 포함된 주문서 목록 보이기
	 */
	@RequestMapping(value="/search_except_addr_order.do", method=RequestMethod.GET)
	public String searchExceptAddrOrderGet(Model model) {
		
		List<ExceptAddressKeywordVO> exceptAddrList = orderConfigService.selectExceptAddressKeyword();
		
		int exceptAddResult = orderConfigService.searchEceptAddrAndUpdateCheckFlag(exceptAddrList);

		List<OrdersVO> orList = null;
		
		if(exceptAddrList.size() == 0) {
			//제외 키워드 목록이 없기 때문에 넘어감
			
		}else {
			//제외 키워드 목록이 있을 경우 조회하여 가져옴
			orList = orderConfigService.exceptAddrTargetOrder(exceptAddrList);
				
		}
		
		model.addAttribute("orList", orList);
		model.addAttribute("eakList", exceptAddrList);
		
		return "orders/config/except_addr/except_address_keyword";
	}
	
	
	/**
	 * 
	 * @MethodName : searchExceptAddrOrderPost
	 * @date : 2020. 7. 3.
	 * @author : Jeon KiChan
	 * @param orPkList
	 * @param model
	 * @return
	 * @메소드설명 : 제외 키워드 확인 처리하기
	 */
	@RequestMapping(value="/check_except_addr.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String searchExceptAddrOrderPost(@RequestParam List<Integer> orPkList) {
		
		String msg = "";
		
		if(orPkList.size() == 0) {
			msg = "제외 키워드로 체크된 주문서가 없습니다";
			
		}else {
			int result = orderConfigService.updateSpecialRegionOrder(orPkList);
			
			if(result > 0) {				
				msg="주문서 확인 완료";
			}else {
				msg = "주문서 확인 수정 과정 실패";
			}
		}
		
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : reqFilterKeywordList
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 필터링 단어 목록보기
	 */
	@RequestMapping(value="/req_filter_keyword_list.do", method=RequestMethod.GET)
	public String reqFilterKeywordList(Model model) {
		
		List<ReqFilterKeywordVO> rfkList = orderConfigService.selectAllReqFilterKeywordList();
		
		model.addAttribute("rfkList", rfkList);
		
		return "orders/config/req_filter_keyword/req_filter_keyword_list";
	}
	
	
	
	/**
	 * 
	 * @MethodName : insertReqFilterKeywordAjax
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 필터링 단어 입력하기
	 */
	@RequestMapping(value="/req_filter_keyword.do", method=RequestMethod.POST)
	public String insertReqFilterKeyword(@ModelAttribute ReqFilterKeywordVO rfkVO, Model model) {
		
		String msg = "";
		String url = "/order/config/req_filter_keyword_list.do";
		
		int result = orderConfigService.insertReqFilterKeyword(rfkVO);
		
		if(result > 0) {
			msg = "요구 사항 필터링 단어 입력 완료";
		}else {
			msg = "요구 사항 필터링 단어 입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : deleteReqFilterKeyword
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 필터링 단어 삭제하기
	 */
	@RequestMapping(value="/delete_req_filter_keyword.do", method=RequestMethod.GET)
	public String deleteReqFilterKeyword(@ModelAttribute ReqFilterKeywordVO rfkVO, Model model) {
		
		String msg = "";
		String url = "/order/config/req_filter_keyword_list.do";
		
		int result = orderConfigService.deleteReqFilterKeywordByPk(rfkVO);
		
		if(result > 0) {
			msg = "해당 필터링 단어 삭제 완료";
		}else {
			msg = "해당 필터링 단어 삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : updateReqfFilterKeywordUse
	 * @date : 2020. 7. 31.
	 * @author : Jeon KiChan
	 * @param rfkVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지 요구사항 필터링 단어 사용여부 업데이트 하기
	 */
	@RequestMapping(value="/update_req_filter_keyword.do", method=RequestMethod.POST)
	public String updateReqfFilterKeywordUse(@ModelAttribute ReqFilterKeywordVO rfkVO, Model model) {
		
		String msg = "";
		String url = "/order/config/req_filter_keyword_list.do";
		
		int result = orderConfigService.updateReqFilterKeywordUseOrUnuse(rfkVO);
		
		if(result > 0) {
			msg = "요구 사항 사용여부 수정 완료";
		}else {
			msg = "요구 사항 사용여부 수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : packingIrreOrderListGet
	 * @date : 2020. 8. 6.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 합포해도 무관한 주문서 목록 가져오기
	 */
	@RequestMapping(value="/packing_irre_order_list.do", method=RequestMethod.GET)
	public String packingIrreOrderListGet(Model model) {
		
		List<OrdersVOList> orList = orderConfigService.selectPackingIrreTargetOrderList();
		
		model.addAttribute("orList", orList);
		
		return "orders/config/packing/irre_order_list";
		
	}
	
	
	/**
	 * 
	 * @MethodName : orderDeleteList
	 * @date : 2021. 3. 4.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 삭제 목록 가져오기
	 */
	@RequestMapping(value="/delete_order_list.do", method=RequestMethod.GET)
	public String orderDeleteList(@ModelAttribute OrderSearchVO osVO, Model model) {

		
		if(osVO.getDateStart() == null) {
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(today));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		int totalRecord = orderConfigService.selectOrdersDeleteListCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(7);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(30);
			
		}
		
		List<OrdersDeleteVO> orderDeleteList = orderConfigService.selectOrdersDeleteList(osVO);
		
		model.addAttribute("orderDeleteList", orderDeleteList);
		model.addAttribute("osVO", osVO);
		
		return "logs/delete_order_list";
	}
}
