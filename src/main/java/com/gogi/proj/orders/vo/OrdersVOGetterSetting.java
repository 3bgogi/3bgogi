package com.gogi.proj.orders.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;

public class OrdersVOGetterSetting {

	
	/**
	 * 
	 * @MethodName : excelIOOrders
	 * @date : 2020. 3. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 엑셀 주문서 
	 */
	public List<String> excelIOOrders(OrdersVO osVO){
		
		List<String> IOList = new ArrayList<String>(3);
		
		IOList.add(osVO.getOrUserColumn3());
		IOList.add(osVO.getOrProduct()+"[ "+osVO.getOrProductOption()+" ]");
		IOList.add(osVO.getOrAmount()+"");
		IOList.add(osVO.getOrRequest());
		
		return IOList;
		
	}
	
	/**
	 * 
	 * @MethodName : excelIOOrdersWithBarcode1
	 * @date : 2020. 3. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 엑셀주문서 바코드 추가
	 */
	public List<String> excelIOOrdersWithBarcode1(OrdersVO osVO){
		
		List<String> IOList = new ArrayList<String>(4);
		
		IOList.add(osVO.getOrUserColumn3());
		IOList.add(osVO.getOrProduct()+"[ "+osVO.getOrProductOption()+" ]");
		IOList.add(osVO.getOrAmount()+"");
		IOList.add(osVO.getOrUserColumn1()+"");
		IOList.add(osVO.getOrUserColumn4()+"");
		
		return IOList;
		
	}
	
	
	public List<String> excelIOOrdersToeosSeq(ExcelOrderSeqVO eosVO){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> IOList = new ArrayList<String>(3);
		
		IOList.add(eosVO.getEosSeq()+"");
		IOList.add(eosVO.getEosLocation()+"[ "+sdf.format(new Date())+" ]");
		IOList.add("");
		
		return IOList;
		
	}
}
