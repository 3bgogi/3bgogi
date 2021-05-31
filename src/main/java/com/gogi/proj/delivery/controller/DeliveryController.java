package com.gogi.proj.delivery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.model.DeliveryService;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.excel.CellsStyle;
import com.gogi.proj.orders.autocomplete.Godomall;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.healthmarketscience.jackcess.impl.ByteUtil.ByteStream;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController{

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	@Autowired
	private DeliveryService deliService;
	
	@Autowired
	private ConfigurationService configService;
	
	private CellsStyle cs = new CellsStyle();
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	/**
	 * 
	 * @MethodName : sendingProductPageGet
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 상품 발송(바코드피킹) 페이지 
	 */
	@RequestMapping(value="/sending.do", method=RequestMethod.GET)
	public String sendingProductPageGet() {
		
		return "delivery/sending";
	}
	
	/**
	 * 
	 * @MethodName : selectDelivTargetByOrDeliveryInvoiceNumber
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 송장바코드로 주문서 상품 목록 가져오기
	 */
	@RequestMapping(value="/result.do", method=RequestMethod.POST)
	@ResponseBody
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(@ModelAttribute OrdersVO orVO){
		logger.info("orDeliveryInvoiceNumber = {}",orVO.getOrDeliveryInvoiceNumber());

		return deliService.selectDelivTargetByOrDeliveryInvoiceNumber(orVO);
	}
	
	
	/**
	 * 
	 * @MethodName : updateOrderSendingDay
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @param orPk
	 * @return
	 * @메소드설명 : 다 찍은 상품 발송처리하기
	 */
	@RequestMapping(value="/sending_result.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean updateOrderSendingDay(HttpServletRequest request, @RequestParam List<String> orPk) {
		boolean result;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int updates = deliService.updateOrderSendingDay(orPk, request.getRemoteAddr(), adminVo.getUsername());
		
		if(updates == orPk.size()) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : storeOrderSendingGet
	 * @date : 2020. 3. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 판매처로 송장 넘길 수 있는 페이지 보여지기
	 */
	@RequestMapping(value="/store_order_sending.do", method=RequestMethod.GET)
	public String storeOrderSendingGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(today));
			
		}
		
		List<OrdersVO> storeSendingResult = deliService.selectStoreSendingResultByDate(osVO);
		
		model.addAttribute("storeSendingResult", storeSendingResult);
		model.addAttribute("osVO",osVO);
		
		return "delivery/store_order_sending";
	}
	
	
	/**
	 * 
	 * @MethodName : storeSending
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 스토어 발송처리 하기 
	 */
	@RequestMapping(value="/store_sending.do", method=RequestMethod.GET)
	public String storeSending(@ModelAttribute OrderSearchVO osVO) {
		
		if(osVO.getDateStart() == null) {
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat detailSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			osVO.setDateStart(sdf.format(today));
			osVO.setDateEnd(detailSdf.format(sevenDays));
			
		}

		deliService.storeSendingFinished(osVO);
		
		return "redirect: /delivery/store_order_sending.do";
	}
	
	/**
	 * 
	 * @MethodName : godomallAutoSending
	 * @date : 2021. 1. 12.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 고도몰 자동 발송처리하기
	 */
	@RequestMapping(value="/godomall_sending.do", method=RequestMethod.GET)
	public String godomallAutoSending(@ModelAttribute StoreSectionVO ssVO) {
		Godomall gm = new Godomall();
		
		List<OrdersVO> orList = deliService.godomallAutoSendingTarget(ssVO);
		
		String result = gm.godomallAutoSend(orList);
		
		logger.info(result);
		return "redirect: /delivery/store_order_sending.do";
	}
	
	
	/**
	 * 
	 * @MethodName : storeSendingCancled
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 발송처리 건 재 취소하기
	 */
	@RequestMapping(value="/store_sending_cancled.do", method=RequestMethod.GET)
	public String storeSendingCancled(HttpServletRequest request ,@ModelAttribute OrderSearchVO osVO, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		String msg = "";
		String url = "/delivery/store_order_sending.do";
		
		int result = deliService.updateDeliveryOutPutCancled(osVO, request.getRemoteAddr(), adminVo.getUsername());
		
		if(result != 0 ) {
			msg = "발송 취소 완료";
			
		}else {
			msg = "발송 취소 실패";
			
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : sendingResultGet
	 * @date : 2020. 10. 29.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @메소드설명 : 송장부여 페이지에서 발송된 주문 건 불러오기
	 */
	@RequestMapping(value="/sending/results.do", method=RequestMethod.GET)
	public String sendingResultGet(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		List<OrdersVO> sendingList = deliService.selectSendingResults(ssVO);
		
		model.addAttribute("sendingList", sendingList);
		
		return "delivery/sending_results";
	}
	
	/**
	 * 
	 * @MethodName : sendingExcelDownload
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @throws IOException
	 * @메소드설명 : 판매처 별로 발송 엑셀 파일 만들기
	 */
	@RequestMapping(value="/sending/excel_download.do", method=RequestMethod.GET)
	public ModelAndView sendingExcelDownload(@ModelAttribute StoreSectionVO ssVOParam, Model model) throws IOException {
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOParam.getSsPk());
		
		ssVOParam.setSsSendingBodyForm(ssVO.getSsSendingBodyForm());
		ssVOParam.setSsSendingGroupForm(ssVO.getSsSendingGroupForm());
		ssVOParam.setSsSendingHeadFormList(ssVO.getSsSendingHeadForm().split(","));
		if(ssVOParam.getSsPk() == 17) {
			
			List<Map<String, Object>> list = deliService.selectSendingExcelEmall(ssVOParam);
			// 워크북 생성
			SXSSFWorkbook workbook = new SXSSFWorkbook();
	        
	        // 워크시트 생성
	        SXSSFSheet sheet =(SXSSFSheet) workbook.createSheet();
	        
	        workbook.setSheetName(0, "발송처리");
	        // 행 생성
	        SXSSFRow row =(SXSSFRow) sheet.createRow(0);
	        row.setHeight((short)500);
	        // 쎌 생성
	        SXSSFCell cell;
	        
	        String [] title = ssVOParam.getSsSendingHeadFormList();
	        // 헤더 정보 구성
	        for(int i = 0; i < ssVOParam.getSsSendingHeadFormList().length; i++) {
	        	cell = (SXSSFCell)row.createCell(i);
	        	cell.setCellValue(title[i]);
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 0;
	        int cellCounting = 1;
	        
	       for(int i=0; i < list.size(); i++) {
	    	   Set<String> set = list.get(i).keySet();
	    	   Iterator<String> iter = set.iterator();
	    	   
	    	   
	    	   row = (SXSSFRow)sheet.createRow(cellCounting);
	    	   
	    	   while(iter.hasNext()) {
	    		   String key = (String)iter.next();
	    		   String value= (String)(list.get(i).get(key)+"");
	    		   
	    		   
		    		cell = (SXSSFCell)row.createCell(rowCounting);
			       	cell.setCellValue(value);
			       	rowCounting++;
	    	   }
	    	   
	    	   
	    	   rowCounting=0;
	    	   cellCounting++;
	       }

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = ssVO.getSsName()+" 발송 엑셀 파일"+filedate+".xlsx";	        
	        String filePath = fileProperties.getProperty("file.upload.store_sending_excel_file.path.test");
	    
		    
	        // 입력된 내용 파일로 쓰기
	        File file = new File(filePath, fileWrite);
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
	            	
	                if(fos!=null) fos.close();
	                
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        
			Map<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("myfile", file);
			ModelAndView mav = new ModelAndView("downloadView", fileMap);
			
			return mav;
		
		}else {
		List<Map<String, Object>> list = deliService.selectSendingExcel(ssVOParam);
		
		// 워크북 생성
		SXSSFWorkbook workbook = new SXSSFWorkbook();
        
        // 워크시트 생성
        SXSSFSheet sheet =(SXSSFSheet) workbook.createSheet();
        
        workbook.setSheetName(0, "발송처리");
        // 행 생성
        SXSSFRow row =(SXSSFRow) sheet.createRow(0);
        row.setHeight((short)500);
        // 쎌 생성
        SXSSFCell cell;
        
        String [] title = ssVOParam.getSsSendingHeadFormList();
        // 헤더 정보 구성
        for(int i = 0; i < ssVOParam.getSsSendingHeadFormList().length; i++) {
        	cell = (SXSSFCell)row.createCell(i);
        	cell.setCellValue(title[i]);
        	
        }
        
        // 리스트의 size 만큼 row를 생성
        
        int rowCounting = 0;
        int cellCounting = 1;
        
       for(int i=0; i < list.size(); i++) {
    	   Set<String> set = list.get(i).keySet();
    	   Iterator<String> iter = set.iterator();
    	   
    	   
    	   row = (SXSSFRow)sheet.createRow(cellCounting);
    	   
    	   while(iter.hasNext()) {
    		   String key = (String)iter.next();
    		   String value= (String)(list.get(i).get(key)+"");
    		   
    		   
	    		cell = (SXSSFCell)row.createCell(rowCounting);
		       	cell.setCellValue(value);
		       	rowCounting++;
    	   }
    	   
    	   
    	   rowCounting=0;
    	   cellCounting++;
       }

        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String filedate = sdf.format(day);
        String fileWrite = ssVO.getSsName()+" 발송 엑셀 파일"+filedate+".xlsx";	        
        String filePath = fileProperties.getProperty("file.upload.store_sending_excel_file.path.test");
    
	    
        // 입력된 내용 파일로 쓰기
        File file = new File(filePath, fileWrite);
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
            	
                if(fos!=null) fos.close();
                
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
	
	
	/**
	 * 
	 * @MethodName : selectSendingRequestNotChecked
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 강제출고 요청 값 가져오기
	 */
	@RequestMapping(value="/sending_req.do", method=RequestMethod.GET)
	@ResponseBody
	public List<SendingRequestVO> selectSendingRequestNotChecked(){
		
		return deliService.selectSendingRequestNotChecked();
	}
	
	
	
	/**
	 * 
	 * @MethodName : insertSendingReq
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param request
	 * @param srVO
	 * @return
	 * @메소드설명 : 강제출고 요청 ( 상품 출고 페이지에서의 요청값 )
	 */
	@RequestMapping(value="/sending_req.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean insertSendingReq(HttpServletRequest request, @ModelAttribute SendingRequestVO srVO) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		srVO.setSrAdminId(adminVo.getUsername());
		srVO.setSrAdminName(adminVo.getAdminname());
		
		int result = deliService.insertSendingRequest(srVO, request.getRemoteAddr());
		
		if(result > 0) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * @MethodName : nonPickingCount
	 * @date : 2021. 2. 23.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 분류되지 않은 송장 개수
	 */
	@RequestMapping(value="/non_picking_count.do", method=RequestMethod.GET)
	@ResponseBody
	public int nonPickingCount() {
		
		return deliService.nonPickingCount();
		
	}
}
