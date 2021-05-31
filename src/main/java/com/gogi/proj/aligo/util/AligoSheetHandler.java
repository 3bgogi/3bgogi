package com.gogi.proj.aligo.util;

import java.util.List;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;

import com.gogi.proj.configurations.util.ConfigurationUtil;

public class AligoSheetHandler implements SheetContentsHandler{

	private List<AligoVO> rows;
	
	private AligoVO row;
	
	private int columnCnt;
	
	private int currColNum = 0;
	
	private ConfigurationUtil cUtil = new ConfigurationUtil();

	public AligoSheetHandler(List<AligoVO> rows, int columnsCnt) {
		// TODO Auto-generated constructor stub
		
		this.rows = rows;
		this.columnCnt = columnsCnt;
	}
	
	@Override
	public void startRow(int rowNum) {
		// TODO Auto-generated method stub
		this.row = new AligoVO();
		currColNum = 0;
	}

	@Override
	public void endRow() {
		// TODO Auto-generated method stub
		rows.add(row);
	}

	@Override
	public void cell(String columnNum, String value) {
		// TODO Auto-generated method stub
		
		if(value==null) {
			
		}else {
			
			//고객명
			if(cUtil.returnXxcelColumnToNumber(columnNum)==0) {

				row.setDestination(value);
			
			//전화번호
			}if(cUtil.returnXxcelColumnToNumber(columnNum)==1) {

				row.setReceiver(value);
				
			}
		}
		
	}

	@Override
	public void headerFooter(String text, boolean isHeader, String tagName) {
		// TODO Auto-generated method stub
		
	}
}
