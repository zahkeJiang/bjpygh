package com.wxgzpt.bjpygh.entity;

import java.util.List;

public class Status {

	private int status;
	private List<DsInformation> dspInfolist;
	private List<DsPackage> dsplist;
	private List<DsOrder> dsOrder;
	private int price;
	private int count;
	private Object data;
	private List<Object> datas;
	private String msg;
	private String imageurl;
	private String out_trade_no;
	private String timeStamp;
	private String paySign;
	
	
	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Object> getDatas() {
		return datas;
	}

	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DsOrder> getDsOrder() {
		return dsOrder;
	}

	public void setDsOrder(List<DsOrder> dsOrder) {
		this.dsOrder = dsOrder;
	}

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

	public void setOrderNumber(String ordernumber) {
		out_trade_no = ordernumber;
	}
	
	public String getOrderNumber() {
		return out_trade_no;
	}

	
}
