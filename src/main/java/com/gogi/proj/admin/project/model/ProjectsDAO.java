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

public interface ProjectsDAO {
	
	/*업무 관련 부분*/	
	//업무 삽입
	public int insertProject(ProjectsVO projectVO);
	
	//업무 리스트 가져오기
	public List<ProjectsVO> selectProject(ProjectSearchParameter psp);
	
	//업무 관련사항 전부 가져오기 psp는 다양한 조건으로 설정 : VO임
	public ProjectsVO selectProjectDetailView(ProjectSearchParameter psp);
	
	//업무 제목 색상 변경
	public int changeProjectTitleColor(ProjectsVO vo);
	
	//업무 삭제 처리(완전 삭제처리는 하지 않음)
	public int deleteProjectProcess(int proPk);
	
	//업무 알람일 변경
	public int changeProjectAlarmDate(ProjectsVO vo);
	
	//업무 알람 타입 변경
	public int changeProjectAlarmType(ProjectsVO vo);
	
	//업무 완료하기
	public int projectDone(ProjectsVO vo);
	
	
	/*업무 대상자*/
	//업무 대상자 넣기
	public int insertProjectTarget(ProjectTargetVO projectTargetVO);
	
	//업무 대상자가 맞는지 체크
	public boolean selectProjectTargetChecks(Map<String, Object> ptProFkadminPk);
	
	//업무 대상자를 기본으로 상단 알람에 고정 상태 변경
	public int changeProjectTargetTopFixedStatus(Map<String, Object> map);
	
	//업무 조건 부 검색 부분
	public List<ProjectsVO> selectProjectConditionWithAlarmAndOwn(int adminPk);
	
	//업무 만든이 여부 체크
	public boolean selectProjectTargetCreateCheck(Map<String, Object> map);
	
	//업무 대상자 전부 가져오기
	public List<AdminNormalVO> selectProjectTargetIncludeCheck(int proPk);
	
	//업무 대상자 관리자 혹은 만든이가 추가
	public int addProjectTarget(ProjectTargetVO projectTargetVO);
	
	//업무 대상자 삭제
	public int deleteProjectTarget(int ptPk);
	
	//업무 알람 체크
	public int checkProjectAlarm(ProjectTargetVO vo);
	
	//관리자 고유값과 업무 고유값으로 업무대상자 고유값 검색하여 가져오기
	public int selectProjectTargetByPropkAndAdminpkForPtpk(ProjectTargetVO vo);
	
	
	/*업무 완료사항*/
	//업무 완료사항 삽입 자세한 사항은 serviceiple 부분에 있음
	public int insertProjectDetail(ProjectDetailVO projectDetailVO);
	
	//업무 완료사항 중요도 체크 상태 변경
	public int changeProjectDetailImportantStatus(Map<String, Object> map);
	
	//primary key로 업무 완료사항 전체 가져오기
	public ProjectDetailVO selectProjectDetailByPdPk(int pdPk);
	
	//primary key로 업무 완료사항 삭제하기
	public int deleteProjectDetailByPdPk(int pdPk);
	
	//업무 완료 사항 개수
	public int projectDetailCountByProfk(int proPk);
	
	
	/*업무 태그*/
	//업무 태그 삽입
	public int insertProjectTag(ProjectTagVO projectTagVO);
	
	//업무 태그 삭제
	public int deleteProjectTag(int ptagPk);
	
	//업무 태그 카운팅
	public int selectProjectTagCounting(int proPk);
	
	//업무 태그 조회용 : 0일 경우 전부다 조회할 수 있게 아니라면 자신것만
	public List<ProjectTagVO> selectProjectTag(int adminPk);
}
