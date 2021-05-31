package com.gogi.proj.admin.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import com.gogi.proj.admin.project.model.ProjectsService;
import com.gogi.proj.admin.project.vo.ProjectDetailVO;
import com.gogi.proj.admin.project.vo.ProjectSearchParameter;
import com.gogi.proj.admin.project.vo.ProjectTagVO;
import com.gogi.proj.admin.project.vo.ProjectTargetVO;
import com.gogi.proj.admin.project.vo.ProjectsVO;
import com.gogi.proj.security.AdminNormalVO;
import com.gogi.proj.security.AdminServiceImpl;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.util.FileExtensionCheck;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.StringReplaceUtil;

@Controller
@RequestMapping(value="/project")
public class ProjectsController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);
	
	@Autowired
	private ProjectsService projectsService;
	
	//특수 문자 치환을 위한 클래스
	@Autowired
	private StringReplaceUtil sru;
	
	//파일 확장자 체크
	@Autowired
	private FileExtensionCheck fesc;
	
	//파일 업로드
	@Autowired
	private FileuploadUtil fUtil;
	
	//관리자 확인용
	@Autowired
	private AdminServiceImpl adminService;
	
	
	/**
	 * @MethodName : insertProjectGet
	 * @date : 2019. 4. 8.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 확인 페이지 들어가기
	 */
	@RequestMapping(value="/projects.do", method=RequestMethod.GET)
	public String insertProjectGet(HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		//유저 정보가 없을 경우
		if(auth.getPrincipal() == null) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String pathUrl = urlPathHelper.getOriginatingRequestUri(request);
			
			logger.info("request url => {}", pathUrl);
			logger.info("error type : user info is null");
			logger.info("check login session");
			
			return null;
			
		}else {
			
			adminVo = (AdminVO)auth.getPrincipal();
		}
		
		ProjectSearchParameter psp =  new ProjectSearchParameter();
		
		psp.setAdminPk(adminVo.getAdminPk());
		
		List<ProjectsVO> projectsList = projectsService.selectProject(psp);
		
		logger.info("result = {}", projectsList.toString());
		model.addAttribute("projectsList",projectsList);
		model.addAttribute("projectsTagList", projectsService.selectProjectTag(adminVo.getAdminPk()));
		
		return "admin/project/insert_project";
	}
	
	
	/**
	 * @MethodName : insertprojectPost
	 * @date : 2019. 4. 10.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 확인 페이지에서 업무 등록하기 #을 이용해서 업무명과 상세사항을 나누며 썸네일 사진도 같이 업로드 가능.
	 */
	@RequestMapping(value="/projects.do", method=RequestMethod.POST)
	@Transactional
	public String insertprojectPost(@ModelAttribute ProjectsVO projectsVO, HttpServletRequest request, Model model) {		
		logger.info("Project parameter = {}", projectsVO.toString());
		projectsVO.setProTitleColor("#3d405c");
		
		//특수문자 치환
		projectsVO.setProTitle(sru.wordFiltering(projectsVO.getProTitle()));
		projectsVO.setProDetail(sru.wordFiltering(projectsVO.getProDetail()));
		
		String msg = "";
		String url = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		
		//로그인 전이라면 로그인 창으로
		if((AdminVO)auth.getPrincipal() == null) {
			logger.info("admin info is null back login page");
			
			return "redirect:/login.do";
		}
		
		adminVo = (AdminVO)auth.getPrincipal();

		List<Map<String, Object>> fileNameList = null;
		
		try {
			//파일 업로드 처리
			fileNameList = fUtil.fileupload2(request, fUtil.UPLOAD_IMAGE);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			logger.info("file Image upload Fail..");
			
			return "redirect:/login.do";
		}
		
		Iterator<Map<String, Object>> iter = fileNameList.iterator();
		
		int targetResult = 0;
		
		//업로드 파일이 없을 경우
		if( ! iter.hasNext() ) {
			
			int proPk = projectsService.insertProject(projectsVO);
			
			targetResult = projectsService.insertProjectTarget(adminVo.getAdminPk(), projectsVO.getProPk());
		
		//업로드 파일이 있을 경우
		}else {
			
			while(iter.hasNext()) {
				
				Map<String, Object> mapList = iter.next();
				
				projectsVO.setProThumbnailImage((String)mapList.get("uniFileName"));
				projectsVO.setProThumbnailImageRealName((String)mapList.get("oriFileName"));
				projectsVO.setProThumbnailImageExtType((String)mapList.get("fileExtType"));
				
				if(!fesc.checkImageFileExt(projectsVO.getProThumbnailImageExtType())) {
					msg="이미지 파일이 아닙니다. 재등록 해주세요.";
					url = "/project/projects.do";
					
					File file = new File(fUtil.getUploadPath(request, fUtil.UPLOAD_IMAGE)+"\\"+(String)mapList.get("uniFileName"));
					file.delete();
					
					model.addAttribute("msg",msg);
					model.addAttribute("url",url);
					
					return "common/message";
				}
				int proPk = projectsService.insertProject(projectsVO);

				targetResult = projectsService.insertProjectTarget(adminVo.getAdminPk(), projectsVO.getProPk());
				
			}
			
		}
		
		msg = "업무 등록 완료";
		url = "/project/projects.do";
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	/**
	 * @MethodName : pageTopFixedProjectList
	 * @date : 2019. 4. 11.
	 * @author : Jeon KiChan
	 * @메소드설명 : 모든 페이지의 상단 고정부분 자신 업무 확인
	 */
	@RequestMapping(value="/project_alarm.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProjectsVO> pageTopFixedProjectList(HttpServletRequest request){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		//유저 정보가 없을 경우
		if((AdminVO)auth.getPrincipal() == null) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String pathUrl = urlPathHelper.getOriginatingRequestUri(request);
			logger.info("request url => {}", pathUrl);
			logger.info("error type : user info is null");
			logger.info("check login session");
			
			return null;
		}else {
			
			adminVo = (AdminVO)auth.getPrincipal();
		}
		
		List<ProjectsVO> projectsList = null;
		
		try {
			
			projectsList = projectsService.selectProjectConditionWithAlarmAndOwn(adminVo.getAdminPk());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("error 발생, 혹은 값이 없음.");
			
			return null;
		}
		
		if(projectsList.isEmpty()) {
			
			return null;
		}else {
			
			return projectsList;
		}
	}
	
	/**
	 * @MethodName : selectProjectDetailView
	 * @date : 2019. 4. 17.
	 * @author : Jeon KiChan
	 * @메소드설명 : 모든 페이지의 상단 고정부분 자신 업무를 누를 시 상세사항 가져오기 _ ajax 형태
	 */
	@RequestMapping(value="/project_detail.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectProjectDetailView(HttpServletRequest request,@ModelAttribute ProjectSearchParameter psp){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		//유저 정보가 없을 경우
		if(auth.getPrincipal() == null) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String pathUrl = urlPathHelper.getOriginatingRequestUri(request);
			logger.info("request url => {}", pathUrl);
			logger.info("error type : user info is null");
			logger.info("check login session");
			
			return null;
			
		}else {
			
			adminVo = (AdminVO)auth.getPrincipal();
		}
		
		int authCount = 0;

		for (GrantedAuthority a : auth.getAuthorities()) {

			if (a.getAuthority().equals("ROLE_ADMIN"))
				authCount++;
		}
		
		ProjectsVO vo = projectsService.selectProjectDetailView(psp, adminVo.getAdminPk());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("projectDetails", vo);
		
		//업무 생성자 넣어주기
		map.put("projectOwn",  projectsService.selectProjectTargetCreateCheck(psp.getProPk(), adminVo.getAdminPk()));
		
		//관리자 여부 넣어주기
		if(authCount > 0) {
			map.put("adminFlag", true);
			
		}else {
			map.put("adminFlag", false);
			
		}
		
		return map;
	}
	
	
	/**
	 * @MethodName : selectProjectTargetIncludeCheck
	 * @date : 2019. 4. 29.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 대상자 가져오기 : 보안상 나중에 개선하도록 함
	 */
	@RequestMapping(value="/project_detail_target.do", method=RequestMethod.GET)
	@ResponseBody
	public List<AdminNormalVO> selectProjectTargetIncludeCheck(@RequestParam int proPk){
		List<AdminNormalVO> list = projectsService.selectProjectTargetIncludeCheck(proPk);
		
		return list;
	}
	
	
	/**
	 * @MethodName : insertProjectDetailPost
	 * @date : 2019. 4. 19.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 완료사항 입력 _ ajax 형태
	 */
	@RequestMapping(value="/project_detail.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	@Transactional
	public String insertProjectDetailPost(HttpServletRequest request, @ModelAttribute ProjectDetailVO projectDetailVO) {
		String msg = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		//유저 정보가 없을 경우
		if(auth.getPrincipal() == null) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String pathUrl = urlPathHelper.getOriginatingRequestUri(request);
			
			logger.info("request url => {}", pathUrl);
			logger.info("error type : user info is null");
			logger.info("check login session");
			
			return null;
			
		}else {
			
			adminVo = (AdminVO)auth.getPrincipal();
		}
		
		//업무 대상자인지 체크
		boolean targetFlag = projectsService.selectProjectTargetChecks(projectDetailVO.getPdProFk(), adminVo.getAdminPk());
		
		if(targetFlag == false) {
			 msg = "업무 대상자가 아닙니다 ";
			 return msg;
		}
		
		List<Map<String, Object>> fileNameList = null;
		
		try {
			//파일 업로드 처리
			fileNameList = fUtil.fileupload2(request, fUtil.UPLOAD_FILE);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			logger.info("file Image upload Fail..");
			
		}
		
		Iterator<Map<String, Object>> iter = fileNameList.iterator();
		
		int result = 0;
		
		projectDetailVO.setPdCheckAdminPk(adminVo.getAdminPk());
		
		//업로드 파일이 없을 경우
		if( ! iter.hasNext() ) {
			
			projectDetailVO.setPdDetail(sru.wordFiltering(projectDetailVO.getPdDetail()));
			result = projectsService.insertProjectDetail(projectDetailVO, adminVo.getAdminname());
		
		//업로드 파일이 있을 경우
		}else {
			
			while(iter.hasNext()) {
				
				Map<String, Object> mapList = iter.next();
				
				projectDetailVO.setPdDetail(sru.wordFiltering(projectDetailVO.getPdDetail()));
				
				projectDetailVO.setPdFile1((String)mapList.get("uniFileName"));
				projectDetailVO.setPdFileRealName((String)mapList.get("oriFileName"));
				projectDetailVO.setPdFileExtType((String)mapList.get("fileExtType"));
				
				result = projectsService.insertProjectDetail(projectDetailVO, adminVo.getAdminname());
				
			}
			
		}
		
		//특수 문자 치환
		
		if(result == 0) msg = "완료사항 등록에 실패했습니다.";
		else msg = "완료사항 등록 완료";
		return msg;
	}
	
	
	/**
	 * @MethodName : alarmStatusChange
	 * @date : 2019. 4. 23.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 상단 고정 상태 변경
	 */
	@RequestMapping(value="/project_top_alarm.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProjectsVO> alarmStatusChange(HttpServletRequest request, @RequestParam int proPk, @RequestParam int status) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = null;
		
		//유저 정보가 없을 경우
		if(auth.getPrincipal() == null) {
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String pathUrl = urlPathHelper.getOriginatingRequestUri(request);
			
			logger.info("request url => {}", pathUrl);
			logger.info("error type : user info is null");
			logger.info("check login session");
			
			return null;
			
		}else {
			
			adminVo = (AdminVO)auth.getPrincipal();
		}
		
		int result = projectsService.changeProjectTargetTopFixedStatus(status, proPk, adminVo.getAdminPk());
		
		logger.info("projectTopChange result = {}", result);
		
		return  projectsService.selectProjectConditionWithAlarmAndOwn(adminVo.getAdminPk());
	}
	
	/**
	 * @MethodName : projectDetailImportantStatusChange
	 * @date : 2019. 4. 24.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 완료 사항 중요도 상태 변경
	 */
	@RequestMapping(value="/project_important_status.do", method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String projectDetailImportantStatusChange(HttpServletRequest request,@RequestParam int ptProFk, @RequestParam int pdPk, @RequestParam int status){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		String msg = "";
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(ptProFk, adminVo.getAdminPk());
		
		if(checkProjectTartget) {
			msg = "상태 변경 완료";
		}else {
			msg = "업무 대상자 혹은 관리자가 아닙니다.";
			
			return msg;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();

		int result = projectsService.changeProjectDetailImportantStatus(status, pdPk);
		
		logger.info("projectImportant Status Change Result = {}", result);
		
		return msg;
	}
	
	
	/**
	 * @MethodName : projectDetailDelete
	 * @date : 2019. 4. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 완료 사항 체크박스로 선택 뒤 삭제
	 */
	@RequestMapping(value="/project_delete.do", method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public int projectDetailDelete(HttpServletRequest request,@RequestParam int ptProFk, @RequestParam List<String> pdPkArray) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = 0;
		
		int deleteFail = 0;
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(ptProFk, adminVo.getAdminPk());
		
		logger.info("업무 상세사항 작업자 대상 여부 = {}", checkProjectTartget);
		
		//업무 대상자가 아니라면 돌아감
		if(!checkProjectTartget) {
			
			return 0;
		}
		
		//관리자일 경우 바로 삭제가능하도록
		int authCount = 0;
		   
		for(GrantedAuthority a : auth.getAuthorities()) {
			
			if(a.getAuthority().equals("ROLE_ADMIN")) authCount++;
			
		}
		
		for(int i = 0; i < pdPkArray.size(); i++) {
			ProjectDetailVO pdVO = projectsService.selectProjectDetailByPdPk(Integer.parseInt( pdPkArray.get(i)));
			
			if(authCount > 0) {
				
				if(pdVO.getPdFile1() != null) {				
					fUtil.deleteFile(fUtil.getUploadPath(request, fUtil.UPLOAD_FILE), pdVO.getPdFile1());
					
				}
				
				projectsService.deleteProjectDetailByPdPk(pdVO.getPdPk());
				
				result++;
				
			}else {
				
				if(pdVO.getPdCheckAdminPk() == adminVo.getAdminPk()) {
					
					if(pdVO.getPdFile1() != null) {				
						fUtil.deleteFile(fUtil.getUploadPath(request, fUtil.UPLOAD_FILE), pdVO.getPdFile1());
						
					}
					
					projectsService.deleteProjectDetailByPdPk(pdVO.getPdPk());
					
					result++;
					
				}else {
					
					deleteFail--;
				}
				
				
			}
			
		}
		
		if(deleteFail < 0) {
			
			return deleteFail;
			
		}
		
		return result;
	}
	
	
	/**
	 * @MethodName : changeProjectTitleColor
	 * @date : 2019. 4. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 제목 색상 변경
	 */
	@RequestMapping(value="/project_title_color_change.do", method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public int  changeProjectTitleColor(ProjectsVO vo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = 0;
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(vo.getProPk(), adminVo.getAdminPk());
		
		logger.info("업무 상세사항 작업자 대상 여부 = {}", checkProjectTartget);
		
		//업무 대상자가 아니라면 돌아감
		if(!checkProjectTartget) {
			
			return 0;
		}
		
		projectsService.changeProjectTitleColor(vo);
		result++;
	
		return result;
	}
	
	/**
	 * @MethodName : addProjectTag
	 * @date : 2019. 4. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 태그 생성
	 */
	@RequestMapping(value="/add_project_tag.do", method=RequestMethod.GET)
	@ResponseBody
	public int addProjectTag(@ModelAttribute ProjectTagVO projectTagVO) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		logger.info("parameter = {}", projectTagVO);
		int result = 0;
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(projectTagVO.getPtagProFk(), adminVo.getAdminPk());
		
		logger.info("업무 상세사항 작업자 대상 여부 = {}", checkProjectTartget);
		
		//업무 대상자가 아니라면 돌아감
		if(!checkProjectTartget) {
			return -1;
			
		}
		projectTagVO.setPtagAdminFk(adminVo.getAdminPk());
		result = projectsService.insertProjectTag(projectTagVO);
		
		return result;
	}
	
	
	/**
	 * @MethodName : deleteProjectTag
	 * @date : 2019. 4. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 태그 삭제 기능
	 */
	@RequestMapping(value="/delete_project_tag.do", method=RequestMethod.GET)
	@ResponseBody
	public int deleteProjectTag(@ModelAttribute ProjectTagVO projectTagVO) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int result = 0;
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(projectTagVO.getPtagProFk(), adminVo.getAdminPk());
		
		logger.info("업무 상세사항 작업자 대상 여부 = {}", checkProjectTartget);
		
		//업무 대상자가 아니라면 돌아감
		if(!checkProjectTartget) {
			return -1;
			
		}
		
		result = projectsService.deleteProjectTag(projectTagVO.getPtagPk());
		
		return result;
		
	}
	
	
	/**
	 * @MethodName : deleteProjectAllData
	 * @date : 2019. 4. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 삭제 기능 : 데이터 베이스 상에는 남아있음
	 */
	@RequestMapping(value="/delete_project.do", method=RequestMethod.POST)
	public String deleteProjectAllData( @RequestParam int proPk, @RequestParam String deletePass, Model model) {
		
		logger.info("업무 삭제 parameter, proPk={}, deletePass={}", proPk, deletePass);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		String msg = "";
		String url = "/project/projects.do";
		String path = "common/message";
		
		//업무 대상자가 맞는 지 확인
		boolean checkProjectTartget = projectsService.selectProjectTargetChecks(proPk, adminVo.getAdminPk());
		
		logger.info("업무 대상자 = {}", checkProjectTartget);
		if(!checkProjectTartget) {
			msg = "업무 대상자가 아닙니다. ";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return path;
		}
		
		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(proPk, adminVo.getAdminPk());
		
		logger.info("업무 생성자 = {}", checkCreateTarget);
		if(!checkCreateTarget) {
			msg = "업무 생성자가 아닙니다. ";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return path;
		}
		
		if(!adminService.checkAdminPassByAdminPk(deletePass, adminVo.getAdminPk())) {
			msg = "비밀번호가 다릅니다. ";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return path;
		}
		
		int result = projectsService.deleteProjectProcess(proPk);
		
		if(result > 0) {
			msg = "업무 삭제 완료";
		}else {
			msg = "업무 삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return path;
	}
	
	
	
	/**
	 * @MethodName : addProjectTarget
	 * @date : 2019. 4. 30.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 대상자 추가 기능
	 */
	@RequestMapping(value="/add_project_target.do", method=RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String addProjectTarget(@ModelAttribute ProjectTargetVO projectTargetVO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();		
		// 관리자일 경우 무시하고 가능
		int authCount = 0;

		for (GrantedAuthority a : auth.getAuthorities()) {

			if (a.getAuthority().equals("ROLE_ADMIN"))
				authCount++;
		}

		String msg = "";
		
		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(projectTargetVO.getPtProFk(), adminVo.getAdminPk());
				
		if(!checkCreateTarget) {
			//관리자라면 바로 가능
			if(authCount > 0) {
				projectsService.addProjectTarget(projectTargetVO);
				 
				msg = "관리자 권한으로 업무 대상자로 추가 완료.";
				
				return msg;
			}else {
				
				msg = "업무 생성자가 아닙니다. ";
				
				return msg;
			}
		}
		
		int result = projectsService.addProjectTarget(projectTargetVO);
		
		if(result > 0) {
			msg = "업무 대상자 추가 완료.";
			
		}else {
			msg = "업무 대상으로 추가 실패";
		}
		
		return msg;
		
	}
	
	
	/**
	 * @MethodName : deleteProjectTarget
	 * @date : 2019. 4. 30.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 대상자 제외 기능
	 */
	@RequestMapping(value="/delete_project_target.do", method=RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String deleteProjectTarget(@RequestParam(defaultValue="0") int ptPk, @RequestParam int proPk) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();		
		// 관리자일 경우 무시하고 가능
		int authCount = 0;

		for (GrantedAuthority a : auth.getAuthorities()) {

			if (a.getAuthority().equals("ROLE_ADMIN"))
				authCount++;
		}

		String msg = "";
		
		//업무대상자 중복 처리로 인해 값이 없을 경우
		if(ptPk == 0) {
			msg = "현재 업무 대상자가 아닙니다.";
			
			return msg;
		}
		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(proPk, adminVo.getAdminPk());
				
		if(!checkCreateTarget) {
			//관리자라면 바로 가능
			if(authCount > 0) {
				projectsService.deleteProjectTarget(ptPk);
				 
				msg = "관리자 권한으로 업무 대상자 삭제 완료.";
				
				return msg;
			}else {
				
				msg = "업무 생성자가 아닙니다. ";
				
				return msg;
			}
		}
		
		int result = projectsService.deleteProjectTarget(ptPk);
		
		if(result > 0) {
			msg = "업무 대상자 삭제 완료";
			
		}else {
			msg = "업무 대상으로 삭제 실패";
		}
		
		return msg;

	}
	
	/**
	 * @MethodName : selectProjectTag
	 * @date : 2019. 5. 1.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 태그 가져오기 값이 0일 경우 전부 , 아닐 경우엔 자신의 것만
	 */
	@RequestMapping(value="/project_tag_list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProjectTagVO> selectProjectTag(@RequestParam(defaultValue="0") int adminPk){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AdminVO adminVo = (AdminVO)auth.getPrincipal();	
		
		if(adminPk == -1) {
			adminPk = adminVo.getAdminPk();
			
		}
		
		return projectsService.selectProjectTag(adminPk);
	}
	
	
	/**
	 * @MethodName : callProjectList
	 * @date : 2019. 5. 8.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 리스트 다시 불러오기 ajax 형태로
	 */
	@RequestMapping(value="/call_project_list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProjectsVO> callProjectList(@ModelAttribute ProjectSearchParameter psp){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AdminVO adminVo = (AdminVO)auth.getPrincipal();	
		if(psp.isShowAnotherTeamProject() == false) psp.setAdminPk(adminVo.getAdminPk());
		
		return projectsService.selectProject(psp);
	}
	
	
	/**
	 * @MethodName : settingProjectAlarm
	 * @date : 2019. 5. 9.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 알람 설정하기
	 */
	@RequestMapping(value="/setting_alarm.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String settingProjectAlarm(@ModelAttribute ProjectsVO projectsVO) {
		
		String msg = "";
		
		int result = 0;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();		
		// 관리자일 경우 무시하고 가능
		int authCount = 0;

		for (GrantedAuthority a : auth.getAuthorities()) {

			if (a.getAuthority().equals("ROLE_ADMIN"))
				authCount++;
		}

		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(projectsVO.getProPk(), adminVo.getAdminPk());
				
		if(!checkCreateTarget) {
			//관리자라면 바로 가능
			if(authCount > 0) {
				
				try {
					
					result = projectsService.changeProjectAlarmType(projectsVO);
					
				}catch(Exception e) {
					logger.info("error 발생 : {}", e.getStackTrace());
					
				}
				 
				msg = "관리자 권한으로 알람 타입 변경 완료.";
				
				return msg;
			}else {
				
				msg = "업무 생성자가 아닙니다. ";
				
				return msg;
			}
		}
		
		try {
			result = projectsService.changeProjectAlarmType(projectsVO);
			
		}catch(Exception e) {
			logger.info("error 발생 : {}", e.getStackTrace());
			
		}
		
		
		if(result > 0) {
			msg = "타입 변경 완료";
		}else {
			msg = "타입 변경 실패 : 로그를 확인해주세요.";
		}
		
		return msg;
	}
	
	
	/**
	 * @MethodName : changeProjectAlarmDate
	 * @date : 2019. 5. 9.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 알람 일 설정하기
	 * @param : proAlarmDate 값 가져옴
	 */
	@RequestMapping(value="/change_alarm_date.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String changeProjectAlarmDate(@ModelAttribute ProjectsVO projectsVO) {
		
		String msg = "";
		
		int result = 0;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();		
		// 관리자일 경우 무시하고 가능
		int authCount = 0;

		for (GrantedAuthority a : auth.getAuthorities()) {

			if (a.getAuthority().equals("ROLE_ADMIN"))
				authCount++;
		}

		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(projectsVO.getProPk(), adminVo.getAdminPk());
				
		if(!checkCreateTarget) {
			//관리자라면 바로 가능
			if(authCount > 0) {
				
				try {
					result = projectsService.changeProjectAlarmDate(projectsVO);
					
				}catch(Exception e) {
					logger.info("error 발생 : {}", e.getStackTrace());
					msg ="error 발생 로그를 확인해주세요.";
					return msg;
				}
				 
				msg = "관리자 권한으로 날짜 설정 완료.";
				
				return msg;
			}else {
				
				msg = "업무 생성자가 아닙니다. ";
				
				return msg;
			}
		}
		
		
		try {
			result = projectsService.changeProjectAlarmDate(projectsVO);
			
		}catch(Exception e) {
			logger.info("error 발생 : {}", e.getStackTrace());
			
		}
		
		
		if(result > 0) {
			msg = "날짜 설정 완료";
			
		}else {
			msg = "날짜 설정 실패 : 로그를 확인해주세요.";
			
		}
		
		return msg;
	}
	
	
	
	/**
	 * @MethodName : clearProjectAlarm
	 * @date : 2019. 5. 10.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 알람 갱신 기능
	 */
	@RequestMapping(value="/clear_alarm.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String clearProjectAlarm(@RequestParam int proPk) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();	
		
		ProjectTargetVO vo = new ProjectTargetVO();
		
		vo.setPtAdminFk(adminVo.getAdminPk());
		
		vo.setPtProFk(proPk);
		
		int ptPk = 0;
		
		String msg = "";
		try {
			ptPk = projectsService.selectProjectTargetByPropkAndAdminpkForPtpk(vo);
		}catch(NullPointerException e) {
			logger.info("업무 대상자가 아님");
			msg = "잘못된 접근 방법입니다.";
			return msg;
		}
		
		
		if(ptPk > 0 ) {
			
			vo.setPtPk(ptPk);
			
			projectsService.checkProjectAlarm(vo);
			msg = "알람 해제 완료";
		}
		
		
		return msg;
	}
	
	
	/**
	 * @MethodName : projectDetailCounting
	 * @date : 2019. 5. 10.
	 * @author : Jeon KiChan
	 * @메소드설명 : 완료한 업무 상세사항 개수 가져오기
	 */
	@RequestMapping(value="/project_detail_count.do", method=RequestMethod.GET)
	@ResponseBody
	public int projectDetailCounting(@RequestParam int proPk) {
		
		return projectsService.projectDetailCountByProfk(proPk);
	}
	

	/**
	 * @MethodName : projectDone
	 * @date : 2019. 5. 13.
	 * @author : Jeon KiChan
	 * @메소드설명 : 업무 최종 완료 처리
	 */
	@RequestMapping(value="/project_done.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String projectDone(@ModelAttribute ProjectsVO projectsVO) {
		
		String msg = "";
		
		int result = 0;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();		

		//업무 생성자가 맞는 지 확인
		boolean checkCreateTarget = projectsService.selectProjectTargetCreateCheck(projectsVO.getProPk(), adminVo.getAdminPk());
		
		if(!checkCreateTarget) {
			msg = "업무 최초 생성자가 아닙니다. ";
			return msg;
			
		}
		
		try {			
			result = projectsService.projectDone(projectsVO);
			
		}catch(Exception e) {
			logger.info("에러 발생 = {}", e.getStackTrace());
			
			msg = "에러 발생. 로그를 확인해주세요.";
			return msg;
		}
		
		if(result > 0) {
			msg = "업무 최종 완료";
			return msg;
			
		}else {
			msg = "업무 완료 처리 실패";
			return msg;
			
		}
	}
	
}
