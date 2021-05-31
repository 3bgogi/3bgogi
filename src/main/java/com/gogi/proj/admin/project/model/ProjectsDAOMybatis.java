package com.gogi.proj.admin.project.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.admin.project.vo.ProjectDetailVO;
import com.gogi.proj.admin.project.vo.ProjectSearchParameter;
import com.gogi.proj.admin.project.vo.ProjectTagVO;
import com.gogi.proj.admin.project.vo.ProjectTargetVO;
import com.gogi.proj.admin.project.vo.ProjectsVO;
import com.gogi.proj.security.AdminNormalVO;
import com.gogi.proj.security.AdminVO;

@Repository
public class ProjectsDAOMybatis extends SqlSessionDaoSupport implements ProjectsDAO {

	private String namespace = "admin.project";

	@Override
	public int insertProject(ProjectsVO projectVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertProject", projectVO);
	}

	@Override
	public int insertProjectTarget(ProjectTargetVO projectTargetVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertProjectTarget", projectTargetVO);
	}

	@Override
	public List<ProjectsVO> selectProject(ProjectSearchParameter psp) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectProject", psp);
	}

	@Override
	public List<ProjectsVO> selectProjectConditionWithAlarmAndOwn(int adminPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectProjectConditionWithAlarmAndOwn", adminPk);
	}

	@Override
	public ProjectsVO selectProjectDetailView(ProjectSearchParameter psp) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectDetailView", psp);
	}

	@Override
	public int insertProjectDetail(ProjectDetailVO projectDetailVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertProjectDetail", projectDetailVO);
	}

	@Override
	public boolean selectProjectTargetChecks(Map<String, Object> ptProFkadminPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectTargetChecks", ptProFkadminPk);
	}

	@Override
	public int changeProjectTargetTopFixedStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeProjectTargetTopFixedStatus", map);
	}

	@Override
	public int changeProjectDetailImportantStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeProjectDetailImportantStatus", map);
	}

	@Override
	public ProjectDetailVO selectProjectDetailByPdPk(int pdPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectDetailByPdPk", pdPk);
	}

	@Override
	public int deleteProjectDetailByPdPk(int pdPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteProjectDetailByPdPk", pdPk);
	}

	@Override
	public int changeProjectTitleColor(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeProjectTitleColor", vo);
	}

	@Override
	public int insertProjectTag(ProjectTagVO projectTagVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertProjectTag", projectTagVO);
	}

	@Override
	public int deleteProjectTag(int ptagPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteProjectTag", ptagPk);
	}

	@Override
	public int selectProjectTagCounting(int proPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectTagCounting", proPk);
	}

	@Override
	public boolean selectProjectTargetCreateCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectTargetCreateCheck", map);
	}

	@Override
	public int deleteProjectProcess(int proPk) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".deleteProjectProcess", proPk);
	}

	@Override
	public List<AdminNormalVO> selectProjectTargetIncludeCheck(int proPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectProjectTargetIncludeCheck", proPk);
	}

	@Override
	public int addProjectTarget(ProjectTargetVO projectTargetVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".addProjectTarget", projectTargetVO);
	}

	@Override
	public int deleteProjectTarget(int ptPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteProjectTarget", ptPk);
	}

	@Override
	public List<ProjectTagVO> selectProjectTag(int adminPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectProjectTag", adminPk);
	}

	@Override
	public int changeProjectAlarmDate(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeProjectAlarmDate", vo);
	}

	@Override
	public int changeProjectAlarmType(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeProjectAlarmType", vo);
	}

	@Override
	public int checkProjectAlarm(ProjectTargetVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".checkProjectAlarm", vo);
	}

	@Override
	public int selectProjectTargetByPropkAndAdminpkForPtpk(ProjectTargetVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectProjectTargetByPropkAndAdminpkForPtpk", vo);
	}

	@Override
	public int projectDetailCountByProfk(int proPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".projectDetailCountByProfk", proPk);
	}

	@Override
	public int projectDone(ProjectsVO vo) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".projectDone", vo);
	}
}
