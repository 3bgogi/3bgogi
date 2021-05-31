package com.gogi.proj.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.proj.commission.SmartstoreCommission;
import com.gogi.proj.orders.config.vo.StoreCancleExcelDataSortingVO;
import com.gogi.proj.orders.config.vo.StoreExcelDataSortingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.util.EmptyCheckUtil;
import com.gogi.proj.util.FileuploadUtil;

@Component
public class ReadOrderExcel {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<OrdersVO> readOrderExcelDataToXLS(String fileName, int ssFk, boolean sendingDeadlineFlag) throws POIXMLException{
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		SmartstoreCommission sc = new SmartstoreCommission();
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());

		try {

			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			
			int rowindex=0;
			
			int columnindex=0;

			XSSFSheet sheet=workbook.getSheetAt(0);

			int rows=sheet.getPhysicalNumberOfRows();
			
			 OrdersVO orderVO;
			for(rowindex=2;rowindex<rows;rowindex++){

			    XSSFRow row=sheet.getRow(rowindex);
			    
			    orderVO = new OrdersVO();
			    
			    if(row !=null){
			        
			        for(columnindex=0;columnindex<60;columnindex++){

			            XSSFCell cell=row.getCell(columnindex);
			            	//스마트스토어 기준
			            	//구매자명
			           
			            if(cell == null) {
			            	
			            	continue;
			            }else{
			            	orderVO.setSsFk(ssFk);
			            	if(columnindex==0) {
			            		orderVO.setOrBuyerName(cell.getStringCellValue());
			            		//구매자ID		
			            	}else if(columnindex==1) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=((int)cell.getNumericCellValue())+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrBuyerId(value);
			            		//수취인명	
			            	}else if(columnindex==2) {
			            		orderVO.setOrReceiverName(cell.getStringCellValue());
			            		//상품명
			            	}else if(columnindex==3) {
			            		orderVO.setOrProduct(cell.getStringCellValue());
			            		//상품종류
			            	}else if(columnindex==4) {
			            		orderVO.setOrProductType(cell.getStringCellValue());
			            		//수량
			            	}else if(columnindex==5) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrAmount((int)Integer.parseInt(value));
			            		//옵션정보 : 옵션명
			            	}else if(columnindex==6) {
			            		
			            		String value = "";
			            		
			            		if(cell == null ) {
			            			orderVO.setOrProductOption("단일상품");
			            			
			            		}else {
			            			
			            			switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            			
			            			orderVO.setOrProductOption(cell.getStringCellValue());
			            			
			            		}
			            		//배송메세지
			            	}else if(columnindex==7) {
			            		if(cell != null) {
			            			String delivMsg = cell.getStringCellValue()+"";
			            			int firstIndex = delivMsg.lastIndexOf("[");
			            			int lastIndex = delivMsg.lastIndexOf("]");
			            			
			            			// [   ] 형식으로 된 출입방법이 적혀 있을 있을 경우
			            			if(lastIndex != -1 && delivMsg.length() != 0) {
			            				lastIndex = lastIndex+1;
			            				
			            				orderVO.setOrDeliveryMessage(delivMsg.substring(lastIndex, delivMsg.length()));
			            				orderVO.setOrUserColumn4(delivMsg.substring(firstIndex, lastIndex));
			            			}else {
			            				orderVO.setOrDeliveryMessage(delivMsg);
			            			}
			            			
			            		}else {
			            			orderVO.setOrDeliveryMessage("");
			            		}
			            		//배송방법
			            	}else if(columnindex==8) {
			            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
			            		//택배사
			            	}else if(columnindex==9) {
			            		//
			            		//송장번호
			            	}else if(columnindex==10) {
			            		
			            		//상품주문번호
			            	}else if(columnindex==11) {
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		
			            		orderVO.setOrProductOrderNumber(value);
			            		//주문번호
			            	}else if(columnindex==12) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrOrderNumber(value);
			            		//발송일 : 다운로드 받은 시간이 나옴 보류해야됌
			            	}else if(columnindex==13) {
			            		
			            		//주문상태 : 아직 필요한지 모름
			            	}else if(columnindex==14) {
			            		
			            		//주문세부상태 : 아직 필요한지 모름
			            	}else if(columnindex==15) {
			            		
			            		//결제위치 : PC or MOBILE
			            	}else if(columnindex==16) {
			            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
			            		//결제일
			            	}else if(columnindex==17) {
			            		orderVO.setOrSettlementDay(new Timestamp(cell.getDateCellValue().getTime()));
			            		//상품번호 : 스마트스토어 내에서의 상품번호 ( 필요없을 것 같음 )
			            	}else if(columnindex==18) {
			            		orderVO.setOrProductNumber(cell.getStringCellValue());
			            		//옵션 가격
			            	}else if(columnindex==19) {
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrProductOptionPrice((int)Integer.parseInt(value));
			            		//상품 가격
			            	}else if(columnindex==20) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrProductPrice((int)Integer.parseInt(value));
			            		//상품별 할인액
			            	}else if(columnindex==21) {
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		
			            		orderVO.setOrDiscountPrice((int)Integer.parseInt(value));
			            		//판매자 부담 할인액
			            	}else if(columnindex==22) {
			            		//필요없을거 같음 상품별 할인액이 판매자 부담 할인액으로 되는 것으로 판단
			            		//상품별 총 주문금액
			            	}else if(columnindex==23) {
			            		orderVO.setOrTotalPrice((int)cell.getNumericCellValue());
			            		//발주확인일 : 공백임
			            	}else if(columnindex==24) {
			            		
			            		if(cell.getDateCellValue() == null) {
			            			continue;
			            		}else {			            			
			            			orderVO.setOrCheckDay(new Date(cell.getDateCellValue().getTime()));
			            			
			            		}
			            		//발송기한 : 이걸로 예약자를 자동으로 걸러내거나 따로 예약을 잡을 수 있도록 함
			            	}else if(columnindex==25) {
			            		if(sendingDeadlineFlag == true) {
			            			orderVO.setOrSendingDeadline(new Date(cell.getDateCellValue().getTime()));
			            			
			            		}else {
			            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
			            		}
			            		//발송처리일 : 현재 우리에게 필요없음
			            	}else if(columnindex==26) {
			            		
			            		//송장출력일 : 현재 우리에게 필요없음
			            	}else if(columnindex==27) {
			            		
			            		//배송비형태 : 선결제, 무료 등
			            	}else if(columnindex==28) {
			            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
			            		//배송비묶음번호 자동으로 묶을 때 쓰면 될 것 같지만 너무 많아서 따로 분류해야 할 경우엔 필요없을 것 같음. 하지만 스마트스토어로 직접 연결할 경우 필요함으로 보임
			            	}else if(columnindex==29) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=(int)cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		
			            		orderVO.setOrDeliveryNumber(value);
			            		//배송비유형 : 전부 조건부무료
			            	}else if(columnindex==30) {
			            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
			            		//배송비 합계 3000원 아니면 0원 일듯
			            	}else if(columnindex==31) {
			            		orderVO.setOrDeliveryPrice((int)cell.getNumericCellValue());
			            		//제주/도서 추가배송비
			            	}else if(columnindex==32) {
			            		orderVO.setOrDeliverySpecialPrice((int)cell.getNumericCellValue());
			            		//배송비 할인액 : 쿠폰으로 인해 할인할 경우 활성화 되는 것처럼 보임
			            	}else if(columnindex==33) {
			            		orderVO.setOrDeliveryDiscountPrice((int)cell.getNumericCellValue());
			            		//판매자 상품코드 : 현재 필요없어 보임
			            	}else if(columnindex==34) {
			            		
			            		//수취인 연락처 1
			            	}else if(columnindex==35) {
			            		
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		
			            		orderVO.setOrReceiverContractNumber1(value);
			            		//수취인 연락처 2
			            	}else if(columnindex==55) {
			            		orderVO.setOrReceiverContractNumber2(cell.getStringCellValue());
			            		//배송지
			            	}else if(columnindex==37) {
			            		
			            		//구매자연락처
			            	}else if(columnindex==38) {
			            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
			            		//우편번호
			            	}else if(columnindex==39) {
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=cell.getNumericCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }
			            		 
