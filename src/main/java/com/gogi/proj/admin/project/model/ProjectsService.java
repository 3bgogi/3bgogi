package com.gogi.proj.admin.project.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.admin.project.vo.ProjectDetailVO;
import com.gogi.proj.admin.project.vo.ProjectSearchParameter;
import com.gogi.proj.admin.project.vo.ProjectTagVO;
import com.gogi.proj.admin.project.vo.ProjectTargetVO;
import com.gogi.proj.admin.project.vo.ProjectsVO;
import com.gogi.proj.security.AdminNormalVO;
import com.gogi.proj.security.AdminVO;

public interface ProjectsService {

	//업무 관련 부분
	public int insertProject(ProjectsVO projectVO);
	
	public List<ProjectsVO> selectProject(ProjectSearchParameter psp);
	
	public ProjectsVO selectProjectDetailView(ProjectSearchParameter psp, int adminPk);
	
	public int changeProjectTitleColor(ProjectsVO vo);
	
	public int deleteProjectProcess(int proPk);
	
	public int changeProjectAlarmDate(ProjectsVO vo);
		
	public int changeProjectAlarmType(ProjectsVO vo);
	
	public int projectDone(ProjectsVO vo);
	
	
	//업무 대상자 부분
	public int insertProjectTarget(int adminPk, int proPk);
	
	public int changeProjectTargetTopFixedStatus(int status, int ptProFk, int ptAdminFk);
	
	public boolean selectProjectTargetCreateCheck(int proPk, int adminPk);
	
	public List<AdminNormalVO> selectProjectTargetIncludeCheck(int proPk);
	
	public int addProjectTarget(ProjectTargetVO projectTargetVO);
		
	public int deleteProjectTarget(int ptPk);
	
	public int checkProjectAlarm(ProjectTargetVO vo);
	
	public int selectProjectTargetByPropkAndAdminpkForPtpk(ProjectTargetVO vo);
	
	//업무 조건부 검색 부분
	public List<ProjectsVO> selectProjectConditionWithAlarmAndOwn(int adminPk);
	
	//업무 상세사항
	public int insertProjectDetail(ProjectDetailVO projectDetailVO, String adminId);
	
	public boolean selectProjectTargetChecks(int ptProFk, int ptAdminFk);
	
	public int changeProjectDetailImportantStatus(int status, int pdPk);
	
	public ProjectDetailVO selectProjectDetailByPdPk(int pdPk);
	
	public int deleteProjectDetailByPdPk(int pdPk);
	
	public int projectDetailCountByProfk(int proPk);
	
	
	/*업무 태그*/
	//업무 태그 삽입
	public int insertProjectTag(ProjectTagVO projectTagVO);
	
	//업무 태그 삭제
	public int deleteProjectTag(int ptagPk);
	
	public List<ProjectTagVO> selectProjectTag(int adminPk);
	
}
