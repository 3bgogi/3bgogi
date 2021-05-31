package com.gogi.proj.classification.code.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;

@Controller
@RequestMapping(value="/code")
public class CodeController {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
	
	@Autowired
	private AllClassificationCodeService accService;
	
	
	/**
	 * 
	 * @MethodName : excelOrderSeqGet
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 순서 확인 및 변경 페이지
	 */
	@RequestMapping(value="/excel_order_seq.do", method=RequestMethod.GET)
	public String excelOrderSeqGet(Model model) {
		
		List<ExcelOrderSeqVO> eosList = accService.selectExcelOrderSeqCodeList();
		
		model.addAttribute("eosList", eosList);
		
		return "code/excel_order";
	}
	
	/**
	 * 
	 * @MethodName : excelOrderSeqUpdate
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param eosVO
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 분류값 순서 및 정보 변경하기
	 */
	@RequestMapping(value="/excel_order_seq.do", method=RequestMethod.POST)
	public String excelOrderSeqUpdate(@ModelAttribute ExcelOrderSeqVO eosVO, Model model) {
		String msg = "";
		String url = "/code/excel_order_seq.do";
		
		int result = accService.updateExcelOrderSeq(eosVO);
		
		if(result > 0) {
			
			msg = "순서 및 정보 변경 완료";
		}else {
			msg = "수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : excelOrderSeqInsert
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param eosVO
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 분류값 새로 만들기
	 */
	@RequestMapping(value="/insert_excel_order_seq.do", method=RequestMethod.POST)
	public String excelOrderSeqInsert(@ModelAttribute ExcelOrderSeqVO eosVO, Model model) {
		
		String msg = "";
		String url = "/code/excel_order_seq.do";
		
		int result = accService.insertExcelOrderSeq(eosVO);
		
		if(result > 0) {
			
			msg = "입력 완료";
		}else {
			msg = "입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : selectClassificationCodeGet
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 상품 분류 코드값 보기
	 */
	@RequestMapping(value="/cf_code.do", method=RequestMethod.GET)
	public String selectClassificationCodeGet(Model model) {
		
		List<ClassificationVO> cfList = accService.selectClassificationList();
		
		model.addAttribute("cfList", cfList);
		
		return "code/cf_code";
	}
	
	
	/**
	 * 
	 * @MethodName : selectClassificationCodePost
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cfVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 분류 코드값 일괄 수정하기
	 */
	@RequestMapping(value="/cf_code.do", method=RequestMethod.POST)
	public String selectClassificationCodePost(@ModelAttribute ClassificationVO cfVO, Model model) {
		
		String msg = "";
		String url = "/code/cf_code.do";
		
		int result = accService.updateClassificationCode(cfVO);
		
		if(result > 0) {
			
			msg = "순서 및 정보 변경 완료";
		}else {
			msg = "수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : insertClassificationCode
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cfVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 분류 코드값 입력하기
	 */
	@RequestMapping(value="/insert_cf_code.do", method=RequestMethod.POST)
	public String insertClassificationCode(@ModelAttribute ClassificationVO cfVO, Model model) {
		
		String msg = "";
		String url = "/code/cf_code.do";
		
		int result = accService.insertClassificationCode(cfVO);
		
		if(result > 0) {
			
			msg = "상품 분류 코드값 입력 완료";
		}else {
			msg = "입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : selectCostCodeGet
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 원재로 분류 코드 보기
	 */
	@RequestMapping(value="/cost_code.do", method=RequestMethod.GET)
	public String selectCostCodeGet(Model model) {
		
		List<CostCodeVO> ccList = accService.selectCostCodeList();
		
		model.addAttribute("ccList", ccList);
		
		return "code/cost_code";
	}
	
	
	/**
	 * 
	 * @MethodName : updateCostCodePost
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param ccVO
	 * @param model
	 * @return
	 * @메소드설명 : 원재료 분류 코드 일괄 변경하기
	 */
	@RequestMapping(value="/cost_code.do", method=RequestMethod.POST)
	public String updateCostCodePost(@ModelAttribute CostCodeVO ccVO, Model model) {
		
		String msg = "";
		String url = "/code/cost_code.do";
		
		int result = accService.updateCostCode(ccVO);
		
		if(result > 0) {
			
			msg = "순서 및 정보 변경 완료";
		}else {
			msg = "수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : insertCostCode
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param ccVO
	 * @param model
	 * @return
	 * @메소드설명 : 원재료 분류 코드 입력하기
	 */
	@RequestMapping(value="/insert_cost_code.do", method=RequestMethod.POST)
	public String insertCostCode(@ModelAttribute CostCodeVO ccVO, Model model) {
		
		String msg = "";
		String url = "/code/cost_code.do";
		
		int result = accService.insertCostCode(ccVO);
		
		if(result > 0) {
			msg = "원재료 분류 코드 추가 완료";
			
		}else {
			msg = "추가 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
}
