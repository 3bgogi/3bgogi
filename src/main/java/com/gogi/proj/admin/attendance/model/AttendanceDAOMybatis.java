package com.gogi.proj.admin.attendance.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.admin.attendance.vo.AdminAttendanceVO;
import com.gogi.proj.admin.attendance.vo.AdminDaysoffVO;
import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class AttendanceDAOMybatis extends SqlSessionDaoSupport implements AttendanceDAO{

	private String namespace = "admin.attendance";
	
	public List<AdminAttendanceVO> selectAdminAttendanceYearAndMonth(){
		
		return getSqlSession().selectList(namespace+".selectAdminAttendanceYearAndMonth");
	}
	
	public List<AdminAttendanceVO> selectAdminAttendanceByFkAndYearAndMonth(AdminAttendanceVO aaVO){
		
		return getSqlSession().selectList(namespace+".selectAdminAttendanceByFkAndYearAndMonth", aaVO);
	}
	
	public List<AdminAttendanceVO> selectAdminAttendanceList(int adminPk){
		
		return getSqlSession().selectList(namespace+".selectAdminAttendanceList", adminPk);
	}

	public int insertAdminAttendanceStartWork(int adminPk) {
		
		return getSqlSession().insert(namespace+".insertAdminAttendanceStartWork", adminPk);
	}
	
	public int updateAdminAttendanceEndWork(int aaPk) {
		
		return getSqlSession().update(namespace+".updateAdminAttendanceEndWork", aaPk);
	}
	
	public AdminAttendanceVO selectAdminAttendance(int adminPk) {
		
		return getSqlSession().selectOne(namespace+".selectAdminAttendance",adminPk);
	}

	@Override
	public List<AdminAttendanceVO>  selectTotalAdminBreaks(int adminPk) {
		
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTotalAdminBreaks", adminPk);
	}

	@Override
	public List<AdminAttendanceVO> selectTodayAttendanceStatus() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTodayAttendanceStatus");
	}

	@Override
	public List<AdminDaysoffVO> selectAdmindaysoffByAdminFk(int adminPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAdmindaysoffByAdminFk", adminPk);
	}

	@Override
	public int insertAdmindaysoff(AdminDaysoffVO admindaysoffVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertAdmindaysoff", admindaysoffVO);
	}

	@Override
	public int permissionDaysoffByAdPk(int adPk) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".permissionDaysoffByAdPk", adPk);
	}

	@Override
	public int deleteAdmindaysoffByAdPk(int adPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteAdmindaysoffByAdPk", adPk);
	}

	@Override
	public List<DatesVO> selectAdminAttendanceYearLog(DatesVO datesVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAdminAttendanceYearLog", datesVO);
	}

	@Override
	public List<DatesVO> selectAdminAttendanceMonthLogByYear(DatesVO datesVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAdminAttendanceMonthLogByYear",datesVO);
	}

	@Override
	public List<AdminAttendanceVO> selectTotalAdminBreaksByDatesVO(DatesVO datesVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTotalAdminBreaksByDatesVO", datesVO);
	}

	@Override
	public List<Map<String, String>> selectWeeklyPriceByAdminPk(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectWeeklyPriceByAdminPk", osVO);
	}

	@Override
	public AdminAttendanceVO selectAdminAttendanceByAaPk(AdminAttendanceVO aaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectAdminAttendanceByAaPk", aaVO);
	}

	@Override
	public int updateAdminAttendance(AdminAttendanceVO aaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateAdminAttendance", aaVO);
	}
}
