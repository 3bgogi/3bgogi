package com.gogi.proj.util;

public class CheckFileExtension {
	
	private static final int TYPE_XLS= 0;
	private static final int TYPE_XLSX = 1;
	private static final int TYPE_JPG = 2;
	private static final int TYPE_PNG = 3;
	private static final int TYPE_JPEG = 4;
	private static final int TYPE_JPE = 5;
	private static final int TYPE_ANOTHER = 6;
	
	/*public int checkFileExtension(String fileName) {
		
		int index = fileName.lastIndexOf(".");
		
		String strext = fileName.substring(index + 1);
		
		switch(strext) {
		case "xls" :
			return TYPE_XLS;
		case "xlsx" :
			return TYPE_XLSX;
		case "jpg" :
			return TYPE_JPG;
		case "png" :
			return TYPE_PNG;
		case "jpeg" :
			return TYPE_JPEG;
		case "jpe" :
			return TYPE_JPE;
		default :
			return TYPE_ANOTHER;
		}
		
	}*/
	
}
