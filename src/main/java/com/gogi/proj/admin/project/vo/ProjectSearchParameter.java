package com.gogi.proj.admin.project.vo;

import java.util.List;

public class ProjectSearchParameter {

	private int proPk;
	private int projectNum;
	private int pdDisplayFlag;
	private int pdImportant;
	private int adminPk;
	
	//업무 상세 검색용
	private boolean todayProject;
	private boolean showHide;
	private boolean finishedProject;
	private boolean showAnotherTeamProject;
	private boolean alarmOnlyProject;
	
	//태그
	private List<String> projectTags;
	private boolean showAnotherTeamPorjectTag;
	
	public ProjectSearchParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectSearchParameter(int proPk, int projectNum, int pdDisplayFlag, int pdImportant, int adminPk,
			boolean todayProject, boolean showHide, boolean finishedProject, boolean showAnotherTeamProject,
			boolean showAnotherTeamPorjectTag, boolean alarmOnlyProject, List<String> projectTags) {
		super();
		this.proPk = proPk;
		this.projectNum = projectNum;
		this.pdDisplayFlag = pdDisplayFlag;
		this.pdImportant = pdImportant;
		this.adminPk = adminPk;
		this.todayProject = todayProject;
		this.showHide = showHide;
		this.finishedProject = finishedProject;
		this.showAnotherTeamProject = showAnotherTeamProject;
		this.showAnotherTeamPorjectTag = showAnotherTeamPorjectTag;
		this.alarmOnlyProject = alarmOnlyProject;
		this.projectTags = projectTags;
	}

	public int getProPk() {
		return proPk;
	}

	public void setProPk(int proPk) {
		this.proPk = proPk;
	}

	public int getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}

	public int getPdDisplayFlag() {
		return pdDisplayFlag;
	}

	public void setPdDisplayFlag(int pdDisplayFlag) {
		this.pdDisplayFlag = pdDisplayFlag;
	}

	public int getPdImportant() {
		return pdImportant;
	}

	public void setPdImportant(int pdImportant) {
		this.pdImportant = pdImportant;
	}

	public int getAdminPk() {
		return adminPk;
	}

	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}

	public boolean isTodayProject() {
		return todayProject;
	}

	public void setTodayProject(boolean todayProject) {
		this.todayProject = todayProject;
	}

	public boolean isShowHide() {
		return showHide;
	}

	public void setShowHide(boolean showHide) {
		this.showHide = showHide;
	}

	public boolean isFinishedProject() {
		return finishedProject;
	}

	public void setFinishedProject(boolean finishedProject) {
		this.finishedProject = finishedProject;
	}

	public boolean isShowAnotherTeamProject() {
		return showAnotherTeamProject;
	}

	public void setShowAnotherTeamProject(boolean showAnotherTeamProject) {
		this.showAnotherTeamProject = showAnotherTeamProject;
	}

	public boolean isShowAnotherTeamPorjectTag() {
		return showAnotherTeamPorjectTag;
	}

	public void setShowAnotherTeamPorjectTag(boolean showAnotherTeamPorjectTag) {
		this.showAnotherTeamPorjectTag = showAnotherTeamPorjectTag;
	}

	public boolean isAlarmOnlyProject() {
		return alarmOnlyProject;
	}

	public void setAlarmOnlyProject(boolean alarmOnlyProject) {
		this.alarmOnlyProject = alarmOnlyProject;
	}

	public List<String> getProjectTags() {
		return projectTags;
	}

	public void setProjectTags(List<String> projectTags) {
		this.projectTags = projectTags;
	}

	@Override
	public String toString() {
		return "ProjectSearchParameter [proPk=" + proPk + ", projectNum=" + projectNum + ", pdDisplayFlag="
				+ pdDisplayFlag + ", pdImportant=" + pdImportant + ", adminPk=" + adminPk + ", todayProject="
				+ todayProject + ", showHide=" + showHide + ", finishedProject=" + finishedProject
				+ ", showAnotherTeamProject=" + showAnotherTeamProject + ", showAnotherTeamPorjectTag="
				+ showAnotherTeamPorjectTag + ", alarmOnlyProject=" + alarmOnlyProject + ", projectTags=" + projectTags
				+ "]";
	}

}
