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
	 * @??????????????? : ?????? ????????? ????????? ?????? ????????? ?????? json ????????? ????????? ????????? ???????????? ?????????
	 */
	@RequestMapping(value="/order_page.do", method=RequestMethod.GET)
	public String insertOrdersPageGet(Model model) {
		
		//????????? ??? ????????????
		List<StoreSectionVO> storeList = configService.selectStoreSectionList();
		
		//????????? ????????? ??? ????????????
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
	 * @??????????????? : ?????? ????????? ????????? ???????????? (??????????????????)
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
				msg = "?????? ????????? ??????. ????????? ??????????????????.";
				
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
			msg = "????????? ?????? ????????????.";
			
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
		
		//?????? ????????? ?????????
		List<IrregularOrderVO> iroList = ordersService.selectIrregularOrdersNotFiltered();
		
		//?????? ????????? ???????????????
		List<IrregularOrderVO> checkingIroList = new ArrayList<IrregularOrderVO>();
		
		//?????? ????????? ??????
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
		//?????? ????????? ???
		
		List<StoreSectionVO> storeList = configService.selectStoreSectionList();
		
		model.addAttribute("storeList", storeList);
		
		model.addAttribute("insertResult", result[0]);
		model.addAttribute("duplResult",result[1]);
		model.addAttribute("mergedSuccessedResult",result[2]);
		model.addAttribute("checkingIroList",checkingIroList);
		
		//???????????? ?????? ?????????
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
	 * @??????????????? : ??????????????? ???????????? ?????? ???????????? ?????? ???????????? ?????? ??? insert page
	 * 			???????????? ?????????, ??????????????? ?????? ????????? ???
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
	 * @??????????????? : ??????????????? ???????????? ?????? ???????????? ?????? ???????????? ?????? ???  insert page
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
			msg = "?????? ????????? ??????. ????????? ??????????????????.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";
		}

		List<OrdersVO> orderList= null;
		
		try {

			orderList = readOrderExcel.readOrderExcelDataToXLSForSmartStoreSendingData(fileName);
			
		}catch(NullPointerException nulle) {
			msg = "????????? ?????? ????????????.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			return "common/message";
			
		}
		
		
		int updateResult = 0;
		
		updateResult = epostService.updateFreshSolutionInvoiceNumber(orderList);

		model.addAttribute("updateResult", updateResult);
		
		//???????????? ?????? ?????????
		//matchingService.matchingsProductAndOrders();
		
		
		return "orders/insert_orders_result";
		
	}
	
	// ?????? ?????? ?????? ??????
	
	/**
	 * @MethodName : searchCustomerOrders
	 * @date : 2019. 7. 01.
	 * @author : Jeon KiChan
	 * @??????????????? : ?????? ?????? ????????? ????????????
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
	 * @??????????????? : ?????? ?????? ?????? ???????????? ????????????
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
	 * @??????????????? : ?????? ?????????????????? ????????? ???????????? ?????? ?????? ????????? ??????
	 */
	@RequestMapping(value="/search/customer_order_detail.do", method=RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> searchCustomerOrderDetailResult(@ModelAttribute OrdersVO orVO){
		logger.info("???????????? = {}",orVO.getOrSerialSpecialNumber());
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
	 * @??????????????? : ???????????? ????????? ???????????? ???????????? ?????? ?????? ??? ????????????
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
	 * @??????????????? : ????????? ????????? ??????
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
	 * @??????????????? : ????????? ??????????????? ???????????????
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
	 * @??????????????? : ????????? ?????? ?????? ????????? ?????????
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
	 * @??????????????? : ????????? ?????? ?????? ?????????
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
	 * @??????????????? : ????????? ????????? ????????? ??? ?????? ????????????
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
	 * @??????????????? : ?????? ??????
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
	 * @??????????????? : ?????? ?????? ?????????
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
	 * @??????????????? : ?????? ?????? 
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
	 * @??????????????? : ?????? ?????? ????????? ????????????
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
	 * @??????????????? : ?????? ?????? ?????? ajax
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
	 * @??????????????? : ?????? ?????? ????????? ????????????
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
	 * @??????????????? : ????????? ?????? ?????????
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
	 * @??????????????? : ????????? ??????
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
	 * @??????????????? : ??? ????????? ??????
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
		String msg = "????????? ?????? ??????";
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
					orVO.setOrProductOrderNumber("??????-"+orderNum+countNum);
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
				throw new RuntimeException("?????? ?????? ?????? ??????", e);
			}
			
		}
		
		if(orList.size() != 0) {			
			int [] result = ordersService.insertOrderData(orList, orVOParam.getSsFk());
			
			//?????? ??????
			if(smsSendFlag == 1) {
				AligoSendingForm asf = new AligoSendingForm();
				
				AligoVO aligo = asf.aligoSendingForm(orList);
				String res = asf.smsMsg(aligo);
				System.out.println(res);
			}
			
		}else {
			msg = "?????? ?????? ??????";
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
	 * @??????????????? : ?????? ?????? ?????? ??????
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
	 * @??????????????? : ?????? ?????? ???????????? ????????? ????????? ??? ???????????? ?????????????????? json parsing?????? map?????? ?????????
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
			 throw new RuntimeException("API ????????? ?????? ??????", e);
		}
		
		Map<String, Object> resultMap =  null; 
		
		try {
			resultMap = nma.returnJson(result);
			model.addAttribute("resultMap",resultMap);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new  RuntimeException("?????? ?????? ", e);
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
	 * @??????????????? : ??? ???????????? ?????? ????????????
	 */
	@RequestMapping(value="/cs/add_create_order_product.do", method=RequestMethod.GET)
	public String addCreatetedOrderProduct(Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("ccList", ccList);
		
		return "orders/cs/add_create_order_product";
	}
	
	//?????? ?????? ?????? ???
	
	// ????????? ?????? ????????????
	/**
	 * @MethodName : irregularOrdersList
	 * @date : 2019. 8. 02.
	 * @author : Jeon KiChan
	 * @??????????????? : ????????? ?????? ???????????? ??????
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
	 * @??????????????? : ????????? ?????? ????????? ?????? ??????
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
	 * @??????????????? : ????????? ?????? ???????????? ???????????? ?????????
	 */
	@RequestMapping(value="/irregular/add.do", method=RequestMethod.GET)
	@Transactional
	public String irregularOrdersAdd(Model model){
		
		//????????? ????????? ??????
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
	 * @??????????????? : ?????? ????????? ???????????? ?????? - post
	 */
	@RequestMapping(value="/irregular/add.do", method=RequestMethod.POST)
	@Transactional
	public String irregularOrdersAddPost(@ModelAttribute IrregularOrderVO iroVO, Model model) {
		
		String msg = "";
		String url = "";
		
		int result = ordersService.addIrregularOrders(iroVO);
		
		if(result == 0) {
			msg = "DB??? ?????? ????????? ???????????? ???????????????. ";
			url = "/orders/irregular/add.do";
		}else {
			msg = "?????? ????????? ????????? ?????? ??????";
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
	 * @??????????????? : ????????? ????????? ???????????? ??????
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
			msg = "DB ?????? ????????? ??????";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		if(result != 0) {
			msg = " ?????? ?????? ";
		}else {
			msg = "?????? ?????? ??????. ??????????????? ??????????????????.";
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
			msg = "DB ?????? ????????? ??????";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		if(result != 0) {
			msg = " ?????? ????????? ?????? ?????? ";
		}else {
			msg = "?????? ?????? ??????. ??????????????? ??????????????????.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	//????????? ?????? ???????????? ???
	
	
	/**
	 * 
	 * @MethodName : devideExcelGiftSetGet
	 * @date : 2020. 6. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ?????? ??????
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
	 * @??????????????? : ????????? ?????? ?????? ?????? ??????
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
			throw new RuntimeException("?????? ????????? ??????", e);
			
		} catch ( IOException e) {
			throw new RuntimeException("?????? ?????? ??????", e);
		}

		
		OrdersVO originalOrVO = ordersService.selectOrdersByPk(orVO.getOrPk());

		ordersService.deleteExcelGiftOrderByOrFk(originalOrVO);
		
		int [] result = ordersService.updateExcelDivOrders(originalOrVO, fileName);

		if(result == null || result[0] == 0) {
			msg = "????????? ????????? ????????? ????????????. ?????? ??? ??? ??????????????????.";
			model.addAttribute("msg", msg);
			model.addAttribute("closing", closing);
			return "common/message";
		}
		
		msg = result[0]+" ??? ?????? ??????. ???????????? ???????????? ?????????.";
		
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
	 * @??????????????? : ?????? ????????? ?????? ?????????
	 */
	@RequestMapping(value="/cancled_order_check.do", method=RequestMethod.GET)
	public String cancledOrdersCheckGet(Model model) {
		//????????? ??? ????????????
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
	 * @??????????????? : ?????? ?????? ????????????
	 */
	@RequestMapping(value="/cancled_order_check.do", method=RequestMethod.POST)
	public String cancledOrdersCheckPost(HttpServletRequest request,@ModelAttribute StoreSectionVO ssVO, Model model) {
		//????????? ??? ????????????
		
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
				msg = "?????? ????????? ??????. ????????? ??????????????????.";
				
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
				//????????? ???????????? ????????? ?????????????????? ??????
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
	 * @??????????????? : ?????? ???????????? ??????
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
	 * @??????????????? : ?????? ?????? ??????????????? ???????????? ??????
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
	 * @??????????????? : ?????? ?????? ??????
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
	 * @??????????????? : ?????? ?????? ????????? ????????????
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
	 * @??????????????? : ?????? ????????????
	 */
	@RequestMapping(value="/refund_order.do", method=RequestMethod.POST)
	public String editRefundOrderPost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/orders/refund_order.do";
		boolean closing = false;
		
		int result = ordersService.orderRefundsEdit(orVO);
		
		if(result > 0) {
			msg = "?????? ?????? ??????";
			closing = true;
		}else {
			msg = "?????? ?????? ??????";
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
	 * @??????????????? : ?????? ?????? ?????? ?????? ??? ??????, ?????? ????????? ????????????
	 */
	@RequestMapping(value="/special_request.do", method=RequestMethod.GET)
	public String customerSpecialRequestGet(@ModelAttribute OrdersVO paramOrVO, Model model) {
		
		OrdersVO orVO = ordersService.selectCustomerSpecialRequest(paramOrVO);
		String msg="?????? ????????? ????????? ????????? ????????? ??????????????????";
		
		//?????? ?????? ??????
		int orderMatchingCounting = matchingService.selectOrderMatchingCounting(paramOrVO);
		boolean orderCombineFlag = false;
		
		//????????? ?????? ??? ?????? ????????? ????????? ?????? ????????? ??????
		int orderMeatCount= matchingService.selectOrderMatchingIncMeatCounting(paramOrVO);
	
		
		logger.info("???????????? => {}, ?????? ?????? ?????? ?????? => {}",orderMatchingCounting,orderMeatCount);
		//?????? ????????? ?????? ?????? ????????? ??????
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
	 * @??????????????? : ?????? ?????? ?????? ?????? ??? ??????, ????????????
	 */
	@RequestMapping(value="/special_request.do", method=RequestMethod.POST)
	public String customerSpecialRequestPost(@ModelAttribute OrdersVO paramOrVO, Model model) {
		
		logger.info("?????? ?????? ?????? => {}, ?????? ?????? => {}", paramOrVO.getOrRequest(), paramOrVO.getOrRequestCombFlag());
		
		String msg = "";
		String url = "/orders/special_request.do";
		boolean closing = false;
		
		int result = ordersService.addCustomerSpecialRequest(paramOrVO);
			
		if(result > 0) {
			msg="?????? ?????? ???????????? ?????? ??????";
			closing = true;
		}else {
			msg="?????? ?????? ???????????? ?????? ??????";
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
	 * @??????????????? : ??????????????? ???????????? ( ?????? ????????? ????????? ???????????? )
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
	 * @??????????????? : ?????? ???????????? ??????????????? ????????????
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
	 * @??????????????? : ?????? ???????????? ?????? ??????
	 */
	@RequestMapping(value="/config/edit_deliv_num.do", method=RequestMethod.POST)
	public String updateDeliveryInvoiceNumberPost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/orders/config/edit_deliv_num.do?orPk="+orVO.getOrPk();
		boolean closing = false;
		
		int result = ordersService.editDelivNum(orVO);
		
		if(result > 0) {
			msg = "???????????? ?????? ??????";
			closing = true;
		}else {
			msg = "?????? ?????? ??????";
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
	 * @??????????????? : ?????? ?????? ???????????? ????????? ????????? ???????????? 
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
	 * @??????????????? : ????????? ????????? ?????????????????? ???????????? ?????? ??????
	 */
	@RequestMapping(value="/delete_order_regdate.do", method=RequestMethod.GET)
	public String deleteOrderByRegdate(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "????????????";
		String url = "/orders/order_page.do";
		
		logger.info("regdate => {}, ss_fk => {}",orVO.getOrRegdate(),orVO.getSsFk());
		
		int result = ordersService.deleteOrdersByDate(orVO);
		if(result != 0) {
			msg = "????????????";
			url = "/orders/order_page.do";
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";			
		}else {
			msg = "????????????";
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
	 * @??????????????? : ?????? ??????????????????
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
	 * @??????????????? : ????????????, ?????????????????? ???????????? ?????????
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
	 * @??????????????? : ????????????, ?????????????????? ??????
	 */
	@RequestMapping(value="/pick_up_service.do", method=RequestMethod.POST)
	public String pickUpServicePost(@ModelAttribute OrdersVO orVO, Model model) {
		String msg = "";
		String url = "/";
		boolean closing = true;
		
		int result = ordersService.receiverPickUp(orVO);
		
		if(result > 0) {
			msg = "?????? ?????? ?????? ??????";
			
		}else {
			msg = "?????? ?????? ?????? ??????, ?????? ??? ??? ??????????????????";
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
	 * @??????????????? : ????????? ???????????? ?????? ?????? ???????????? (cs ??????)
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
	 * @??????????????? : ????????? ???????????? ?????? ?????? ?????? ???????????? (cs ??????) - cs ???????????? ???????????? ajax ??????
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
	 * @??????????????? : ????????? ???????????? ?????? ?????? ?????? ???????????? (cs ??????) - ?????? ???????????? ?????? ????????? ??? ????????? ?????? ??????
	 */
	@RequestMapping(value="/order_record.do")
	public String searchAdminOrderRecordList(@ModelAttribute OrdersVO orVO, Model model){
		
		List<AdminOrderRecordVO> aorList = ordersService.searchAdminOrderRecordBySerialSpecialNumber(orVO);
		
		if(aorList == null || aorList.size() == 0) {
			String msg = "cs ????????? ???????????? ????????????";
			boolean closing = true;
			
			model.addAttribute("msg", msg);
			model.addAttribute("closing", closing);
			
			return "common/message";
			
		}
		
		model.addAttribute("aorList", aorList);
		
		return "orders/cs/admin_order_record";
	}
}