			            		orderVO.setOrShippingAddressNumber(value+"");
			            		//출고지 : 우리회사로 입력됌
			            	}else if(columnindex==40) {
			            		orderVO.setOrSendingAddress(cell.getStringCellValue());
			            		//결제수단 신용카드, 무통장등으로 보임
			            	}else if(columnindex==41) {
			            		orderVO.setOrPaymentType(cell.getStringCellValue());
			            		//수수료 과금구분
			            	}else if(columnindex==42) {
			            		
			            		//수수료결제방식
			            	}else if(columnindex==43) {
			            		
			            		//결제수수료
			            	}else if(columnindex==44) {
			            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
			            		//네이버 쇼핑 매출연동 수수료
			            	}else if(columnindex==45) {
			            		orderVO.setOrAnotherPaymentCommision((int)cell.getNumericCellValue());
			            		//정산예정금액
			            	}else if(columnindex==46) {
			            		
			            		//유입경로
			            	}else if(columnindex==47) {
			            		orderVO.setOrInflowRoute(cell.getStringCellValue());
			            		orderVO.setOrAnotherPaymentCommision(sc.matchingInflowCommission(cell.getStringCellValue(), orderVO.getOrTotalPrice()));
			            		//주문일시 : 결제일과 다름
			            	}else if(columnindex==48) {
			            		
			            		//구매자주민등록번호 : 현재 우리에게 필요없음 현 고객등급
			            	}else if(columnindex==49) {
			            		
			            		//옵션 관리 코드
			            	}else if(columnindex==50) {
			            		orderVO.setOrUserColumn1(cell.getStringCellValue());
			            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
			            	}else if(columnindex==52) {
			            		
			            		//우편번호 이것도 왜 ..
			            	}else if(columnindex==53) {
			            		
			            		//기본주소
			            	}else if(columnindex==57) {
			            		int subNum = cell.getStringCellValue().lastIndexOf("(");

			            		if(subNum > 0) {
			            			
			            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
			            			
			            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
			            			orderVO.setOrShippingAddressDetail(subStr);
			            		}else {
			            			
			            			orderVO.setOrShippingAddress(cell.getStringCellValue());
			            		}
			            		//상세주소 ?...
			            	}else if(columnindex==58) {
			            		
			            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
			            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
			            			
			            		}else {
			            			
			            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
			            			
			            		}
			            		
			            	
			            	}
			        	}
			        }//for
			    }
			    if(orderVO.getOrProductOption() == null) {
			    	orderVO.setOrProductOption("단일옵션");
			    }
			    orderVO.setOrRegdate(ts);
			    orderList.add(orderVO);
			    orderVO = null;
			}//for
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orderList;
	}
	
	public List<OrdersVO> readOrderExcelDatatoXLSX(String fileName){
		
		return null;
	}
	
	
