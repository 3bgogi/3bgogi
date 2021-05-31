package com.gogi.proj.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellsStyle {
	
	public void setCellsStyles(CellStyle cs,HSSFSheet sheet,HSSFWorkbook wb,HSSFCell cell) {
		
		HSSFPrintSetup print = sheet.getPrintSetup();
		
		print.setFitHeight((short)1);
		print.setFitWidth((short)1);
		print.setScale((short) 80);

		// 셀 병합

		/*sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));	// 가로병합

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));	// 세로병합
*/


		// 틀 고정

		//sheet.createFreezePane(1, 2);	// 1열, 2행 고정



		// 셀 스타일

		CellStyle style = wb.createCellStyle();



		// 가로정렬

		//style.setAlignment((short)1);			// 가로 정렬 왼쪽

		style.setAlignment((short)2);			// 가로 정렬 중간

		//style.setAlignment((short)3);			// 가로 정렬 오른쪽



		// 세로정렬

		//style.setVerticalAlignment((short)0);	// 세로 정렬 상단

		style.setVerticalAlignment((short)1);	// 세로 정렬 중단

		//style.setVerticalAlignment((short)2);	// 세로 정렬 하단
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		style.setBottomBorderColor(HSSFColor.BLACK.index); 
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		style.setLeftBorderColor(HSSFColor.BLACK.index); 
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		style.setRightBorderColor(HSSFColor.BLACK.index); 
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);




		// 셀 스타일 적용

		cell.setCellStyle(style);



		// 폰트 설정

		Font font = wb.createFont();

		font.setFontName("맑은 고딕");                  // 폰트 이름

		font.setFontHeightInPoints((short)11);          // 폰트 크기

		/*font.setColor(IndexedColors.RED.getIndex());    // 폰트 컬러

		font.setStrikeout(true);	                    // 글자 가운데 라인

		font.setItalic(true);	                        // 이탤릭체

		font.setUnderline(Font.U_SINGLE);		        // 밑줄

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);	    // 볼드체*/

		style.setFont(font);



		// 컬럼 사이즈 자동 조절

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);

		
	}
	
