package com.gogi.proj.configurations.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.util.ConfigurationUtil;
import com.gogi.proj.configurations.vo.BlockSendingListVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.model.DeliveryService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.AES256Util;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/config")
public class ConfigurationController {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private DeliveryService deliService;
	
	@Autowired
	private AllClassificationCodeService accService;
	/**
	 * @MethodName : storeListGet
	 * @date : 2019. 7. 17.
	 * @author : Jeon KiChan
	 * @메소드설명 : 등록된 판매처 목록 및 판매처 추가 페이지
	 */
	@RequestMapping(value="/store/list.do", method=RequestMethod.GET)
	public String storeListGet(Model model) {
		
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		
		model.addAttribute("ssList", ssList);
		model.addAttribute("storeCounting", ssList.size());
		
		return "configuration/store/store_list";
	}
	
	/**
	 * 
	 * @MethodName : storeSendingFormGet
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 발송파일 설정 페이지
	 */
	@RequestMapping(value="/store/sending_form.do", method=RequestMethod.GET)
	public String storeSendingFormGet(@ModelAttribute StoreSectionVO ssVOPram, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOPram.getSsPk());
		
		model.addAttribute("ssVO", ssVO);
		
		return "configuration/store/config/store_sending_form";
	}
	
	/**
	 * 
	 * @MethodName : storeSendingFormPost
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVOPram
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 발송파일 설정 수정하기
	 */
	@RequestMapping(value="/store/sending_form.do", method=RequestMethod.POST)
	public String storeSendingFormPost(@ModelAttribute StoreSectionVO ssVOPram, Model model) {
		
		String msg = "";
		String url = "/config/store/sending_form.do?ssPk="+ssVOPram.getSsPk();
		
		int result = configService.updateStoreSendingForm(ssVOPram);
		
		if(result != 0) {
			msg = "수정 완료";
			
		}else {
			msg = "수정 실패 [로그를 확인해주세요]";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	
	/**
	 * @MethodName : addStore
	 * @date : 2019. 7. 22.
	 * @author : Jeon KiChan
	 * @throws UnsupportedEncodingException 
	 * @메소드설명 : 판매처 새로 등록하기
	 */
	@RequestMapping(value="/store/add_store.do", method=RequestMethod.POST)
	public String addStore(@ModelAttribute StoreSectionVO ssVO, Model model) throws UnsupportedEncodingException {
		AES256Util aesUtil = new AES256Util();
		String msg = "";
		String url = "/config/store/list.do";
		
		String encodePass = "";
		
		int result = 0;
		
		logger.info("addStore data : StoreSectionVO, ssVO = {}", ssVO);
		
		try {
			encodePass =  aesUtil.aesEncode(ssVO.getSsStorePassword());
			logger.info("암호화 된 비밀번호 = {}",encodePass);
			
			logger.info("복호화 된 비밀번호 = {}", aesUtil.aesDecode(encodePass));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			result = configService.addStoreSection(ssVO);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			msg = "data base or parameter error";
			
		}
		
		if(result == 0) {
			msg = "값이 입력되지 않았습니다";
			
		}else if(result == 1) {
			msg = "값이 입력되지 않았습니다";
		}else if(result == 2) {
			msg = "판매처 [ "+ssVO.getSsName()+" ]등록 성공";
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}	

	
	/**
	 * 
	 * @MethodName : storeInfoViewGet
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 정보 변경 페이지 들어가기
	 */
	@RequestMapping(value="/store/info.do", method=RequestMethod.GET)
	public String storeInfoViewGet(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOParam.getSsPk());
		
		model.addAttribute("ssVO",ssVO);
		
		return "configuration/store/config/store_info";
	}
	
	
	/**
	 * 
	 * @MethodName : storeInfoChange
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 정보 변경 하기
	 */
	@RequestMapping(value="/store/info_change.do", method=RequestMethod.POST)
	public String storeInfoChange(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		String msg = "";
		String url = "/config/store/info.do?ssPk="+ssVOParam.getSsPk();
		
		int result = configService.updateStoreSection(ssVOParam);
		
		if(result != 0) {
			msg = "판매처 정보 변경 완료";
			
		}else {
			msg = "판매처 정보 변경 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : storeMergeConfigGet
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 묶음정리 조회
	 */
	@RequestMapping(value="/store/merge.do", method=RequestMethod.GET)
	public String storeMergeConfigGet(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreMerge(ssVOParam);
		
		model.addAttribute("ssVO",ssVO);
		
		return "configuration/store/config/store_order_merge";
	}
	
	
	/**
	 * 
	 * @MethodName : storeMergeConfigPost
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 묶음정리 수정
	 */
	@RequestMapping(value="/store/merge.do", method=RequestMethod.POST)
	public String storeMergeConfigPost(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		String msg = "";
		String url = "/config/store/merge.do?ssPk="+ssVO.getSsPk();
		
		int result = configService.updateStoreMerge(ssVO);
		
		if(result != 0 ) {
			msg = "판매처 묶음 정리 변경 완료";
		}else {
			msg = "판매처 묶음 정리 변경 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : insertBlockSendingListGet
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 문자발송금지명단 입력 페이지 들어가기
	 */
	@RequestMapping(value="/block_sending_list/insert.do", method=RequestMethod.GET)
	public String insertBlockSendingListGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		int totalRecord = configService.selectBlockSendingListCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<BlockSendingListVO> bslList = configService.selectBlockSendingList(osVO);
		
		model.addAttribute("bslList", bslList);
		model.addAttribute("osVO", osVO);
		
		return "orders/config/block_sending_list/insert";
		
	}
	
	
	/**
	 * 
	 * @MethodName : insertBlockSendingListPost
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param bslVO
	 * @param model
	 * @return
	 * @메소드설명 : 문자발송금지명단 입력하기
	 */
	@RequestMapping(value="/block_sending_list/insert.do", method=RequestMethod.POST)
	public String insertBlockSendingListPost(@ModelAttribute BlockSendingListVO bslVO, Model model) {
		String msg = "";
		String url = "/config/block_sending_list/insert.do";
		
		int dupli = configService.selectBlockSendingListDupli(bslVO);
		
		if(dupli > 0) {
			msg = "이미 등록된 연락처입니다";
			
		}else {
			int result = configService.insertBlockSendingList(bslVO);
			
			if(result > 0) {
				msg = "문자발송금지명단 입력완료";
				
			}else {
				msg = "문자발송금지명단 입력 실패";
				
			}
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : deleteBlockSendingList
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param bslVO
	 * @param model
	 * @return
	 * @메소드설명 : 문자발송금지명단 삭제하기
	 */
	@RequestMapping(value="/block_sending_list/delete.do", method=RequestMethod.GET)
	public String deleteBlockSendingList(@ModelAttribute BlockSendingListVO bslVO, Model model) {
		
		String msg = "";
		String url = "/config/block_sending_list/insert.do";

		int result = configService.deleteBlockSendingList(bslVO);
		
		if(result > 0) {
			msg = "삭제 완료";
			
		}else {
			msg = "삭제 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : eventMsgTargetGet
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 문자 발송 명단 뽑아오기
	 */
	@RequestMapping(value="/event_msg.do", method=RequestMethod.GET)
	public String eventMsgTargetGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		List<OrdersVO> odList = null;
		
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
		
		if(osVO.getSearchKeyword() != null && !osVO.getSearchKeyword().equals("")) {
			String [] wordSplit = osVO.getSearchKeyword().split(",");
			List<String> wordList = new ArrayList<String>();
			
			for(int i = 0; i < wordSplit.length; i++) {
				wordList.add(wordSplit[i]);
				
			}
			
			osVO.setSearchKeywordList(wordList);
		}
		
		if(osVO.getExSerachKeyword() != null && !osVO.getExSerachKeyword().equals("")) {
			String [] wordSplit = osVO.getExSerachKeyword().split(",");
			List<String> wordList = new ArrayList<String>();
			
			for(int i = 0; i < wordSplit.length; i++) {
				wordList.add(wordSplit[i]);
				
			}
			
			osVO.setExSearchKeywordList(wordList);
		}
		
		List<BlockSendingListVO> bslList = configService.selectAllBlockSendingList();
		osVO.setBslList(bslList);
		
		int totalRecord = configService.selectEventMsgTargetCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		osVO.setRecordCountPerPage(40);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		odList = configService.selectEventMsgTarget(osVO);
		
		if((osVO.getSearchKeyword() != null && !osVO.getSearchKeyword().equals("")) ||(osVO.getExSerachKeyword() != null && !osVO.getExSerachKeyword().equals("")) ) {
			
			List<OrdersVO> keywords = configService.selectEventMsgProductKeyword(osVO);
			
			if(osVO.getSearchKeyword() == null && osVO.getSearchKeyword().equals("")) {
				model.addAttribute("keywords", null);
				
			}else {
				model.addAttribute("keywords", keywords);
				
			}
			
		}
		
		
		
		List<ClassificationVO> cfList = accService.selectClassificationList();
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		
		model.addAttribute("ssList", ssList);
		model.addAttribute("cfList", cfList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("odList", odList);
		
		
		return "event/search";
	}
	
	
	/**
	 * 
	 * @MethodName : eventMsgTargetExcelDown
	 * @date : 2020. 10. 20.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 문자 발송 명단 엑셀파일로 다운로드하기
	 */
	@RequestMapping(value="/event_msg_excel.do", method=RequestMethod.POST)
	public ModelAndView eventMsgTargetExcelDown(@ModelAttribute OrderSearchVO osVO) {

		if(osVO.getSearchKeyword() != null && !osVO.getSearchKeyword().equals("")) {
			String [] wordSplit = osVO.getSearchKeyword().split(",");
			List<String> wordList = new ArrayList<String>();
			
			for(int i = 0; i < wordSplit.length; i++) {
				wordList.add(wordSplit[i]);
				
			}
			
			osVO.setSearchKeywordList(wordList);
		}
		
		if(osVO.getExSerachKeyword() != null && !osVO.getExSerachKeyword().equals("")) {
			String [] wordSplit = osVO.getExSerachKeyword().split(",");
			List<String> wordList = new ArrayList<String>();
			
			for(int i = 0; i < wordSplit.length; i++) {
				wordList.add(wordSplit[i]);
				
			}
			
			osVO.setExSearchKeywordList(wordList);
		}

		List<BlockSendingListVO> bslList = configService.selectAllBlockSendingList();
		osVO.setBslList(bslList);

		List<OrdersVO> odList = configService.selectAllEventMsgTarget(osVO);
		
		// 워크북 생성
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// 워크시트 생성
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
		// 행 생성
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		row.setHeight((short) 500);
		// 쎌 생성
		SXSSFCell cell;


		// 헤더 정보 구성
		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("연락처");
		
		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue("고객명");
		
		int rowCounting = 1;

		for (int i = 0; i < odList.size(); i++) {
			
			row = (SXSSFRow) sheet.createRow(rowCounting);

			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(odList.get(i).getOrBuyerContractNumber1());
			cell = (SXSSFCell) row.createCell(1);
			cell.setCellValue(odList.get(i).getOrBuyerName());
			
			rowCounting++;

		}

		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filedate = sdf.format(day);
		String fileWrite = "문자 발송 명단" + filedate + ".xlsx";

		// 입력된 내용 파일로 쓰기
		File file = new File( fileWrite);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);

		return mav;

	}
	
	
}