public List<OrdersVO> readOrderExcelDataToXLSForSmartStoreSendingData(String fileName) throws POIXMLException{
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		Calendar cal = Calendar.getInstance();
		try {

			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			
			int rowindex=0;
			
			int columnindex=0;
			
			OrdersVO orderVO = null;

			XSSFSheet sheet=workbook.getSheetAt(0);

			int rows=sheet.getPhysicalNumberOfRows();
			
			for(rowindex=1;rowindex<rows;rowindex++){

			    XSSFRow row=sheet.getRow(rowindex);
			    
			    orderVO = new OrdersVO();
			    
			    if(row !=null){
			        
			        for(columnindex=0;columnindex<48;columnindex++){

			            XSSFCell cell=row.getCell(columnindex);
			            if(cell==null) {
			            	continue;
			            }else {
			            	//주문번호
			            	if(columnindex==1) {
			            		orderVO.setOrDeliveryInvoiceNumber(cell.getStringCellValue());
			            		
			            	}else if(columnindex==14) {
			            		String value = "";
			            		 switch (cell.getCellType()){
		                            case HSSFCell.CELL_TYPE_FORMULA:
		                                value=cell.getCellFormula();
		                                break;
		                            case HSSFCell.CELL_TYPE_NUMERIC:
		                                value=((int)cell.getNumericCellValue())+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_STRING:
		                                value=cell.getStringCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_BLANK:
		                                value=cell.getBooleanCellValue()+"";
		                                break;
		                            case HSSFCell.CELL_TYPE_ERROR:
		                                value=cell.getErrorCellValue()+"";
		                                break;
		                            }

			            		orderVO.setOrPk((int)Double.parseDouble(value));
			            		
			            	//수취인명
			            	}
			            }
			            
			        }//for
			    }
			    orderList.add(orderVO);
			    orderVO = null;
			}//for
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orderList;
	}



	
public List<OrdersVO> readOrderExcelDatas(String fileName, int ssFk, StoreExcelDataSortingVO sortingVO, boolean sendingDeadlineFlag) throws POIXMLException{
	
	List<OrdersVO> orderList = new ArrayList<OrdersVO>();
	
	SmartstoreCommission sc = new SmartstoreCommission();
	
	FileuploadUtil fu = new FileuploadUtil();
	
	Calendar cal = Calendar.getInstance();
	
	Timestamp ts = new Timestamp(cal.getTimeInMillis());
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
	
	String ext = fu.getExtType(fileName);
	
	try {

		
		FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);

		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheetAt(0);
				
		int rowStartIndex = sortingVO.getSedsStartRow();
		
		int columnindex=0;		

		int rows=sheet.getPhysicalNumberOfRows();
		
		 OrdersVO orderVO;
		 
		for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){

		    XSSFRow row=sheet.getRow(rowindex);
		    
		    orderVO = new OrdersVO();
		    
		    if(row !=null){
		        
		        for(columnindex=-1;columnindex<59;columnindex++){

		            XSSFCell cell=row.getCell(columnindex);
		            	// 판매처별로 엑셀 열을 읽어서 씀
		            	//구매자명
		            if(cell==null) {
		            	continue;
		            	
		            }else {
		            	orderVO.setSsFk(ssFk);
		            	
		            	if(columnindex==sortingVO.getSedsBuyerName()) {
		            		orderVO.setOrBuyerName(cell.getStringCellValue());
		            		//구매자ID		
		            	}if(columnindex==sortingVO.getSedsBuyerId()) {
		            		
		            		String value = "";
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		orderVO.setOrBuyerId(value);
		            		//수취인명	
		            	}if(columnindex==sortingVO.getSedsReceiverName()) {
		            		orderVO.setOrReceiverName(cell.getStringCellValue());
		            		//상품명
		            	}if(columnindex==sortingVO.getSedsProduct()) {
		            		orderVO.setOrProduct(cell.getStringCellValue());
		            		//상품종류
		            	}if(columnindex==sortingVO.getSedsProductType()) {
		            		orderVO.setOrProductType(cell.getStringCellValue());
		            		//수량
		            	}if(columnindex==sortingVO.getSedsQuantity()) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		
		            		orderVO.setOrAmount((int)Integer.parseInt(value));
		            		//옵션정보 : 옵션명
		            	}if(columnindex==sortingVO.getSedsProductOption()) {
		            		
		            		if(cell.getStringCellValue().equals("")) {
		            			orderVO.setOrProductOption("단일상품");
		            			
		            		}else {
		            			orderVO.setOrProductOption(cell.getStringCellValue());
		            			
		            		}
		            		
		            		//배송메세지
		            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
		            		if(cell.getStringCellValue() != null) {
		            			
		            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
		            		}else {
		            			orderVO.setOrDeliveryMessage("");
		            		}
		            		//배송방법
		            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
		            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
		            		//택배사
		            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
		            		//
		            		//송장번호
		            	}if(columnindex == -1) {
		            		
		            		//상품주문번호
		            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrProductOrderNumber(value);
		            		//주문번호
		            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		 
		            		orderVO.setOrOrderNumber(value);
		            		//발송일 : 다운로드 받은 시간이 나옴 보류해야됌
		            	}if(columnindex==sortingVO.getSedsSendingDay()) {
		            		
		            		//주문상태 : 아직 필요한지 모름
		            	}if(columnindex== -1) {
		            		
		            		//주문세부상태 : 아직 필요한지 모름
		            	}if(columnindex== -1) {
		            		
		            		//결제위치 : PC or MOBILE
		            	}if(columnindex==sortingVO.getSedsPaymentType()) {
		            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
		            		//결제일
		            	}if(columnindex== sortingVO.getSedsSettlementDay()) {

		            		String value = cellTypeReturn(cell);
		            			
		            		if(value.equals("false")) {
		            			continue;
		            			
		            		}else {
		            			if(value.length() == 16) {
		            				value=value+":00";
		            			}

		            			orderVO.setOrSettlementDay(new Timestamp(sdf.parse(value).getTime())); 
		            		}
		            		//상품번호 : 스마트스토어 내에서의 상품번호 ( 필요없을 것 같음 )
		            	}if(columnindex==sortingVO.getSedsProductNumber()) {
		            		orderVO.setOrProductNumber(cell.getStringCellValue());
		            		//옵션 가격
		            	}if(columnindex==sortingVO.getSedsProductOptionPrice()) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            				
		            		 
		            		orderVO.setOrProductOptionPrice((int)Integer.parseInt(value));
		            		//상품 가격
		            	}if(columnindex==sortingVO.getSedsProductPrice()) {
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		
		            		orderVO.setOrProductPrice((int)Integer.parseInt(value));
		            		//상품별 할인액
		            	}if(columnindex==sortingVO.getSedsDiscountPrice()) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		if(value == null || value.equals("")) {
		            			orderVO.setOrDiscountPrice(0);
		            		}else {
		            			
		            			orderVO.setOrDiscountPrice((int)Integer.parseInt(value));
		            		}
		            		//판매자 부담 할인액
		            	}if(columnindex==22) {
		            		
		            		//필요없을거 같음 상품별 할인액이 판매자 부담 할인액으로 되는 것으로 판단
		            		
		            		
		            		//상품별 총 주문금액
		            	}if(columnindex==sortingVO.getSedsTotalPrice()) {
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		orderVO.setOrTotalPrice((int)Integer.parseInt(value));
		            		//발주확인일 : 공백임
		            	}if(columnindex==sortingVO.getSedsOrderCheckDay()) {
		            		
		            		if(cell == null) {
		            			continue;
		            		}else {
		            			
		            			String value =cellTypeReturn(cell);
			            		 
		            			if(value.length() == 16) {
		            				value=value+":00";
		            			}
		            			
		            			orderVO.setOrCheckDay(new Date(sdf.parse(value).getTime()));
		            			
		            		}
		            		//발송기한 : 이걸로 예약자를 자동으로 걸러내거나 따로 예약을 잡을 수 있도록 함
		            		
		            	}if(columnindex==sortingVO.getSedsSendingDeadline()) {
		            		
		            		if(sendingDeadlineFlag == true) {
		            			String value = cellTypeReturn(cell);
			            		 
			            		orderVO.setOrSendingDeadline(new Date(sdfWithoutTime.parse(value).getTime()));
			            		
		            		}else {
		            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
		            		}
		            		
		            		//발송처리일 : 현재 우리에게 필요없음
		            	}if(columnindex==26) {
		            		
		            		//송장출력일 : 현재 우리에게 필요없음
		            	}if(columnindex==27) {
		            		
		            		//배송비형태 : 선결제, 무료 등
		            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
		            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
		            		//배송비묶음번호 자동으로 묶을 때 쓰면 될 것 같지만 너무 많아서 따로 분류해야 할 경우엔 필요없을 것 같음. 하지만 스마트스토어로 직접 연결할 경우 필요함으로 보임
		            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrDeliveryNumber(value);
		            		//배송비유형 : 전부 조건부무료
		            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
		            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
		            		
		            		
		            		//배송비 합계 3000원 아니면 0원 일듯
		            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		orderVO.setOrDeliveryPrice((int)Integer.parseInt(value));
		            		
		            		//제주/도서 추가배송비 아직 제대로 정해지지 않음
		            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		orderVO.setOrDeliverySpecialPrice((int)Integer.parseInt(value));
		            		//배송비 할인액 : 쿠폰으로 인해 할인할 경우 활성화 되는 것처럼 보임
		            	}if(columnindex==sortingVO.getSedsDeliveryDiscountPrice()) {
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		
		            		orderVO.setOrDeliveryDiscountPrice((int)Integer.parseInt(value));
		            		//판매자 상품코드 : 현재 필요없어 보임
		            	}if(columnindex== -1) {
		            		
		            		//수취인 연락처 1
		            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrReceiverContractNumber1(value);
		            		//수취인 연락처 2
		            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
		            		String value = cellTypeReturn(cell);
		            		
		            		
		            		orderVO.setOrReceiverContractNumber2(value);
		            		//배송지
		            	}if(columnindex== -1) {
		            		
		            		//구매자연락처
		            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
		            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
		            		
		            		//우편번호
		            	}if(columnindex==sortingVO.getSedsShippingAddressNumber()) {
		            		orderVO.setOrShippingAddressNumber(cell.getStringCellValue());
		            		
		            	}if(columnindex==sortingVO.getSedsPaymentType()) {
		            		orderVO.setOrPaymentType(cell.getStringCellValue());
		            		
		            		//수수료 과금구분
		            	}if(columnindex==-1) {
		            		
		            		//수수료결제방식
		            	}if(columnindex== -1) {
		            		
		            		//결제수수료
		            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
		            		
		            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
		            		
		            		
		            		//네이버 쇼핑 매출연동 수수료
		            	}if(columnindex== -1 ) {
		            		
		            		String value = "";
		            		
		            		 switch (cell.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                                value=cell.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                                value=((int)cell.getNumericCellValue())+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                                value=cell.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                                value=cell.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                                value=cell.getErrorCellValue()+"";
	                                break;
	                            }
		            		 
		            		orderVO.setOrAnotherPaymentCommision((int)Integer.parseInt(value)); //////////////////////
		            		
		            		//정산예정금액
		            	}if(columnindex== -1) {
		            		
		            		//유입경로
		            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
		            		
		            		orderVO.setOrInflowRoute(cell.getStringCellValue());
		            		
		            		//주문일시 : 결제일과 다름
		            	}if(columnindex== -1) { //////////////////
		            		
		            		//구매자주민등록번호 : 현재 우리에게 필요없음
		            	}if(columnindex== -1) {
		            		
		            		//옵션 관리 코드
		            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
		            		
		            		orderVO.setOrUserColumn1(cell.getStringCellValue());
		            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
		            	}if(columnindex==51) {
		            		
		            		//수령주소
		            	}if(orderVO.getSsFk() == 17) {
		            		if(columnindex==sortingVO.getSedsShippingAddress()) {
		            			orderVO.setOrShippingAddress(cell.getStringCellValue());
		            		}			            		
		            	}
		            	if(columnindex==sortingVO.getSedsShippingAddress()) {
		            		
		            		int subNum = cell.getStringCellValue().lastIndexOf("(");

		            		if(subNum > 0) {
		            			
		            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
		            			
		            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
		            			orderVO.setOrShippingAddressDetail(subStr);
		            		}else {
		            			
		            			orderVO.setOrShippingAddress(cell.getStringCellValue());
		            		}
		            		
		            		//수령주소 상세사항
		            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
		            		
		            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
		            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
		            			
		            		}else {
		            			
		            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
		            			
		            		}
		            	
		            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
		            		
		            		orderVO.setOrUserColumn2(cell.getStringCellValue());
		            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
		            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
		            		
		            		orderVO.setOrUserColumn3(cell.getStringCellValue());
		            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
		            	}
		            }
		            
		        }//for
		    }
		    
		    if(sortingVO.getSedsOrderCheckDay() == -1) {
		    	orderVO.setOrCheckDay(new Date(ts.getTime()));
		    	
		    }if(sortingVO.getSedsSendingDeadline() == -1) {
		    	orderVO.setOrSendingDeadline(new Date(ts.getTime()));
		    	
		    }
		    orderVO.setOrRegdate(ts);
		    orderVO.setOrSendingAddress("(21126) 인천광역시 계양구 효서로  388 삼형제고기");
		    orderList.add(orderVO);
		    orderVO = null;
		}//for
		
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}
	
	return orderList;
}
	
	public List<OrdersVO> readGiftSetExcelFile(String fileName, OrdersVO originalOrVO) throws POIXMLException{
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		FileuploadUtil fu = new FileuploadUtil();
		
		String ext = fu.getExtType(fileName);
		
		SmartstoreCommission sc = new SmartstoreCommission();
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int orderCounting = 0;
		
		try {
	
			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if(ext.equals(".xlsx")) {
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					OrdersVO orderVO;
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						orderVO = new OrdersVO();
						
						if(row !=null){
							boolean nullCount = false;
							orderVO = originalOrVO.copy();
							
							for(columnindex=0;columnindex<10;columnindex++){
								
								XSSFCell cell=row.getCell(columnindex);
								// 판매처별로 엑셀 열을 읽어서 씀
								//구매자명
								if(cell==null) {
									if(columnindex == 1) {
										nullCount = true;
										break;
									}
									continue;
									
								}else {
									
									if(columnindex==0) {
										if(cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
										}else {											
											String value = cellTypeReturn(cell);
											
											orderVO.setOrBuyerAnotherName(value);
										}
											
									}if(columnindex==1) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrReceiverName(value);
											
									}if(columnindex==2) {
										
										String value = cellTypeReturn(cell);
										
										if(value.length() < 5) {
											value = "0"+value;
										}
										
										orderVO.setOrShippingAddressNumber(value);
										
									}if(columnindex==3) {
										
										String value = cellTypeReturn(cell);
										
										int subNum = value.lastIndexOf("(");

					            		if(subNum > 0) {
					            			
					            			String subStr = value.substring(subNum, value.length());
					            			
					            			orderVO.setOrShippingAddress(value.substring(0, subNum));
					            			orderVO.setOrShippingAddressDetail(subStr);
					            		}else {
					            			
					            			orderVO.setOrShippingAddress(value);
					            			orderVO.setOrShippingAddressDetail("");
					            		}
											
									}if(columnindex==4) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrReceiverContractNumber1(value);
											
									}if(columnindex==5) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrReceiverContractNumber2(value);
									}if(columnindex==6) {
										
										orderVO.setOrAmount((int)cell.getNumericCellValue());
											
									}if(columnindex==7) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrProduct(value);
											
									}if(columnindex==8) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrProductOption(value);
											
									}if(columnindex==9) {
										
										String value = cellTypeReturn(cell);
										
										orderVO.setOrDeliveryMessage(value);
										
											
									}
								}
								
							}//for
							orderVO.setOrOrderNumber(originalOrVO.getOrOrderNumber()+"-"+orderCounting);
							orderVO.setOrProductOrderNumber(originalOrVO.getOrProductOrderNumber()+"-"+orderCounting);
							orderVO.setOrProductPrice(originalOrVO.getOrProductPrice()/originalOrVO.getOrAmount());
							orderVO.setOrProductOptionPrice(originalOrVO.getOrProductOptionPrice()/originalOrVO.getOrAmount());
							orderVO.setOrDiscountPrice(originalOrVO.getOrDiscountPrice()/originalOrVO.getOrAmount());
							orderVO.setOrTotalPrice(originalOrVO.getOrTotalPrice()/originalOrVO.getOrAmount());
							orderVO.setOrTotalCost(0);
							if(orderCounting > 1) {
								orderVO.setOrDeliveryPrice(0);
								orderVO.setOrDeliveryDiscountPrice(0);
							}
							orderVO.setOrPaymentCommision(originalOrVO.getOrPaymentCommision()/originalOrVO.getOrAmount());
							orderVO.setOrAnotherPaymentCommision(originalOrVO.getOrAnotherPaymentCommision()/originalOrVO.getOrAmount());
							orderVO.setOrFk(orderVO.getOrPk());
							
							orderCounting++;
							
							if(nullCount == false) orderList.add(orderVO);
						}
						
						
						
					}//for
				
			//엑셀파일 확장자가 xls일 경우
			}else if(ext.equals(".xls")){
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = 1;
				
				int columnindex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				OrdersVO orderVO;
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
					HSSFRow row=sheet.getRow(rowindex);
					
					orderVO = new OrdersVO();
					
					if(row !=null){
						boolean nullCount = false;
						orderVO = originalOrVO.copy();
						
						for(columnindex=0;columnindex<10;columnindex++){
							
							HSSFCell cell=row.getCell(columnindex);
							// 판매처별로 엑셀 열을 읽어서 씀
							//구매자명
							if(cell==null) {
								if(columnindex == 1) {
									nullCount = true;
									break;
								}
								continue;

							}else {

								if(columnindex==0) {
									if(cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
									}else {											
										String value = cellTypeReturnHSS(cell);
										
										orderVO.setOrBuyerAnotherName(value);
									}
										
								}if(columnindex==1) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrReceiverName(value);
										
								}if(columnindex==2) {
									
									String value = cellTypeReturnHSS(cell);
									
									if(value.length() < 5) {
										value = "0"+value;
									}
									
									orderVO.setOrShippingAddressNumber(value);
									
								}if(columnindex==3) {
									
									String value = cellTypeReturnHSS(cell);
									
									int subNum = value.lastIndexOf("(");

				            		if(subNum > 0) {
				            			
				            			String subStr = value.substring(subNum, value.length());
				            			
				            			orderVO.setOrShippingAddress(value.substring(0, subNum));
				            			orderVO.setOrShippingAddressDetail(subStr);
				            		}else {
				            			
				            			orderVO.setOrShippingAddress(value);
				            			orderVO.setOrShippingAddressDetail("");
				            		}
										
								}if(columnindex==4) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrReceiverContractNumber1(value);
										
								}if(columnindex==5) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrReceiverContractNumber2(value);
								}if(columnindex==6) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrAmount((int) Double.parseDouble(value));
										
								}if(columnindex==7) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrProduct(value);
										
								}if(columnindex==8) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrProductOption(value);
										
								}if(columnindex==9) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrDeliveryMessage(value);
								}
							}
							
						}//for
						
						orderVO.setOrOrderNumber(originalOrVO.getOrOrderNumber()+"-"+orderCounting);
						orderVO.setOrProductOrderNumber(originalOrVO.getOrProductOrderNumber()+"-"+orderCounting);
						orderVO.setOrProductPrice(originalOrVO.getOrProductPrice()/originalOrVO.getOrAmount());
						orderVO.setOrProductOptionPrice(originalOrVO.getOrProductOptionPrice()/originalOrVO.getOrAmount());
						orderVO.setOrDiscountPrice(originalOrVO.getOrDiscountPrice()/originalOrVO.getOrAmount());
						orderVO.setOrTotalPrice(originalOrVO.getOrTotalPrice()/originalOrVO.getOrAmount());
						orderVO.setOrTotalCost(0);
						if(orderCounting > 1) {
							orderVO.setOrDeliveryPrice(0);
							orderVO.setOrDeliveryDiscountPrice(0);
						}
						orderVO.setOrPaymentCommision(originalOrVO.getOrPaymentCommision()/originalOrVO.getOrAmount());
						orderVO.setOrAnotherPaymentCommision(originalOrVO.getOrAnotherPaymentCommision()/originalOrVO.getOrAmount());
						orderVO.setOrFk(orderVO.getOrPk());
						
						orderCounting++;
						
						if(nullCount == false) orderList.add(orderVO);
					}
					
				}//for
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orderList;
	}

	public String cellTypeReturn(XSSFCell cell) {
		
		String value = "";
		if(cell == null) return "";
		
		 switch (cell.getCellType()){
           case HSSFCell.CELL_TYPE_FORMULA:
               value=cell.getCellFormula()+"";
               break;
               
           case HSSFCell.CELL_TYPE_NUMERIC:
	           	if(HSSFDateUtil.isCellDateFormatted(cell)) {
	           		value=dateFormat.format(cell.getDateCellValue());
	           		
	           	}else {		                            		
	           		value=((int)cell.getNumericCellValue())+"";
	           	}
	           	
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value=cell.getStringCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_BLANK:
               value=cell.getBooleanCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_ERROR:
               value=cell.getErrorCellValue()+"";
               break;
               
           default: 
        	   value=cell.getStringCellValue()+"";
           	break;
           }
		 
		return value;
	}
	
