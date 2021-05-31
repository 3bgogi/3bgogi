package com.gogi.proj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompare {

	public boolean dateCompareWithDatabase(String workStartTime) {
		
		Date now = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String nowTime = sdf.format(now);
		
		try {
			
			//출근 마감 시간
			Date work = sdf.parse(nowTime);
			
			//출근시간
			Date checked = sdf.parse(workStartTime);
			
			if(work.getTime() < checked.getTime()) {
				return true;
				
			}else {
				return false;
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
}
