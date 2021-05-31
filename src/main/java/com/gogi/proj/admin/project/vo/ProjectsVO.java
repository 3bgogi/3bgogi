package com.gogi.proj.admin.project.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gogi.proj.security.AdminVO;

public class ProjectsVO {

	private int proPk; // 업무 고유 번호
	private String proTitle; //업무명
	private String proTitleColor; //업무명 색상 예) #ffffff
	private String proDetail; // 업무 사항
	private Timestamp proAlarmDate; //업무 알람일
	private int proAlarmType; //반복 타입 0=반복 없음, 1=업무알람일 기준, 2=업무알람일의 매월 일마다 반복, 3= 업무알람일 기준 요일마다반복
	private int proRepeatDay; // 반복 타입이 2일 경우 day 계산해서 넣기
	private int proRepeatWeek; //반복 타입이 3일 경우 요일 계산해서 넣기
	private int proLeft; //메모장의 자유 위치를 하기 위한 absolute left 
	private int proTop; //메모장의 자유 위치를 위한 absolute top
	private int proZindex; //메모장의 자유 위치를 설정 후 먼저 보일 z-index
	private int proSorting; //자유 위치가 아직 순서의 정렬 값
	private boolean proDeleteFlag; //삭제여부, 데이터베이스상을 완전삭제하지 않음
	private String proThumbnailImage; //썸네일 유니크 파일명
	private String proThumbnailImageRealName; //썸네일 파일명
	private String proThumbnailImageExtType; //썸네일 파일 확장자명 예) png, jpg, jpeg
	private boolean proFinishedFlag;
	private Timestamp proRegdate; //등록일
	
	// 추가사항
	private List<ProjectDetailVO> projectDetailList;
	private List<ProjectTagVO> projectTagList;
	private List<ProjectTargetVO> projectTargetList;
	private List<ProjectCommentVO> projectCommentList;
	private List<AdminVO> adminList;
	
	// 알람추가사항
	private boolean proTopAlarmCheck;
	
	// 알람일을 문자열로
	private String proAlarmDateString;
	
	public ProjectsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectsVO(int proPk, String proTitle, String proTitleColor, String proDetail, Timestamp proAlarmDate,
			int proAlarmType, int proRepeatDay, int proRepeatWeek, int proLeft, int proTop, int proZindex,
			int proSorting, boolean proDeleteFlag, String proThumbnailImage, String proThumbnailImageRealName,
			String proThumbnailImageExtType, boolean proFinishedFlag, Timestamp proRegdate,
			List<ProjectDetailVO> projectDetailList, List<ProjectTagVO> projectTagList,
			List<ProjectTargetVO> projectTargetList, List<ProjectCommentVO> projectCommentList, List<AdminVO> adminList,
			boolean proTopAlarmCheck, String proAlarmDateString) {
		super();
		this.proPk = proPk;
		this.proTitle = proTitle;
		this.proTitleColor = proTitleColor;
		this.proDetail = proDetail;
		this.proAlarmDate = proAlarmDate;
		this.proAlarmType = proAlarmType;
		this.proRepeatDay = proRepeatDay;
		this.proRepeatWeek = proRepeatWeek;
		this.proLeft = proLeft;
		this.proTop = proTop;
		this.proZindex = proZindex;
		this.proSorting = proSorting;
		this.proDeleteFlag = proDeleteFlag;
		this.proThumbnailImage = proThumbnailImage;
		this.proThumbnailImageRealName = proThumbnailImageRealName;
		this.proThumbnailImageExtType = proThumbnailImageExtType;
		this.proFinishedFlag = proFinishedFlag;
		this.proRegdate = proRegdate;
		this.projectDetailList = projectDetailList;
		this.projectTagList = projectTagList;
		this.projectTargetList = projectTargetList;
		this.projectCommentList = projectCommentList;
		this.adminList = adminList;
		this.proTopAlarmCheck = proTopAlarmCheck;
		this.proAlarmDateString = proAlarmDateString;
	}

	public int getProPk() {
		return proPk;
	}

