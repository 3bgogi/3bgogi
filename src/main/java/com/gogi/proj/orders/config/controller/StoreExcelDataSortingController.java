package com.gogi.proj.orders.config.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.configurations.util.ConfigurationUtil;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.model.StoreExcelDataSortingService;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;

@Controller
@RequestMapping(value="/config")
public class StoreExcelDataSortingController {

	private static final Logger logger = LoggerFactory.getLogger(StoreExcelDataSortingController.class);
	
	@Autowired
	private StoreExcelDataSortingService sedsService;
	
	
	/**
	 * @MethodName : storeExcelConfigGet
	 * @date : 2019. 7. 17.
	 * @author : Jeon KiChan
	 * @메소드설명 : 등록된 판매처 목록 및 판매처 추가 페이지
	 */
	@RequestMapping(value="/store/excel_config.do", method=RequestMethod.GET)
	public String storeExcelConfigGet(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		ConfigurationUtil cu = new ConfigurationUtil();
		List<String> excelColumnList = cu.excelColumnCoutingAtoAZ();
		
		StoreExcelDataSortingVO sedsVO = sedsService.selectStoreExcelDataSorting(ssVO);
		
		model.addAttribute("excelColumnList",excelColumnList);
		model.addAttribute("sedsVO",sedsVO);
		model.addAttribute("ssVO",ssVO);
		
		return "configuration/store/config/excel_config";
	}
	
	/**
	 * 
	 * @MethodName : storeExcelConfigPost
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param sedsVO
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 엑셀 열 변경
	 */
	@RequestMapping(value="/store/update/excel_config.do", method=RequestMethod.POST)
	public String storeExcelConfigPost(@ModelAttribute StoreExcelDataSortingVO sedsVO, Model model) {
		
		String msg = "";
		String url = "/config/store/excel_config.do?ssPk="+sedsVO.getSsFk();
		
		int result = sedsService.updateStoreExcelDataSorting(sedsVO);
		
		if(result > 0) {
			msg="엑셀 열 수정완료";
		}else {
			msg="엑셀 열 수정실패...";
		}
		
		StoreSectionVO ssVO = new StoreSectionVO();
		ssVO.setSsPk(sedsVO.getSsFk());
		
		model.addAttribute("ssVO", ssVO);
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}