public void setCellsStylesSpecial(CellStyle cs,HSSFSheet sheet,HSSFWorkbook wb,HSSFCell cell) {
		
		HSSFPrintSetup print = sheet.getPrintSetup();
		
		print.setFitHeight((short)1);
		print.setFitWidth((short)1);
		print.setScale((short) 80);

		// 셀 병합

		/*sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));	// 가로병합

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));	// 세로병합
*/


		// 틀 고정

		//sheet.createFreezePane(1, 2);	// 1열, 2행 고정



		// 셀 스타일

		CellStyle style = wb.createCellStyle();



		// 가로정렬

		//style.setAlignment((short)1);			// 가로 정렬 왼쪽

		style.setAlignment((short)2);			// 가로 정렬 중간

		//style.setAlignment((short)3);			// 가로 정렬 오른쪽



		// 세로정렬

		//style.setVerticalAlignment((short)0);	// 세로 정렬 상단

		style.setVerticalAlignment((short)1);	// 세로 정렬 중단

		//style.setVerticalAlignment((short)2);	// 세로 정렬 하단
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		style.setBottomBorderColor(HSSFColor.BLACK.index); 
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		style.setLeftBorderColor(HSSFColor.BLACK.index); 
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		style.setRightBorderColor(HSSFColor.BLACK.index); 
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		style.setTopBorderColor(HSSFColor.BLACK.index);




		// 셀 스타일 적용

		cell.setCellStyle(style);



		// 폰트 설정

		Font font = wb.createFont();

		font.setFontName("맑은 고딕");                  // 폰트 이름

		font.setFontHeightInPoints((short)11);          // 폰트 크기

		/*font.setColor(IndexedColors.RED.getIndex());    // 폰트 컬러

		font.setStrikeout(true);	                    // 글자 가운데 라인

		font.setItalic(true);	                        // 이탤릭체

		font.setUnderline(Font.U_SINGLE);		        // 밑줄

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);	    // 볼드체*/

		style.setFont(font);



		// 컬럼 사이즈 자동 조절

		sheet.autoSizeColumn(0);

		
	}


	public void setCellsStylesSpecialXssf(XSSFCellStyle cs,XSSFSheet sheet,XSSFWorkbook wb,XSSFCell cell) {
		
		XSSFPrintSetup print = sheet.getPrintSetup();
		
		print.setFitHeight((short)1);
		print.setFitWidth((short)1);
		print.setScale((short) 80);
	
		// 셀 병합
	
		/*sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));	// 가로병합
	
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));	// 세로병합
	*/
	
	
		// 틀 고정
	
		//sheet.createFreezePane(1, 2);	// 1열, 2행 고정
	
	
	
		// 셀 스타일
	
		CellStyle style = wb.createCellStyle();
	
	
	
		// 가로정렬
	
		//style.setAlignment((short)1);			// 가로 정렬 왼쪽
	
		style.setAlignment((short)2);			// 가로 정렬 중간
	
		//style.setAlignment((short)3);			// 가로 정렬 오른쪽
	
	
	
		// 세로정렬
	
		//style.setVerticalAlignment((short)0);	// 세로 정렬 상단
	
		style.setVerticalAlignment((short)1);	// 세로 정렬 중단
	
		//style.setVerticalAlignment((short)2);	// 세로 정렬 하단
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		style.setBottomBorderColor(HSSFColor.BLACK.index); 
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		style.setLeftBorderColor(HSSFColor.BLACK.index); 
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		style.setRightBorderColor(HSSFColor.BLACK.index); 
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		style.setTopBorderColor(HSSFColor.BLACK.index);
	
	
	
	
		// 셀 스타일 적용
	
		cell.setCellStyle(style);
	
	
	
		// 폰트 설정
	
		Font font = wb.createFont();
	
		font.setFontName("맑은 고딕");                  // 폰트 이름
	
		font.setFontHeightInPoints((short)11);          // 폰트 크기
	
		/*font.setColor(IndexedColors.RED.getIndex());    // 폰트 컬러
	
		font.setStrikeout(true);	                    // 글자 가운데 라인
	
		font.setItalic(true);	                        // 이탤릭체
	
		font.setUnderline(Font.U_SINGLE);		        // 밑줄
	
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);	    // 볼드체*/
	
		style.setFont(font);
	
	
	
		// 컬럼 사이즈 자동 조절
	
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
	
		
	}
	
	
public void setCellsBorderStyle(CellStyle style,HSSFSheet sheet,HSSFWorkbook wb,HSSFCell cell,
										boolean firstRow, boolean midRow, boolean endRow,
										boolean firstColm, boolean midColm, boolean endColm) {
		
		HSSFPrintSetup print = sheet.getPrintSetup();
		
		print.setFitHeight((short)1);
		print.setFitWidth((short)1);
		print.setScale((short) 80);
	
	
		style = wb.createCellStyle();

		style.setAlignment((short)2);			// 가로 정렬 중간
	
		style.setVerticalAlignment((short)1);	// 세로 정렬 중단

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		if(firstRow) {
			style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		}
		
		if(midRow) {
		}
		
		if(endRow) {
			style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		}

		if(firstColm) {
			style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		}
		
		if(midColm) {
			
		}
		
		if(endColm) {
			style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		}
	
	
	
		// 셀 스타일 적용
	
		cell.setCellStyle(style);
	
	
	
		// 폰트 설정
	
		Font font = wb.createFont();
	
		font.setFontName("맑은 고딕");                  // 폰트 이름
	
		font.setFontHeightInPoints((short)11);          // 폰트 크기
		style.setFont(font);
	
	
	
		// 컬럼 사이즈 자동 조절
	
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
	
		
	}
}
