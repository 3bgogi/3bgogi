package com.gogi.proj.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileuploadUtil {
	private static final Logger logger=LoggerFactory.getLogger(FileuploadUtil.class);
	
	//파일 업로드 관련 상수
	public static final int UPLOAD_FILE=1; 	//자료실 업로드
	public static final int ORDER_EXCEL=2;  //엑셀 임시 저장
	public static final int IMAGE_UPLOAD=3; //이미지 업로드
	public static final int UPLOAD_IMAGE=4; //자료실 이미지
	public static final int STOCK_STATEMENT_IMG = 5;
	public static final int CARCASS_FILE = 6; // 도체 파일 업로드
	public static final int TAX_FILE = 7; // 세금계산거 파일 업로드
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	public String fileupload(HttpServletRequest request, int uploadGb) 
			throws IllegalStateException, IOException {
		
		String fileoName = "";
		//파일 업로드 처리
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;

		Map<String, MultipartFile> fileMap=multipartRequest.getFileMap();
		
		//file정보 결과를 저장할 list
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		Iterator<String> iter=fileMap.keySet().iterator();
		while(iter.hasNext()) {
			String key=iter.next();
			MultipartFile tempFile=fileMap.get(key);
			//=>업로드 된 파일을 임시파일 형태로 제공
			
			//업로드 된 경우
			if(!tempFile.isEmpty()) {
				String ofileName=tempFile.getOriginalFilename();
				fileoName = getUniqueFileName(ofileName);
				//unique한 파일명 구하기
				String fileName=getUniqueFileName(ofileName);
				fileoName = fileName;
				
				long fileSize=tempFile.getSize();
				
				//업로드 처리
				String uploadPath=getUploadPath(request, uploadGb);
				File file=new File(uploadPath, fileName);
				tempFile.transferTo(file);	//실제 업로드 처리
				
				//결과 저장
				Map<String, Object> resultMap=new HashMap<String, Object>();
				resultMap.put("real", ofileName);
				resultMap.put("filename", fileName);
				resultMap.put("fileSize", fileSize);
				
				list.add(resultMap);
			}//if
			
		}//while
		
		return fileoName;
	}
	
	public List<Map<String, Object>> multiFileupload(HttpServletRequest request, int uploadGb) 
			throws IllegalStateException, IOException {
		
		//파일 업로드 처리
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		
		//file정보 결과를 저장할 list
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		Iterator<String> iter=fileMap.keySet().iterator();
		while(iter.hasNext()) {
			
			String key=iter.next();
			MultipartFile tempFile=fileMap.get(key);
			
			
			//=>업로드 된 파일을 임시파일 형태로 제공
			
			//업로드 된 경우
			if(!tempFile.isEmpty()) {
				String oriFileName=tempFile.getOriginalFilename();
				
				//unique한 파일명 구하기
				String uniqFileName=getUniqueFileName(oriFileName);
				String extType = getExtType(oriFileName);
				
				long fileSize=tempFile.getSize();
				
				//업로드 처리
				String uploadPath=getUploadPath(request, uploadGb);
				File file=new File(uploadPath, uniqFileName);
				tempFile.transferTo(file);	//실제 업로드 처리
				
				//결과 저장
				Map<String, Object> resultMap=new HashMap<String, Object>();
				resultMap.put("oriFileName", oriFileName);
				resultMap.put("uniqFileName", uniqFileName);
				resultMap.put("fileSize", fileSize);
				resultMap.put("fileExtType", extType);
				resultMap.put("filePath", uploadPath);
				
				list.add(resultMap);
				
			}//if
			
		}//while
		
		return list;
	}
	
	public List<Map<String, Object>> fileupload2(HttpServletRequest request, int uploadGb) 
			throws IllegalStateException, IOException {
		
		//파일 업로드 처리
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;

		Map<String, MultipartFile> fileMap=multipartRequest.getFileMap();
		
		//file정보 결과를 저장할 list
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		Iterator<String> iter=fileMap.keySet().iterator();
		
		while(iter.hasNext()) {
			String key=iter.next();
			MultipartFile tempFile=fileMap.get(key);
			//=>업로드 된 파일을 임시파일 형태로 제공
			
			//업로드 된 경우
			if(!tempFile.isEmpty()) {
				String ofileName=tempFile.getOriginalFilename();
				String fileName=getUniqueFileName(ofileName);
				String extType = getExtType(ofileName);
				
				long fileSize=tempFile.getSize();
				
				//업로드 처리
				String uploadPath=getUploadPath(request, uploadGb);
				File file=new File(uploadPath, fileName);
				tempFile.transferTo(file);	//실제 업로드 처리
				
				//결과 저장
				Map<String, Object> resultMap=new HashMap<String, Object>();
				resultMap.put("oriFileName", ofileName);
				resultMap.put("uniFileName", fileName);
				resultMap.put("fileSize", fileSize);
				resultMap.put("fileExtType", extType);
				resultMap.put("filePath", uploadPath);
				
				list.add(resultMap);
				
			}//if
			
		}//while
		
		return list;
	}

	public String getUploadPath(HttpServletRequest request, int uploadGb) {
		String upPath="";
		
		String type=fileProperties.getProperty("file.upload.type");
		if(type.equals("test")) {
			//테스트시 경로
			if(uploadGb==UPLOAD_FILE) {
				upPath=fileProperties.getProperty("file.upload.upload_file.path.test");				
			}else if(uploadGb==ORDER_EXCEL) {
				upPath=fileProperties.getProperty("file.upload.order_excel.path.test");				
			}else if(uploadGb==IMAGE_UPLOAD) {
				upPath=fileProperties.getProperty("imageFile.upload.path.test");				
			}else if(uploadGb==UPLOAD_IMAGE) {
				upPath=fileProperties.getProperty("file.upload.upload_image.path.test");				
			}else if(uploadGb == STOCK_STATEMENT_IMG) {
				upPath=fileProperties.getProperty("file.upload.stock_statement_img.path.test");
			}else if(uploadGb == CARCASS_FILE) {
				upPath=fileProperties.getProperty("file.upload.carcass_file.path.test");
				upPath=existFileDirectoryByDateAndReturnPath(upPath);
			}else if(uploadGb == TAX_FILE) {
				upPath=fileProperties.getProperty("file.upload.tax_file.path.test");
				upPath=existFileDirectoryByDateAndReturnPath(upPath);
			}
			
		}else {
			//배포시 경로
			if(uploadGb==UPLOAD_FILE) {
				upPath=fileProperties.getProperty("file.upload.upload_file.path");
			}else if(uploadGb==ORDER_EXCEL) {
				upPath=fileProperties.getProperty("file.upload.order_excel.path");
				
			}else if(uploadGb==IMAGE_UPLOAD) {
				upPath=fileProperties.getProperty("imageFile.upload.path");				
			}else if(uploadGb==UPLOAD_IMAGE) {
				upPath=fileProperties.getProperty("file.upload.upload_image.path");				
			}else if(uploadGb == STOCK_STATEMENT_IMG) {
				upPath=fileProperties.getProperty("file.upload.stock_statement_img.path");
			}else if(uploadGb == CARCASS_FILE) {
				upPath=fileProperties.getProperty("file.upload.carcass_file.path");
				upPath=existFileDirectoryByDateAndReturnPath(upPath);
			}else if(uploadGb == TAX_FILE) {
				upPath=fileProperties.getProperty("file.upload.tax_file.path");
				upPath=existFileDirectoryByDateAndReturnPath(upPath);
			}
			
			//실제 물리적인 경로 구하기
			upPath=request.getSession().getServletContext().getRealPath(upPath);
		}
		return upPath;		
	}

	public String getUniqueFileName(String ofileName) {
		//test.txt => test201712040504830123.txt
		int idx=ofileName.lastIndexOf(".");
		String fName=ofileName.substring(0, idx);	//test
		String ext=ofileName.substring(idx);	//.txt
		
		String fileName=fName+getCurrentTime()+ext;
		
		return fileName;
	}
	
	public String getExtType(String ofileName) {
		
		int idx=ofileName.lastIndexOf(".");
		String ext=ofileName.substring(idx);
		
		return ext;
	}
	
	private String getCurrentTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		Date d=new Date();
		
		return sdf.format(d);
	}
	
	public boolean deleteFile(String path, String fileName) {
		
		File file = new File(path+"/"+fileName);
		
		if(file.exists()) {
			file.delete();
			return true;
			
		}else {
			return false;
			
		}
	}
	
	public static String existFileDirectoryByDateAndReturnPath(String rootPath) {
		Date today = new Date();
		
		String years =  new SimpleDateFormat("yyyy").format(today);
		String month = new SimpleDateFormat("MM").format(today);
		String dates = new SimpleDateFormat("dd").format(today);
		
		File file = new File(rootPath+"/"+years);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		file = new File(rootPath+"/"+years+"/"+month);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		file = new File(rootPath+"/"+years+"/"+month+"/"+dates);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		return rootPath+"/"+years+"/"+month+"/"+dates+"/";
	}
}
