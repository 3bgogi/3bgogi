package com.gogi.proj.security;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gogi.proj.admin.attendance.vo.AdminAttendanceVO;

@Service
public class AdminServiceImpl implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	private static final int ABLED_ID = 0;
	private static final int DUPLICATE_ID = 1;
	
	@Inject
	private AdminDAOMybatis dao;
	
	@Autowired
	private PasswordEncoder passEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminVO adminVO = null;

		try {
			
			adminVO =  dao.fineAdminByUsername(username);
			
			if(adminVO != null) {
				
				logger.info(adminVO.getAdminname());
				
			}else {
				
				logger.info("UNIDENTIFIED REPORTER.");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return adminVO;
	}
	
	public AdminVO selectAdminInfoByAdminId(String adminId) {
		
		return dao.selectAdminInfoByAdminId(adminId);
	}
	
	public int insertAdminSignup(AdminVO adminVo) {
		
		return dao.insertAdminSignup(adminVo);
	}
	
	public int selectAdminDuplicateId(String adminId) {
		
		if(dao.selectAdminDuplicateId(adminId) == null) {
			
			return ABLED_ID;
		}else {
			
			return DUPLICATE_ID;
		}
		
	}
	
	public List<AdminVO> selectAllAdmins(){
		
		return dao.selectAllAdmins();
	}
	
	public int adminEnabledChange(AdminVO adminVO) {
		
		return dao.adminEnabledChange(adminVO);
	}
	
	public List<AdminRolesVO> selectRolesByAdminId(String adminId){
		
		return dao.selectRolesByAdminId(adminId);
	}
	
	public int addAdminAuthRole(AdminRolesVO arVO) {
		
		return dao.addAdminAuthRole(arVO);
	}
	
	public int deleteAdminAuth(int adminRolePk) {
		
		return dao.deleteAdminAuth(adminRolePk);
	}
	
	public AdminVO selectAdminInfoByAdminPk(int adminPk) {
		
		return dao.selectAdminInfoByAdminPk(adminPk);
	}
	
	public boolean checkAdminPassByAdminPk(String pass, int adminPk) {
		
		if(passEncoder.matches((CharSequence)pass, dao.selectAdminPassByAdminPk(adminPk))) {
			return true;
			
		}else {
			return false;
			
		}
	}
	
	
	public String updateAdminInfo(AdminVO adminVO) {
		
		String msg = "";
		
		boolean passCheck = checkAdminPassByAdminPk(adminVO.getAdminPass(), adminVO.getAdminPk());
		
		if(passCheck) {
			
			
			int result = dao.updateAdminInfo(adminVO);
			
			if(result > 0) {
				msg = "관리자 정보 변경 완료";
			}else {
				msg = "관리자 정보 변경 실패";
			}
		}else {
			msg = "올바른 비밀번호가 아닙니다";
		}
		
		return msg;
	}
	
	public int changeAdminPassword(AdminVO adminVO) {
		
		adminVO.setAdminPass(passEncoder.encode(adminVO.getAdminPass()));
		
		return dao.changeAdminPassword(adminVO);
	}
	
	public List<JobCodeVO> selectJobCode(){
		
		return dao.selectJobCode();
	}
}
