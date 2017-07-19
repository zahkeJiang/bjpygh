package com.wxgzpt.bjpygh.entity;

import java.util.List;

public class Status {

	private int status;
	private List<DsInformation> dspInfolist;
	private List<DsPackage> dsplist;
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<DsPackage> getDsplist() {
		return dsplist;
	}

	public void setDsplist(List<DsPackage> dsplist) {
		this.dsplist = dsplist;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<DsInformation> getDspInfolist() {
		return dspInfolist;
	}

	public void setDspInfolist(List<DsInformation> dspInfolist) {
		this.dspInfolist = dspInfolist;
	}
	
}
