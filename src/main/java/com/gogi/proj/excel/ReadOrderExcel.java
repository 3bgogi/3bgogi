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
			            	//?????????????????? ??????
			            	//????????????
			           
			            if(cell == null) {
			            	
			            	continue;
			            }else{
			            	orderVO.setSsFk(ssFk);
			            	if(columnindex==0) {
			            		orderVO.setOrBuyerName(cell.getStringCellValue());
			            		//?????????ID		
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
			            		//????????????	
			            	}else if(columnindex==2) {
			            		orderVO.setOrReceiverName(cell.getStringCellValue());
			            		//?????????
			            	}else if(columnindex==3) {
			            		orderVO.setOrProduct(cell.getStringCellValue());
			            		//????????????
			            	}else if(columnindex==4) {
			            		orderVO.setOrProductType(cell.getStringCellValue());
			            		//??????
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
			            		//???????????? : ?????????
			            	}else if(columnindex==6) {
			            		
			            		String value = "";
			            		
			            		if(cell == null ) {
			            			orderVO.setOrProductOption("????????????");
			            			
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
			            		//???????????????
			            	}else if(columnindex==7) {
			            		if(cell != null) {
			            			String delivMsg = cell.getStringCellValue()+"";
			            			int firstIndex = delivMsg.lastIndexOf("[");
			            			int lastIndex = delivMsg.lastIndexOf("]");
			            			
			            			// [   ] ???????????? ??? ??????????????? ?????? ?????? ?????? ??????
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
			            		//????????????
			            	}else if(columnindex==8) {
			            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
			            		//?????????
			            	}else if(columnindex==9) {
			            		//
			            		//????????????
			            	}else if(columnindex==10) {
			            		
			            		//??????????????????
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
			            		//????????????
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
			            		//????????? : ???????????? ?????? ????????? ?????? ???????????????
			            	}else if(columnindex==13) {
			            		
			            		//???????????? : ?????? ???????????? ??????
			            	}else if(columnindex==14) {
			            		
			            		//?????????????????? : ?????? ???????????? ??????
			            	}else if(columnindex==15) {
			            		
			            		//???????????? : PC or MOBILE
			            	}else if(columnindex==16) {
			            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
			            		//?????????
			            	}else if(columnindex==17) {
			            		orderVO.setOrSettlementDay(new Timestamp(cell.getDateCellValue().getTime()));
			            		//???????????? : ?????????????????? ???????????? ???????????? ( ???????????? ??? ?????? )
			            	}else if(columnindex==18) {
			            		orderVO.setOrProductNumber(cell.getStringCellValue());
			            		//?????? ??????
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
			            		//?????? ??????
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
			            		//????????? ?????????
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
			            		//????????? ?????? ?????????
			            	}else if(columnindex==22) {
			            		//??????????????? ?????? ????????? ???????????? ????????? ?????? ??????????????? ?????? ????????? ??????
			            		//????????? ??? ????????????
			            	}else if(columnindex==23) {
			            		orderVO.setOrTotalPrice((int)cell.getNumericCellValue());
			            		//??????????????? : ?????????
			            	}else if(columnindex==24) {
			            		
			            		if(cell.getDateCellValue() == null) {
			            			continue;
			            		}else {			            			
			            			orderVO.setOrCheckDay(new Date(cell.getDateCellValue().getTime()));
			            			
			            		}
			            		//???????????? : ????????? ???????????? ???????????? ??????????????? ?????? ????????? ?????? ??? ????????? ???
			            	}else if(columnindex==25) {
			            		if(sendingDeadlineFlag == true) {
			            			orderVO.setOrSendingDeadline(new Date(cell.getDateCellValue().getTime()));
			            			
			            		}else {
			            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
			            		}
			            		//??????????????? : ?????? ???????????? ????????????
			            	}else if(columnindex==26) {
			            		
			            		//??????????????? : ?????? ???????????? ????????????
			            	}else if(columnindex==27) {
			            		
			            		//??????????????? : ?????????, ?????? ???
			            	}else if(columnindex==28) {
			            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
			            		//????????????????????? ???????????? ?????? ??? ?????? ??? ??? ????????? ?????? ????????? ?????? ???????????? ??? ????????? ???????????? ??? ??????. ????????? ????????????????????? ?????? ????????? ?????? ??????????????? ??????
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
			            		//??????????????? : ?????? ???????????????
			            	}else if(columnindex==30) {
			            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
			            		//????????? ?????? 3000??? ????????? 0??? ??????
			            	}else if(columnindex==31) {
			            		orderVO.setOrDeliveryPrice((int)cell.getNumericCellValue());
			            		//??????/?????? ???????????????
			            	}else if(columnindex==32) {
			            		orderVO.setOrDeliverySpecialPrice((int)cell.getNumericCellValue());
			            		//????????? ????????? : ???????????? ?????? ????????? ?????? ????????? ?????? ????????? ??????
			            	}else if(columnindex==33) {
			            		orderVO.setOrDeliveryDiscountPrice((int)cell.getNumericCellValue());
			            		//????????? ???????????? : ?????? ???????????? ??????
			            	}else if(columnindex==34) {
			            		
			            		//????????? ????????? 1
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
			            		//????????? ????????? 2
			            	}else if(columnindex==55) {
			            		orderVO.setOrReceiverContractNumber2(cell.getStringCellValue());
			            		//?????????
			            	}else if(columnindex==37) {
			            		
			            		//??????????????????
			            	}else if(columnindex==38) {
			            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
			            		//????????????
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
			            		//????????? : ??????????????? ?????????
			            	}else if(columnindex==40) {
			            		orderVO.setOrSendingAddress(cell.getStringCellValue());
			            		//???????????? ????????????, ?????????????????? ??????
			            	}else if(columnindex==41) {
			            		orderVO.setOrPaymentType(cell.getStringCellValue());
			            		//????????? ????????????
			            	}else if(columnindex==42) {
			            		
			            		//?????????????????????
			            	}else if(columnindex==43) {
			            		
			            		//???????????????
			            	}else if(columnindex==44) {
			            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
			            		//????????? ?????? ???????????? ?????????
			            	}else if(columnindex==45) {
			            		orderVO.setOrAnotherPaymentCommision((int)cell.getNumericCellValue());
			            		//??????????????????
			            	}else if(columnindex==46) {
			            		
			            		//????????????
			            	}else if(columnindex==47) {
			            		orderVO.setOrInflowRoute(cell.getStringCellValue());
			            		orderVO.setOrAnotherPaymentCommision(sc.matchingInflowCommission(cell.getStringCellValue(), orderVO.getOrTotalPrice()));
			            		//???????????? : ???????????? ??????
			            	}else if(columnindex==48) {
			            		
			            		//??????????????????????????? : ?????? ???????????? ???????????? ??? ????????????
			            	}else if(columnindex==49) {
			            		
			            		//?????? ?????? ??????
			            	}else if(columnindex==50) {
			            		orderVO.setOrUserColumn1(cell.getStringCellValue());
			            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
			            	}else if(columnindex==52) {
			            		
			            		//???????????? ????????? ??? ..
			            	}else if(columnindex==53) {
			            		
			            		//????????????
			            	}else if(columnindex==57) {
			            		int subNum = cell.getStringCellValue().lastIndexOf("(");

			            		if(subNum > 0) {
			            			
			            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
			            			
			            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
			            			orderVO.setOrShippingAddressDetail(subStr);
			            		}else {
			            			
			            			orderVO.setOrShippingAddress(cell.getStringCellValue());
			            		}
			            		//???????????? ?...
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
			    	orderVO.setOrProductOption("????????????");
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
			            	//????????????
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
			            		
			            	//????????????
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
		            	// ??????????????? ?????? ?????? ????????? ???
		            	//????????????
		            if(cell==null) {
		            	continue;
		            	
		            }else {
		            	orderVO.setSsFk(ssFk);
		            	
		            	if(columnindex==sortingVO.getSedsBuyerName()) {
		            		orderVO.setOrBuyerName(cell.getStringCellValue());
		            		//?????????ID		
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
		            		//????????????	
		            	}if(columnindex==sortingVO.getSedsReceiverName()) {
		            		orderVO.setOrReceiverName(cell.getStringCellValue());
		            		//?????????
		            	}if(columnindex==sortingVO.getSedsProduct()) {
		            		orderVO.setOrProduct(cell.getStringCellValue());
		            		//????????????
		            	}if(columnindex==sortingVO.getSedsProductType()) {
		            		orderVO.setOrProductType(cell.getStringCellValue());
		            		//??????
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
		            		//???????????? : ?????????
		            	}if(columnindex==sortingVO.getSedsProductOption()) {
		            		
		            		if(cell.getStringCellValue().equals("")) {
		            			orderVO.setOrProductOption("????????????");
		            			
		            		}else {
		            			orderVO.setOrProductOption(cell.getStringCellValue());
		            			
		            		}
		            		
		            		//???????????????
		            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
		            		if(cell.getStringCellValue() != null) {
		            			
		            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
		            		}else {
		            			orderVO.setOrDeliveryMessage("");
		            		}
		            		//????????????
		            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
		            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
		            		//?????????
		            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
		            		//
		            		//????????????
		            	}if(columnindex == -1) {
		            		
		            		//??????????????????
		            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrProductOrderNumber(value);
		            		//????????????
		            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		 
		            		orderVO.setOrOrderNumber(value);
		            		//????????? : ???????????? ?????? ????????? ?????? ???????????????
		            	}if(columnindex==sortingVO.getSedsSendingDay()) {
		            		
		            		//???????????? : ?????? ???????????? ??????
		            	}if(columnindex== -1) {
		            		
		            		//?????????????????? : ?????? ???????????? ??????
		            	}if(columnindex== -1) {
		            		
		            		//???????????? : PC or MOBILE
		            	}if(columnindex==sortingVO.getSedsPaymentType()) {
		            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
		            		//?????????
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
		            		//???????????? : ?????????????????? ???????????? ???????????? ( ???????????? ??? ?????? )
		            	}if(columnindex==sortingVO.getSedsProductNumber()) {
		            		orderVO.setOrProductNumber(cell.getStringCellValue());
		            		//?????? ??????
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
		            		//?????? ??????
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
		            		//????????? ?????????
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
		            		//????????? ?????? ?????????
		            	}if(columnindex==22) {
		            		
		            		//??????????????? ?????? ????????? ???????????? ????????? ?????? ??????????????? ?????? ????????? ??????
		            		
		            		
		            		//????????? ??? ????????????
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
		            		//??????????????? : ?????????
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
		            		//???????????? : ????????? ???????????? ???????????? ??????????????? ?????? ????????? ?????? ??? ????????? ???
		            		
		            	}if(columnindex==sortingVO.getSedsSendingDeadline()) {
		            		
		            		if(sendingDeadlineFlag == true) {
		            			String value = cellTypeReturn(cell);
			            		 
			            		orderVO.setOrSendingDeadline(new Date(sdfWithoutTime.parse(value).getTime()));
			            		
		            		}else {
		            			orderVO.setOrSendingDeadline(new Date(ts.getTime()));
		            		}
		            		
		            		//??????????????? : ?????? ???????????? ????????????
		            	}if(columnindex==26) {
		            		
		            		//??????????????? : ?????? ???????????? ????????????
		            	}if(columnindex==27) {
		            		
		            		//??????????????? : ?????????, ?????? ???
		            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
		            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
		            		//????????????????????? ???????????? ?????? ??? ?????? ??? ??? ????????? ?????? ????????? ?????? ???????????? ??? ????????? ???????????? ??? ??????. ????????? ????????????????????? ?????? ????????? ?????? ??????????????? ??????
		            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrDeliveryNumber(value);
		            		//??????????????? : ?????? ???????????????
		            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
		            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
		            		
		            		
		            		//????????? ?????? 3000??? ????????? 0??? ??????
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
		            		
		            		//??????/?????? ??????????????? ?????? ????????? ???????????? ??????
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
		            		//????????? ????????? : ???????????? ?????? ????????? ?????? ????????? ?????? ????????? ??????
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
		            		//????????? ???????????? : ?????? ???????????? ??????
		            	}if(columnindex== -1) {
		            		
		            		//????????? ????????? 1
		            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
		            		
		            		String value = cellTypeReturn(cell);
		            		
		            		orderVO.setOrReceiverContractNumber1(value);
		            		//????????? ????????? 2
		            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
		            		String value = cellTypeReturn(cell);
		            		
		            		
		            		orderVO.setOrReceiverContractNumber2(value);
		            		//?????????
		            	}if(columnindex== -1) {
		            		
		            		//??????????????????
		            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
		            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
		            		
		            		//????????????
		            	}if(columnindex==sortingVO.getSedsShippingAddressNumber()) {
		            		orderVO.setOrShippingAddressNumber(cell.getStringCellValue());
		            		
		            	}if(columnindex==sortingVO.getSedsPaymentType()) {
		            		orderVO.setOrPaymentType(cell.getStringCellValue());
		            		
		            		//????????? ????????????
		            	}if(columnindex==-1) {
		            		
		            		//?????????????????????
		            	}if(columnindex== -1) {
		            		
		            		//???????????????
		            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
		            		
		            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
		            		
		            		
		            		//????????? ?????? ???????????? ?????????
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
		            		
		            		//??????????????????
		            	}if(columnindex== -1) {
		            		
		            		//????????????
		            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
		            		
		            		orderVO.setOrInflowRoute(cell.getStringCellValue());
		            		
		            		//???????????? : ???????????? ??????
		            	}if(columnindex== -1) { //////////////////
		            		
		            		//??????????????????????????? : ?????? ???????????? ????????????
		            	}if(columnindex== -1) {
		            		
		            		//?????? ?????? ??????
		            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
		            		
		            		orderVO.setOrUserColumn1(cell.getStringCellValue());
		            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
		            	}if(columnindex==51) {
		            		
		            		//????????????
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
		            		
		            		//???????????? ????????????
		            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
		            		
		            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
		            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
		            			
		            		}else {
		            			
		            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
		            			
		            		}
		            	
		            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
		            		
		            		orderVO.setOrUserColumn2(cell.getStringCellValue());
		            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
		            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
		            		
		            		orderVO.setOrUserColumn3(cell.getStringCellValue());
		            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
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
		    orderVO.setOrSendingAddress("(21126) ??????????????? ????????? ?????????  388 ???????????????");
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
			
			// ???????????? ???????????? xlsx ??? ??????
			// ???????????? ???????????? ?????????
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
								// ??????????????? ?????? ?????? ????????? ???
								//????????????
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
				
			//???????????? ???????????? xls??? ??????
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
							// ??????????????? ?????? ?????? ????????? ???
							//????????????
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
	 * @??????????????? : ?????? ?????? ????????? ?????? ?????????
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
			
			// ???????????? ???????????? xlsx ??? ??????
			// ???????????? ???????????? ?????????
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
								// ??????????????? ?????? ?????? ????????? ???
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
				
			//???????????? ???????????? xls??? ??????
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
							// ??????????????? ?????? ?????? ????????? ???
							//????????????
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
			
			// ???????????? ???????????? xlsx ??? ??????
			// ???????????? ???????????? ?????????
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
					            	// ??????????????? ?????? ?????? ????????? ???
					            	//????????????
					            if(cell==null) {
					            	continue;
					            	
					            }else {
					            	orderVO.setSsFk(ssFk);
					            	
					            	if(columnindex==sortingVO.getSedsBuyerName()) {
					            		orderVO.setOrBuyerName(cell.getStringCellValue());
					            		//?????????ID		
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
					            		//????????????	
					            	}if(columnindex==sortingVO.getSedsReceiverName()) {
					            		orderVO.setOrReceiverName(cell.getStringCellValue());
					            		//?????????
					            	}if(columnindex==sortingVO.getSedsProduct()) {
					            		orderVO.setOrProduct(cell.getStringCellValue());
					            		//????????????
					            	}if(columnindex==sortingVO.getSedsProductType()) {
					            		orderVO.setOrProductType(cell.getStringCellValue());
					            		//??????
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
					            		//???????????? : ?????????
					            	}if(columnindex==sortingVO.getSedsProductOption()) {
					            		
					            		if(cell.getStringCellValue().equals("")) {
					            			orderVO.setOrProductOption("????????????");
					            			
					            		}else {
					            			orderVO.setOrProductOption(cell.getStringCellValue());
					            			
					            		}
					            		
					            		//???????????????
					            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
					            		if(cell.getStringCellValue() != null) {
					            			
					            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
					            		}else {
					            			orderVO.setOrDeliveryMessage("");
					            		}
					            		//????????????
					            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
					            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
					            		//?????????
					            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
					            		//
					            		//????????????
					            	}if(columnindex == -1) {
					            		
					            		//??????????????????
					            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrProductOrderNumber(value);
					            		//????????????
					            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		 
					            		orderVO.setOrOrderNumber(value);
					            		//????????? : ???????????? ?????? ????????? ?????? ???????????????
					            	}if(columnindex==sortingVO.getSedsSendingDay()) {
					            		
					            		//???????????? : ?????? ???????????? ??????
					            	}if(columnindex== -1) {
					            		
					            		//?????????????????? : ?????? ???????????? ??????
					            	}if(columnindex== -1) {
					            		
					            		//???????????? : PC or MOBILE
					            	}if(columnindex==sortingVO.getSedsPaymentType()) {
					            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
					            		//?????????
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
					            		//???????????? : ?????????????????? ???????????? ???????????? ( ???????????? ??? ?????? )
					            	}if(columnindex==sortingVO.getSedsProductNumber()) {
					            		orderVO.setOrProductNumber(cell.getStringCellValue());
					            		//?????? ??????
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
					            		//?????? ??????
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
					            		//????????? ?????????
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
					            		//????????? ?????? ?????????
					            	}if(columnindex==22) {
					            		
					            		//??????????????? ?????? ????????? ???????????? ????????? ?????? ??????????????? ?????? ????????? ??????
					            		
					            		
					            		//????????? ??? ????????????
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
					            		//??????????????? : ?????????
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
					            		//???????????? : ????????? ???????????? ???????????? ??????????????? ?????? ????????? ?????? ??? ????????? ???
					            		
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
					            		
					            		//??????????????? : ?????? ???????????? ????????????
					            	}if(columnindex==26) {
					            		
					            		//??????????????? : ?????? ???????????? ????????????
					            	}if(columnindex==27) {
					            		
					            		//??????????????? : ?????????, ?????? ???
					            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
					            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
					            		//????????????????????? ???????????? ?????? ??? ?????? ??? ??? ????????? ?????? ????????? ?????? ???????????? ??? ????????? ???????????? ??? ??????. ????????? ????????????????????? ?????? ????????? ?????? ??????????????? ??????
					            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrDeliveryNumber(value);
					            		//??????????????? : ?????? ???????????????
					            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
					            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
					            		
					            		
					            		//????????? ?????? 3000??? ????????? 0??? ??????
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
					            		
					            		//??????/?????? ??????????????? ?????? ????????? ???????????? ??????
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
					            		//????????? ????????? : ???????????? ?????? ????????? ?????? ????????? ?????? ????????? ??????
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
					            		//????????? ???????????? : ?????? ???????????? ??????
					            	}if(columnindex== -1) {
					            		
					            		//????????? ????????? 1
					            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
					            		
					            		String value = cellTypeReturn(cell);
					            		
					            		orderVO.setOrReceiverContractNumber1(value);
					            		//????????? ????????? 2
					            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
					            		String value = cellTypeReturn(cell);
					            		
					            		
					            		orderVO.setOrReceiverContractNumber2(value);
					            		//?????????
					            	}if(columnindex== -1) {
					            		
					            		//??????????????????
					            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
					            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
					            		
					            		//????????????
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
					            		
					            		//????????? ????????????
					            	}if(columnindex==-1) {
					            		
					            		//?????????????????????
					            	}if(columnindex== -1) {
					            		
					            		//???????????????
					            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
					            		
					            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
					            		
					            		
					            		//????????? ?????? ???????????? ?????????
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
					            		
					            		//??????????????????
					            	}if(columnindex== -1) {
					            		
					            		//????????????
					            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
					            		
					            		orderVO.setOrInflowRoute(cell.getStringCellValue());
					            		
					            		//???????????? : ???????????? ??????
					            	}if(columnindex== -1) { //////////////////
					            		
					            		//??????????????????????????? : ?????? ???????????? ????????????
					            	}if(columnindex== -1) {
					            		
					            		//?????? ?????? ??????
					            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
					            		
					            		orderVO.setOrUserColumn1(cell.getStringCellValue());
					            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
					            	}if(columnindex==51) {
					            		
					            		//????????????
					            	}if(columnindex==sortingVO.getSedsShippingAddress()) {
					            		
					            		int subNum = cell.getStringCellValue().lastIndexOf("(");

					            		if(subNum > 0) {
					            			
					            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
					            			
					            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
					            			orderVO.setOrShippingAddressDetail(subStr);
					            		}else {
					            			
					            			orderVO.setOrShippingAddress(cell.getStringCellValue());
					            		}
					            		
					            		//???????????? ????????????
					            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
					            		
					            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
					            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
					            			
					            		}else {
					            			
					            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
					            			
					            		}
					            	
					            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
					            		
					            		orderVO.setOrUserColumn2(cell.getStringCellValue());
					            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
					            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
					            		
					            		orderVO.setOrUserColumn3(cell.getStringCellValue());
					            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
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
					    orderVO.setOrSendingAddress("(21126) ??????????????? ????????? ?????????  388 ???????????????");
					    orderList.add(orderVO);
					    orderVO = null;
					}//for
				
			//???????????? ???????????? xls??? ??????
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
				            	// ??????????????? ?????? ?????? ????????? ???
				            	//????????????
				            if(cell==null) {
				            	continue;
				            	
				            }else {
				            	orderVO.setSsFk(ssFk);
				            	
				            	if(columnindex==sortingVO.getSedsBuyerName()) {
				            		orderVO.setOrBuyerName(cell.getStringCellValue());
				            		//?????????ID		
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
				            		//????????????	
				            	}if(columnindex==sortingVO.getSedsReceiverName()) {
				            		orderVO.setOrReceiverName(cell.getStringCellValue());
				            		//?????????
				            	}if(columnindex==sortingVO.getSedsProduct()) {
				            		orderVO.setOrProduct(cell.getStringCellValue());
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsProductType()) {
				            		orderVO.setOrProductType(cell.getStringCellValue());
				            		//??????
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
				            		//???????????? : ?????????
				            	}if(columnindex==sortingVO.getSedsProductOption()) {
				            		
				            		if(cell.getStringCellValue().equals("")) {
				            			orderVO.setOrProductOption("????????????");
				            			
				            		}else {
				            			orderVO.setOrProductOption(cell.getStringCellValue());
				            			
				            		}
				            		
				            		//???????????????
				            	}if(columnindex==sortingVO.getSedsDeliveryMessage()) {
				            		if(cell.getStringCellValue() != null) {
				            			
				            			orderVO.setOrDeliveryMessage(cell.getStringCellValue());
				            		}else {
				            			orderVO.setOrDeliveryMessage("");
				            		}
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsDeliveryType()) {
				            		//orderVO.setOrDeliveryType(cell.getStringCellValue());
				            		//?????????
				            	}if(columnindex==sortingVO.getSedsDeliveryCompany()) {
				            		//
				            		//????????????
				            	}if(columnindex == -1) {
				            		
				            		//??????????????????
				            	}if(columnindex==sortingVO.getSedsProductOrderNumber()) {
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrProductOrderNumber(value);
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsOrderNumber()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		 
				            		orderVO.setOrOrderNumber(value);
				            		//????????? : ???????????? ?????? ????????? ?????? ???????????????
				            	}if(columnindex==sortingVO.getSedsSendingDay()) {
				            		
				            		//???????????? : ?????? ???????????? ??????
				            	}if(columnindex== -1) {
				            		
				            		//?????????????????? : ?????? ???????????? ??????
				            	}if(columnindex== -1) {
				            		
				            		//???????????? : PC or MOBILE
				            	}if(columnindex==sortingVO.getSedsPaymentType()) {
				            		orderVO.setOrPaymentPositionType(cell.getStringCellValue());
				            		//?????????
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
				            		//???????????? : ?????????????????? ???????????? ???????????? ( ???????????? ??? ?????? )
				            	}if(columnindex==sortingVO.getSedsProductNumber()) {
				            		orderVO.setOrProductNumber(cell.getStringCellValue());
				            		//?????? ??????
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
				            		//?????? ??????
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
				            		//????????? ?????????
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
				            		//????????? ?????? ?????????
				            	}if(columnindex==22) {
				            		
				            		//??????????????? ?????? ????????? ???????????? ????????? ?????? ??????????????? ?????? ????????? ??????
				            		
				            		
				            		//????????? ??? ????????????
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
				            		//??????????????? : ?????????
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
				            		//???????????? : ????????? ???????????? ???????????? ??????????????? ?????? ????????? ?????? ??? ????????? ???
				            		
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
				            		
				            		//??????????????? : ?????? ???????????? ????????????
				            	}if(columnindex==26) {
				            		
				            		//??????????????? : ?????? ???????????? ????????????
				            	}if(columnindex==27) {
				            		
				            		//??????????????? : ?????????, ?????? ???
				            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
				            		orderVO.setOrDeliveryChargeType(cell.getStringCellValue());
				            		//????????????????????? ???????????? ?????? ??? ?????? ??? ??? ????????? ?????? ????????? ?????? ???????????? ??? ????????? ???????????? ??? ??????. ????????? ????????????????????? ?????? ????????? ?????? ??????????????? ??????
				            	}if(columnindex==sortingVO.getSedsDeliveryNumber()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrDeliveryNumber(value);
				            		//??????????????? : ?????? ???????????????
				            	}if(columnindex==sortingVO.getSedsDeliveryChargeType()) {
				            		orderVO.setOrDeliveryChargePaymentType(cell.getStringCellValue());
				            		
				            		
				            		//????????? ?????? 3000??? ????????? 0??? ??????
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
				            		
				            		//??????/?????? ??????????????? ?????? ????????? ???????????? ??????
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
				            		//????????? ????????? : ???????????? ?????? ????????? ?????? ????????? ?????? ????????? ??????
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
				            		//????????? ???????????? : ?????? ???????????? ??????
				            	}if(columnindex== -1) {
				            		
				            		//????????? ????????? 1
				            	}if(columnindex==sortingVO.getSedsReceiverContractNumber1()) {
				            		
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		orderVO.setOrReceiverContractNumber1(value);
				            		//????????? ????????? 2
				            	}if(columnindex==sortingVO.getSedsReceiverContractNumber2()) {
				            		String value = cellTypeReturnHSS(cell);
				            		
				            		
				            		orderVO.setOrReceiverContractNumber2(value);
				            		//?????????
				            	}if(columnindex== -1) {
				            		
				            		//??????????????????
				            	}if(columnindex==sortingVO.getSedsBuyerContractNumber1()) {
				            		orderVO.setOrBuyerContractNumber1(cell.getStringCellValue());
				            		
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsShippingAddressNumber()) {
				            		orderVO.setOrShippingAddressNumber(cell.getStringCellValue());
				            		
				            	}if(columnindex==sortingVO.getSedsPaymentType()) {
				            		orderVO.setOrPaymentType(cell.getStringCellValue());
				            		
				            		//????????? ????????????
				            	}if(columnindex==-1) {
				            		
				            		//?????????????????????
				            	}if(columnindex== -1) {
				            		
				            		//???????????????
				            	}if(columnindex==sortingVO.getSedsPaymentCommision()) {
				            		
				            		orderVO.setOrPaymentCommision(sc.matchingPaymentCommission(orderVO.getOrPaymentType(), orderVO.getOrTotalPrice()));
				            		
				            		
				            		//????????? ?????? ???????????? ?????????
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
				            		
				            		//??????????????????
				            	}if(columnindex== -1) {
				            		
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsInflowRoute()) {
				            		
				            		orderVO.setOrInflowRoute(cell.getStringCellValue());
				            		
				            		//???????????? : ???????????? ??????
				            	}if(columnindex== -1) { //////////////////
				            		
				            		//??????????????????????????? : ?????? ???????????? ????????????
				            	}if(columnindex== -1) {
				            		
				            		//?????? ?????? ??????
				            	}if(columnindex==sortingVO.getSedsUserColumn1()) {
				            		
				            		orderVO.setOrUserColumn1(cell.getStringCellValue());
				            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
				            	}if(columnindex==51) {
				            		
				            		//????????????
				            	}if(columnindex==sortingVO.getSedsShippingAddress()) {
				            		
				            		int subNum = cell.getStringCellValue().lastIndexOf("(");

				            		if(subNum > 0) {
				            			
				            			String subStr = cell.getStringCellValue().substring(subNum, cell.getStringCellValue().length());
				            			
				            			orderVO.setOrShippingAddress(cell.getStringCellValue().substring(0, subNum));
				            			orderVO.setOrShippingAddressDetail(subStr);
				            		}else {
				            			
				            			orderVO.setOrShippingAddress(cell.getStringCellValue());
				            		}
				            		
				            		//???????????? ????????????
				            	}if(columnindex==sortingVO.getSedsShippingAddressDetail()) {
				            		
				            		if(!EmptyCheckUtil.isEmpty(orderVO.getOrShippingAddressDetail())) {
				            			orderVO.setOrShippingAddressDetail(orderVO.getOrShippingAddressDetail()+" "+cell.getStringCellValue());
				            			
				            		}else {
				            			
				            			orderVO.setOrShippingAddressDetail(cell.getStringCellValue());
				            			
				            		}
				            	
				            	}if(columnindex==sortingVO.getSedsUserColumn2()) {
				            		
				            		orderVO.setOrUserColumn2(cell.getStringCellValue());
				            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
				            	}if(columnindex==sortingVO.getSedsUserColumn3()) {
				            		
				            		orderVO.setOrUserColumn3(cell.getStringCellValue());
				            		//??????????????????2 : ????????? ??? ??? ????????? ????????????
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
				    orderVO.setOrSendingAddress("(21126) ??????????????? ????????? ?????????  388 ???????????????");
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