public String cellTypeReturnSXSS(SXSSFCell cell) {
		
		String value = "";
		
		 switch (cell.getCellType()){
           case HSSFCell.CELL_TYPE_FORMULA:
               value=cell.getCellFormula()+"";
               break;
               
           case HSSFCell.CELL_TYPE_NUMERIC:
	           	if(HSSFDateUtil.isCellDateFormatted(cell)) {
	           		value=dateFormat.format(cell.getDateCellValue());
	           		
	           	}else {		                            		
	           		value=((int)cell.getNumericCellValue())+"";
	           	}
	           	
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value=cell.getStringCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_BLANK:
               value=cell.getBooleanCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_ERROR:
               value=cell.getErrorCellValue()+"";
               break;
               
           default: 
        	   value=cell.getStringCellValue()+"";
           	break;
           }
		 
		return value;
	}
	
	public String cellTypeReturnHSS(HSSFCell cell) {
		
		String value = "";
		if(cell == null) return "";
		
		 switch (cell.getCellType()){
           case HSSFCell.CELL_TYPE_FORMULA:
               value=cell.getCellFormula()+"";
               break;
               
           case HSSFCell.CELL_TYPE_NUMERIC:
	           	if(HSSFDateUtil.isCellDateFormatted(cell)) {
	           		value=dateFormat.format(cell.getDateCellValue());
	           		
	           	}else {		                            		
	           		value=((int)cell.getNumericCellValue())+"";
	           	}
	           	
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value=cell.getStringCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_BLANK:
               value=cell.getBooleanCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_ERROR:
               value=cell.getErrorCellValue()+"";
               break;
               
           default: 
        	   value=cell.getStringCellValue()+"";
           	break;
           }
		 
		return value;
	}
	
	/**
	 * 
	 * @MethodName : cancledExcelFile
	 * @date : 2020. 6. 30.
	 * @author : Jeon KiChan
	 * @param fileName
	 * @param sortingVO
	 * @return
	 * @throws POIXMLException
	 * @메소드설명 : 주문 취소 엑셀열 읽어 들이기
	 */
	public List<OrdersVO> cancledExcelFile(String fileName, StoreCancleExcelDataSortingVO sortingVO) throws POIXMLException{
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		FileuploadUtil fu = new FileuploadUtil();
		
		String ext = fu.getExtType(fileName);
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int orderCounting = 0;

		
		try {
	
			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if(ext.equals(".xlsx")) {
			
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					OrdersVO orderVO = null;
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						orderVO = new OrdersVO();
						
						if(row !=null){
							
							for(columnindex=0;columnindex<58;columnindex++){
								
								XSSFCell cell=row.getCell(columnindex);
								// 판매처별로 엑셀 열을 읽어서 씀
								if(cell==null) {
									continue;
									
								}else {

									if(columnindex==sortingVO.getScedsOrderNumber()) {
										String value = cellTypeReturn(cell);
										
										orderVO.setOrOrderNumber(value);
											
									}if(columnindex==sortingVO.getScedsProductOrderNumber()) {
										
										String value = cellTypeReturn(cell);
										
										orderVO.setOrProductOrderNumber(value);
									}if(columnindex==sortingVO.getScedsReason()) {
										
										String value = cellTypeReturn(cell);
										
										orderVO.setOrUserColumn1(value);
											
									}if(columnindex==sortingVO.getScedsDate()) {
										String value = cellTypeReturn(cell);

										orderVO.setOrRegdate(new Timestamp(sdf.parse(value).getTime()));
									}
								}
								
							}//for
							orderList.add(orderVO);
							orderVO = null;
						}
						
						
						
					}//for
				
			//엑셀파일 확장자가 xls일 경우
			}else if(ext.equals(".xls")){
				
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = 1;
				
				int columnindex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				OrdersVO orderVO = null;
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
					HSSFRow row=sheet.getRow(rowindex);
					
					orderVO = new OrdersVO();
					
					if(row !=null){
						
						for(columnindex=0;columnindex<58;columnindex++){
							
							HSSFCell cell=row.getCell(columnindex);
							// 판매처별로 엑셀 열을 읽어서 씀
							//구매자명
							if(cell==null) {
								continue;
								
							}else {
								if(columnindex==sortingVO.getScedsOrderNumber()) {
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrOrderNumber(value);
										
								}if(columnindex==sortingVO.getScedsProductOrderNumber()) {
									
									String value = cellTypeReturnHSS(cell);

									orderVO.setOrProductOrderNumber(value);
									
								}if(columnindex==sortingVO.getScedsReason()) {
									
									String value = cellTypeReturnHSS(cell);
									
									orderVO.setOrUserColumn1(value);
										
								}if(columnindex==sortingVO.getScedsDate()) {
									String value = cellTypeReturnHSS(cell);

									orderVO.setOrRegdate(new Timestamp(sdf.parse(value).getTime()));
								}
							}
							
						}//for
						
						orderList.add(orderVO);
						orderVO = null;
					}
					
					
					
				}//for
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orderList;
	}
	
	
