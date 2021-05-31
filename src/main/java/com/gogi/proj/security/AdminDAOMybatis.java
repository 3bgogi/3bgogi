package com.gogi.proj.security;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.admin.attendance.vo.AdminAttendanceVO;

@Repository
public class AdminDAOMybatis extends SqlSessionDaoSupport{
	
	private String namespace = "admin";
	private String jobCode = "admin.job_code";
	
	public AdminVO fineAdminByUsername(String username) {
		return getSqlSession().selectOne(namespace+".fineAdminByUsername",username);
	}
	
	public AdminVO selectAdminInfoByAdminId(String adminId) {
		
		return getSqlSession().selectOne(namespace+".selectAdminInfoByAdminId",adminId);
	}
	
	public int insertAdminSignup(AdminVO adminVo) {
		
		return getSqlSession().insert(namespace+".insertAdminSignup", adminVo);
	}
	
	public AdminVO selectAdminDuplicateId(String adminId) {
		
		return getSqlSession().selectOne(namespace+".selectAdminDuplicateId",adminId);
	}
	
	public List<AdminVO> selectAllAdmins(){
		
		return getSqlSession().selectList(namespace+".selectAllAdmins");
	}
	
	public int adminEnabledChange(AdminVO adminVO) {
		
		return getSqlSession().update(namespace+".adminEnabledChange", adminVO);
	}
	
	public List<AdminRolesVO> selectRolesByAdminId(String adminId){
		
		return getSqlSession().selectList(namespace+".selectRolesByAdminId", adminId);
	}
	
	public int addAdminAuthRole(AdminRolesVO arVO) {
		
		return getSqlSession().insert(namespace+".addAdminAuthRole", arVO);
	}
	
	public int deleteAdminAuth(int adminRolePk) {
		
		return getSqlSession().delete(namespace+".deleteAdminAuth", adminRolePk);
	}
	
	public AdminVO selectAdminInfoByAdminPk(int adminPk) {
		
		return getSqlSession().selectOne(namespace+".selectAdminInfoByAdminPk", adminPk);
	}
	
	public String selectAdminPassByAdminPk(int adminPk) {
		
		return getSqlSession().selectOne(namespace+".selectAdminPassByAdminPk", adminPk);
	}
	
	public int updateAdminInfo(AdminVO adminVO) {
		
		return getSqlSession().update(namespace+".updateAdminInfo", adminVO);
	}
	
	public int changeAdminPassword(AdminVO adminVO) {
		
		return getSqlSession().update(namespace+".changeAdminPassword", adminVO);
	}
	
	public List<JobCodeVO> selectJobCode(){
		
		return getSqlSession().selectList(jobCode+".selectJobCode");
	}
}
