package com.gogi.proj.delivery.config.controller;

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

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.DelivImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Controller
@RequestMapping("/delivery/config")
public class DeliveryConfigController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryConfigController.class);
	
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private OrdersService ordersService;
	
	/**
	 * 
	 * @MethodName : earlyDelivGet
	 * @date : 2020. 11. 27.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 배송 구분 및 설정 페이지 들어가기
	 */
	@RequestMapping(value="/early_deliv.do", method=RequestMethod.GET)
	public String earlyDelivGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getSearchKeyword()!= null && osVO.getSearchKeyword().matches(".*~.*")) {
			String [] result = osVO.getSearchKeyword().split("~");
			osVO.setSearchKeyword(result[0]);
			osVO.setSearchAddKeyword(result[1]);
			
		}
		
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		List<EarlyDelivTypeVO> searchList = dcService.searchDelivArea(osVO);
		
		
		
		model.addAttribute("searchList", searchList);
		model.addAttribute("edtList", edtList);
		model.addAttribute("osVO", osVO);
		
		return "delivery/config/early_deliv";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivPost
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param eda
	 * @param model
	 * @return
	 * @메소드설명 : 우편번호 등록하기
	 */
	@RequestMapping(value="/early_deliv.do", method=RequestMethod.POST)
	public String earlyDelivPost(@ModelAttribute EarlyDelivAreaVO eda, Model model) {
		String msg = "";
		String url = "/delivery/config/early_deliv.do";
		logger.info("우편번호 등록, 값 {}", eda.toString());
		
		int result = dcService.insertEarlyAreaZipcCode(eda);
		
		if(result > 0 ) {
			msg = "우편번호 등록완료";
			
		}else {
			msg = "우편번호 등록실패 중복된 우편번호가 있을 수 있습니다";
			
		}
	
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : earlyDelivImposGet
	 * @date : 2020. 12. 1.
	 * @author : Jeon KiChan
	 * @param model
	 * @param EarlyDelivAreaVO : edaPk 필요
	 * @return
	 * @메소드설명 : 배송 구분 중 배송이 불가능한 지역 키워드 확인 페이지
	 */
	@RequestMapping(value="/early_deliv/impos.do", method=RequestMethod.GET)
	public String earlyDelivImposGet(@ModelAttribute EarlyDelivAreaVO eda, Model model) {
		
		List<EarlyDelivAreaVO> edaList = dcService.selectDelivPosArea(eda);
		
		model.addAttribute("edaList",edaList);
		model.addAttribute("eda", eda);
		
		return "delivery/config/early_deliv_impos";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivImposPost
	 * @date : 2020. 12. 7.
	 * @author : Jeon KiChan
	 * @param diVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송 구분 중 배송이 불가능한 지역 키워드 입력하기
	 */
	@RequestMapping(value="/early_deliv/impos.do", method=RequestMethod.POST)
	public String earlyDelivImposPost(@ModelAttribute DelivImposVO diVO, Model model) {
		String msg = "";
		String url = "/delivery/config/early_deliv/impos.do?edaPk="+diVO.getEdaFk();
		
		int result = dcService.insertDelivImposKeyword(diVO);
		
		if(result >  0) {
			msg = "배송불가 키워드 입력완료";
			
		}else {
			msg = "배송불가 키워드 입력실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivImposDelete
	 * @date : 2020. 12. 8.
	 * @author : Jeon KiChan
	 * @param diVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송불가 지역 키워드 삭제 하기
	 */
	@RequestMapping(value="/early_deliv/impos_del.do", method=RequestMethod.GET)
	public String earlyDelivImposDelete(@ModelAttribute DelivImposVO diVO, Model model) {
		String msg = "";
		String url = "/delivery/config/early_deliv/impos.do?edaPk="+diVO.getEdaFk();
		logger.info("배송불가 지역 키워드 삭제, 값 = {}", diVO.toString());
		int result = dcService.deleteDelivImpos(diVO);
		
		if(result > 0) {
			msg = "배송불가 키워드 삭제 완료";
			
		}else {
			msg = "배송불가 키워드 삭제 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : delivInvoiceNumCheck
	 * @date : 2020. 12. 1.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 송장 나올 때 체크
	 */
	@RequestMapping(value="/deliv_num_check.do", method=RequestMethod.GET)
	public String delivInvoiceNumCheck(@ModelAttribute OrderSearchVO osVO,Model model) {
		
		List<OrdersVO> invoiceNum = ordersService.selectCreateInvoiceNum();
		List<OrdersVO> orList = dcService.selectDelivNumCheckTarget(osVO);
		
		model.addAttribute("orList", orList);
		model.addAttribute("invoiceNum", invoiceNum);
		
		return "delivery/deliv_num_check";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivAreaDelete
	 * @date : 2020. 12. 8.
	 * @author : Jeon KiChan
	 * @param eda
	 * @param model
	 * @return
	 * @메소드설명 : 빠른 배송 우편번호 삭제하기
	 */
	@RequestMapping(value="/early_deliv_del.do", method=RequestMethod.GET)
	public String earlyDelivAreaDelete(@ModelAttribute EarlyDelivAreaVO edaVO, Model model) {
		String url = "/delivery/config/early_deliv.do";
		String msg = "";
		logger.info("빠른 배송 우편번호 삭제, = {}", edaVO.toString());
		
		int result = dcService.deleteEarlyDelivArea(edaVO);
		
		if(result > 0) {
			msg = "우편번호 삭제 완료";
		}else {
			msg = "우편번호 삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}

