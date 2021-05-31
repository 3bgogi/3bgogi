package com.gogi.proj.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.admin.project.vo.ProjectsVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;

@Controller
@RequestMapping("")
public class UtilController {

	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@RequestMapping(value="/alarm/popup.do", method=RequestMethod.GET)
	public String openAlarmPage(HttpServletRequest request, @RequestParam List<Object> projects, Model model) {
		
		logger.info("result = {}", projects.toString());
		
		model.addAttribute("projects", projects);
		return "common/alarm";
	}
	
	@RequestMapping(value="/util/file_download.do", method=RequestMethod.GET)
	public ModelAndView cilFileDownload(HttpServletRequest request, String FilePath, String FileUniqName) {
		
		File file = new File(FilePath, FileUniqName);
		 
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
}
