package com.wxgzpt.bjpygh.entity;

import java.util.List;

/**
 * 驾校信息类
 * @author 蒋圆
 *
 */
public class DsInformation {

	private String dsname;			//驾校名称
	private String dsimage;			//驾校图片
	private String dsintro;			//驾校简介
	private String address;			//驾校地址
	
	private List<DsPackage> dspList;//驾校套餐列表

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getDsimage() {
		return dsimage;
	}

	public void setDsimage(String dsimage) {
		this.dsimage = dsimage;
	}

	public String getDsintro() {
		return dsintro;
	}

	public void setDsintro(String dsintro) {
		this.dsintro = dsintro;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<DsPackage> getDspList() {
		return dspList;
	}

	public void setDspList(List<DsPackage> dspList) {
		this.dspList = dspList;
	}
	
	
}
