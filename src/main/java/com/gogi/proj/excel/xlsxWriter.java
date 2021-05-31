package com.gogi.proj.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOGetterSetting;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.stock.vo.PrintDataSetVO;

public class xlsxWriter {
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	private CellsStyle cs = new CellsStyle();
	
	private OrdersVOGetterSetting osGs;
	
	 public File orderXlsWriter(List<String> idxTitle, List<OrdersVO> veList, List<OrdersVOList> orderVoList  ,List<ExcelOrderSeqVO> eoSeq, String upPath, String fileName) {
		 	osGs = new OrdersVOGetterSetting();
		 	
	        // 워크북 생성
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        
	        CellStyle style = workbook.createCellStyle();
	        // 워크시트 생성
	        HSSFSheet sheet = workbook.createSheet();
	        sheet.setMargin(sheet.BottomMargin, 0);
	        sheet.setMargin(sheet.FooterMargin, 0);
	        sheet.setMargin(sheet.HeaderMargin, 0);
	        sheet.setMargin(sheet.LeftMargin, 0);
	        sheet.setMargin(sheet.RightMargin, 0);
	        sheet.setMargin(sheet.TopMargin, 0);
	        
	        HSSFPrintSetup print = sheet.getPrintSetup();
	        print.setScale((short)170);
	        print.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
	        print.setFitHeight((short)1);
	        print.setFitWidth((short)1);
	        
	        // 행 생성
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)500);
	        // 쎌 생성
	        HSSFCell cell;
	        
	        // 헤더 정보 구성
	        for(int i = 0; i < idxTitle.size(); i++) {
	        	cell = row.createCell(i);
	        	cell.setCellValue(idxTitle.get(i));
	        	cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 1;
	        boolean productEx = false;
	        
	        for(int rowIdx=1 + 0; rowIdx < 1 + eoSeq.size(); rowIdx++) {
	        	
	        	row = sheet.createRow(rowCounting);
	        	row.setHeight((short)500);
	        	cell = row.createCell(0);
	        	cell.setCellValue(osGs.excelIOOrdersToeosSeq(eoSeq.get(rowIdx-1)).get(1));
	        	cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(1);
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(2);
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		sheet.addMergedRegion(new CellRangeAddress(rowCounting, rowCounting, 0, 2));
        		
        		sheet.autoSizeColumn(rowIdx);
        		sheet.setColumnWidth(rowIdx, (sheet.getColumnWidth(rowIdx))+(short)1024);
	        	rowCounting++;
	        	
	        	if(rowIdx < eoSeq.size() + 1) {
	        		int totalCount = 0;
	        		
	        		for(int i = 0; i < veList.size(); i++) {
	        			
	        			if(eoSeq != null) {
	        				
	        				//분류 코드에 따라 정렬
	        				if( eoSeq.get(rowIdx - 1).getEosSeq() == Integer.parseInt(veList.get(i).getOrUserColumn3())) {
	        					row = sheet.createRow(rowCounting);
	        					row.setHeight((short)500);
	        					//상품 정보, 분류코드,상품명옵션,개수
	        					List<String> osGsList = osGs.excelIOOrders(veList.get(i));
	        					
	        					//행 정보 생성
	        					for(int cellIdx = 0; cellIdx < osGsList.size(); cellIdx ++) {
	        						if(cellIdx == 0) {
	        							cell = row.createCell(cellIdx);
	        							if(osGsList.get(3) != null && !osGsList.get(3).equals("")) {
	        								cell.setCellValue(osGsList.get(1)+"\n"+osGsList.get(3));
	        								cs.setCellsStyles(style, sheet, workbook, cell);
	        								
	        							}else {
	        								cell.setCellValue(osGsList.get(1));
	        								cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        							}
	        							sheet.addMergedRegion(new CellRangeAddress(rowCounting, rowCounting, 0, 1));
	        							
	        						}else if(cellIdx == 1){
	        							cell = row.createCell(cellIdx);
	        							cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        						}else if(cellIdx == 2){	        							
	        							
	        							totalCount+=Integer.parseInt(osGsList.get(cellIdx));
	        									
	        							cell = row.createCell(cellIdx);
	        							cell.setCellValue(Integer.parseInt(osGsList.get(cellIdx)));
	        							cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        						}
	        						
	        					}//for
	        					
	        					rowCounting++;
	        				}//if
	        			}else {
	        				
	        				//행 정보 생성
        					for(int cellIdx = 0; cellIdx < veList.size(); cellIdx ++) {
        						cell = row.createCell(cellIdx);
        						cell.setCellValue(veList.get(cellIdx)+"");
        						cs.setCellsStylesSpecial(style, sheet, workbook, cell);
        						
        					}//for
        					
        					rowCounting++;
        					
	        			}
	        			
	        			
	        		}//for
	        		
	        		
	        		/**
	        		 * 1 한우, 3 한돈 : 총 개수 넣어주기
	        		 */
	        		if(eoSeq.get(rowIdx - 1).isEosExcelTotalQtyFlag() == true) {
	        			row = sheet.createRow(rowCounting);
        				cell = row.createCell(0);
						cell.setCellValue("총 합 ");
						cs.setCellsStylesSpecial(style, sheet, workbook, cell);
						
						cell = row.createCell(1);
						cell.setCellValue("");
						cs.setCellsStylesSpecial(style, sheet, workbook, cell);
						
						cell = row.createCell(2);
						cell.setCellValue(totalCount);
						cs.setCellsStylesSpecial(style, sheet, workbook, cell);
						
						sheet.addMergedRegion(new CellRangeAddress(rowCounting, rowCounting, 0, 1));
						rowCounting++;
						totalCount = 0;
					}
        			
	        		
	        	}//if
	           
	        }//for
	        
	        if(orderVoList.size() != 0) {
	        	
				row = sheet.createRow(rowCounting);
	        	row.setHeight((short)500);
	        	cell = row.createCell(0);
	        	cell.setCellValue("이름");
	        	cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(1);
        		cell.setCellValue("상품");
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(2);
        		cell.setCellValue("개수");
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		
        		int vecRowCounting = rowCounting+1;
        		
        		List<Region> regList = new ArrayList<Region>();
        		
        		List<CellRangeAddress> cellList = new ArrayList<CellRangeAddress>();
        		
        		for(int veC = 0; veC < orderVoList.size(); veC++) {
        			
        			for(int q = 0; q < orderVoList.get(veC).getOrVoList().size(); q++) {
        				
        				row = sheet.createRow(vecRowCounting);

        				//첫번째 로우 시작
        				if(q == 0 ) {
        					//sheet.addMergedRegion(new Region(vecRowCounting, (short)vecRowCounting,  (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)vecRowCounting ));
        					//sheet.addMergedRegion(new Region(vecRowCounting, (short)0,  (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)0 ));
        					
        					// regList.add(new Region(vecRowCounting , (short)0, (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)0 ));
        					
        					cell = row.createCell(0);
        					cell.setCellValue(orderVoList.get(veC).getOrBuyerName()+"/"+orderVoList.get(veC).getOrReceiverName());
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,true,false,false);
        					sheet.addMergedRegion(new CellRangeAddress(vecRowCounting, vecRowCounting + orderVoList.get(veC).getOrVoList().size()-1,  0, 0));
        					
        				    
        				}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cell = row.createCell(0);
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,true,false,true,false,false);
        					
        				}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cell = row.createCell(0);
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,true,false,false);
        				}

        				
        				cell = row.createCell(1);
            			cell.setCellValue(orderVoList.get(veC).getOrVoList().get(q).getOrProduct() + "[ "+orderVoList.get(veC).getOrVoList().get(q).getOrProductOption()+" ]");
            			
            			if(q == 0){
            				cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,false,false,false);

            			}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,false,false,false);
            				
            			}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,true,false,false,true,false);
        				}

            			cell = row.createCell(2);
            			cell.setCellValue(orderVoList.get(veC).getOrVoList().get(q).getOrAmount());

    				    
        				if(q == 0){
            				//테두리 스타일
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,false,false,true);

        				}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,false,false,true);
        					
        				}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,false,false,false,true);
        				}

        				vecRowCounting++;
        			}
        			
        			
            		
        		}
        		
			}

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = fileName+" ["+filedate+"].xls";	        
	        
	        // 입력된 내용 파일로 쓰기
	        File file = new File(upPath, fileWrite);
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
	 
	 public File labelXlsWriter(List<String> idxTitle, List<PrintDataSetVO> labelList, List<ExcelOrderSeqVO> eoSeq, String upPath, String fileName) {
		 
		 	osGs = new OrdersVOGetterSetting();
		 	
	        // 워크북 생성
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        
	        CellStyle style = workbook.createCellStyle();
	        // 워크시트 생성
	        HSSFSheet sheet = workbook.createSheet();
	        // 행 생성
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)500);
	        // 쎌 생성
	        HSSFCell cell;
	        
	        // 헤더 정보 구성
	        for(int i = 0; i < idxTitle.size(); i++) {
	        	cell = row.createCell(i);
	        	cell.setCellValue(idxTitle.get(i));
	        	cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 1;
	        boolean productEx = false;
	        
	        for(int rowIdx=0 ; rowIdx < labelList.size(); rowIdx++) {
	        	row = sheet.createRow(rowCounting);
    			//행 정보 생성
					
				cell = row.createCell(0);
				cell.setCellValue(labelList.get(rowIdx).getQty());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(1);
				cell.setCellValue(labelList.get(rowIdx).getProduct());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(2);
				cell.setCellValue(labelList.get(rowIdx).getWeight());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(3);
				cell.setCellValue(labelList.get(rowIdx).getCountryOfOrigin());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(4);
				cell.setCellValue(labelList.get(rowIdx).getRawMeterials());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(5);
				cell.setCellValue(labelList.get(rowIdx).getSellByDate());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(6);
				cell.setCellValue(labelList.get(rowIdx).getStorageMethod());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(7);
				cell.setCellValue(labelList.get(rowIdx).getLevels());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(8);
				cell.setCellValue(labelList.get(rowIdx).getItemsManufNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(9);
				cell.setCellValue(labelList.get(rowIdx).getAbattoir());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(10);
				cell.setCellValue(labelList.get(rowIdx).getAnimalProdTraceNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(11);
				cell.setCellValue(labelList.get(rowIdx).getBarcodeNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);		
					
				rowCounting++;
	           
	        }//for

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = fileName+" ["+filedate+"].xls";	        
	        
	        // 입력된 내용 파일로 쓰기
	        File file = new File(upPath, fileWrite);
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

	 
	 
	 public File csSearchResultExcelFile(List<OrdersVO> orList) {
		 
		 	osGs = new OrdersVOGetterSetting();
		 	
		 	SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	
		 	SimpleDateFormat ymd = new SimpleDateFormat("yyy-MM-dd");
		 	
		 	
		 	// 워크북 생성
			SXSSFWorkbook workbook = new SXSSFWorkbook();

			// 워크시트 생성
			SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
			// 행 생성
			SXSSFRow row = (SXSSFRow) sheet.createRow(0);
			row.setHeight((short) 500);
			// 쎌 생성
			SXSSFCell cell;
	        
	        List<String> header = csFileHeader();
	        
	        // 헤더 정보 구성
	        for(int i = 0; i < header.size(); i++) {
	        	cell = (SXSSFCell) row.createCell(i);
	        	cell.setCellValue(header.get(i));
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 1;
	        boolean productEx = false;
	        
	        for(int rowIdx=0 ; rowIdx < orList.size(); rowIdx++) {
	        	row = (SXSSFRow)sheet.createRow(rowCounting);
 			//행 정보 생성
					
				cell = (SXSSFCell)row.createCell(0);
				cell.setCellValue(orList.get(rowIdx).getSsName());
				
				cell = (SXSSFCell)row.createCell(1);
				cell.setCellValue(orList.get(rowIdx).getOrBuyerId() == null ? " - " : orList.get(rowIdx).getOrBuyerId());
				
				cell = (SXSSFCell)row.createCell(2);
				cell.setCellValue(orList.get(rowIdx).getOrBuyerName());
				
				cell = (SXSSFCell)row.createCell(3);
				cell.setCellValue(orList.get(rowIdx).getOrBuyerContractNumber1());
				
				cell = (SXSSFCell)row.createCell(4);
				cell.setCellValue(orList.get(rowIdx).getOrBuyerContractNumber2() == null ? " - " : orList.get(rowIdx).getOrBuyerContractNumber2());
				
				cell = (SXSSFCell)row.createCell(5);
				cell.setCellValue(orList.get(rowIdx).getOrReceiverName());
				
				cell = (SXSSFCell)row.createCell(6);
				cell.setCellValue(orList.get(rowIdx).getOrReceiverContractNumber1());
				
				cell = (SXSSFCell)row.createCell(7);
				cell.setCellValue(orList.get(rowIdx).getOrReceiverContractNumber2() == null ? " - " : orList.get(rowIdx).getOrReceiverContractNumber2());
				
				cell = (SXSSFCell)row.createCell(8);
				cell.setCellValue(orList.get(rowIdx).getOrShippingAddress());
				
				cell = (SXSSFCell)row.createCell(9);
				cell.setCellValue(orList.get(rowIdx).getOrShippingAddressDetail() == null ? " - " : orList.get(rowIdx).getOrShippingAddressDetail());
				
				cell = (SXSSFCell)row.createCell(10);
				cell.setCellValue(orList.get(rowIdx).getOrDeliveryInvoiceNumber() == null ? " - " : orList.get(rowIdx).getOrDeliveryInvoiceNumber());
				
				cell =(SXSSFCell) row.createCell(11);
				cell.setCellValue(orList.get(rowIdx).getOrAmount());	
				
				cell =(SXSSFCell) row.createCell(12);
				cell.setCellValue(orList.get(rowIdx).getOrOrderNumber());
				
				cell =(SXSSFCell) row.createCell(13);
				cell.setCellValue(orList.get(rowIdx).getOrProductOrderNumber());
				
				
				cell = (SXSSFCell)row.createCell(14);
				cell.setCellValue(orList.get(rowIdx).getOrProduct());
				
				cell = (SXSSFCell)row.createCell(15);
				cell.setCellValue(orList.get(rowIdx).getOrProductOption());
				
				cell = (SXSSFCell)row.createCell(16);
				cell.setCellValue(orList.get(rowIdx).getOrUserColumn1());
				
				cell = (SXSSFCell)row.createCell(17);
				cell.setCellValue(orList.get(rowIdx).getOrUserColumn2());
				
				cell = (SXSSFCell)row.createCell(18);
				cell.setCellValue((int)orList.get(rowIdx).getOrCancledQty());
				
				cell = (SXSSFCell)row.createCell(19);
				cell.setCellValue((int)orList.get(rowIdx).getOrRefunds());

				cell = (SXSSFCell)row.createCell(20);
				cell.setCellValue(orList.get(rowIdx).getOrSendingDeadline() == null ? " - " : ymd.format(orList.get(rowIdx).getOrSendingDeadline()));

				cell = (SXSSFCell)row.createCell(21);
				cell.setCellValue((orList.get(rowIdx).getOrSendingDay() == null ? " - " : ymdhms.format(orList.get(rowIdx).getOrSendingDay())));

				cell = (SXSSFCell)row.createCell(22);
				cell.setCellValue(orList.get(rowIdx).getOrSettlementDay() == null ? " - " : ymdhms.format(orList.get(rowIdx).getOrSettlementDay()));

				cell = (SXSSFCell)row.createCell(23);
				cell.setCellValue(orList.get(rowIdx).getOrInflowRoute());


				rowCounting++;
	           
	        }//for

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = "cs 검색 결과값 ["+filedate+"].xlsx";	        
	        
	        // 입력된 내용 파일로 쓰기
	        File file = new File(fileWrite);
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
	 
	 
	 private List<String> csFileHeader(){
		 
		 List<String> header = new ArrayList<>();
		 
		 header.add("판매처명");
		 header.add("아이디");
		 header.add("주문자명");
		 header.add("주문자 연락처1");
		 header.add("주문자 연락처2");
		 header.add("수취인명");
		 header.add("수취인 연락처1");
		 header.add("수취인 연락처2");
		 header.add("배송지");
		 header.add("배송지 상세사항");
		 header.add("송장번호");
		 header.add("수량");
		 header.add("주문번호");
		 header.add("상품주문번호");
		 header.add("판매처 상품명");
		 header.add("판매처 옵션명");
		 header.add("매칭 상품명");
		 header.add("매칭 옵션명");
		 header.add("취소여부");
		 header.add("환불여부");
		 header.add("발송기한");
		 header.add("발송일");
		 header.add("결제일");
		 header.add("유입경로");
		 
		 return header;
		 
	 }
}
