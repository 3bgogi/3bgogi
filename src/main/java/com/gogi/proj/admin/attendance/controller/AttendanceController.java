package com.gogi.proj.admin.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.admin.attendance.model.AttendanceService;
import com.gogi.proj.admin.attendance.vo.AdminAttendanceVO;
import com.gogi.proj.admin.attendance.vo.AdminDaysoffVO;
import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminRolesVO;
import com.gogi.proj.security.AdminServiceImpl;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.security.JobCodeVO;
import com.gogi.proj.util.DateCompare;

@Controller
public class AttendanceController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	//출근시 메세지 랜덤으로 보이도록
	private String[] workMessage = {"오늘 하루 우리 파트너를 '3번 이상' 칭찬해 보는건 어떨까요? 화이팅!",
									"웃어보세요^^ [행복해서] 웃는게 아니라 웃어서 [행복]한거래요~ 화이팅!"};
	
	/**
	 * @MethodName : attendanceLogGet
	 * @date : 2019. 3. 12.
	 * @author : Jeon KiChan
	 * @메소드설명 : 모든 관리자의 출퇴근 기록 보기
	 */
   @RequestMapping(value="/admin/attendance/log.do", method=RequestMethod.GET)
   public String attendanceLogGet(Model model) {
	   
	   List<AdminVO> adminvoList = adminService.selectAllAdmins();
	   List<AdminAttendanceVO> aaYearAndMonthList = attendanceService.selectAdminAttendanceYearAndMonth();
	   
	   model.addAttribute("adminvoList", adminvoList);
	   model.addAttribute("aaYearAndMonthList", aaYearAndMonthList);
	   
	   return "admin/attendance/attendance_log";
   }
   
   @RequestMapping(value="/admin/attendance/log_Detail.do", method=RequestMethod.POST)
   @ResponseBody
   public List<AdminAttendanceVO> selectAdminAttendanceByFkAndYearAndMonth(@ModelAttribute AdminAttendanceVO aaVO){
	   
	   return attendanceService.selectAdminAttendanceByFkAndYearAndMonth(aaVO);
   }
   
   /**
	 * @MethodName : adminAttendancePageGet 사용하지 않음
	 * @date : 2019. 3. 5.
	 * @author : Jeon KiChan
	 * @메소드설명 : 출 퇴근 기록 페이지로 이동, 유저의 간단 정보와 출결 기록을 파라미터로 넘김
	 */
  @RequestMapping(value="/admin/attendance.do", method=RequestMethod.GET)
  public String adminAttendancePageGet(HttpSession session, Model model) {
	   
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	   AdminVO adminVo = (AdminVO)auth.getPrincipal();
	   
	   AdminAttendanceVO aaVO = attendanceService.selectAdminAttendance(adminVo.getAdminPk());
	   
	   List<AdminAttendanceVO> aaList =  attendanceService.selectTotalAdminBreaks(adminVo.getAdminPk());
	   
	   List<JobCodeVO> jcList = adminService.selectJobCode();
	   
	   model.addAttribute("adminVo", adminVo);
	   model.addAttribute("aaVO", aaVO);
	   model.addAttribute("aaList", aaList);
	   model.addAttribute("jcList", jcList);
	   
	   return "admin/attendance/attendance";
	   
  }
  
  /**
	 * @MethodName : adminAttendanceCheck 사용하지 않음
	 * @date : 2019. 3. 6.
	 * @author : Jeon KiChan
	 * @메소드설명 : 출퇴근 기록 페이지에서 출근 혹은 퇴근 버튼을 누를 경우, 출근-> insert, 퇴근-> 출근 기록의 pk로 update
	 */
  @RequestMapping(value="/admin/attendance_check.do", method=RequestMethod.GET)
  public String adminAttendanceCheck  (@RequestParam int checkWork,
		   								@RequestParam(value="aaPk", required=false) int aaPk,
		   								@RequestParam(value="adminPk", required=false) int adminPk,
		   								Model model) {
	   
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	   AdminVO adminVo = (AdminVO)auth.getPrincipal();
	   
	   if(adminVo == null) {
		   
		   return "redirect:/login.do";
	   }
	   
	   String msg = "";
	   String url = "/admin/attendance/admin_attendance_status.do";
	   if(checkWork == 1) {
		 //출근 찍기
		   DateCompare dc = new DateCompare();
		   
		   attendanceService.insertAdminAttendanceStartWork(adminPk);
		   
		   if(dc.dateCompareWithDatabase(adminVo.getAdminWorktime())) {
			   
			   msg = workMessage[(int)Math.floor((Math.random()*workMessage.length))];   
		   }else {
			   msg = "지각 인거 같아요..ㅜㅜ";
		   }
		   
	   }else if(checkWork ==2) {
		   //퇴근으로 업데이트 하기
		   attendanceService.updateAdminAttendanceEndWork(aaPk);
		   msg = "퇴근 완료! 수고하셨습니다!!! ";
	   }
	   
	   model.addAttribute("msg",msg);
	   model.addAttribute("url",url);
	   
	   return "common/message";
  }
  
  
  /**
	 * @MethodName : adminAttendanceStatusGet
	 * @date : 2019. 3. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 팀원 -> 근태관리 -> 팀별 근퇴 확인 페이지
	 */
  @RequestMapping(value="/admin/attendance/admin_attendance_status.do", method=RequestMethod.GET)
  public String adminAttendanceStatusGet(Model model) {
	  
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	   AdminVO adminVo = (AdminVO)auth.getPrincipal();
	   
	   AdminAttendanceVO aaVO = attendanceService.selectAdminAttendance(adminVo.getAdminPk());
	   
	   List<AdminAttendanceVO> aaList =  attendanceService.selectTodayAttendanceStatus();
	   
	   model.addAttribute("adminVo", adminVo);
	   model.addAttribute("aaVO", aaVO);
	   model.addAttribute("aaList", aaList);
	  
	  return "admin/attendance/admin_attendance_status";
  }
  
  
  /**
	 * @MethodName : teamInfoDetail
	 * @date : 2019. 3. 21.
	 * @author : Jeon KiChan
	 * @메소드설명 : 출퇴근 기록 확인에서 상세 보기 누를 경우 
	 */
  @RequestMapping(value="/admin/team/team_info_detail.do", method=RequestMethod.GET)
  public String teamInfoDetailGet(@RequestParam int adminPk, Model model) {
	  
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	  AdminVO adminAuth = (AdminVO)auth.getPrincipal();
	  
	  OrderSearchVO osVO= new OrderSearchVO();
	  
	  if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
	  
	  int counting = 0;
	  DatesVO datesVO = new DatesVO();
	  datesVO.setAdminPk(adminPk);
	  
	  for(String role :adminAuth.getRoles()) {
		
		  if(role.equals("ROLE_ADMIN")) counting++;
		  
	  }
	  
	  if(adminPk != adminAuth.getAdminPk()) {
		  
		  if(counting == 0) {
			  return "redirect:/access_denied_page.do";
		  }
			  
	  }
	  
	  List<AdminDaysoffVO> adList = attendanceService.selectAdmindaysoffByAdminFk(adminPk);
	  AdminVO adminVO = adminService.selectAdminInfoByAdminPk(adminPk);
	  
	  //출결 기록에서 년도 기록 수정날짜 2019-04-01 
	  List<DatesVO> datesYearList = attendanceService.selectAdminAttendanceYearLog(datesVO);
	  
	  List<AdminRolesVO> adRoleList = adminService.selectRolesByAdminId(adminVO.getAdminId());
	  
	  model.addAttribute("osVO", osVO);
	  model.addAttribute("adList", adList);
	  model.addAttribute("adminVO", adminVO);
	  model.addAttribute("datesYearList", datesYearList);
	  model.addAttribute("adRoleList", adRoleList);
	  
	  return "/admin/team/team_info_detail";
  }
  
  
  /**
 	 * @MethodName : teamInfoDetailPost
 	 * @date : 2019. 3. 22.
 	 * @author : Jeon KiChan
 	 * @메소드설명 : 팀원 기록 상세확인 창에서 휴무신청
 	 */
  @RequestMapping(value="/admin/team/team_info_detail.do", method=RequestMethod.POST)
  public String teamInfoDetailPost(@ModelAttribute AdminDaysoffVO adminDaysoffVO, Model model) {
	  
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  
	  AdminVO adminAuth = (AdminVO)auth.getPrincipal();
	  
	  String msg = "";
	  String url = "/admin/team/team_info_detail.do?adminPk="+adminDaysoffVO.getAdminFk();
	  
	  int result = attendanceService.insertAdmindaysoff(adminDaysoffVO);
	  
	  if(result > 0) {
		  msg = "휴무 신청 완료";
		  
	  }else {
		  msg = "시스템 오류로 인한 휴무 등록 실패";
		  
	  }
	  
	  model.addAttribute("msg", msg);
	  model.addAttribute("url", url);
	  
	  return "common/message";
  }
  
  /**
 	 * @MethodName : daysoffUpdate
 	 * @date : 2019. 3. 25.
 	 * @author : Jeon KiChan
 	 * @메소드설명 : 팀원 기록 상세확인 창에서 휴무신청 후 승인 및 삭제
 	 */
  @RequestMapping(value="/admin/team/admin_daysoff_change.do", method=RequestMethod.GET)
  public String daysoffUpdate(@RequestParam int updateType, @RequestParam int adPk, @RequestParam int adminPk, Model model) {
	  
	  String msg = "";
	  String url = "/admin/team/team_info_detail.do?adminPk="+adminPk;
	  
	  if(updateType == 1) {
		  
		  attendanceService.permissionDaysoffByAdPk(adPk);
		  msg = "휴무 승인 완료";
		 
	  }else {
		  
		  attendanceService.deleteAdmindaysoffByAdPk(adPk);
		  msg = "휴무 삭제 완료";
	  }
	  
	  model.addAttribute("msg", msg);
	  model.addAttribute("url", url);
	  
	  return "common/message";
  }
  
  /**
	 * @MethodName : selectAdminAttendanceMonthLogByYear
	 * @date : 2019. 4. 01.
	 * @author : Jeon KiChan
	 * @메소드설명 : 팀원 기록 상세페이지에서 년도를 누를 시 월 로그 가져오기
	 */
  @RequestMapping(value="/admin/team/ajax_aamonth.do")
  @ResponseBody
  public List<DatesVO> selectAdminAttendanceMonthLogByYear(@ModelAttribute DatesVO datesVO) {
	  
	  return attendanceService.selectAdminAttendanceMonthLogByYear(datesVO);
  }
  
  /**
	 * @MethodName : selectTotalAdminBreaksByDatesVO
	 * @date : 2019. 4. 01.
	 * @author : Jeon KiChan
	 * @메소드설명 : 팀원 기록 상세페이지에서 년도와 월을 누를 시 그에 해당하는 휴무기록 가져오기
	 */
  @RequestMapping(value="/admin/team/ajax_admin_breaks.do")
  @ResponseBody
  public List<AdminAttendanceVO> selectTotalAdminBreaksByDatesVO(@ModelAttribute DatesVO datesVO){
	  
	  return attendanceService.selectTotalAdminBreaksByDatesVO(datesVO);
  }
  
  
  /**
   * 
   * @MethodName : selectWeeklyPriceByAdminPk
   * @date : 2020. 4. 14.
   * @author : Jeon KiChan
   * @param osVO
   * @return
   * @메소드설명 : 날짜 범위로 주급 계산해서 가져오기
   */
  @RequestMapping(value="/admin/team/ajax_weekly_price.do", method=RequestMethod.GET)
  @ResponseBody
  public List<Map<String, String>> selectWeeklyPriceByAdminPk(@ModelAttribute OrderSearchVO osVO){
	  
	  return attendanceService.selectWeeklyPriceByAdminPk(osVO);
  }
  
  @RequestMapping(value="/admin/team/attendance_change.do", method=RequestMethod.GET)
  public String attendanceChangeGet(@ModelAttribute AdminAttendanceVO aaVOPram, Model model) {
	  
	  AdminAttendanceVO aaVO = attendanceService.selectAdminAttendanceByAaPk(aaVOPram);
	  
	  model.addAttribute("aaVO", aaVO);
	  
	  return "admin/team/attendance_change";
  }
  
  @RequestMapping(value="/admin/team/attendance_change.do", method=RequestMethod.POST)
  @ResponseBody
  public boolean attendanceChangePost(@ModelAttribute AdminAttendanceVO aaVO) {
	  
	  int result = attendanceService.updateAdminAttendance(aaVO);
	  
	  if(result != 0) {
		  return true;
	  }else {
		  return false;
	  }
  }
  
}
