package com.gogi.proj.orders.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileUpload;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.gogi.proj.aligo.util.AligoSendingForm;
import com.gogi.proj.aligo.util.AligoVO;
import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.epost.model.EpostService;
import com.gogi.proj.excel.ReadOrderExcel;
import com.gogi.proj.excel.xlsxWriter;
import com.gogi.proj.matching.model.MatchingService;
import com.gogi.proj.orders.autocomplete.Godomall;
import com.gogi.proj.orders.config.model.OrderConfigService;
import com.gogi.proj.orders.config.model.StoreExcelDataSortingService;
import com.gogi.proj.orders.config.vo.ExceptAddressKeywordVO;
import com.gogi.proj.orders.config.vo.ReqFilterKeywordVO;
import com.gogi.proj.orders.config.vo.StoreCancleExcelDataSortingVO;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.util.OrderUtilityClass;
import com.gogi.proj.orders.vo.AdminOrderRecordVO;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PageUtility;
import com.gogi.proj.util.StringReplaceUtil;
import com.gogi.proj.util.naverMapApiUtil;
import com.mysql.jdbc.SQLError;

@Controller
@RequestMapping(value="/orders")
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Autowired
	private ReadOrderExcel readOrderExcel;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private MatchingService matchingService;
	
	@Autowired
	private StringReplaceUtil srUtil;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private StoreExcelDataSortingService sedsService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderConfigService orderConfigService;
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private EpostService epostService;
	
	private final int PROCESS_ORDER_INSERT = 1;
	private final int PROCESS_PRODUCT_MATCHING = 2;
	private final int PROCESS_OPTION_MATCHING = 3;
	private final int PROCESS_STOCK_CHECK = 4;
	private final int PROCESS_CANCLED_ORDER_CHECK = 5;
	
	/**
	 * @MethodName : insertOrdersPage
	 * @date : 2019. 3. 05.
	 * @author : Jeon KiChan
	 * @메소드설명 : 엑셀 형태의 주문서 혹은 제휴를 통한 json 형식의 데이터 입력을 받아오는 페이지
	 */
	@RequestMapping(value="/order_page.do", method=RequestMethod.GET)
	public String insertOrdersPageGet(Model model) {
		
		//판매처 값 가져오기
		List<StoreSectionVO> storeList = configService.selectStoreSectionList();
		
		//입력된 주문서 값 가져오기
		List<OrdersVO> insertStoreOrderList = ordersService.selectOrdersCountingByInputDate();
		
		
		model.addAttribute("order_process", PROCESS_ORDER_INSERT);
		model.addAttribute("storeList", storeList);
		model.addAttribute("insertStoreOrderList",insertStoreOrderList);
		
		return "orders/insert_orders";
	}
	
	
	/**
	 * @MethodName : insertOrdersPagePost
	 * @date : 2019. 3. 11.
	 * @author : Jeon KiChan
	 * @메소드설명 : 엑셀 형태의 주문서 받아오기 (스마트스토어)
	 */
	@RequestMapping(value="/order_page.do", method=RequestMethod.POST)
	public String insertOrdersPagePost(HttpServletRequest request, @ModelAttribute StoreExcelDataSortingVO sedsVO, @RequestParam boolean sendingDeadlineFlag, Model model) {
		
		String msg = "";
		String url = "/orders/order_page.do";
		
		String fileName = "";
		
		if(sedsVO.getSsFk() != 14) {
			
			try {
				
				fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("upload error! checking fileExtension or excel file");
				logger.info(e.getMessage());
				msg = "파일 업로드 실패. 로그를 확인해주세요.";
				
				model.addAttribute("msg", msg);
				model.addAttribute("url",url);
				
				return "common/message";
			}
		}

		List<OrdersVO> orderList= null;
		
		StoreSectionVO ssVO = new StoreSectionVO();
		
		ssVO.setSsPk(sedsVO.getSsFk());
		
		StoreExcelDataSortingVO sedsData = sedsService.selectStoreExcelDataSorting(ssVO);
		
		
		try {
			if(sedsVO.getSsFk() == 3) {				
				orderList = readOrderExcel.readOrderExcelDataToXLS(fileName, sedsVO.getSsFk(), sendingDeadlineFlag);
				
			}else if(sedsVO.getSsFk() == 14) {
				Godomall gm = new Godomall();

				orderList = gm.getGodomallOrders(sedsVO.getSsFk());

			}else {
				orderList = readOrderExcel.readOrderExcelData(fileName, sedsVO.getSsFk(), sedsData, sendingDeadlineFlag);
				
			}
			
		}catch(NullPointerException nulle) {
			msg = "데이터 값이 없습니다.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			return "common/message";
			
		}
		
		if(orderList.get(0).getSsFk() == 17) {
			for(int i = 0; i<orderList.size(); i++) {
			
			
			orderList.get(i).setOrTotalPrice((orderList.get(i).getOrProductPrice()+orderList.get(i).getOrProductOptionPrice())*orderList.get(i).getOrAmount());
			orderList.get(i).setOrProductOrderNumber(orderList.get(i).getOrOrderNumber()+"-"+i);
			}
		}
		int [] result = ordersService.insertOrderData(orderList, sedsVO.getSsFk());
		
		//고객 필터링 데이터
		List<IrregularOrderVO> iroList = ordersService.selectIrregularOrdersNotFiltered();
		
		//걸린 필터링 데이터추가
		List<IrregularOrderVO> checkingIroList = new ArrayList<IrregularOrderVO>();
		
		//고객 필터링 부분
		for(int counting = 0; counting < orderList.size(); counting++) {
			
			for(int iroCounting = 0; iroCounting < iroList.size(); iroCounting++) {
				
				if(orderList.get(counting).getOrBuyerContractNumber1().equals(iroList.get(iroCounting).getIroPhoneNumber())) {
					
					if( orderList.get(counting).getOrBuyerName().equals(iroList.get(iroCounting).getIroName()) ) {
						
						if(checkingIroList.size() == 0) {
							checkingIroList.add(iroList.get(iroCounting));
							
						}else{							
							for(int duplCounting = 0; duplCounting < checkingIroList.size(); duplCounting++) {
								
								if(checkingIroList.get(duplCounting).getIroPhoneNumber().equals(iroList.get(iroCounting).getIroPhoneNumber())) {
									break;
									
								}else {
									checkingIroList.add(iroList.get(iroCounting));
									
								}
							}//for
						}//if
					}//if
				}//if
			}//for
		}//for
		//고객 필터링 끝
		
		List<StoreSectionVO> storeList = configService.selectStoreSectionList();
		
		model.addAttribute("storeList", storeList);
		
		model.addAttribute("insertResult", result[0]);
		model.addAttribute("duplResult",result[1]);
		model.addAttribute("mergedSuccessedResult",result[2]);
		model.addAttribute("checkingIroList",checkingIroList);
		
		//자동으로 매칭 시키기
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		matchingService.matchingsProductAndOrders(request.getRemoteAddr(), adminVo.getUsername());
		
		
		return "orders/insert_orders_result";
		
	}
	
	/**
	 * @MethodName : insertSmartStoreSendingOrdersPageGet
	 * @date : 2019. 9. 11.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 발송대기중 주문서가 아닌 배송중에 있는 주문서를 넘길 때 insert page
	 * 			예외상황 수수료, 발주확인일 등이 빠지게 됌
	 */
	@RequestMapping(value="/smart_store_sending_order_insert.do", method=RequestMethod.GET)
	public String insertSmartStoreSendingOrdersPageGet() {
		
		return "orders/smart_store_sending_order_insert";
	}
	
	/**
	 * @MethodName : insertSmartStoreSendingOrdersPagePost
	 * @date : 2019. 9. 11.
	 * @author : Jeon KiChan
	 * @param request
	 * @param ssFk
	 * @param model
	 * @return
	 * @메소드설명 : 발송대기중 주문서가 아닌 배송중에 있는 주문서를 넘길 때  insert page
	 */
	@RequestMapping(value="/smart_store_sending_order_insert.do", method=RequestMethod.POST)
	public String insertSmartStoreSendingOrdersPagePost(HttpServletRequest request, Model model) {
		String msg = "";
		String url = "/orders/smart_store_sending_order_insert.do";

		String fileName = "";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("upload error! checking fileExtension or excel file");
			logger.info(e.getMessage());
			msg = "파일 업로드 실패. 로그를 확인해주세요.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";
		}

		List<OrdersVO> orderList= null;
		
		try {

			orderList = readOrderExcel.readOrderExcelDataToXLSForSmartStoreSendingData(fileName);
			
		}catch(NullPointerException nulle) {
			msg = "데이터 값이 없습니다.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			return "common/message";
			
		}
		
		
		int updateResult = 0;
		
		updateResult = epostService.updateFreshSolutionInvoiceNumber(orderList);

		model.addAttribute("updateResult", updateResult);
		
		//자동으로 매칭 시키기
		//matchingService.matchingsProductAndOrders();
		
		
		return "orders/insert_orders_result";
		
	}
	
	// 고객 검색 부분 시작
	
	/**
	 * @MethodName : searchCustomerOrders
	 * @date : 2019. 7. 01.
	 * @author : Jeon KiChan
	 * @메소드설명 : 고객 검색 페이지 들어가기
	 */
	@RequestMapping(value="/search/customer_orders.do", method=RequestMethod.GET)
	public String searchCustomerOrders(@ModelAttribute OrderSearchVO osVO, Model model){

		if(osVO.getDateType() == null) {
			osVO.setDateType("or_regdate");
		}
		
		if(osVO.getSearchKeyword() != null && ( osVO.getSearchType().equals("orderNames") || osVO.getSearchType().equals("orderNum"))) {
			String [] searchList = osVO.getSearchKeyword().split(",");
			List<String> searchLists = new ArrayList<String>();
			for(int i =0; i<searchList.length; i++) {
				searchLists.add(searchList[i]);
			}
			osVO.setSearchKeywordList(searchLists);
		}
		
		int totalRecord = ordersService.newSearchCustomerOrderInfoCounting(osVO);
		
		/*if(osVO.getInsertingCount() == null || osVO.getInsertingCount().equals("")) {
			osVO.setInsertingCount("0");
		}*/
		
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
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<OrdersVO> orderList = ordersService.newSearchCustomerOrderInfo(osVO);
		List<StoreSectionVO> ssList =configService.selectStoreSectionList();
		List<OrdersVO> insertStoreOrderList = ordersService.selectOrdersCountingByInputDate();
		List<OrdersVO> invoiceNum = ordersService.selectCreateInvoiceNum();
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		
		model.addAttribute("invoiceNum", invoiceNum);
		model.addAttribute("insertStoreOrderList", insertStoreOrderList);
		model.addAttribute("ssList", ssList);
		model.addAttribute("orderList",orderList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("edtList", edtList);
		
		return "orders/cs/search_cs";
	}
	
	
	/**
	 * 
	 * @MethodName : searchCustomerExcelFileDownload
	 * @date : 2020. 10. 16.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 고객 검색 결과 엑셀파일 다운로드
	 */
	@RequestMapping(value="/search/customer_orders_excel.do", method=RequestMethod.POST)
	public ModelAndView searchCustomerExcelFileDownload(@ModelAttribute OrderSearchVO osVO, Model model){
		
		if(osVO.getDateType() == null) {
			osVO.setDateType("or_regdate");
		}
		
		if(osVO.getSearchKeyword() != null && ( osVO.getSearchType().equals("orderNames") || osVO.getSearchType().equals("orderNum"))) {
			String [] searchList = osVO.getSearchKeyword().split(",");
			List<String> searchLists = new ArrayList<String>();
			for(int i =0; i<searchList.length; i++) {
				searchLists.add(searchList[i]);
			}
			osVO.setSearchKeywordList(searchLists);
		}
		
		int totalRecord = ordersService.newSearchCustomerOrderInfoCounting(osVO);
		
		/*if(osVO.getInsertingCount() == null || osVO.getInsertingCount().equals("")) {
			osVO.setInsertingCount("0");
		}*/
		
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
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		List<OrdersVO> orderList = ordersService.newSearchCustomerOrderInfoToExcelFile(osVO);
		xlsxWriter xw = new xlsxWriter();
		
		File resultFile = xw.csSearchResultExcelFile(orderList);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", resultFile);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);

		return mav;
		
	}
	
	
	/**
	 * 
	 * @MethodName : searchCustomerOrderDetailResult
	 * @date : 2019. 8. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고객 검색부분에서 주문서 확인시에 주문 상품 결과를 리턴
	 */
	@RequestMapping(value="/search/customer_order_detail.do", method=RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> searchCustomerOrderDetailResult(@ModelAttribute OrdersVO orVO){
		logger.info("고유번호 = {}",orVO.getOrSerialSpecialNumber());
		List<OrdersVO> orderDetailList = ordersService.selectCustomerOrderProductInfoDetail(orVO);
		
		return orderDetailList;
	}
	
	/**
	 * 
	 * @MethodName : deleteOrders
	 * @date : 2019. 8. 26.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @return
	 * @메소드설명 : 묶음번호 목록을 가져와서 고유번호 값을 찾은 뒤 삭제처리
	 */
	@RequestMapping(value="/delete/customer_order.do", method=RequestMethod.POST)
	@ResponseBody
	public int deleteOrders(HttpServletRequest request ,@ModelAttribute OrderSearchVO osVO ) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = ordersService.deleteOrders(osVO.getOrSerialSpecialNumberList(), request.getRemoteAddr(), adminVo.getUsername());
		
		return result;
	}
	
	/**
	 * 
	 * @MethodName : devideOrderPageGet
	 * @date : 2019. 10. 18.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 주문서 나누기 기능
	 */
	@RequestMapping(value="/config/devide.do", method=RequestMethod.GET)
	public String devideOrderPageGet(@ModelAttribute OrdersVO orVO, Model model) {
		
		List<OrdersVO> orderList = ordersService.selectCustomerOrderProductInfoDetail(orVO);
		model.addAttribute("orderList",orderList);
		
		return "orders/config/devide_order";
	}
	
	/**
	 * 
	 * @MethodName : devideOrderPagePost
	 * @date : 2019. 10. 22.
	 * @author : Jeon KiChan
	 * @param orPkList
	 * @return
	 * @메소드설명 : 주문서 고유값으로 주문나누기
	 */
	@RequestMapping(value="/config/devide.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean devideOrderPagePost(HttpServletRequest request ,@RequestParam int [] orPkList) {
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		return ordersService.devideOrders(orPkList, request.getRemoteAddr(), adminVo.getUsername());
			
	}
	
	/**
	 * 
	 * @MethodName : selfDevideOrderPageGet
	 * @date : 2019. 11. 6.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 주문서 상품 개수 나누기 페이지
	 */
	@RequestMapping(value="/config/self_devide_order.do", method=RequestMethod.GET)
	public String selfDevideOrderPageGet(@ModelAttribute OrdersVO paramOrVO, 
											@RequestParam(required=false, defaultValue="1") boolean closing,
											Model model) {
		
		OrdersVO orVO = ordersService.selectOrderQtyByPk(paramOrVO);
		model.addAttribute("orVO", orVO);
		model.addAttribute("closing", closing);
		
		return "orders/config/self_devide_order";
	}
	
	/**
	 * 
	 * @MethodName : selfDevideOrderPagePost
	 * @date : 2019. 11. 12.
	 * @author : Jeon KiChan
	 * @param orPk
	 * @param orderDevideType
	 * @param radioDevideValue
	 * @param selfDevideOriginalValue
	 * @param selfDevideValue
	 * @return
	 * @메소드설명 : 주문서 상품 개수 나누기
	 */
	@RequestMapping(value="/config/self_devide_order.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean selfDevideOrderPagePost(
										@RequestParam int orPk,
										@RequestParam int orderDevideType,
										@RequestParam(defaultValue="0", required=false) int radioDevideValue,
										@RequestParam(defaultValue="0", required=false) int selfDevideOriginalValue,
										@RequestParam(defaultValue="0", required=false) int selfDevideValue,
										HttpServletRequest request) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			AdminVO adminVo = (AdminVO)auth.getPrincipal();
			
			ordersService.selfDevideOrders(orPk, orderDevideType, radioDevideValue, selfDevideOriginalValue, selfDevideValue, request.getRemoteAddr(), adminVo.getUsername());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * 
	 * @MethodName : combineOrderGet
	 * @date : 2019. 11. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 합포할 주문서 페이지 및 목록 가져오기
	 */
	@RequestMapping(value="/config/combine_order.do", method=RequestMethod.GET)
	public String combineOrderGet(@RequestParam List<String> orSerialSpecialNumberList, Model model) {
		
		List<OrdersVO> combineOrderList = ordersService.selectCombineInfoBySerialSpecialNumber(orSerialSpecialNumberList);
		
		model.addAttribute("combineOrderList", combineOrderList);
		return "orders/config/combine_order";
	}
	
	
	/**
	 * 
	 * @MethodName : combineOrderPost
	 * @date : 2019. 11. 21.
	 * @author : Jeon KiChan
	 * @param orSerialList
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 합포 하기
	 */
	@RequestMapping(value="/config/combine_order.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean combineOrderPost(@RequestParam List<String> orSerialList, @ModelAttribute OrdersVO orVO, HttpServletRequest req) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		return ordersService.updateCombineOrders(orSerialList, orVO, req.getRemoteAddr(), adminVo.getUsername());
	}
	
	
	/**
	 * 
	 * @MethodName : changeProductOptionPageGet
	 * @date : 2019. 11. 27.
	 * @author : Jeon KiChan
	 * @param ordersVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 변경 페이지
	 */
	@RequestMapping(value="/config/change_product_option.do", method=RequestMethod.GET)
	public String changeProductOptionPageGet(@ModelAttribute OrdersVO ordersVO, Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("ordersVO", ordersVO);
		model.addAttribute("ccList", ccList);
		
		return "orders/config/change_product_option";
	}
	
	
	/**
	 * 
	 * @MethodName : changeProductOptionPagePost
	 * @date : 2019. 11. 29.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 상품 변경 
	 */
	@RequestMapping(value="/config/change_product_option.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean changeProductOptionPagePost(HttpServletRequest request ,@ModelAttribute OrdersVO orVO) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		boolean changeStat = false;
		int result = ordersService.changeProductAndOptionByOrPk(orVO, request.getRemoteAddr(), adminVo.getUsername());
		
		if(result > 0) {
			changeStat = true;
			
		}else {
			changeStat = false;
			
		}
		
		return changeStat;
	}
	
	/**
	 * 
	 * @MethodName : addProductOptionPageGet
	 * @date : 2019. 12. 4.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @param model
	 * @return
	 * @메소드설명 : 상품 추가 페이지 들어가기
	 */
	@RequestMapping(value="/config/add_product_option.do", method=RequestMethod.GET)
	public String addProductOptionPageGet(@RequestParam List<String> orSerialSpecialNumberList, Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("orSerialSpecialNumberList", orSerialSpecialNumberList);
		model.addAttribute("ccList", ccList);
		
		return "orders/config/add_product_option";
	}
	
	
	/**
	 * 
	 * @MethodName : addProductOptionPagePost
	 * @date : 2019. 12. 4.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumbers
	 * @param orVO
	 * @return
	 * @메소드설명 : 상품 추가 결과 ajax
	 */
	@RequestMapping(value="/config/add_product_option.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean addProductOptionPagePost(@RequestParam List<String> orSerialSpecialNumbers, @ModelAttribute OrdersVO orVO) {
		
		int result = ordersService.addProductAndOptionIntoOrders(orSerialSpecialNumbers, orVO);
		
		if(result > 0) {
			return true;
			
		}else {
			return false;
			
		}
		
	}
	
	/**
	 * 
	 * @MethodName : deleteOrdersOneGet
	 * @date : 2019. 12. 5.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 단일 선택 주문서 삭제하기
	 */
	@RequestMapping(value="/delete/order_one.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean deleteOrdersOneGet(@ModelAttribute OrdersVO orVO) {
		
		
		boolean result = stockService.updateProductChangeValues(orVO);
		
		if(result == false) {
			return false;
		}
		
		return ordersService.deleteOrdersOne(orVO);
	}
	
	
	/**
	 * 
	 * @MethodName : changeSendingDeadlinePageGet
	 * @date : 2020. 6. 8.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 발송일 변경 페이지
	 */
	@RequestMapping(value="/change/deadline.do", method=RequestMethod.GET)
	public String changeSendingDeadlinePageGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(today));
			
		}

		model.addAttribute("osVO", osVO);
		
		
		return "orders/cs/change_sending_deadline_form";
	}
	
	
	/**
	 * 
	 * @MethodName : changeSendingDeadlinePagePost
	 * @date : 2020. 6. 8.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 발송일 변경
	 */
	@RequestMapping(value="/change/deadline.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean changeSendingDeadlinePagePost(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = ordersService.changeSendingDeadline(osVO, request.getRemoteAddr(), adminVo.getUsername());
		
		if(result != 0) {
			
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @MethodName : createNewOrderGet
	 * @date : 2020. 6. 10.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 새 주문서 작성
	 */
	@RequestMapping(value="/create/order.do", method=RequestMethod.GET)
	public String createNewOrderGet(@ModelAttribute OrdersVO orVO, Model model) {
		
		List<StoreSectionVO> ssList =configService.selectStoreSectionList();
		
		if(orVO.getOrSerialSpecialNumber() != null) {
			OrdersVO addressInfo = ordersService.selectBuyerAddrInfo(orVO);
			model.addAttribute("addressInfo", addressInfo);
			
		}
		
		model.addAttribute("ssList", ssList);
		
		return "orders/cs/create_new_order";
	}
	
	@RequestMapping(value="/create/order.do", method=RequestMethod.POST)
	public String createNewOrderPost(@ModelAttribute OrdersVO orVOParam, Model model, @RequestParam int smsSendFlag, @RequestParam boolean orDepositFlag) {
		String msg = "주문서 등록 완료";
		boolean closing = true;
		boolean reload = true;
		
		OrdersVO orVO = null;
		int resulst = orVOParam.getOrAmountList().size();
		int successResult = 0;
		
		List<OrdersVO> orList = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = new Date();
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		
		String orderNum = sdf.format(today);
		orVOParam.setOrOrderNumber(orderNum);
		orVOParam.setOrRegdate(ts);
		int countNum = 0;
		
		
		for(int i = 0; i < orVOParam.getOrAmountList().size(); i++) {
			
			try {
				if(orVOParam.getOrAmountList().get(i) != null) {					
					orVO = orVOParam.copy();
					orVO.setOrProductOrderNumber("개인-"+orderNum+countNum);
					orVO.setOrAmount(orVOParam.getOrAmountList().get(i));
					orVO.setOrProduct(orVOParam.getOrProductList().get(i));
					orVO.setOrProductOption(orVOParam.getOrProductOptionList().get(i));
					orVO.setOrTotalPrice(orVOParam.getOrTotalPriceList().get(i));
					orVO.setOrDepositFlag(orDepositFlag);
					orList.add(orVO);
					
					successResult++;
					countNum++;
					
				}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("원본 파일 복사 오류", e);
			}
			
		}
		
		if(orList.size() != 0) {			
			int [] result = ordersService.insertOrderData(orList, orVOParam.getSsFk());
			
			//문자 발송
			if(smsSendFlag == 1) {
				AligoSendingForm asf = new AligoSendingForm();
				
				AligoVO aligo = asf.aligoSendingForm(orList);
				String res = asf.smsMsg(aligo);
				System.out.println(res);
			}
			
		}else {
			msg = "주문 등록 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("closing", closing);
		model.addAttribute("reload", reload);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : searchTab
	 * @date : 2020. 6. 10.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 주소 검색 새창 열기
	 */
	@RequestMapping(value="/search/tab.do", method=RequestMethod.GET)
	public String searchTab() {
		
		return "inc/search_address";
	}
	
	
	/**
	 * 
	 * @MethodName : searchAddressWithMap
	 * @date : 2020. 6. 10.
	 * @author : Jeon KiChan
	 * @param address
	 * @param model
	 * @return
	 * @메소드설명 : 주소 검색 새창에서 주소를 입력할 때 결과값을 카카오맵에서 json parsing하여 map으로 건내줌
	 */
	@RequestMapping(value="/search/search_address.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchAddressWithMap(@RequestParam String address, Model model) {
		
		naverMapApiUtil nma = new naverMapApiUtil();
		String result = "";
		try {
			result = nma.getCoordiante(address);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 throw new RuntimeException("API 요청과 응답 실패", e);
		}
		
		Map<String, Object> resultMap =  null; 
		
		try {
			resultMap = nma.returnJson(result);
			model.addAttribute("resultMap",resultMap);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new  RuntimeException("변환 오류 ", e);
		}
		
		return resultMap;
		
	}
	
	/**
	 * 
	 * @MethodName : addCreatetedOrderProduct
	 * @date : 2020. 6. 10.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 새 주문서에 상품 추가하기
	 */
	@RequestMapping(value="/cs/add_create_order_product.do", method=RequestMethod.GET)
	public String addCreatetedOrderProduct(Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("ccList", ccList);
		
		return "orders/cs/add_create_order_product";
	}
	
	//고객 검색 부분 끝
	
	// 주문서 고객 체크사항
	/**
	 * @MethodName : irregularOrdersList
	 * @date : 2019. 8. 02.
	 * @author : Jeon KiChan
	 * @메소드설명 : 주문서 고객 체크사항 목록
	 */
	@RequestMapping(value="/irregular/list.do", method=RequestMethod.GET)
	public String irregularOrdersList(Model model){
		
		List<IrregularOrderVO> iroList = ordersService.selectIrregularOrdersNotFiltered();
		
		model.addAttribute("iroList", iroList);
		
		return "orders/irregular/list";
	}
	
	
	/**
	 * 
	 * @MethodName : irregularOrdersAllList
	 * @date : 2019. 8. 7.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 고객 필터링 전체 목록
	 */
	@RequestMapping(value="/irregular/all_list.do", method=RequestMethod.GET)
	public String irregularOrdersAllList(Model model) {
		
		List<IrregularOrderVO> iroAllList = ordersService.selectIrregularOrders();
		
		model.addAttribute("iroAllList", iroAllList);
		
		return "orders/irregular/all_list";
	}
	
	/**
	 * @MethodName : irregularOrdersAdd
	 * @date : 2019. 8. 06.
	 * @author : Jeon KiChan
	 * @메소드설명 : 주문서 고객 체크사항 추가하기 페이지
	 */
	@RequestMapping(value="/irregular/add.do", method=RequestMethod.GET)
	@Transactional
	public String irregularOrdersAdd(Model model){
		
		//등록된 판매처 정보
		List<StoreSectionVO> storeList = configService.selectStoreSectionList();
		
		model.addAttribute("storeList",storeList);
		
		return "orders/irregular/add";
	}
	
	/**
	 * @MethodName : irregularOrdersAddPost
	 * @date : 2019. 8. 6.
	 * @author : Jeon KiChan
	 * @param iroVO
	 * @param model
	 * @메소드설명 : 고객 필터링 추가하기 기능 - post
	 */
	@RequestMapping(value="/irregular/add.do", method=RequestMethod.POST)
	@Transactional
	public String irregularOrdersAddPost(@ModelAttribute IrregularOrderVO iroVO, Model model) {
		
		String msg = "";
		String url = "";
		
		int result = ordersService.addIrregularOrders(iroVO);
		
		if(result == 0) {
			msg = "DB에 값이 제대로 들어가지 않았습니다. ";
			url = "/orders/irregular/add.do";
		}else {
			msg = "고객 필터링 데이터 입력 완료";
			url = "/orders/irregular/list.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : irregularOrderChecking
	 * @date : 2019. 8. 7.
	 * @author : Jeon KiChan
	 * @param iroPk
	 * @param model
	 * @return
	 * @메소드설명 : 필터링 데이터 확인처리 하기
	 */
	@RequestMapping(value="/irregular/checking.do", method=RequestMethod.GET)
	public String irregularOrderChecking(@RequestParam int iroPk, Model model) {
		
		String msg = "";
		String url = "/orders/irregular/list.do";
		
		IrregularOrderVO iroVO = new IrregularOrderVO();
		
		iroVO.setIroPk(iroPk);
		
		int result= 0;
		
		try {
			
			result = ordersService.successedFiltering(iroVO);
		}catch(Exception e) {
			e.printStackTrace();
			msg = "DB 오류 관리자 문의";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		if(result != 0) {
			msg = " 확인 완료 ";
		}else {
			msg = "확인 되지 않음. 관리자에게 문의해주세요.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/irregular/delete.do", method=RequestMethod.GET)
	public String deleteIrregularOrder(@RequestParam int iroPk, Model model) {
		
		String msg = "";
		String url = "/orders/irregular/all_list.do";
		
		IrregularOrderVO iroVO = new IrregularOrderVO();
		
		iroVO.setIroPk(iroPk);
		
		int result= 0;
		
		try {
			
			result = ordersService.deleteFilteringData(iroVO);
		}catch(Exception e) {
			e.printStackTrace();
			msg = "DB 오류 관리자 문의";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		if(result != 0) {
			msg = " 해당 데이터 삭제 완료 ";
		}else {
			msg = "삭제 되지 않음. 관리자에게 문의해주세요.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	//주문서 고객 체크사항 끝
	
	
	/**
	 * 
	 * @MethodName : devideExcelGiftSetGet
	 * @date : 2020. 6. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 대용량 주소 직접 넣기
	 */
	@RequestMapping(value="/devide/gift.do", method=RequestMethod.GET)
	public String devideExcelGiftSetGet(@ModelAttribute OrdersVO osVO, Model model) {
		
		model.addAttribute("osVO", osVO);
		
		return "orders/config/devide_gift";
	}
	
	
	/**
	 * 
	 * @MethodName : devideExcelGiftSetPost
	 * @date : 2020. 6. 29.
	 * @author : Jeon KiChan
	 * @param request
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 대용량 주소 엑셀 파일 넣기
	 */
	@RequestMapping(value="/devide/gift.do", method=RequestMethod.POST)
	public String devideExcelGiftSetPost(HttpServletRequest request, @ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		boolean closing = true;
		boolean reload = true;

		String fileName = "";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (IllegalStateException e) {
			throw new RuntimeException("매개 변수를 확인", e);
			
		} catch ( IOException e) {
			throw new RuntimeException("파일 입력 오류", e);
		}

		
		OrdersVO originalOrVO = ordersService.selectOrdersByPk(orVO.getOrPk());

		ordersService.deleteExcelGiftOrderByOrFk(originalOrVO);
		
		int [] result = ordersService.updateExcelDivOrders(originalOrVO, fileName);

		if(result == null || result[0] == 0) {
			msg = "엑셀의 개수와 개수가 다릅니다. 다시 한 번 확인해주세요.";
			model.addAttribute("msg", msg);
			model.addAttribute("closing", closing);
			return "common/message";
		}
		
		msg = result[0]+" 개 등록 완료. 페이지를 새로고침 합니다.";
		
		model.addAttribute("msg", msg);
		model.addAttribute("closing", closing);
		model.addAttribute("reload", reload);
		
		return "common/message";
		
	}
	
	/**
	 * 
	 * @MethodName : cancledOrdersCheck
	 * @date : 2020. 6. 29.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 취소 주문서 체크 페이지
	 */
	@RequestMapping(value="/cancled_order_check.do", method=RequestMethod.GET)
	public String cancledOrdersCheckGet(Model model) {
		//판매처 값 가져오기
		List<StoreSectionVO> storeList = configService.storeListOrderInTwoMonth();

		model.addAttribute("storeList", storeList);
		model.addAttribute("order_process", PROCESS_CANCLED_ORDER_CHECK);
		return "orders/cancled_order_check";
	}

	
	/**
	 * 
	 * @MethodName : cancledOrdersCheckPost
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param request
	 * @param scedsVO
	 * @param model
	 * @return
	 * @메소드설명 : 취소 주문 확인하기
	 */
	@RequestMapping(value="/cancled_order_check.do", method=RequestMethod.POST)
	public String cancledOrdersCheckPost(HttpServletRequest request,@ModelAttribute StoreSectionVO ssVO, Model model) {
		//판매처 값 가져오기
		
		String msg = "";
		String url = "/cancled_order_check.do";
		String fileName = "";
		
		boolean cancleCheckFlag = true;
		
		if(ssVO.getSsPk() != 14) {
			
			try {
				
				fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("upload error! checking fileExtension or excel file");
				logger.info(e.getMessage());
				msg = "파일 업로드 실패. 로그를 확인해주세요.";
				
				model.addAttribute("msg", msg);
				model.addAttribute("url",url);
				
				return "common/message";
			}
		}
		
		
		
		StoreCancleExcelDataSortingVO scedsVO = sedsService.selectStoreCancleExcelDataSorting(ssVO);
		
		OrdersVOList orVO = new OrdersVOList();
		orVO.setSsName(ssVO.getSsPk()+"");
		
		List<OrdersVO> cancledOrderList = null;
		if(ssVO.getSsPk() != 14) {
			List<OrdersVO> orList = readOrderExcel.cancledExcelFile(fileName, scedsVO);
			orVO.setOrVoList(orList);
			cancledOrderList = sedsService.cancledOrderSearch(orVO);
			
		}else {
			Godomall gm = new Godomall();
			OrdersVOList orList = gm.getGodomallCancledOrders(ssVO.getSsPk());
			
			if(orList.getOrVoList().size() != 0) {				
				//판매처 고유값을 임시로 판매처명으로 넣음
				orList.setSsName(ssVO.getSsPk()+"");
				cancledOrderList = sedsService.cancledOrderSearch(orList);
			}
		}
		

		
		
		List<StoreSectionVO> storeList = configService.storeListOrderInTwoMonth();

		model.addAttribute("storeList", storeList);
		model.addAttribute("order_process", PROCESS_CANCLED_ORDER_CHECK);
		model.addAttribute("cancledOrderList", cancledOrderList);
		model.addAttribute("cancleCheckFlag", cancleCheckFlag);
		
		return "orders/cancled_order_check";
	}
	
	
	/**
	 * 
	 * @MethodName : cancledOrderDelete
	 * @date : 2020. 7. 1.
	 * @author : Jeon KiChan
	 * @param orVOList
	 * @return
	 * @메소드설명 : 주문 취소처리 하기
	 */
	@RequestMapping(value="/update_cancled_order.do", method=RequestMethod.POST)
	@ResponseBody
	public int cancledOrderDelete(@ModelAttribute OrdersVOList orVOList) {
		
		int result = sedsService.updateCancledOrder(orVOList.getOrVoList());
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : orderOutputPost
	 * @date : 2020. 7. 2.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @return
	 * @메소드설명 : 고객 검색 페이지에서 발송처리 작업
	 */
	@RequestMapping(value="/order_output.do", method=RequestMethod.POST)
	@ResponseBody
	public int orderOutputPost(HttpServletRequest request, @ModelAttribute OrdersVOList orVOList) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = ordersService.updateOutputDateBySerialNumber(orVOList, request.getRemoteAddr(), adminVo.getUsername());
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : orderOutputCancledPost
	 * @date : 2020. 7. 14.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @return
	 * @메소드설명 : 발송 취소 기능
	 */
	@RequestMapping(value="/order_output_canled.do", method=RequestMethod.POST)
	@ResponseBody
	public int orderOutputCancledPost(HttpServletRequest request, @ModelAttribute OrdersVOList orVOList) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = ordersService.outputCancledBySerialNumber(orVOList, request.getRemoteAddr(), adminVo.getUsername());
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : editRefundOrder
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param paramOrVO
	 * @param model
	 * @return
	 * @메소드설명 : 환불 처리 페이지 들어가기
	 */
	@RequestMapping(value="/refund_order.do", method=RequestMethod.GET)
	public String editRefundOrderGet(@ModelAttribute OrdersVO paramOrVO, Model model) {
		
		OrdersVO orVO = ordersService.searchRefundOrder(paramOrVO);
		
		model.addAttribute("orVO", orVO);
		return "orders/config/refund_order";
	}
	
	
	/**
	 * 
	 * @MethodName : editRefundOrderPost
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 환불 처리하기
	 */
	@RequestMapping(value="/refund_order.do", method=RequestMethod.POST)
	public String editRefundOrderPost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/orders/refund_order.do";
		boolean closing = false;
		
		int result = ordersService.orderRefundsEdit(orVO);
		
		if(result > 0) {
			msg = "환불 처리 완료";
			closing = true;
		}else {
			msg = "환불 처리 실패";
			closing = false;
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		model.addAttribute("closing", closing);
		
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : customerSpecialRequestGet
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param paramOrVO
	 * @param model
	 * @return
	 * @메소드설명 : 특별 요청 사항 확인 및 수정, 저장 페이지 들어가기
	 */
	@RequestMapping(value="/special_request.do", method=RequestMethod.GET)
	public String customerSpecialRequestGet(@ModelAttribute OrdersVO paramOrVO, Model model) {
		
		OrdersVO orVO = ordersService.selectCustomerSpecialRequest(paramOrVO);
		String msg="해당 상품은 무게를 합쳐서 포장이 불가능합니다";
		
		//매칭 개수 확인
		int orderMatchingCounting = matchingService.selectOrderMatchingCounting(paramOrVO);
		boolean orderCombineFlag = false;
		
		//매칭된 옵션 중 합포 가능한 옵션이 몇개 있는지 체크
		int orderMeatCount= matchingService.selectOrderMatchingIncMeatCounting(paramOrVO);
	
		
		logger.info("매칭개수 => {}, 합포 가능 옵션 개수 => {}",orderMatchingCounting,orderMeatCount);
		//다중 매칭이 아닌 단일 매칭일 경우
		if(orderMatchingCounting == 1) {
			if(orderMeatCount == 1) {
				orderCombineFlag=true;
				msg="";
				
			}else {
				orderCombineFlag=false;
				
			}
			
		}else {
			orderCombineFlag=false;
					
		}
		
		model.addAttribute("orderCombineFlag", orderCombineFlag);
		model.addAttribute("orVO", orVO);
		model.addAttribute("msg", msg);
		
		return "orders/config/special_request";
	}
	
	
	/**
	 * 
	 * @MethodName : customerSpecialRequestPost
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param paramOrVO
	 * @param model
	 * @return
	 * @메소드설명 : 특별 요청 사항 확인 및 수정, 저장하기
	 */
	@RequestMapping(value="/special_request.do", method=RequestMethod.POST)
	public String customerSpecialRequestPost(@ModelAttribute OrdersVO paramOrVO, Model model) {
		
		logger.info("특별 요청 사항 => {}, 합포 여부 => {}", paramOrVO.getOrRequest(), paramOrVO.getOrRequestCombFlag());
		
		String msg = "";
		String url = "/orders/special_request.do";
		boolean closing = false;
		
		int result = ordersService.addCustomerSpecialRequest(paramOrVO);
			
		if(result > 0) {
			msg="고객 특별 요청사항 저장 완료";
			closing = true;
		}else {
			msg="고객 특별 요청사항 저장 실패";
			closing = false;
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		model.addAttribute("closing", closing);
		
		return "common/message";
		
	}
	
	
	/**
	 * 
	 * @MethodName : deliveryMessageCheckGet
	 * @date : 2020. 7. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지 확인하기 ( 완전 매칭된 상품만 확인가능 )
	 */
	@RequestMapping(value="/delivery_msg_check.do", method=RequestMethod.GET)
	public String deliveryMessageCheckGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateType() == null) {
			osVO.setDateType("or_sending_deadline");
		}
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
		
		
		List<OrdersVO> orList = ordersService.selectDeliveryMsg(osVO);
		List<ReqFilterKeywordVO> rfkList = orderConfigService.selectUseReqFilterKeywordList();
		
		model.addAttribute("rfkList", rfkList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("orList", orList);
		
		return "orders/delivery_msg_check";
	}
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryInvoiceNumberGet
	 * @date : 2020. 7. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 수기 송장번호 입력페이지 들어가기
	 */
	@RequestMapping(value="/config/edit_deliv_num.do", method=RequestMethod.GET)
	public String updateDeliveryInvoiceNumberGet(@ModelAttribute OrdersVO orVO, Model model) {
		
		model.addAttribute("orVO", orVO);
		
		return "orders/config/update_deliv_num";
	}
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryInvoiceNumberPost
	 * @date : 2020. 7. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 수기 송장번호 입력 결과
	 */
	@RequestMapping(value="/config/edit_deliv_num.do", method=RequestMethod.POST)
	public String updateDeliveryInvoiceNumberPost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/orders/config/edit_deliv_num.do?orPk="+orVO.getOrPk();
		boolean closing = false;
		
		int result = ordersService.editDelivNum(orVO);
		
		if(result > 0) {
			msg = "송장번호 입력 완료";
			closing = true;
		}else {
			msg = "송장 입력 실패";
			closing = false;
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		model.addAttribute("closing", closing);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : devideMultiMatchingProductGet
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param paramOrVO
	 * @return
	 * @메소드설명 : 복수 상품 매칭으로 이뤄진 주문서 나눠주기 
	 */
	@RequestMapping(value="/multi_matching_devide.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String devideMultiMatchingProductGet(@ModelAttribute OrdersVO paramOrVO) {
		
		return ordersService.multiMatchingDevide(paramOrVO);
	}
	
	
	/**
	 * 
	 * @MethodName : deleteOrderByRegdate
	 * @date : 2020. 9. 24.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 판매처 번호와 주문등록일을 이용해서 일괄 삭제
	 */
	@RequestMapping(value="/delete_order_regdate.do", method=RequestMethod.GET)
	public String deleteOrderByRegdate(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "삭제완료";
		String url = "/orders/order_page.do";
		
		logger.info("regdate => {}, ss_fk => {}",orVO.getOrRegdate(),orVO.getSsFk());
		
		int result = ordersService.deleteOrdersByDate(orVO);
		if(result != 0) {
			msg = "삭제완료";
			url = "/orders/order_page.do";
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";			
		}else {
			msg = "삭제실패";
			url = "/orders/order_page.do";

			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";
		}

	}
	
	
	/**
	 * 
	 * @MethodName : checkDeposit
	 * @date : 2021. 2. 9.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 입금 확인처리하기
	 */
	@RequestMapping(value="/check_deposit.do", method=RequestMethod.GET)
	@ResponseBody
	public int checkDeposit(@ModelAttribute OrdersVO orVO) {
		return ordersService.checkDepositOrder(orVO);
	}
	
	
	/**
	 * 
	 * @MethodName : pickUpServiceGet
	 * @date : 2021. 2. 15.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 퀵서비스, 방문수령으로 변경하는 페이지
	 */
	@RequestMapping(value="/pick_up_service.do", method=RequestMethod.GET)
	public String pickUpServiceGet(@ModelAttribute OrdersVO orVO, Model model ) {
		
		model.addAttribute("orVO", orVO);
		
		return "orders/config/pick_up_service";
	}
	
	
	/**
	 * 
	 * @MethodName : pickUpServicePost
	 * @date : 2021. 2. 15.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 퀵서비스, 방문수령으로 변경
	 */
	@RequestMapping(value="/pick_up_service.do", method=RequestMethod.POST)
	public String pickUpServicePost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/";
		boolean closing = true;
		
		int result = ordersService.receiverPickUp(orVO);
		
		if(result > 0) {
			msg = "수령 상태 변경 완료";
			
		}else {
			msg = "수령 상태 변경 실패, 다시 한 번 확인해주세요";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		model.addAttribute("closing", closing);
		
		return "common/message";
		
	}
	
	/**
	 * 
	 * @MethodName : insertAdminOrderRecord
	 * @date : 2021. 3. 8.
	 * @author : Jeon KiChan
	 * @param aorVO 
	 * @return
	 * @메소드설명 : 주문서 특이사항 혹은 메모 기록하기 (cs 내역)
	 */
	@RequestMapping(value="/order_record.do", method=RequestMethod.POST)
	@ResponseBody
	public int insertAdminOrderRecord(@ModelAttribute AdminOrderRecordVO aorVO) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		aorVO.setAorAdminId(adminVo.getUsername());
		aorVO.setAorAdminName(adminVo.getAdminname());
		
		int result = ordersService.insertAdminOrderRecord(aorVO);
		
		return result;
		
	}
	
	/**
	 * 
	 * @MethodName : searchAdminOrderRecordListAjax
	 * @date : 2021. 3. 8.
	 * @author : Jeon KiChan
	 * @param orVO - orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 주문서 특이사항 혹은 메모 기록 가져오기 (cs 내역) - cs 검색에서 사용하는 ajax 형태
	 */
	@RequestMapping(value="/order_record_ajax.do", method=RequestMethod.GET)
	@ResponseBody
	public List<AdminOrderRecordVO> searchAdminOrderRecordListAjax(@ModelAttribute OrdersVO orVO){
		
		return ordersService.searchAdminOrderRecordBySerialSpecialNumber(orVO);
	}
	
	
	/**
	 * 
	 * @MethodName : searchAdminOrderRecordList
	 * @date : 2021. 3. 8.
	 * @author : Jeon KiChan
	 * @param orVO - orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 주문서 특이사항 혹은 메모 기록 가져오기 (cs 내역) - 송장 부여에서 바로 확인할 수 있도록 목록 부여
	 */
	@RequestMapping(value="/order_record.do")
	public String searchAdminOrderRecordList(@ModelAttribute OrdersVO orVO, Model model){
		
		List<AdminOrderRecordVO> aorList = ordersService.searchAdminOrderRecordBySerialSpecialNumber(orVO);
		
		if(aorList == null || aorList.size() == 0) {
			String msg = "cs 내역이 존재하지 않습니다";
			boolean closing = true;
			
			model.addAttribute("msg", msg);
			model.addAttribute("closing", closing);
			
			return "common/message";
			
		}
		
		model.addAttribute("aorList", aorList);
		
		return "orders/cs/admin_order_record";
	}
}
