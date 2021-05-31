package com.gogi.proj.paging;

import java.util.List;

public class PaginationInfo {
	/**
	 * Required Fields
	  	currentPage : 현재 페이지
		recordCountPerPage : 페이지당 보여질 레코드수
		blockSize : 블럭당 보여질 페이지 수
		totalRecord : totalRecord 총 레코드 수
	 * */
	
	private int currentPage = 1; //현재 페이지
	private int recordCountPerPage;  //pageSize 페이지당 보여질 레코드수
	private int blockSize; //블럭당 보여질 페이지 수
	private int totalRecord; //총 레코드 수
	
	
	// 추가사항
	//검색어가 있을 경우
	private List<String> searchNames;
	//분류 코드
	private int searchCode;
	//최소 날짜
	private String searchMinDate;
	//최대 날짜
	private String searchMaxDate;
	//최소 금액
	private int searchMinPrice;
	//최대 금액
	private int searchMaxPrice;
	//단독 문자열1
	private String searchOne;
	//단독 문자열2
	private String searchTwo;
	//기타 체크박스1
	private List<String> searchString1;
	//기타 체크박스2
	private List<String> searchString2;
	//기타 체크박스3
	private List<String> searchString3;
	//기타 체크박스4
	private List<String> searchString4;
	
	
	//추가사항
	private String permType;
	
	private String dateType;
	
	public String tests() {
		return "searchOne="+searchOne+", searchTwo="+searchTwo+", searchMinPrice="+searchMinPrice+", "
				+ "searchMaxPrice="+searchMaxPrice+", searchCode="+searchCode+", searchMinDate="+searchMinDate+", searchMaxDate="+searchMaxDate
				+", dateType="+dateType;
	}
	//임의로 담기
	private Object objects;
	
	public Object getObjects() {
		return objects;
	}

	public void setObjects(Object objects) {
		this.objects = objects;
	}

	public int getSearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setSearchMaxPrice(int searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public String getPermType() {
		return permType;
	}

	public void setPermType(String permType) {
		this.permType = permType;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getSearchOne() {
		return searchOne;
	}

	public void setSearchOne(String searchOne) {
		this.searchOne = searchOne;
	}

	public String getSearchTwo() {
		return searchTwo;
	}

	public void setSearchTwo(String searchTwo) {
		this.searchTwo = searchTwo;
	}

	public int getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(int searchCode) {
		this.searchCode = searchCode;
	}

	public List<String> getSearchNames() {
		return searchNames;
	}

	public void setSearchNames(List<String> searchNames) {
		this.searchNames = searchNames;
	}

	public String getSearchMinDate() {
		return searchMinDate;
	}

	public void setSearchMinDate(String searchMinDate) {
		this.searchMinDate = searchMinDate;
	}

	public String getSearchMaxDate() {
		return searchMaxDate;
	}

	public void setSearchMaxDate(String searchMaxDate) {
		this.searchMaxDate = searchMaxDate;
	}

	public int getSearchMinPrice() {
		return searchMinPrice;
	}

	public void setSearchMinPrice(int searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}

	public int getsearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setsearchMaxPrice(int searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public List<String> getSearchString1() {
		return searchString1;
	}

	public void setSearchString1(List<String> searchString1) {
		this.searchString1 = searchString1;
	}

	public List<String> getSearchString2() {
		return searchString2;
	}

	public void setSearchString2(List<String> searchString2) {
		this.searchString2 = searchString2;
	}

	public List<String> getSearchString3() {
		return searchString3;
	}

	public void setSearchString3(List<String> searchString3) {
		this.searchString3 = searchString3;
	}

	public List<String> getSearchString4() {
		return searchString4;
	}

	public void setSearchString4(List<String> searchString4) {
		this.searchString4 = searchString4;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	public int getBlockSize() {
		return blockSize;
	}
	
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	/**
	 * Not Required Fields
	 * 
	 */	
	private int totalPage;  //총 페이지수
	private int firstPage;  //블럭당 시작 페이지, 1, 11, 21, 31, ...
	private int lastPage; //블럭당 마지막 페이지 10, 20, 30, 40, ...
	private int firstRecordIndex; //페이지당 시작 인덱스 0, 5, 10, 15 ...
	private int lastRecordIndex;  //페이지당 마지막 인덱스	5,10,15,20....
	  
	public int getTotalPage() {
		totalPage=(int)Math.ceil((float)totalRecord/recordCountPerPage);
		//totalPage = ((getTotalRecord()-1)/getRecordCountPerPage()) + 1;
		
		return totalPage;
	}
		
	
	public int getFirstPage() {
		firstPage= currentPage-((currentPage-1)%blockSize);
		//firstPage = ((getCurrentPage()-1)/getBlockSize())*getBlockSize() + 1;
		return firstPage;
	}
	
	public int getLastPage() {		
		lastPage = firstPage+(blockSize-1);
		//lastPage = getFirstPage() + getBlockSize() - 1;		
		if(lastPage > getTotalPage()){
			lastPage = getTotalPage();
		}
		return lastPage;
	}

	public int getFirstRecordIndex() {
		//curPos=(currentPage-1)*pageSize;
		firstRecordIndex = (getCurrentPage() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPage() * getRecordCountPerPage();
		return lastRecordIndex;
	}

	@Override
	public String toString() {
		return "PaginationInfo [currentPage=" + currentPage + ", recordCountPerPage=" + recordCountPerPage
				+ ", blockSize=" + blockSize + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", firstRecordIndex=" + firstRecordIndex
				+ ", lastRecordIndex=" + lastRecordIndex + "]";
	}
}
