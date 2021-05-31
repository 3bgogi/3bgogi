package com.gogi.proj.epost.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.parser.ParseException;
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
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.EarlyDelivCommonImposVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.epost.api.EpostSendingUtil;
import com.gogi.proj.epost.api.SEED128;
import com.gogi.proj.epost.model.EpostService;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.excel.xlsxWriter;
import com.gogi.proj.orders.config.model.OrderConfigService;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value = "/security")
public class EpostController {

	private static final Logger logger = LoggerFactory.getLogger(EpostController.class);
	
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	//상수
	
	//송장부여
	static final String EPOST_DELIV_SENDING = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	
	static final String EPOST_DELIV_SENDING_VER_2 = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	//송장삭제
	static final String EPOST_DELIV_DELETE = "http://ship.epost.go.kr/api.GetResCancelCmd.jparcel";

	@Autowired
	private EpostService epostService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private CostIoService costIoService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderConfigService orderConfigService;
	
	@Autowired
	private DeliveryConfigService dcService;

	/*
	 * @RequestMapping(value="/epost.do", method=RequestMethod.GET) public String
	 * epostTestPageGet(Model model) {
	 * 
	 * model.addAttribute("eposts",epostService.selectEpostSendingData().get(1).
	 * toString()); return "security/apiSeedTest"; }
	 */

	
	
	/**
	 * 
	 * @MethodName : epostPagePost
	 * @date : 2020. 2. 4.
	 * @author : Jeon KiChan
	 * @param orderSearchVO
	 * @param model
	 * @return
	 * @메소드설명 : 미수여 송장 확인
	 */
	@RequestMapping(value = "/epost.do", method = RequestMethod.GET)
	public String epostPagePost(@ModelAttribute OrderSearchVO orderSearchVO, Model model) {	
		
		/*osVO.setSearchType("1");
		List<RegDataVO> regList = epostService.selectEpostSendingData(osVO);*/
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
		
		if(orderSearchVO.getTotalQtyAlarm() == 0) {
			orderSearchVO.setTotalQtyAlarm(12);
		}

		int recordCounting = epostService.selectDontGrantDelivOrderListInMonthCounting(orderSearchVO);
		
		orderSearchVO.setTotalRecord(recordCounting);
		orderSearchVO.setBlockSize(10);
		
		if(orderSearchVO.getRecordCountPerPage() == 0) {			
			orderSearchVO.setRecordCountPerPage(500);
			
		}
		
		List<OrdersVO> orderList = epostService.selectDontGrantDelivOrderListInMonth(orderSearchVO);
		List<StoreSectionVO> storeSectionList = configService.selectStoreSectionList();
		List<OrdersVO> insertStoreOrderList = ordersService.selectOrdersCountingByInputDate();
		int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();
		
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		
		model.addAttribute("OrderSearchVO", orderSearchVO);
		model.addAttribute("orderList",orderList);
		model.addAttribute("storeSectionList",storeSectionList);
		model.addAttribute("insertStoreOrderList", insertStoreOrderList);
		model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
		model.addAttribute("edtList", edtList);
		
		return "delivery/not_sending_list";
	}
	
