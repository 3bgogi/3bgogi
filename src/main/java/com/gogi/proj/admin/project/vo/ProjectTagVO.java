package com.gogi.proj.admin.project.vo;

public class ProjectTagVO {

	private int ptagPk; //태그 고유 번호
	private int ptagProFk; //업무 고유 번호
	private int ptagAdminFk; //관리자 고유 번호
	private String ptagTitle; //태그명
	
	public ProjectTagVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectTagVO(int ptagPk, int ptagProFk, int ptagAdminFk, String ptagTitle) {
		super();
		this.ptagPk = ptagPk;
		this.ptagProFk = ptagProFk;
		this.ptagAdminFk = ptagAdminFk;
		this.ptagTitle = ptagTitle;
	}

	public int getPtagPk() {
		return ptagPk;
	}

	public void setPtagPk(int ptagPk) {
		this.ptagPk = ptagPk;
	}

	public int getPtagProFk() {
		return ptagProFk;
	}

	public void setPtagProFk(int ptagProFk) {
		this.ptagProFk = ptagProFk;
	}

	public int getPtagAdminFk() {
		return ptagAdminFk;
	}

	public void setPtagAdminFk(int ptagAdminFk) {
		this.ptagAdminFk = ptagAdminFk;
	}

	public String getPtagTitle() {
		return ptagTitle;
	}

	public void setPtagTitle(String ptagTitle) {
		this.ptagTitle = ptagTitle;
	}

	@Override
	public String toString() {
		return "ProjectTagVO [ptagPk=" + ptagPk + ", ptagProFk=" + ptagProFk + ", ptagAdminFk=" + ptagAdminFk
				+ ", ptagTitle=" + ptagTitle + "]";
	}
	
}
