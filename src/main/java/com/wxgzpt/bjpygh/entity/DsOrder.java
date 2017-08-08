package com.wxgzpt.bjpygh.entity;

import java.util.Date;


/**
 * 鐢ㄦ埛璁㈠崟淇℃伅
 * @author 钂嬪渾
 *
 */
public class DsOrder {
	
	private long userid;			//鐢ㄦ埛鍞竴鏍囪瘑
	private String phonenumber;	//鎵嬫満鍙�
	private int orderstatus;		//璁㈠崟鐘舵��
	private String dstype;			//鐝瀷
	private String dsname;			//椹炬牎鍚嶇О
	private String orderprice;		//璁㈠崟浠锋牸
	private String traintime;		//璁粌鏃堕棿
	private String models;			//杞﹀瀷
	private String ordernumber;		//鍟嗘埛璁㈠崟鍙�
	private String address;			//鐢ㄦ埛鍦板潃
	private String realname;		//鐢ㄦ埛鐪熷疄濮撳悕
	private String note;			//澶囨敞
	private int orderid;			//订单id
	private String description;		//套餐描述
	private Date paytime;			//付款时间
	private Date submittime;		//提交审核材料通过时间
	private Date signtime;		//报名完成时间
	private Date gettime;			//得到材料时间
	
	public Date getSigntime() {
		return signtime;
	}
	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public Date getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
	public Date getGettime() {
		return gettime;
	}
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getDsname() {
		return dsname;
	}
	public void setDsname(String dsname) {
		this.dsname = dsname;
	}
	public String getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(String orderprice) {
		this.orderprice = orderprice;
	}
	public String getTraintime() {
		return traintime;
	}
	public void setTraintime(String traintime) {
		this.traintime = traintime;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	
}
