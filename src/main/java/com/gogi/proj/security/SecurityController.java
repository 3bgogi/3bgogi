package com.gogi.proj.security;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import com.gogi.proj.admin.attendance.vo.AdminAttendanceVO;
import com.gogi.proj.util.DateCompare;

@Controller
public class SecurityController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	private String [] msgList = {""};

	/*@RequestMapping(value="/loginInvalidCheck.do")
    public String loginInvalidId(Model model, HttpSession session, HttpServletRequest request){
          logger.info("get Login");
          //customUserDetail에 set한 값을 getter를 통해 가져오는 작업을 가능하게 함
          AdminVO userDetails = (AdminVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
          
          //이런식으로 세션에 값을 넣어주면 컨트롤러에서 사용가능
          session.setAttribute("id", userDetails.getUsername());
          session.setAttribute("name", userDetails.getAdminname());
          session.setAttribute("auth", userDetails.getRoles());
          
          return "goIndex";
    }*/
    
	/**
	 * @MethodName : loginGet
	 * @date : 2019. 2. 11.
	 * @author : Jeon KiChan
	 * @메소드설명 : 로그인 페이지 이동
	 */
    @RequestMapping(value="/login.do", method=RequestMethod.GET)
    public String loginGet(Model model,HttpSession session, HttpServletRequest request){
    	
    	AdminVO adminVO = null;
    	
    	try {
    		
    		adminVO = (AdminVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
    		
    	}catch(Exception e) {
    		
    		logger.info("admin info null.");
    		
    	}
    	
    	if(adminVO != null) {

    		return "redirect:/main/home.do";
    	}
    	
    	return "security/login_security";
          
    }
    
    /**
	 * @MethodName : loginFail
	 * @date : 2019. 2. 12.
	 * @author : Jeon KiChan
	 * @메소드설명 : 로그인 실패 할 경우 로그인 페이지 이동하면서 로그인 실패했다는 파라미터를 넘김
	 */
    @RequestMapping(value="/loginFail.do", method=RequestMethod.GET)
    public String loginFail(Model model,HttpSession session, HttpServletRequest request){;
    	
    	model.addAttribute("fail",true);
    	
    	return "security/login_security";
          
    }
    
    /**
	 * @MethodName : adminLogOut
	 * @date : 2019. 2. 12.
	 * @author : Jeon KiChan
	 * @메소드설명 : 로그아웃 기능, 로그인 했을 때의 유저 정보 세션을 전부 날림
	 */
   @RequestMapping(value="/logout.do")
   public String adminLogOut(HttpServletRequest request, HttpSession session){

          session.invalidate();

          return "login";
    }

   
   /**
	 * @MethodName : accessDeniedPage
	 * @date : 2019. 2. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 권한이 없는 페이지 일 경우 이동 페이지
	 */
   //권한이 없는 페이지의 경우
   @RequestMapping(value="/access_denied_page.do", method=RequestMethod.GET)
   public String accessDeniedPage(HttpServletRequest request) {
	   
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   UrlPathHelper urlPathHelper = new UrlPathHelper();
	   String pathUrl = urlPathHelper.getOriginatingRequestUri(request);

	   AdminVO adminVo = (AdminVO)auth.getPrincipal();
	   logger.info("----------------------------------");
	   logger.info("허가되지 않은 계정의 접근.");
	   logger.info("request url => {}", pathUrl);
	   logger.info("access ID : {}", adminVo.getUsername());
	   logger.info("adminName : {}", adminVo.getAdminname());
	   logger.info("contract Number : {}", adminVo.getAdminPhone());
	   logger.info("----------------------------------");
	   
	   return "security/access_denied";
   }
    
   /**
	 * @MethodName : adminSignupGet
	 * @date : 2019. 3. 6.
	 * @author : Jeon KiChan
	 * @메소드설명 : 관리자 회원 가입 페이지
	 */
   @RequestMapping(value="/sign_up", method=RequestMethod.GET)
   public String adminSignupGet() {
	   
	   return "security/sign_up";
   }
   
   /**
	 * @MethodName : adminSignupPost
	 * @date : 2019. 3. 6.
	 * @author : Jeon KiChan
	 * @메소드설명 : 관리자 회원 가입 페이지에서 insert :: 중복된 아이디 체크 후 있을 경우 x, 없을 경우 insert. 
	 */
   @RequestMapping(value="/sign_up", method=RequestMethod.POST)
   public String adminSignupPost(@ModelAttribute AdminVO adminVO, Model model) {
	   String msg = "";
	   String url = "/login.do";
	   
	   int duplicated = adminService.selectAdminDuplicateId(adminVO.getAdminId());
	   
	   if(duplicated == 0) {
		   adminVO.setAdminPass(passEncoder.encode(adminVO.getAdminPass()));
		   adminService.insertAdminSignup(adminVO);
		   
		   AdminRolesVO arVO = new AdminRolesVO();
		   
		   arVO.setAdminId(adminVO.getAdminId());
		   arVO.setRole("ROLE_USER");
		   adminService.addAdminAuthRole(arVO);
		   msg="회원가입 완료.";
		   
		   
	   }else {
		   msg = "중복된 아이디가 있습니다.";
		   url = "/sign_up.do";
		   
	   }
	   
	   model.addAttribute("msg", msg);
	   model.addAttribute("url", url);
	   
	   return "common/message";
   }
   
   
   /**
	 * @MethodName : teamAuth
	 * @date : 2019. 3. 7.
	 * @author : Jeon KiChan
	 * @메소드설명 : 팀원 각자의 조그만 정보와 권한 조회 페이지 이동 
	 */
   @RequestMapping(value="/admin/team/team_auth.do", method=RequestMethod.GET)
   public String teamAuth(Model model) {
	   
	   List<AdminVO> adminList = adminService.selectAllAdmins();
	   
	   model.addAttribute("adminList", adminList);
	   
	   return "admin/team/team_auth";
   }
   
   //현재 사용하지 않음
   @RequestMapping(value="/admin/ableChange.do")
   public String adminAbledChange(@RequestParam int adminPk, @RequestParam int abled, Model model) {
	   AdminVO adminVO = new AdminVO();
	   adminVO.setAdminPk(adminPk);
	   
	   String msg = "";
	   String url = "/admin/team/team_auth.do";
	   
	   if(abled == 0) {
		   msg = "해당 관리자 계정이 활성화됩니다.";
		   adminVO.setEnabled(1);
		   adminService.adminEnabledChange(adminVO);
	   }else {
		   msg = "해당 관리자 계정이 비활성화됩니다.";
		   adminVO.setEnabled(0);
		   adminService.adminEnabledChange(adminVO);
	   }
	   
	   model.addAttribute("msg", msg);
	   model.addAttribute("url", url);
	   
	   return "common/message";
   }
   
   /**
	 * @MethodName : selectAdminAuth
	 * @date : 2019. 3. 7.
	 * @author : Jeon KiChan
	 * @메소드설명 : 계정의 아이디로 권한 조회하기
	 */
   @RequestMapping(value="/admin/admin_auth.do")
   @ResponseBody
   public List<AdminRolesVO> selectAdminAuth(@RequestParam String adminId){
	   
	   return adminService.selectRolesByAdminId(adminId);
   }
   
   /**
	 * @MethodName : addOrDeleteAdminAuth
	 * @date : 2019. 3. 7.
	 * @author : Jeon KiChan
	 * @메소드설명 : 계정의 아이디로 권한 추가
	 */
   @RequestMapping(value="/admin/add_or_delete_Auth.do", method=RequestMethod.GET)
   public String addOrDeleteAdminAuth(@RequestParam String adminId, 
		   								@RequestParam int adminRolePk,
		   								@RequestParam int adminPk, 
		   								@RequestParam String roleName,
		   								Authentication auth,
		   								Model model) {
	   String msg = "";
	   String url = "/admin/team/team_info_detail.do?adminPk="+adminPk;
	   
	   int authCount = 0;
	   
	   for(GrantedAuthority a : auth.getAuthorities()) {
			if(a.getAuthority().equals("ROLE_ADMIN")) authCount++;
		}
	   
	   if(authCount < 1) {
		   msg = "사용자 수정 권한이 없습니다.";
		   return "common/message";
	   }
	   
	   if(roleName.equals("ROLE_DEVELOPER")) {
		   msg = "개발자 권한은 수정할 수 없습니다."; 
		   return "common/messgae";
	   }

	   AdminRolesVO arVO = new AdminRolesVO();
	   
	   logger.info("adminId = {}", adminId);
	   
	   arVO.setAdminId(adminId);
	   arVO.setAdminRolePk(adminRolePk);
	   arVO.setRole(roleName);

	   if(adminRolePk < 1) {
		   logger.info("arVO tostring = {}", arVO);
		   adminService.addAdminAuthRole(arVO);
		   msg = "해당 관리자 계정에 권한 추가 완료.";
	   }else {
		   adminService.deleteAdminAuth(adminRolePk);
		   msg = "해당 관리 계정의 권한 삭제 완료.";
	   }
	   
	   model.addAttribute("msg", msg);
	   model.addAttribute("url", url);
	   
	   return "common/message";
	   
   }
   
   
   /**
    * 
    * @MethodName : updateAdminInfo
    * @date : 2020. 10. 23.
    * @author : Jeon KiChan
    * @param adminVO
    * @param model
    * @return
    * @메소드설명 : 관리자 정보 변경
    */
   @RequestMapping(value="/update_admin_info.do", method=RequestMethod.POST)
   public String updateAdminInfo(@ModelAttribute AdminVO adminVO, Model model) {
	   
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	   AdminVO adminAuth = (AdminVO)auth.getPrincipal();
	
	   String msg = "";
	   String url = "/admin/team/team_info_detail.do?adminPk="+adminVO.getAdminPk();
	   
	   if(adminVO.getAdminPk() != adminAuth.getAdminPk()) {
		   msg = "본인이 아니면 정보를 변경할 수 없습니다";
		
	   }else {
		   msg = adminService.updateAdminInfo(adminVO);
		   
	   }
			 
	   
	   model.addAttribute("msg", msg);
	   model.addAttribute("url", url);
	   
	   return "common/message";
	   
   }
   
   
   
   /**
    * 
    * @MethodName : changeAdminPassword
    * @date : 2020. 10. 23.
    * @author : Jeon KiChan
    * @param adminVO
    * @param model
    * @return
    * @메소드설명 : 관리자 비밀번호 변경
    */
   @RequestMapping(value="/change_admin_pass.do", method=RequestMethod.POST)
   public String changeAdminPassword(@ModelAttribute AdminVO adminVO, Model model) {
	   
	   String msg = "";
	   String url = "/admin/team/team_info_detail.do?adminPk="+adminVO.getAdminPk();
	   
	   int result = adminService.changeAdminPassword(adminVO);
	   
	   if(result > 0 ) {
		   msg = "비밀번호 변경 완료";
		   
	   }else {
		   msg = "비밀번호 변경 실패";
	   }
	   
	   model.addAttribute("msg", msg);
	   model.addAttribute("url", url);
	   
	   return "common/message";
   }

}