public List<OrdersVO> readOrderExcelData(String fileName, int ssFk, StoreExcelDataSortingVO sortingVO, boolean sendingDeadlineFlag) throws POIXMLException{
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		SmartstoreCommission sc = new SmartstoreCommission();
		
		FileuploadUtil fu = new FileuploadUtil();
		
		Calendar cal = Calendar.getInstance();
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
		
		String ext = fu.getExtType(fileName);
		
		try {
	
			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if(ext.equals(".xlsx")) {
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = sortingVO.getSedsStartRow();
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					OrdersVO orderVO;
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
					    XSSFRow row=sheet.getRow(rowindex);
					    
					    orderVO = new OrdersVO();
					    
					    if(row !=null){
					        
					        for(columnindex=0;columnindex<58;columnindex++){
			
					            XSSFCell cell=row.getCell(columnindex);
					            	// 판매처별로 엑셀 열을 읽어서 씀
					            	//구매자명
					            if(cell==null) {
					            	continue;
					            	
					            }else {
					            	orderVO.setSsFk(ssFk);
					            	
					            	if(columnindex==sortingVO.getSedsBuyerName()) {
					            		orderVO.setOrBuyerName(cell.getStringCellValue());
					            		//구매자ID		
					            	}if(columnindex==sortingVO.getSedsBuyerId()) {
					            		
					            		String value = "";
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrBuyerId(value);
					            		//수취인명	
					            	}if(columnindex==sortingVO.getSedsReceiverName()) {
					            		orderVO.setOrReceiverName(cell.getStringCellValue());
					            		//상품명
					            	}if(columnindex==sortingVO.getSedsProduct()) {
					            		orderVO.setOrProduct(cell.getStringCellValue());
					            		//상품종류
					            	}if(columnindex==sortingVO.getSedsProductType()) {
					            		orderVO.setOrProductType(cell.getStringCellValue());
					            		//수량
					            	}if(columnindex==sortingVO.getSedsQuantity()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		
					            		orderVO.setOrAmount((int)Integer.parseInt(value));
					            		//옵션정보 : 옵션명
					            	}if(columnindex==sortingVO.getSedsProductOption()) {
					            		
					            		if(cell.getStringCellValue().equals("")) {
					            			orderVO.setOrProductOption("단일상품");
					            			
					            		}else {
					            			orderVO.setOrProductOption(cell.getStringCellValue());
					            			
					            		}
					            		
					            		//배송메세지
					            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
					            		if(cell.getStringCellValue() != null) {
					            			
					            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
					            		}else {
					            			orderVO.setOrDeliveryMessage("");
					            		}
					            		//배송방법
					            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
					            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
					            		//택배사
					            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
					            		//
					            		//송장번호
					            	}if(columnindex == -1) {
					            		
					            		//상품주문번호
					            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrProductOrderNumber(value);
					            		//주문번호
					            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		 
					            		orderVO.setOrOrderNumber(value);
					            		//발송일 : 다운로드 받은 시간이 나옴 보류해야됌
					            	}if(columnindex==sortingVO.getSedsSendingDay()) {
					            		
					            		//주문상태 : 아직 필요한지 모름
					            	}if(columnindex== -1) {
					            		
					            		//주문세부상태 : 아직 필요한지 모름
					            	}if(columnindex== -1) {
					            		
					            		//결제위치 : PC or MOBILE
					            	}if(columnindex==sortingVO.getSedsPaymentType()) {
					            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
					            		//결제일
					            	}if(columnindex== sortingVO.getSedsSettlementDay()) {

					            		String value = cellTypeReturn(cell);
					            			
					            		if(value.equals("false")) {
					            			continue;
					            			
					            		}else {
					            			if(value.length() == 16) {
					            				value=value+":00";
					            			}

					            			orderVO.setOrSettlementDay(new Timestamp(sdf.parse(value).getTime())); 
					            		}
					            		//상품번호 : 스마트스토어 내에서의 상품번호 ( 필요없을 것 같음 )
					            	}if(columnindex==sortingVO.getSedsProductNumber()) {
					            		orderVO.setOrProductNumber(cell.getStringCellValue());
					            		//옵션 가격
					            	}if(columnindex==sortingVO.getSedsProductOptionPrice()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            				
					            		 
					            		orderVO.setOrProductOptionPrice((int)Integer.parseInt(value));
					            		//상품 가격
					            	}if(columnindex==sortingVO.getSedsProductPrice()) {
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		
					            		orderVO.setOrProductPrice((int)Integer.parseInt(value));
					            		//상품별 할인액
					            	}if(columnindex==sortingVO.getSedsDiscountPrice()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		if(value == null || value.equals("")) {
					            			orderVO.setOrDiscountPrice(0);
					            		}else {
					            			
					            			orderVO.setOrDiscountPrice((int)Integer.parseInt(value));
					            		}
					            		//판매자 부담 할인액
					            	}if(columnindex==22) {
					            		
					            		//필요없을거 같음 상품별 할인액이 판매자 부담 할인액으로 되는 것으로 판단
					            		
					            		
					            		//상품별 총 주문금액
					            	}if(columnindex==sortingVO.getSedsTotalPrice()) {
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrTotalPrice((int)Integer.parseInt(value));
					            		//발주확인일 : 공백임
					            	}if(columnindex==sortingVO.getSedsOrderCheckDay()) {
					            		
					            		if(cell == null) {
					            			continue;
					            		}else {
					            			
					            			String value =cellTypeReturn(cell);
						            		 
					            			if(value.length() == 16) {
					            				value=value+":00";
					            			}
					            			
					            			orderVO.setOrCheckDay(new Date(sdf.parse(value).getTime()));
					            			
					            		}
					            		//발송기한 : 이걸로 예약자를 자동으로 걸러내거나 따로 예약을 잡을 수 있도록 함
					            		
					            	}if(columnindex==sortingVO.getSedsSendingDeadline()) {
					            		
					            		if(sendingDeadlineFlag == true) {
					            			String value = cellTypeReturn(cell);
						            		 
					            			if(ssFk == 14) {
					            				Calendar cals = Calendar.getInstance();
					            				cals.setTime(new Date(sdfWithoutTime.parse(value).getTime()));
					            				cals.add(Calendar.DATE, -1);
					            				orderVO.setOrSendingDeadline(new Date(cals.getTimeInMillis()));
					            			}else {				            				
					            				orderVO.setOrSendingDeadline(new Date(sdfWithoutTime.parse(value).getTime()));
					            				
					            			}
						            		
					            		}else {
					            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
					            		}
					            		
					            		//발송처리일 : 현재 우리에게 필요없음
					            	}if(columnindex==26) {
					            		
					            		//송장출력일 : 현재 우리에게 필요없음
					            	}if(columnindex==27) {
					            		
					            		//배송비형태 : 선결제, 무료 등
					            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
					            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
					            		//배송비묶음번호 자동으로 묶을 때 쓰면 될 것 같지만 너무 많아서 따로 분류해야 할 경우엔 필요없을 것 같음. 하지만 스마트스토어로 직접 연결할 경우 필요함으로 보임
					            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrDeliveryNumber(value);
					            		//배송비유형 : 전부 조건부무료
					            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
					            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
					            		
					            		
					            		//배송비 합계 3000원 아니면 0원 일듯
					            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrDeliveryPrice((int)Integer.parseInt(value));
					            		
					            		//제주/도서 추가배송비 아직 제대로 정해지지 않음
					            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrDeliverySpecialPrice((int)Integer.parseInt(value));
					            		//배송비 할인액 : 쿠폰으로 인해 할인할 경우 활성화 되는 것처럼 보임
					            	}if(columnindex==sortingVO.getSedsDeliveryDiscountPrice()) {
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		
					            		orderVO.setOrDeliveryDiscountPrice((int)Integer.parseInt(value));
					            		//판매자 상품코드 : 현재 필요없어 보임
					            	}if(columnindex== -1) {
					            		
					            		//수취인 연락처 1
					            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrReceiverContractNumber1(value);
					            		//수취인 연락처 2
					            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
					            		String value = cellTypeReturn(cell);
					            		
					            		
					            		orderVO.setOrReceiverContractNumber2(value);
					            		//배송지
					            	}if(columnindex== -1) {
					            		
					            		//구매자연락처
					            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
					            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
					            		
					            		//우편번호
					            	}if(columnindex==sortingVO.getSedsShippingAddressNumber()) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrShippingAddressNumber(value);
					            		
					            	}if(columnindex==sortingVO.getSedsPaymentType()) {
					            		orderVO.setOrPaymentType(cell.getStringCellValue());
					            		
					            		//수수료 과금구분
					            	}if(columnindex==-1) {
					            		
					            		//수수료결제방식
					            	}if(columnindex== -1) {
					            		
					            		//결제수수료
					            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
					            		
					            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
					            		
					            		
					            		//네이버 쇼핑 매출연동 수수료
					            	}if(columnindex== -1 ) {
					            		
					            		String value = "";
					            		
					            		 switch (cell.getCellType()){
				                            case HSSFCell.CELL_TYPE_FORMULA:
				                                value=cell.getCellFormula();
				                                break;
				                            case HSSFCell.CELL_TYPE_NUMERIC:
				                                value=((int)cell.getNumericCellValue())+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_STRING:
				                                value=cell.getStringCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_BLANK:
				                                value=cell.getBooleanCellValue()+"";
				                                break;
				                            case HSSFCell.CELL_TYPE_ERROR:
				                                value=cell.getErrorCellValue()+"";
				                                break;
				                            }
					            		 
					            		orderVO.setOrAnotherPaymentCommision((int)Integer.parseInt(value)); //////////////////////
					            		
					            		//정산예정금액
					            	}if(columnindex== -1) {
					            		
					            		//유입경로
					            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
					            		
					            		orderVO.setOrInflowRoute(cell.getStringCellValue());
					            		
					            		//주문일시 : 결제일과 다름
					            	}if(columnindex== -1) { //////////////////
					            		
					            		//구매자주민등록번호 : 현재 우리에게 필요없음
					            	}if(columnindex== -1) {
					            		
					            		//옵션 관리 코드
					            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
					            		
					            		orderVO.setOrUserColumn1(cell.getStringCellValue());
					            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
					            	}if(columnindex==51) {
					            		
					            		//수령주소
					            	}if(columnindex==sortingVO.getSedsShippingAddress()) {
					            		
					            		int subNum = cell.getStringCellValue().lastIndexOf("(");

					            		if(subNum > 0) {
					            			
					            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
					            			
					            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
					            			orderVO.setOrShippingAddressDetail(subStr);
					            		}else {
					            			
					            			orderVO.setOrShippingAddress(cell.getStringCellValue());
					            		}
					            		
					            		//수령주소 상세사항
					            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
					            		
					            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
					            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
					            			
					            		}else {
					            			
					            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
					            			
					            		}
					            	
					            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
					            		
					            		orderVO.setOrUserColumn2(cell.getStringCellValue());
					            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
					            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
					            		
					            		orderVO.setOrUserColumn3(cell.getStringCellValue());
					            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
					            	}
					            }
					            
					        }//for
					    }
					    
					    if(sortingVO.getSedsOrderCheckDay() == -1) {
					    	orderVO.setOrCheckDay(new Date(ts.getTime()));
					    	
					    }if(sortingVO.getSedsSendingDeadline() == -1) {
					    	orderVO.setOrSendingDeadline(new Date(ts.getTime()));
					    	
					    }
					    orderVO.setOrRegdate(ts);
					    orderVO.setOrSendingAddress("(21126) 인천광역시 계양구 효서로  388 삼형제고기");
					    orderList.add(orderVO);
					    orderVO = null;
					}//for
				
			//엑셀파일 확장자가 xls일 경우
			}else if(ext.equals(".xls")){
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = sortingVO.getSedsStartRow();
				
				int columnindex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				OrdersVO orderVO;
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
				    HSSFRow row=sheet.getRow(rowindex);
				    
				    orderVO = new OrdersVO();
				    
				    if(row !=null){
				        
				        for(columnindex=0;columnindex<58;columnindex++){
		
				            HSSFCell cell=row.getCell(columnindex);
				            	// 판매처별로 엑셀 열을 읽어서 씀
				            	//구매자명
				            if(cell==null) {
				            	continue;
				            	
				            }else {
				            	orderVO.setSsFk(ssFk);
				            	
				            	if(columnindex==sortingVO.getSedsBuyerName()) {
				            		orderVO.setOrBuyerName(cell.getStringCellValue());
				            		//구매자ID		
				            	}if(columnindex==sortingVO.getSedsBuyerId()) {
				            		
				            		String value = "";
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		orderVO.setOrBuyerId(value);
				            		//수취인명	
				            	}if(columnindex==sortingVO.getSedsReceiverName()) {
				            		orderVO.setOrReceiverName(cell.getStringCellValue());
				            		//상품명
				            	}if(columnindex==sortingVO.getSedsProduct()) {
				            		orderVO.setOrProduct(cell.getStringCellValue());
				            		//상품종류
				            	}if(columnindex==sortingVO.getSedsProductType()) {
				            		orderVO.setOrProductType(cell.getStringCellValue());
				            		//수량
				            	}if(columnindex==sortingVO.getSedsQuantity()) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		
				            		orderVO.setOrAmount((int)Integer.parseInt(value));
				            		//옵션정보 : 옵션명
				            	}if(columnindex==sortingVO.getSedsProductOption()) {
				            		
				            		if(cell.getStringCellValue().equals("")) {
				            			orderVO.setOrProductOption("단일상품");
				            			
				            		}else {
				            			orderVO.setOrProductOption(cell.getStringCellValue());
				            			
				            		}
				            		
				            		//배송메세지
				            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
				            		if(cell.getStringCellValue() != null) {
				            			
				            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
				            		}else {
				            			orderVO.setOrDeliveryMessage("");
				            		}
				            		//배송방법
				            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
				            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
				            		//택배사
				            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
				            		//
				            		//송장번호
				            	}if(columnindex == -1) {
				            		
				            		//상품주문번호
				            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrProductOrderNumber(value);
				            		//주문번호
				            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		 
				            		orderVO.setOrOrderNumber(value);
				            		//발송일 : 다운로드 받은 시간이 나옴 보류해야됌
				            	}if(columnindex==sortingVO.getSedsSendingDay()) {
				            		
				            		//주문상태 : 아직 필요한지 모름
				            	}if(columnindex== -1) {
				            		
				            		//주문세부상태 : 아직 필요한지 모름
				            	}if(columnindex== -1) {
				            		
				            		//결제위치 : PC or MOBILE
				            	}if(columnindex==sortingVO.getSedsPaymentType()) {
				            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
				            		//결제일
				            	}if(columnindex== sortingVO.getSedsSettlementDay()) {

				            		String value = cellTypeReturnHSS(cell);
				            			
				            		if(value.equals("false")) {
				            			continue;
				            			
				            		}else {
				            			if(value.length() == 16) {
				            				value=value+":00";
				            			}

				            			orderVO.setOrSettlementDay(new Timestamp(sdf.parse(value).getTime())); 
				            		}
				            		//상품번호 : 스마트스토어 내에서의 상품번호 ( 필요없을 것 같음 )
				            	}if(columnindex==sortingVO.getSedsProductNumber()) {
				            		orderVO.setOrProductNumber(cell.getStringCellValue());
				            		//옵션 가격
				            	}if(columnindex==sortingVO.getSedsProductOptionPrice()) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            				
				            		 
				            		orderVO.setOrProductOptionPrice((int)Integer.parseInt(value));
				            		//상품 가격
				            	}if(columnindex==sortingVO.getSedsProductPrice()) {
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		
				            		orderVO.setOrProductPrice((int)Integer.parseInt(value));
				            		//상품별 할인액
				            	}if(columnindex==sortingVO.getSedsDiscountPrice()) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		if(value == null || value.equals("")) {
				            			orderVO.setOrDiscountPrice(0);
				            		}else {
				            			
				            			orderVO.setOrDiscountPrice((int)Integer.parseInt(value));
				            		}
				            		//판매자 부담 할인액
				            	}if(columnindex==22) {
				            		
				            		//필요없을거 같음 상품별 할인액이 판매자 부담 할인액으로 되는 것으로 판단
				            		
				            		
				            		//상품별 총 주문금액
				            	}if(columnindex==sortingVO.getSedsTotalPrice()) {
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		orderVO.setOrTotalPrice((int)Integer.parseInt(value));
				            		//발주확인일 : 공백임
				            	}if(columnindex==sortingVO.getSedsOrderCheckDay()) {
				            		
				            		if(cell == null) {
				            			continue;
				            		}else {
				            			
				            			String value =cellTypeReturnHSS(cell);
					            		 
				            			if(value.length() == 16) {
				            				value=value+":00";
				            			}
				            			
				            			orderVO.setOrCheckDay(new Date(sdf.parse(value).getTime()));
				            			
				            		}
				            		//발송기한 : 이걸로 예약자를 자동으로 걸러내거나 따로 예약을 잡을 수 있도록 함
				            		
				            	}if(columnindex==sortingVO.getSedsSendingDeadline()) {
				            		
				            		if(sendingDeadlineFlag == true) {
				            			String value = cellTypeReturnHSS(cell);
					            		 
				            			if(ssFk == 14) {
				            				Calendar cals = Calendar.getInstance();
				            				cals.setTime(new Date(sdfWithoutTime.parse(value).getTime()));
				            				cals.add(Calendar.DATE, 1);
				            				Date d = (Date) cals.getTime();
				            				orderVO.setOrSendingDeadline(d);
				            			}else {				            				
				            				orderVO.setOrSendingDeadline(new Date(sdfWithoutTime.parse(value).getTime()));
				            				
				            			}
					            		
				            		}else {
				            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
				            		}
				            		
				            		//발송처리일 : 현재 우리에게 필요없음
				            	}if(columnindex==26) {
				            		
				            		//송장출력일 : 현재 우리에게 필요없음
				            	}if(columnindex==27) {
				            		
				            		//배송비형태 : 선결제, 무료 등
				            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
				            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
				            		//배송비묶음번호 자동으로 묶을 때 쓰면 될 것 같지만 너무 많아서 따로 분류해야 할 경우엔 필요없을 것 같음. 하지만 스마트스토어로 직접 연결할 경우 필요함으로 보임
				            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrDeliveryNumber(value);
				            		//배송비유형 : 전부 조건부무료
				            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
				            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
				            		
				            		
				            		//배송비 합계 3000원 아니면 0원 일듯
				            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		orderVO.setOrDeliveryPrice((int)Integer.parseInt(value));
				            		
				            		//제주/도서 추가배송비 아직 제대로 정해지지 않음
				            	}if(columnindex==sortingVO.getSedsDeliveryPrice()) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		orderVO.setOrDeliverySpecialPrice((int)Integer.parseInt(value));
				            		//배송비 할인액 : 쿠폰으로 인해 할인할 경우 활성화 되는 것처럼 보임
				            	}if(columnindex==sortingVO.getSedsDeliveryDiscountPrice()) {
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		
				            		orderVO.setOrDeliveryDiscountPrice((int)Integer.parseInt(value));
				            		//판매자 상품코드 : 현재 필요없어 보임
				            	}if(columnindex== -1) {
				            		
				            		//수취인 연락처 1
				            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrReceiverContractNumber1(value);
				            		//수취인 연락처 2
				            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		
				            		orderVO.setOrReceiverContractNumber2(value);
				            		//배송지
				            	}if(columnindex== -1) {
				            		
				            		//구매자연락처
				            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
				            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
				            		
				            		//우편번호
				            	}if(columnindex==sortingVO.getSedsShippingAddressNumber()) {
				            		orderVO.setOrShippingAddressNumber(cell.getStringCellValue());
				            		
				            	}if(columnindex==sortingVO.getSedsPaymentType()) {
				            		orderVO.setOrPaymentType(cell.getStringCellValue());
				            		
				            		//수수료 과금구분
				            	}if(columnindex==-1) {
				            		
				            		//수수료결제방식
				            	}if(columnindex== -1) {
				            		
				            		//결제수수료
				            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
				            		
				            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
				            		
				            		
				            		//네이버 쇼핑 매출연동 수수료
				            	}if(columnindex== -1 ) {
				            		
				            		String value = "";
				            		
				            		 switch (cell.getCellType()){
			                            case HSSFCell.CELL_TYPE_FORMULA:
			                                value=cell.getCellFormula();
			                                break;
			                            case HSSFCell.CELL_TYPE_NUMERIC:
			                                value=((int)cell.getNumericCellValue())+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_STRING:
			                                value=cell.getStringCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_BLANK:
			                                value=cell.getBooleanCellValue()+"";
			                                break;
			                            case HSSFCell.CELL_TYPE_ERROR:
			                                value=cell.getErrorCellValue()+"";
			                                break;
			                            }
				            		 
				            		orderVO.setOrAnotherPaymentCommision((int)Integer.parseInt(value)); //////////////////////
				            		
				            		//정산예정금액
				            	}if(columnindex== -1) {
				            		
				            		//유입경로
				            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
				            		
				            		orderVO.setOrInflowRoute(cell.getStringCellValue());
				            		
				            		//주문일시 : 결제일과 다름
				            	}if(columnindex== -1) { //////////////////
				            		
				            		//구매자주민등록번호 : 현재 우리에게 필요없음
				            	}if(columnindex== -1) {
				            		
				            		//옵션 관리 코드
				            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
				            		
				            		orderVO.setOrUserColumn1(cell.getStringCellValue());
				            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
				            	}if(columnindex==51) {
				            		
				            		//수령주소
				            	}if(columnindex==sortingVO.getSedsShippingAddress()) {
				            		
				            		int subNum = cell.getStringCellValue().lastIndexOf("(");

				            		if(subNum > 0) {
				            			
				            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
				            			
				            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
				            			orderVO.setOrShippingAddressDetail(subStr);
				            		}else {
				            			
				            			orderVO.setOrShippingAddress(cell.getStringCellValue());
				            		}
				            		
				            		//수령주소 상세사항
				            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
				            		
				            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
				            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
				            			
				            		}else {
				            			
				            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
				            			
				            		}
				            	
				            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
				            		
				            		orderVO.setOrUserColumn2(cell.getStringCellValue());
				            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
				            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
				            		
				            		orderVO.setOrUserColumn3(cell.getStringCellValue());
				            		//수취인연락처2 : 이것도 또 왜 있는지 모르겠음
				            	}
				            }
				            
				        }//for
				    }
				    
				    if(sortingVO.getSedsOrderCheckDay() == -1) {
				    	orderVO.setOrCheckDay(new Date(ts.getTime()));
				    	
				    }if(sortingVO.getSedsSendingDeadline() == -1) {
				    	orderVO.setOrSendingDeadline(new Date(ts.getTime()));
				    	
				    }
				    orderVO.setOrRegdate(ts);
				    orderVO.setOrSendingAddress("(21126) 인천광역시 계양구 효서로  388 삼형제고기");
				    orderList.add(orderVO);
				    orderVO = null;
				}//for
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return orderList;
	}

}
