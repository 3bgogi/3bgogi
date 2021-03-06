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
	 * @??????????????? : ????????? ????????? ?????? ??? ????????? ?????? ?????????
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
	 * @??????????????? : ????????? ???????????? ?????? ?????????
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
	 * @??????????????? : ????????? ???????????? ?????? ????????????
	 */
	@RequestMapping(value="/store/sending_form.do", method=RequestMethod.POST)
	public String storeSendingFormPost(@ModelAttribute StoreSectionVO ssVOPram, Model model) {
		
		String msg = "";
		String url = "/config/store/sending_form.do?ssPk="+ssVOPram.getSsPk();
		
		int result = configService.updateStoreSendingForm(ssVOPram);
		
		if(result != 0) {
			msg = "?????? ??????";
			
		}else {
			msg = "?????? ?????? [????????? ??????????????????]";
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
	 * @??????????????? : ????????? ?????? ????????????
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
			logger.info("????????? ??? ???????????? = {}",encodePass);
			
			logger.info("????????? ??? ???????????? = {}", aesUtil.aesDecode(encodePass));
			
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
			msg = "?????? ???????????? ???????????????";
			
		}else if(result == 1) {
			msg = "?????? ???????????? ???????????????";
		}else if(result == 2) {
			msg = "????????? [ "+ssVO.getSsName()+" ]?????? ??????";
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
	 * @??????????????? : ????????? ?????? ?????? ????????? ????????????
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
	 * @??????????????? : ????????? ?????? ?????? ??????
	 */
	@RequestMapping(value="/store/info_change.do", method=RequestMethod.POST)
	public String storeInfoChange(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		String msg = "";
		String url = "/config/store/info.do?ssPk="+ssVOParam.getSsPk();
		
		int result = configService.updateStoreSection(ssVOParam);
		
		if(result != 0) {
			msg = "????????? ?????? ?????? ??????";
			
		}else {
			msg = "????????? ?????? ?????? ??????";
			
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
	 * @??????????????? : ????????? ???????????? ??????
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
	 * @??????????????? : ????????? ???????????? ??????
	 */
	@RequestMapping(value="/store/merge.do", method=RequestMethod.POST)
	public String storeMergeConfigPost(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		String msg = "";
		String url = "/config/store/merge.do?ssPk="+ssVO.getSsPk();
		
		int result = configService.updateStoreMerge(ssVO);
		
		if(result != 0 ) {
			msg = "????????? ?????? ?????? ?????? ??????";
		}else {
			msg = "????????? ?????? ?????? ?????? ??????";
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
	 * @??????????????? : ???????????????????????? ?????? ????????? ????????????
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
	 * @??????????????? : ???????????????????????? ????????????
	 */
	@RequestMapping(value="/block_sending_list/insert.do", method=RequestMethod.POST)
	public String insertBlockSendingListPost(@ModelAttribute BlockSendingListVO bslVO, Model model) {
		String msg = "";
		String url = "/config/block_sending_list/insert.do";
		
		int dupli = configService.selectBlockSendingListDupli(bslVO);
		
		if(dupli > 0) {
			msg = "?????? ????????? ??????????????????";
			
		}else {
			int result = configService.insertBlockSendingList(bslVO);
			
			if(result > 0) {
				msg = "???????????????????????? ????????????";
				
			}else {
				msg = "???????????????????????? ?????? ??????";
				
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
	 * @??????????????? : ???????????????????????? ????????????
	 */
	@RequestMapping(value="/block_sending_list/delete.do", method=RequestMethod.GET)
	public String deleteBlockSendingList(@ModelAttribute BlockSendingListVO bslVO, Model model) {
		
		String msg = "";
		String url = "/config/block_sending_list/insert.do";

		int result = configService.deleteBlockSendingList(bslVO);
		
		if(result > 0) {
			msg = "?????? ??????";
			
		}else {
			msg = "?????? ??????";
			
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
	 * @??????????????? : ?????? ?????? ?????? ????????????
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
	 * @??????????????? : ?????? ?????? ?????? ??????????????? ??????????????????
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
		
		// ????????? ??????
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// ???????????? ??????
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
		// ??? ??????
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		row.setHeight((short) 500);
		// ??? ??????
		SXSSFCell cell;


		// ?????? ?????? ??????
		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("?????????");
		
		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue("?????????");
		
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
		String fileWrite = "?????? ?????? ??????" + filedate + ".xlsx";

		// ????????? ?????? ????????? ??????
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
