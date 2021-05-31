package com.gogi.proj.admin.project.vo;

import java.sql.Timestamp;

public class ProjectCommentVO {

	private int pcPk;
	private int pcProFk;
	private int pcAdminFk;
	private int pcPdFk;
	private String pcDetail;
	private Timestamp pcUpdate;
	private Timestamp pcRegdate;
	
	public ProjectCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectCommentVO(int pcPk, int pcProFk, int pcAdminFk, int pcPdFk, String pcDetail, Timestamp pcUpdate,
			Timestamp pcRegdate) {
		super();
		this.pcPk = pcPk;
		this.pcProFk = pcProFk;
		this.pcAdminFk = pcAdminFk;
		this.pcPdFk = pcPdFk;
		this.pcDetail = pcDetail;
		this.pcUpdate = pcUpdate;
		this.pcRegdate = pcRegdate;
	}

	public int getPcPk() {
		return pcPk;
	}

	public void setPcPk(int pcPk) {
		this.pcPk = pcPk;
	}

	public int getPcProFk() {
		return pcProFk;
	}

	public void setPcProFk(int pcProFk) {
		this.pcProFk = pcProFk;
	}

	public int getPcAdminFk() {
		return pcAdminFk;
	}

	public void setPcAdminFk(int pcAdminFk) {
		this.pcAdminFk = pcAdminFk;
	}

	public int getPcPdFk() {
		return pcPdFk;
	}

	public void setPcPdFk(int pcPdFk) {
		this.pcPdFk = pcPdFk;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public Timestamp getPcUpdate() {
		return pcUpdate;
	}

	public void setPcUpdate(Timestamp pcUpdate) {
		this.pcUpdate = pcUpdate;
	}

	public Timestamp getPcRegdate() {
		return pcRegdate;
	}

	public void setPcRegdate(Timestamp pcRegdate) {
		this.pcRegdate = pcRegdate;
	}

	@Override
	public String toString() {
		return "ProjectCommentVO [pcPk=" + pcPk + ", pcProFk=" + pcProFk + ", pcAdminFk=" + pcAdminFk + ", pcPdFk="
				+ pcPdFk + ", pcDetail=" + pcDetail + ", pcUpdate=" + pcUpdate + ", pcRegdate=" + pcRegdate + "]";
	}

}
