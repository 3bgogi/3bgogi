package com.gogi.proj.aligo.util;

import java.util.List;

public class AligoVOList {

	private List<AligoVO> aligoVOList;
	
	public AligoVOList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoVOList(List<AligoVO> aligoVOList) {
		super();
		this.aligoVOList = aligoVOList;
	}

	public List<AligoVO> getAligoVOList() {
		return aligoVOList;
	}

	public void setAligoVOList(List<AligoVO> aligoVOList) {
		this.aligoVOList = aligoVOList;
	}

	@Override
	public String toString() {
		return "AligoVOList [aligoVOList=" + aligoVOList + "]";
	}
	
}
