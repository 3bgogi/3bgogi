package com.gogi.proj.util;

import java.util.ArrayList;
import java.util.List;

public class StringToListUtil {

	public static List<String> makeForeach(String code){
	    String gb = ",";
	    return makeForeach(code, gb);
	}
	
	public static List<String> makeForeach(String code, String gb){
	     
	    if (code == null || "".equals(code)) {
	        return null;
	    }
	     
	    List<String> codeList = new ArrayList<String>();
	     
	    String[] aCode = code.split(gb);
	    for(int i=0; i< aCode.length; i++){
	         
	        codeList.add(aCode[i].toString());
	    }
	 
	    return codeList;       
	}

}