	@RequestMapping(value="/epost/deliv.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String epostDelivSending(@ModelAttribute OrderSearchVO osVO) {
		
		String encryptStr;
		EpostSendingUtil esu = new EpostSendingUtil();
		
		osVO.setSearchType("0");
		StringBuilder failResult = new StringBuilder("");
		int result = 0;
		int totalDeliv = osVO.getOrSerialSpecialNumberList().size();
		int delivCount = 1;
		
		for (int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			List<RegDataVO> regList = epostService.selectEpostSendingData(osVO);
			
		
			int divCount = 1;
			
			String delivMsg = regList.get(0).getDelivMsg() == null ? "" : regList.get(0).getDelivMsg();
			
			for(int j=0; j < regList.size(); j++ ) {
				String [] productCounting = regList.get(j).getGoodsNm().split(";;");
				
				regList.get(j).setDelivMsg("("+delivCount+"/"+totalDeliv+")"+delivMsg);
				regList.get(j).setOrderNo("("+delivCount+"-"+divCount+"/"+totalDeliv+")"+regList.get(j).getOrderNo());
				
				double countingDiv = (double)(productCounting.length / 8);
				int countingDivMod = (productCounting.length % 8);
				
				if(countingDiv > 0 && countingDivMod != 0) {
					StringBuffer sb = new StringBuffer();
					
					//송장 나누기 개수
					int deliCounting = 1;
					String receiverName = regList.get(j).getRecNm();
					
					for(int duplCounting = 0; duplCounting < productCounting.length; duplCounting++) {
						if((duplCounting + 1) % 8 == 0) {
							sb.append(productCounting[duplCounting]);
							
						}else if(duplCounting == productCounting.length - 1){
							sb.append(productCounting[duplCounting]);
							
						}else {
							sb.append(productCounting[duplCounting]+";;");
						}
						
						if(( (duplCounting + 1) % 8 == 0) || duplCounting == productCounting.length - 1) {
							regList.get(j).setGoodsNm(sb.toString());
							regList.get(j).setRecNm(receiverName+"("+deliCounting+")");
							
							if(deliCounting > 1) {
								regList.get(j).setDelivMsg("("+delivCount+"-"+divCount+"/"+totalDeliv+")"+delivMsg);
								regList.get(j).setRecAddr2(" ********* 해당 송장은 보내지 않습니다 파기해 주세요 *********");
								divCount++;
								
							}
							
							encryptStr=esu.epostEncrypting(regList.get(j).epostDelivToString());
							
							if(deliCounting == 1) {
								try {
									RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
									int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, true);
									if(updateResult != 0) {
										result += updateResult;
										
									}else {
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
									}
									
									deliCounting++;
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else {
								try {
									RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
									int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, false);
									if(updateResult != 0) {
										result += updateResult;
										
									}else {
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
									}
									
									deliCounting++;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							sb = new StringBuffer();
							
							
						}
					}
				}else {
					
					encryptStr=esu.epostEncrypting(regList.get(j).epostDelivToString());
					
					try {
						RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
						int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, true);
						if(updateResult != 0) {
							result += updateResult;
							
						}else {
							failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
			
			delivCount++;
		}
		
		failResult.append("<br>완료된 송장 개수 = "+result+" 장");
		return failResult.toString();
	}
	
	
	/**
	 * 
	 * @MethodName : delivResultGet
	 * @date : 2020. 8. 11.
	 * @author : Jeon KiChan
	 * @param delivResult
	 * @param model
	 * @return
	 * @메소드설명 : 송장 부여 결과값 표시하기
	 */
	@RequestMapping(value="/deliv_result.do", method=RequestMethod.GET)
	public String delivResultGet(@RequestParam String delivResult, Model model) {
		
		model.addAttribute("delivResult",delivResult);
		
		return "delivery/deliv_result";
	}
	
	
	/**
	 * 
	 * @MethodName : createDelivInvoice
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 송장 자체 출력 페이지
	 */
	@RequestMapping(value="/create_deliv_invoice.do", method=RequestMethod.POST)
	public String createDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		List<OrdersVO> orList = new ArrayList<>();
		List<OrdersVO> errorOr = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today= new Date();
		
		String formatDate = sdf.format(today);
		int delivCount = 1;
		
		OrdersVO orVO = null;
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			
			orVO = epostService.deliveryPrintTarget(osVO, request.getRemoteAddr(), adminVo.getUsername(), formatDate, delivCount);
			
			if(orVO != null && orVO.getRegiNo() != null) {
				orList.add(orVO);
				delivCount++;
				
			}else {
				errorOr.add(orVO);
			}
		}
		
		for( OrdersVO or : orList) {
			if(or.getProductOptionList().get(0).getProdSorting() == 0 && or.getProductOptionList().size() > 1) {				
				for( int i = 1; i < or.getProductOptionList().size(); i++) {
					if( or.getProductOptionList().get(i).getProdSorting() == 0 ) {
						or.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}
		
		Collections.sort(orList);
		
		model.addAttribute("orList",orList);
		model.addAttribute("errorOr",errorOr);
		
		return "delivery/create_deliv_invoice";
	}
	
	
	/**
	 * 
	 * @MethodName : reprintingDelivInvoice
	 * @date : 2020. 9. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 등록된 송장 재출력하기
	 */
	@RequestMapping(value="/reprinting_deliv_invoice.do", method=RequestMethod.POST)
	public String reprintingDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		List<OrdersVO> orList = new ArrayList<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			orList.add(epostService.deliveryInvoiceNumberReprinting(osVO, request.getRemoteAddr(), adminVo.getUsername() ));
		}
		
		
		for( OrdersVO or : orList) {
			if(or.getProductOptionList().get(0).getProdSorting() == 0 && or.getProductOptionList().size() > 1) {				
				for( int i = 1; i < or.getProductOptionList().size(); i++) {
					if( or.getProductOptionList().get(i).getProdSorting() == 0 ) {
						or.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}

		
		Collections.sort(orList);
		
		model.addAttribute("orList",orList);
		
		return "delivery/create_deliv_invoice";
	}
	
	@RequestMapping(value="/orderIO.do", method=RequestMethod.POST)
	public ModelAndView selectedOrdersExcelIO(@ModelAttribute OrderSearchVO osVO){
		
		List<OrdersVOList> orVoList = ordersService.selectedOrderExcelByOrderSerachVOForVegit(osVO);
		System.out.println("orVoList 개수 = >"+orVoList.size());
		
		List<Integer> testInt = new ArrayList<>();
		testInt.add(1);
		testInt.add(3);
		osVO.setSearchKeywordNumList(testInt);
		
		xlsxWriter xw = new xlsxWriter();
		
		List<OrdersVO> orList =  ordersService.selectedOrderExcelByOrderSerachVO(osVO);
		
		List<ExcelOrderSeqVO> eoSeq = accService.selectExcelOrderSeqCodeList();
		
		
		List<String> idxTitle = new ArrayList<String>();
		idxTitle.add("분류코드");
		idxTitle.add("상품명");
		idxTitle.add("개수");
		
		File file = xw.orderXlsWriter(idxTitle, orList, orVoList, eoSeq, fileProperties.getProperty("file.upload.order_IO_excel.path.test"), "2층 주문서");
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	@RequestMapping(value="/product_label.do", method=RequestMethod.POST)
	public ModelAndView printProductLabel(@ModelAttribute OrderSearchVO osVO) {
		
		List<String> idxTitle = new ArrayList<String>();
		idxTitle.add("수량");
		idxTitle.add("제품명");
		idxTitle.add("중량");
		idxTitle.add("원산지");
		idxTitle.add("원재료 및 함량");
		idxTitle.add("유통기한");
		idxTitle.add("보관방법");
		idxTitle.add("등급");
		idxTitle.add("품목제조보고");
		idxTitle.add("도축장");
		idxTitle.add("이력번호");
		idxTitle.add("바코드");
		idxTitle.add("기타번호");
		
		xlsxWriter xw = new xlsxWriter();
		
		List<PrintDataSetVO> labelList = stockService.selectProductLabel(osVO);
		
		File file = xw.labelXlsWriter(idxTitle, labelList, null, fileProperties.getProperty("file.upload.order_IO_excel.path.test"), "라벨지 데이터셋 ");
		
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	/**
	 * 
	 * @MethodName : epostDelivDelete
	 * @date : 2020. 1. 6.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @return
	 * @throws Exception 
	 * @메소드설명 : 부여된 송장 삭제시 사용
	 */
	@RequestMapping(value="/epost/delete.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String epostDelivDelete(@RequestParam List<String> orSerialSpecialNumberList,HttpServletRequest request) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		return epostService.deleteEpostDelivData(orSerialSpecialNumberList, EPOST_DELIV_DELETE, request.getRemoteAddr(), adminVo.getUsername());
		
	}

	@RequestMapping(value = "/test/mdb.do", method = RequestMethod.GET)
	public String mdbDBTest() throws Exception, SQLException {

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:/javatest/V_POSTALCODE.mdb" ,"3bgogi_admin", "ww123123");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT ZIPCD FROM VPOSTALCODE");
		while (rs.next()) {
		    System.out.println(rs.getString(1));
		}
		return null;
	}
	
	
	/**
	 * 
	 * @MethodName : selectDelivResultDate
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 송장발송 결과를 위한 날짜 선택창
	 */
	@RequestMapping(value="/epost/deliv_date.do", method=RequestMethod.GET)
	public String selectDelivResultDate(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		OrderSearchVO osVO = new OrderSearchVO();
		
		osVO.setDateStart(today);
		
		model.addAttribute("osVO",osVO);
		
		return "delivery/deliv_date";
	}
	
	/**
	 * 
	 * @MethodName : delivResult
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 발송기한을 기준으로 우체국에 송장조회하여 잘보내졌는지 확인
	 */
	@RequestMapping(value="/epost/deliv_sending_result.do", method=RequestMethod.GET)
	public String delivResult(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		List<OrdersVO> deliveryInfoList = null;
		
		try {
			deliveryInfoList = epostService.selectDeliveryInvoiceNumberByDate(osVO);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("deliveryInfoList",deliveryInfoList);
		model.addAttribute("osVO", osVO);
		
		return "delivery/deliv_sending_result";
	}
	
	
	@RequestMapping(value="/fresh_solution.do", method=RequestMethod.POST)
	public ModelAndView freshSolutionExcelDelivFile(@ModelAttribute OrderSearchVO osVO) {
		
		File file = epostService.freshSolutionInfo(osVO);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
}
