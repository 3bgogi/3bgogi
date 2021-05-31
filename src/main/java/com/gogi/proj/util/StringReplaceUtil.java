package com.gogi.proj.util;

import org.springframework.stereotype.Component;

@Component
public class StringReplaceUtil {

	public String wordFiltering(String word) {
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < word.length(); i++) {
			char wordChar = word.charAt(i);
			
			if(wordChar == '<') sb.append("&lt;");
			else if(wordChar == '>') sb.append("&gt;");
			else if(wordChar == '&') sb.append("&amp;");
			else if(wordChar == '(') sb.append("&#40;");
			else if(wordChar == ')') sb.append("&#41;");
			else sb.append(wordChar);
		}
		
		return sb.toString();
	}
}
