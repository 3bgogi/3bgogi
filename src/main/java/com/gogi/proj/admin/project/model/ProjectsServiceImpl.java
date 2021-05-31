package com.gogi.proj.admin.project.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.admin.project.vo.ProjectDetailVO;
import com.gogi.proj.admin.project.vo.ProjectSearchParameter;
import com.gogi.proj.admin.project.vo.ProjectTagVO;
import com.gogi.proj.admin.project.vo.ProjectTargetVO;
import com.gogi.proj.admin.project.vo.ProjectsVO;
import com.gogi.proj.security.AdminNormalVO;
import com.gogi.proj.security.AdminVO;

@Service
public class ProjectsServiceImpl implements ProjectsService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectsServiceImpl.class);
	@Autowired
	private ProjectsDAO projectsDao;

	@Override
	public int insertProject(ProjectsVO projectVO) {
		// TODO Auto-generated method stub
		return projectsDao.insertProject(projectVO);
	}

	@Override
	public int insertProjectTarget(int adminPk, int proPk) {
		// TODO Auto-generated method stub
		ProjectTargetVO projectTargetVO = new ProjectTargetVO();
		projectTargetVO.setPtAdminFk(adminPk);
		projectTargetVO.setPtProFk(proPk);

		return projectsDao.insertProjectTarget(projectTargetVO);
	}

	@Override
	public List<ProjectsVO> selectProject(ProjectSearchParameter psp) {
		// TODO Auto-generated method stub
		
		return projectsDao.selectProject(psp);
	}

	@Override
	public List<ProjectsVO> selectProjectConditionWithAlarmAndOwn(int adminPk) {
		// TODO Auto-generated method stub
		return projectsDao.selectProjectConditionWithAlarmAndOwn(adminPk);
	}

	@Override
	public ProjectsVO selectProjectDetailView(ProjectSearchParameter psp, int adminPk) {
		// TODO Auto-generated method stub
		psp.setAdminPk(adminPk);
		
		return projectsDao.selectProjectDetailView(psp);
	}

	@Override
	public int insertProjectDetail(ProjectDetailVO projectDetailVO, String adminId) {
		// TODO Auto-generated method stub
		projectDetailVO.setPdAdmin(adminId);
		
		return projectsDao.insertProjectDetail(projectDetailVO);
	}

	@Override
	public boolean selectProjectTargetChecks(int ptProFk, int ptAdminFk) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ptProFk", ptProFk);
		map.put("adminPk", ptAdminFk);
		
		return projectsDao.selectProjectTargetChecks(map);
	}

	@Override
	public int changeProjectTargetTopFixedStatus(int status, int ptProFk, int ptAdminFk) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("status", status);
		map.put("ptProFk", ptProFk);
		map.put("adminPk", ptAdminFk);
		
		return projectsDao.changeProjectTargetTopFixedStatus(map);
	}

	@Override
	public int changeProjectDetailImportantStatus(int status, int pdPk) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("status", status);
		map.put("pdPk", pdPk);
		
		return projectsDao.changeProjectDetailImportantStatus(map);
	}

	@Override
	public ProjectDetailVO selectProjectDetailByPdPk(int pdPk) {
		// TODO Auto-generated method stub
		return projectsDao.selectProjectDetailByPdPk(pdPk);
	}

	@Override
	public int deleteProjectDetailByPdPk(int pdPk) {
		// TODO Auto-generated method stub
		return projectsDao.deleteProjectDetailByPdPk(pdPk);
	}

	@Override
	public int changeProjectTitleColor(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.changeProjectTitleColor(vo);
	}

	@Override
	public int insertProjectTag(ProjectTagVO projectTagVO) {
		// TODO Auto-generated method stub
		int countResult = projectsDao.selectProjectTagCounting(projectTagVO.getPtagProFk());
		
		int result= 0;
		
		//태그가 5개 이상이라면 더 이상 등록하지 못함
		if(countResult >= 5) {
			result = -1;
			
		}else {
			result = projectsDao.insertProjectTag(projectTagVO);
			
		}
		
		return result;
	}

	@Override
	public int deleteProjectTag(int ptagPk) {
		// TODO Auto-generated method stub
		
		return projectsDao.deleteProjectTag(ptagPk);
	}

	@Override
	public boolean selectProjectTargetCreateCheck(int proPk, int adminPk) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("proPk", proPk);
		map.put("adminPk", adminPk);
		
		return projectsDao.selectProjectTargetCreateCheck(map);
		
	}

	@Override
	public int deleteProjectProcess(int proPk) {
		// TODO Auto-generated method stub
		return projectsDao.deleteProjectProcess(proPk);
	}

	@Override
	public List<AdminNormalVO> selectProjectTargetIncludeCheck(int proPk) {
		// TODO Auto-generated method stub
		return projectsDao.selectProjectTargetIncludeCheck(proPk);
	}

	@Override
	public int addProjectTarget(ProjectTargetVO projectTargetVO) {
		// TODO Auto-generated method stub
		return projectsDao.addProjectTarget(projectTargetVO);
	}

	@Override
	public int deleteProjectTarget(int ptPk) {
		// TODO Auto-generated method stub
		return projectsDao.deleteProjectTarget(ptPk);
	}

	@Override
	public List<ProjectTagVO> selectProjectTag(int adminPk) {
		// TODO Auto-generated method stub
		return projectsDao.selectProjectTag(adminPk);
	}

	@Override
	public int changeProjectAlarmDate(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.changeProjectAlarmDate(vo);
	}

	@Override
	public int changeProjectAlarmType(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.changeProjectAlarmType(vo);
	}

	@Override
	public int checkProjectAlarm(ProjectTargetVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.checkProjectAlarm(vo);
	}

	@Override
	public int projectDetailCountByProfk(int proPk) {
		// TODO Auto-generated method stub
		return projectsDao.projectDetailCountByProfk(proPk);
	}

	@Override
	public int selectProjectTargetByPropkAndAdminpkForPtpk(ProjectTargetVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.selectProjectTargetByPropkAndAdminpkForPtpk(vo);
	}

	@Override
	public int projectDone(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return projectsDao.projectDone(vo);
	}
	
	
	
	
}
