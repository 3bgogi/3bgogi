package com.gogi.proj.product.cost.vo;

public class CostIoOrderVO {

	private int cioPk;
	private int ciFk;
	private int orFk;
	private int cioQty;
	
	public CostIoOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostIoOrderVO(int cioPk, int ciFk, int orFk, int cioQty) {
		super();
		this.cioPk = cioPk;
		this.ciFk = ciFk;
		this.orFk = orFk;
		this.cioQty = cioQty;
	}

	public int getCioPk() {
		return cioPk;
	}

	public void setCioPk(int cioPk) {
		this.cioPk = cioPk;
	}

	public int getCiFk() {
		return ciFk;
	}

	public void setCiFk(int ciFk) {
		this.ciFk = ciFk;
	}

	public int getOrFk() {
		return orFk;
	}

	public void setOrFk(int orFk) {
		this.orFk = orFk;
	}

	public int getCioQty() {
		return cioQty;
	}

	public void setCioQty(int cioQty) {
		this.cioQty = cioQty;
	}

	@Override
	public String toString() {
		return "CostIoOrderVO [cioPk=" + cioPk + ", ciFk=" + ciFk + ", orFk=" + orFk + ", cioQty=" + cioQty + "]";
	}
	
}
