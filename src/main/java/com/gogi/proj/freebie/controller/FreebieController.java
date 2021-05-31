package com.gogi.proj.freebie.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.freebie.model.FreebieService;
import com.gogi.proj.freebie.vo.FreebieVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping("/config/freebie")
public class FreebieController {

	private static final Logger logger = LoggerFactory.getLogger(FreebieController.class);
	
	@Autowired
	private FreebieService fbService;
	
	@Autowired
	private ConfigurationService configService;
	
	
	/**
	 * 
	 * @MethodName : InsertFreebieGet
	 * @date : 2020. 6. 18.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 추가하기 페이지
	 */
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String insertFreebieGet(@ModelAttribute FreebieVO fbVO, Model model) {
		
		if(fbVO.getFbMinDate() == null && fbVO.getFbMaxDate() == null) {
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			fbVO.setFbMinDate(sdf.format(sevenDays));
			fbVO.setFbMaxDate(sdf.format(today));
		}
		
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		
		model.addAttribute("fbVO", fbVO);
		model.addAttribute("ssList", ssList);
		
		return "configuration/freebie/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : insertFreebiePost
	 * @date : 2020. 6. 22.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 적용
	 * 값 체크 후 넘기도록 함
	 */
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String insertFreebiePost(@ModelAttribute FreebieVO fbVO, Model model) {
		
		String msg = "";
		String url = "";
		
		System.out.println(" FreebieVO toString => "+fbVO.toString());
		
		int result = fbService.insertFreebie(fbVO);
		
		if(result > 0) {
			msg = "사은품 정책 추가 완료";
		}else {
			msg = "사은품 정책 추가 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : freebieListGet
	 * @date : 2020. 6. 23.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 목록
	 */
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String freebieListGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		int totalRecord = fbService.selectFreebieCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(15);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		List<FreebieVO> fbList = fbService.selectFreebies(osVO);
		
		model.addAttribute("ssList", ssList);
		model.addAttribute("fbList", fbList);
		model.addAttribute("osVO", osVO);
		
		return "configuration/freebie/list";
	}
	
	
	/**
	 * 
	 * @MethodName : freebieTest
	 * @date : 2020. 6. 25.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 적용
	 */
	@RequestMapping(value="/apply.do", method=RequestMethod.GET)
	public String freebieApply(Model model) {
		
		String msg = "";
		String url = "/order/matching/products_matching.do";
		
		int result = 0;
		
		List<FreebieVO> fbList = fbService.selectFreebieList();
		
		for(int i=0; i < fbList.size(); i++) {
			
			String [] wordSplit = fbList.get(i).getFbAnotherCheckWord().split(",");
			String [] storeSplit = fbList.get(i).getSsList().split(",");
			List<String> wordList = new ArrayList<String>();
			List<String> storeList = new ArrayList<String>();
			
			for(int j=0; j < wordSplit.length; j++) {
				wordList.add(wordSplit[j]);
			}
			
			for(int j=0; j < storeSplit.length; j++) {
				storeList.add(storeSplit[j]);
			}
			
			fbList.get(i).setFbAnotherCheckWordList(wordList);
			fbList.get(i).setSsArrayList(storeList);
			result += fbService.selectFreebieTargetOrder(fbList.get(i));
			
		}
		
		if(result > 0) {
			msg = result+" 개 사은품 부여 완료";
			
		}else {
			msg = "사은품 적용 대상이 없습니다";
			
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : freebieUpdateGet
	 * @date : 2020. 6. 25.
	 * @author : Jeon KiChan
	 * @param paramFbVO
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 수정 및 보기 페이지
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String freebieUpdateGet(@ModelAttribute FreebieVO paramFbVO, Model model) {
		
		FreebieVO fbVO = fbService.selectFreebieByFbpk(paramFbVO);
		
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		
		model.addAttribute("fbVO", fbVO);
		model.addAttribute("ssList", ssList);
		
		return "configuration/freebie/update";
	}
	
	
	/**
	 * 
	 * @MethodName : freebieUpdatePost
	 * @date : 2020. 6. 25.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 수정
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String freebieUpdatePost(@ModelAttribute FreebieVO fbVO, Model model) {
		
		String msg = "";
		String url = "/config/freebie/list.do";
		
		int result = fbService.updateFreebieByPk(fbVO);
		
		if(result > 0) {
			msg = "수정 완료";
		}else {
			msg = "수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : deleteFreebie
	 * @date : 2021. 2. 26.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @param model
	 * @return
	 * @메소드설명 : 사은품 정책 삭제하기
	 */
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String deleteFreebie(@ModelAttribute FreebieVO fbVO, Model model) {
		
		String msg = "";
		String url = "/config/freebie/list.do";
		
		int result = fbService.deleteFreebie(fbVO);
		
		if(result > 0) {
			msg = "사은품 삭제 완료";
		}else {
			msg = "사은품 삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
}
