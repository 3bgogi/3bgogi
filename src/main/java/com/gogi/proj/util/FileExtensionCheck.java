package com.gogi.proj.util;

import org.springframework.stereotype.Component;

@Component
public class FileExtensionCheck {

	String [] imageExtList = {"jpg", "png", "gif", "jpeg"}; 
	
	public boolean checkImageFileExt(String ext) {
		
		for(String checks : imageExtList) {
			if(ext.contains(checks)) return true;
		}
		
		return false;
	}
}
