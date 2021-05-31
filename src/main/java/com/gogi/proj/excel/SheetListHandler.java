package com.gogi.proj.excel;

import java.util.List;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;

public class SheetListHandler implements SheetContentsHandler{

	private List<String[]> rows;
	
	private String [] row;
	
	private int columnCnt;
	
	private int currColNum = 0;
	
	public SheetListHandler(List<String[]> rows, int columnsCnt) {
		// TODO Auto-generated constructor stub
		
		this.rows = rows;
		this.columnCnt = columnsCnt;
	}
	
	@Override
	public void startRow(int rowNum) {
		// TODO Auto-generated method stub
		this.row = new String[columnCnt];
		currColNum = 0;
		
	}

	@Override
	public void endRow() {
		// TODO Auto-generated method stub
		boolean addFlag = false;
		for(String data : row) {
			if(! "".equals(data)) {
				addFlag = true;
			}
		}
		
		if(addFlag)rows.add(row);
		
	}

	@Override
	public void cell(String columnNum, String value) {
		// TODO Auto-generated method stub
		row[currColNum++] = value == null ? "":value;
		
	}

	@Override
	public void headerFooter(String text, boolean isHeader, String tagName) {
		// TODO Auto-generated method stub
		
	}

}
