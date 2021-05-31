package com.gogi.proj.configurations.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationUtil {

	private static Map<String, Integer> columnNumList;
	/**
	 * @MethodName : excelColumnCoutingAtoAZ
	 * @date : 2019. 7. 18.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 엑셀 A 부터 AZ 까지 문자열 순서 제공 
	 */
	
	public ConfigurationUtil() {
		
		Map<String, Integer> columnNumList = new HashMap<String, Integer>();
		

		int counting = 4;
		int countings = 0;
		for(int num = 1; num < counting; num++) {
			
			for(char i = 'A'; i <= 'Z'; i++) {
				
				if(num == 1) {					
					columnNumList.put(""+i, countings);
					countings++;
				}else if( num == 2){
					columnNumList.put("A"+i, countings);
					countings++;
					
				}else if( num == 3){
					columnNumList.put("B"+i, countings);
					countings++;
					
				}else if( num == 4){
					columnNumList.put("C"+i, countings);
					countings++;
				}
				
			}
			
		}
		
		this.columnNumList = columnNumList;
	}
	
	public List<String> excelColumnCoutingAtoAZ() {
		List<String> result = new ArrayList<String>();
		int counting = 4;
		
		for(int num = 0; num < counting; num++) {
			
			for(char i = 'A'; i <= 'Z'; i++) {
				
				if(num == 1) {					
					result.add(""+i);
					
				}else if( num == 2){
					result.add("A"+i);
					
				}else if( num == 3){
					result.add("B"+i);
					
				}else if( num == 4){
					result.add("C"+i);
					
				}
				
			}
			
		}
		
		return result;
	}
	
	public static int returnXxcelColumnToNumber(String columnNum) {
		
        String result = columnNum.replaceAll("[\\d.]","");
        int colNum= columnNumList.get(result);
        
        return colNum;
	}
}