	public void setProPk(int proPk) {
		this.proPk = proPk;
	}

	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}

	public String getProTitleColor() {
		return proTitleColor;
	}

	public void setProTitleColor(String proTitleColor) {
		this.proTitleColor = proTitleColor;
	}

	public String getProDetail() {
		return proDetail;
	}

	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public Timestamp getProAlarmDate() {
		return proAlarmDate;
	}

	public void setProAlarmDate(Timestamp proAlarmDate) {
		this.proAlarmDate = proAlarmDate;
	}

	public int getProAlarmType() {
		return proAlarmType;
	}

	public void setProAlarmType(int proAlarmType) {
		this.proAlarmType = proAlarmType;
	}

	public int getProRepeatDay() {
		return proRepeatDay;
	}

	public void setProRepeatDay(int proRepeatDay) {
		this.proRepeatDay = proRepeatDay;
	}

	public int getProRepeatWeek() {
		return proRepeatWeek;
	}

	public void setProRepeatWeek(int proRepeatWeek) {
		this.proRepeatWeek = proRepeatWeek;
	}

	public int getProLeft() {
		return proLeft;
	}

	public void setProLeft(int proLeft) {
		this.proLeft = proLeft;
	}

	public int getProTop() {
		return proTop;
	}

	public void setProTop(int proTop) {
		this.proTop = proTop;
	}

	public int getProZindex() {
		return proZindex;
	}

	public void setProZindex(int proZindex) {
		this.proZindex = proZindex;
	}

	public int getProSorting() {
		return proSorting;
	}

	public void setProSorting(int proSorting) {
		this.proSorting = proSorting;
	}

	public boolean isProDeleteFlag() {
		return proDeleteFlag;
	}

	public void setProDeleteFlag(boolean proDeleteFlag) {
		this.proDeleteFlag = proDeleteFlag;
	}

	public String getProThumbnailImage() {
		return proThumbnailImage;
	}

	public void setProThumbnailImage(String proThumbnailImage) {
		this.proThumbnailImage = proThumbnailImage;
	}

	public String getProThumbnailImageRealName() {
		return proThumbnailImageRealName;
	}

	public void setProThumbnailImageRealName(String proThumbnailImageRealName) {
		this.proThumbnailImageRealName = proThumbnailImageRealName;
	}

	public String getProThumbnailImageExtType() {
		return proThumbnailImageExtType;
	}

	public void setProThumbnailImageExtType(String proThumbnailImageExtType) {
		this.proThumbnailImageExtType = proThumbnailImageExtType;
	}

	public boolean isProFinishedFlag() {
		return proFinishedFlag;
	}

	public void setProFinishedFlag(boolean proFinishedFlag) {
		this.proFinishedFlag = proFinishedFlag;
	}

	public Timestamp getProRegdate() {
		return proRegdate;
	}

	public void setProRegdate(Timestamp proRegdate) {
		this.proRegdate = proRegdate;
	}

	public List<ProjectDetailVO> getProjectDetailList() {
		return projectDetailList;
	}

	public void setProjectDetailList(List<ProjectDetailVO> projectDetailList) {
		this.projectDetailList = projectDetailList;
	}

	public List<ProjectTagVO> getProjectTagList() {
		return projectTagList;
	}

	public void setProjectTagList(List<ProjectTagVO> projectTagList) {
		this.projectTagList = projectTagList;
	}

	public List<ProjectTargetVO> getProjectTargetList() {
		return projectTargetList;
	}

	public void setProjectTargetList(List<ProjectTargetVO> projectTargetList) {
		this.projectTargetList = projectTargetList;
	}

	public List<ProjectCommentVO> getProjectCommentList() {
		return projectCommentList;
	}

	public void setProjectCommentList(List<ProjectCommentVO> projectCommentList) {
		this.projectCommentList = projectCommentList;
	}

	public List<AdminVO> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<AdminVO> adminList) {
		this.adminList = adminList;
	}

	public boolean isProTopAlarmCheck() {
		return proTopAlarmCheck;
	}

	public void setProTopAlarmCheck(boolean proTopAlarmCheck) {
		this.proTopAlarmCheck = proTopAlarmCheck;
	}

	public String getProAlarmDateString() {
		return proAlarmDateString;
	}

	public void setProAlarmDateString(String proAlarmDateString) {
		this.proAlarmDateString = proAlarmDateString;
	}

	@Override
	public String toString() {
		return "ProjectsVO [proPk=" + proPk + ", proTitle=" + proTitle + ", proTitleColor=" + proTitleColor
				+ ", proDetail=" + proDetail + ", proAlarmDate=" + proAlarmDate + ", proAlarmType=" + proAlarmType
				+ ", proRepeatDay=" + proRepeatDay + ", proRepeatWeek=" + proRepeatWeek + ", proLeft=" + proLeft
				+ ", proTop=" + proTop + ", proZindex=" + proZindex + ", proSorting=" + proSorting + ", proDeleteFlag="
				+ proDeleteFlag + ", proThumbnailImage=" + proThumbnailImage + ", proThumbnailImageRealName="
				+ proThumbnailImageRealName + ", proThumbnailImageExtType=" + proThumbnailImageExtType
				+ ", proFinishedFlag=" + proFinishedFlag + ", proRegdate=" + proRegdate + ", projectDetailList="
				+ projectDetailList + ", projectTagList=" + projectTagList + ", projectTargetList=" + projectTargetList
				+ ", projectCommentList=" + projectCommentList + ", adminList=" + adminList + ", proTopAlarmCheck="
				+ proTopAlarmCheck + ", proAlarmDateString=" + proAlarmDateString + "]";
	}

}
