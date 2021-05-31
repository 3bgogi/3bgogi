package com.gogi.proj.epost.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.EarlyDelivCommonImposVO;
import com.gogi.proj.delivery.model.DeliveryDAO;
import com.gogi.proj.delivery.model.DeliveryService;
import com.gogi.proj.epost.api.EpostSendingUtil;
import com.gogi.proj.epost.controller.EpostController;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.EmptyCheckUtil;
import com.gogi.proj.util.naverMapApiUtil;

@Service
public class EpostServiceImpl implements EpostService {

	@Autowired
	private EpostDAO epostDao;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DeliveryDAO deliDao;
	
	@Autowired
	private DeliveryConfigService dcService;

	@Autowired
	private OrdersDAO orderDao;
	
	private static final Logger logger = LoggerFactory.getLogger(EpostServiceImpl.class);
	
	static final String EPOST_DELIV_SENDING = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	
	static final String EPOST_DELIV_SENDING_VER_2 = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	//송장삭제
	static final String EPOST_DELIV_DELETE = "http://ship.epost.go.kr/api.GetResCancelCmd.jparcel";
	
	private static EpostSendingUtil esu = new EpostSendingUtil();
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	@Override
	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		return epostDao.selectEpostSendingData(osVO);
	}

	@Override
	public int grantRegiNoByOrPk(RegDataVO regVO, RegDataVO reVO, boolean updateType) {
		
		if(reVO.getError_code() != null) return 0;
		
		if(updateType == true) {			
			String [] orPkList = regVO.getOrPk().split(",");
			int result = 0;
			for(int i=0; i < orPkList.length; i++) {			
				reVO.setOrPk(orPkList[i]);
				//regVO.setRegiNo(regiNo);
				result = epostDao.grantRegiNoByOrPk(reVO);
				
				if(EmptyCheckUtil.isEmpty(reVO.getRegiNo())) return 0;
			}
			
			return result;
		}else {
			
			return 1;
		}
	}

	@Transactional
	@Override
	public String deleteEpostDelivData(List<String> orSerialSpecialNumberList, String epostUrl, String ip, String adminId) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		EpostSendingUtil esu = new EpostSendingUtil();
		
		int result = 0 ;
		int errorResult = 0;
		
		StringBuilder results = new StringBuilder("");
		
		RegDataVO regVO = null;
		String regData = "";
		String encryptStr = "";
		
		for(int i=0; i < orSerialSpecialNumberList.size(); i++) {
			regVO = epostDao.selectEpostInfoByOrserialspecialnumber(orSerialSpecialNumberList.get(i));
			List<OrdersVO> orList = orderDao.selectOrdersPkByOrSerialSpecialNumber(orSerialSpecialNumberList.get(i));
			System.out.println(regVO.epostDeliteToString());
			System.out.println(regVO.getReqno());
			System.out.println(regVO.getReqNo());
			//직접 입력된 송장일 경우 송장 정보가 없음
			if(regVO == null) {
				deliDao.deleteSendingReq(regVO);
				epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
				logger.info("송장 삭제, regVO 정보가 존재하지 않아 직접 입력된 것으로 판단, 데이터베이스 상으로 삭제처리");
			}else {				
				deliDao.deleteSendingReq(regVO);
					try {
						regData = regVO.epostDeliteToString();
						
						encryptStr = esu.epostEncrypting(regData);
						RegDataVO sendingResult = esu.epostSending(encryptStr, epostUrl);
						
						if(sendingResult.getCanceledyn() != null) {				
							if(sendingResult.getCanceledyn().equals("Y")) {
								epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
								result++;
								
							}else if(sendingResult.getNotcancelReason() != null) {
								results.append(sendingResult.getNotcancelReason()+"<br>");
								epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
							}else {
								results.append(""+orSerialSpecialNumberList.get(i)+" => "+sendingResult.getMessage()+"<br>");
							}
						}else {
							epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
							result++;
						}
						
					}catch(NullPointerException e) {
						regData = "";
						epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
						errorResult++;
					}
			}
			
			for(int j = 0; j < orList.size(); j++) {	
				OrderHistoryVO ohVO = null;
				
				ohVO = new OrderHistoryVO();
				ohVO.setOrFk(orList.get(j).getOrPk());
				ohVO.setOhIp(ip);
				ohVO.setOhAdmin(adminId);
				ohVO.setOhRegdate(dates);
				ohVO.setOhEndPoint("송장 삭제");
				ohVO.setOhDetail("송장 송장 삭제 완료 => ( "+regVO.getRegiNo()+" )");
				
				logService.insertOrderHistory(ohVO);
			}
			
		}
		
		results.append("<br>삭제 완료된 개수 = "+result+" 장");
		
		return results.toString();
	}

	@Override
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.selectDontGrantDelivOrderListInMonth(osVO);
	}

	@Override
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.selectDontGrantDelivOrderListInMonthCounting(osVO);
	}

	@Transactional
	@Override
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO, String ip, String adminId, String createInvoiceNumDateCounting, int delivCount) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		OrdersVO orderList = epostDao.deliveryPrintTarget(osVO);
		
		if(orderList == null) return null;
		
		if(orderList.getOrRecType() > 0 && orderList.getOrRecType() < 3) {
			double dValue = Math.random();
			long delivNum = 10000000000000L;
			
			String delivInvoiceNum = ((long) (dValue * delivNum) ) + "";
			
			if(orderList.getOrRecType() == 1) {
				orderList.setOrDeliveryInvoiceNumber(delivInvoiceNum);
				orderList.setRegiNo(delivInvoiceNum);
				orderList.setOrDeliveryCompany("퀵서비스");

			}else if(orderList.getOrRecType() == 2) {
				orderList.setOrDeliveryInvoiceNumber(delivInvoiceNum);
				orderList.setRegiNo(delivInvoiceNum);
				orderList.setOrDeliveryCompany("방문수령");
				
			}
			
			orderList.setOrInvoiceNumDate(createInvoiceNumDateCounting);
			orderList.setOrDelivCount(delivCount+"");
			epostDao.gtranReceiverPickUp(orderList);
			
			OrderHistoryVO ohVO = null;
			
			int temp = 0;
			
			for(int i = 0; i < orderList.getProductOptionList().size(); i++) {
				
				if( temp == orderList.getProductOptionList().get(i).getAnotherOptionPk()) {
					
				}else {
					ohVO = new OrderHistoryVO();
					ohVO.setOrFk(orderList.getProductOptionList().get(i).getAnotherOptionPk());
					ohVO.setOhIp(ip);
					ohVO.setOhAdmin(adminId);
					ohVO.setOhRegdate(dates);
					ohVO.setOhEndPoint(orderList.getOrDeliveryCompany()+" 생성");
					ohVO.setOhDetail(orderList.getOrDeliveryCompany()+" 분류코드 ( "+orderList.getOrDeliveryInvoiceNumber()+" ) 생성 완료");
					
					logService.insertOrderHistory(ohVO);
					temp = orderList.getProductOptionList().get(i).getAnotherOptionPk();
					
				}
			}
			
			return orderList;
		}

		OrdersVO resOr = null;
		OrderHistoryVO ohVO = null;
		
			try {
				
				//System.out.println(orList.epostDelivSelfPrintToString());
				//sendingData = esu.epostSending( esu.epostEncrypting(orList.epostDelivSelfPrintToString()) , EpostServiceImpl.EPOST_DELIV_SENDING);
				
				Object epostInfo = esu.selfPrintepostSendingTest(esu.epostEncrypting(orderList.epostDelivSelfPrintToString()), "http://ship.epost.go.kr/api.InsertOrder.jparcel", orderList);
				
				if(epostInfo == null) return null;
				
				resOr = (OrdersVO)epostInfo;

				if(resOr != null && resOr.getRegiNo() != null) {
					
					
					/*String [] orPkSplit = resOr.getOrUserColumn1().split(",");
					System.out.println(orPkSplit.toString());
					List<String> orProductList = new ArrayList<String>();

					for(int j=0; j < orPkSplit.length ; j++) {
						System.out.println("orPkSplit[j] = > "+orPkSplit[j]);
						orProductList.add(orPkSplit[j]);
					}
					
					resOr.setOrProductList(orProductList);*/
					
					resOr.setOrInvoiceNumDate(createInvoiceNumDateCounting);
					resOr.setOrDelivCount(delivCount+"");
					int result = epostDao.grantDeliveryInvoiceNumber(resOr);
					int temp = 0;
					for(int i = 0; i < resOr.getProductOptionList().size(); i++) {
						
						if( temp == resOr.getProductOptionList().get(i).getAnotherOptionPk()) {
							
						}else {
							ohVO = new OrderHistoryVO();
							ohVO.setOrFk(resOr.getProductOptionList().get(i).getAnotherOptionPk());
							ohVO.setOhIp(ip);
							ohVO.setOhAdmin(adminId);
							ohVO.setOhRegdate(dates);
							ohVO.setOhEndPoint("송장 생성");
							ohVO.setOhDetail("송장 생성 완료 => 우체국 ( "+resOr.getRegiNo()+" )");
							
							logService.insertOrderHistory(ohVO);
							temp = resOr.getProductOptionList().get(i).getAnotherOptionPk();
							
						}
					}
					
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return resOr;
	}

	@Override
	public OrdersVO deliveryInvoiceNumberReprinting(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		OrdersVO orVO = epostDao.deliveryInvoiceNumberReprinting(osVO);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		List<OrdersVO> orList = orderDao.selectOrdersPkByOrSerialSpecialNumber(osVO.getSearchKeyword());
		
		int temp = 0;
		
		OrderHistoryVO ohVO = null;
		
		for(int i = 0; i < orList.size(); i++) {			
			
			if( temp == orList.get(i).getOrPk()) {
				
			}else {				
				ohVO = new OrderHistoryVO();
				ohVO.setOrFk(orList.get(i).getOrPk());
				ohVO.setOhIp(ip);
				ohVO.setOhAdmin(adminId);
				ohVO.setOhRegdate(dates);
				if( orVO.getOrRecType() == 0) {					
					ohVO.setOhEndPoint("송장 재출력");
					ohVO.setOhDetail("송장 재출력 완료 => 우체국 ( "+orVO.getRegiNo()+" )");
					
				}else if( orVO.getOrRecType() == 1) {
					ohVO.setOhEndPoint("퀵서비스 분류코드 재생성");
					ohVO.setOhDetail("퀵서비스 분류코드 ( "+orVO.getRegiNo()+" ) 재생성 완료");
					
				}else if( orVO.getOrRecType() == 2) {
					ohVO.setOhEndPoint("방문수령 분류코드 재생성");
					ohVO.setOhDetail("방문수령 분류코드 ( "+orVO.getRegiNo()+" ) 재생성 완료");
					
				}else if( orVO.getOrRecType() == 3) {
					ohVO.setOhEndPoint("송장 재출력");
					ohVO.setOhDetail("송장 재출력 완료 => 우체국 ( "+orVO.getRegiNo()+" )");
					
				}
				
				logService.insertOrderHistory(ohVO);
				temp = orList.get(i).getOrPk();
			}
		}
		
		return epostDao.deliveryInvoiceNumberReprinting(osVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO) throws IOException, ParseException {
		// TODO Auto-generated method stub
		List<OrdersVO> orList = epostDao.selectDeliveryInvoiceNumberByDate(osVO);
		
		List<Map<String, Object>> deliveryInfoList = new ArrayList<>();
		EpostSendingUtil esu = new EpostSendingUtil();
		naverMapApiUtil nmu = new naverMapApiUtil();
		
		for(OrdersVO orVO : orList) {
			
			String urlParameters = "regkey="+esu.getEpost_api_key()
	                +"&target=trace&query="+orVO.getOrDeliveryInvoiceNumber()+"&regymd="+osVO.getDateStart().replaceAll("-", "");
			
	        URL obj = new URL("http://biz.epost.go.kr/KpostPortal/openapi");
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        //add reuqest header
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Connection", "keep-alive");
	        con.setRequestProperty("Host", "biz.epost.go.kr");
	        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.1(Java/1.8.0_91)");
	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        int responseCode = con.getResponseCode();

	        Charset charset = Charset.forName("UTF-8");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        
	        JSONObject xmlJSONObj = XML.toJSONObject(response.toString());
			String objString = xmlJSONObj.toString();
	       
			Map<String, Object> result = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("trace");
	        
	        if(result != null) {
	        	orVO.setMessage("발송 완료");
	        }else {
	        	Map<String, Object> xs = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("xsync");
	        	if(xs != null) {
	        		
	        		Map<String, Object> x1 = (Map<String, Object>) xs.get("xsyncData");
	        		orVO.setMessage((String)x1.get("message"));
	        		
	        	}else {
	        		Map<String, Object> error = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("error");
	        		orVO.setMessage((String)error.get("message"));
	        	}
	        }
	        
	        //String msg = (String)naverMapApiUtil.returnJson(objString).get("xsync");
	        
	        //orVO.setMessage(naverMapApiUtil.returnJson(objString));
	        
	        in.close();
		}
		
		return orList;
	}

	@Override
	@Transactional
	public List<OrdersVO> freshSolutionDelivExcel(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		List<OrdersVO> orderList = epostDao.freshSolutionDelivExcel(osVO);
		
		for(int i = 0; i < orderList.size(); i++) {
			epostDao.updateFreshSolutionTarget(orderList.get(i));
			
		}
		
		return orderList;
	}

	@Override
	@Transactional
	public int updateFreshSolutionInvoiceNumber(List<OrdersVO> orList) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for(int i = 0; i < orList.size(); i++) {
			result += epostDao.updateFreshSolutionInvoiceNumber(orList.get(i));
		}
		
		return result;
	}

	@Override
	public int deleteDelivInfoByPk(OrdersVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.deleteDelivInfoByPk(osVO);
	}

	@Transactional
	@Override
	public File freshSolutionInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		List<OrdersVO> delivTarget = new ArrayList<>();
		List<OrdersVO> delivImpos = new ArrayList<>();
		osVO.setEdtFk(3);
		List<EarlyDelivCommonImposVO> imposList = dcService.selectEarlyDelivCommonImposList(osVO);
		
		List<OrdersVO> selectedOrder = null;
		
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			
			selectedOrder = this.freshSolutionDelivExcel(osVO);
			
			if(selectedOrder.size() != 0) {
				for(int j = 0; j < selectedOrder.size(); j++) {
					
					boolean match = false;
					
					for(int imposCount = 0; imposCount < imposList.size(); imposCount++) {						
						if(selectedOrder.get(j).getOrShippingAddressDetail().matches(".*"+imposList.get(imposCount).getEdciKeyword()+".*")) {
							selectedOrder.get(j).setOrUserColumn5(imposList.get(imposCount).getEdciKeyword());
							match = true;
							break;	
						}
							
					}
					
					if(match == true) {
						delivImpos.add(selectedOrder.get(j));
						this.deleteDelivInfoByPk(selectedOrder.get(j));
					}else {
						delivTarget.add(selectedOrder.get(j));
					}
					
					match = false;
					
				}
			}
			
		}
		
		
		List<String> list = new ArrayList<String>();
		
		list.add("거래처 주문코드");
		list.add("배송요청일");
		list.add("주문자");
		list.add("수령인");
		list.add("우편번호");
		list.add("수령인 주소");
		list.add("수령인 상세주소");
		list.add("수령인 전화번호");
		list.add("수령인 핸드폰");
		list.add("비고");
		list.add("비고2(배송메시지)");
		list.add("요청유형");
		list.add("배송문자유형");
		list.add("상품코드");
		list.add("상품유형");
		list.add("상품명");
		list.add("상품옵션");
		list.add("수량");
		
		
		// 워크북 생성
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// 워크시트 생성
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("발송명단");
		SXSSFSheet errorSheet = (SXSSFSheet) workbook.createSheet("에러명단");
		// 행 생성
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		SXSSFRow errorRow = (SXSSFRow) errorSheet.createRow(0);
		row.setHeight((short) 500);
		// 쎌 생성
		SXSSFCell cell;
		SXSSFCell errorCell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < list.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(list.get(HeaderCounting));

		}
		
		errorCell = (SXSSFCell) errorRow.createCell(0);
		errorCell.setCellValue("구매자명");
		
		errorCell = (SXSSFCell) errorRow.createCell(1);
		errorCell.setCellValue("수령자명");
		
		errorCell = (SXSSFCell) errorRow.createCell(2);
		errorCell.setCellValue("주소");
		
		errorCell = (SXSSFCell) errorRow.createCell(3);
		errorCell.setCellValue("필터링 키워드");
		
		int errorCellCounting = 1;
		
		if(delivImpos.size() != 0 ) {
			
			for(int i=0; i < delivImpos.size(); i++) {
				errorRow = (SXSSFRow) errorSheet.createRow(errorCellCounting);
				
				cell = (SXSSFCell) errorRow.createCell(0);
				cell.setCellValue(delivImpos.get(i).getOrBuyerName());
				
				cell = (SXSSFCell) errorRow.createCell(1);
				cell.setCellValue(delivImpos.get(i).getOrReceiverName());
				
				cell = (SXSSFCell) errorRow.createCell(2);
				cell.setCellValue(delivImpos.get(i).getOrShippingAddress()+" "+delivImpos.get(i).getOrShippingAddressDetail());
				
				cell = (SXSSFCell) errorRow.createCell(3);
				cell.setCellValue(delivImpos.get(i).getOrUserColumn5());
				
				errorCellCounting++;
			}
		}
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();
		
		cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		
		for (int i = 0; i < delivTarget.size(); i++) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(delivTarget.get(i).getOrSerialSpecialNumber());

			cell = (SXSSFCell) row.createCell(1);
			cell.setCellValue(delivTarget.get(i).getOrSendingDeadline());
			cell.setCellStyle(cs);
			
			cell = (SXSSFCell) row.createCell(2);
			cell.setCellValue(delivTarget.get(i).getOrBuyerName());
			
			cell = (SXSSFCell) row.createCell(3);
			cell.setCellValue(delivTarget.get(i).getOrReceiverName());
			
			cell = (SXSSFCell) row.createCell(4);
			cell.setCellValue(delivTarget.get(i).getOrShippingAddressNumber());
			
			cell = (SXSSFCell) row.createCell(5);
			cell.setCellValue(delivTarget.get(i).getOrShippingAddress());
			
			cell = (SXSSFCell) row.createCell(6);
			cell.setCellValue(delivTarget.get(i).getOrShippingAddressDetail());
			
			cell = (SXSSFCell) row.createCell(7);
			cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber2() == null ? "" : delivTarget.get(i).getOrReceiverContractNumber2() );
			
			cell = (SXSSFCell) row.createCell(8);
			cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber1());
			
			cell = (SXSSFCell) row.createCell(9);
			cell.setCellValue(delivTarget.get(i).getOrDeliveryMessage());
			
			cell = (SXSSFCell) row.createCell(10);
			cell.setCellValue(delivTarget.get(i).getOrDelivEnter());
			
			cell = (SXSSFCell) row.createCell(11);
			cell.setCellValue("배송대행");
			
			cell = (SXSSFCell) row.createCell(12);
			cell.setCellValue("7시전송");
			
			cell = (SXSSFCell) row.createCell(13);
			cell.setCellValue(delivTarget.get(i).getOrPk());
			
			cell = (SXSSFCell) row.createCell(14);
			cell.setCellValue(delivTarget.get(i).getOrUserColumn1());
			
			cell = (SXSSFCell) row.createCell(15);
			cell.setCellValue(delivTarget.get(i).getOrProduct());
			
			
			cell = (SXSSFCell) row.createCell(16);
			cell.setCellValue("");
			
			cell = (SXSSFCell) row.createCell(17);
			cell.setCellValue(delivTarget.get(i).getOrAmount());
			
			cellCounting++;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "fresh_solution_upload_file["+sdf.format(new Date())+"].xlsx";
		
		File file = new File(fileProperties.getProperty("file.upload.order_IO_excel.path.test"), fileName);
		
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
		
		return file;
	}

}
