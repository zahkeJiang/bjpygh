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
	private int orderprice;		//璁㈠崟浠锋牸
	private String traintime;		//璁粌鏃堕棿
	private String models;			//杞﹀瀷
	private String ordernumber;		//鍟嗘埛璁㈠崟鍙�
	private String address;			//鐢ㄦ埛鍦板潃
	private String realname;		//鐢ㄦ埛鐪熷疄濮撳悕
	private String note;			//澶囨敞
	private int orderid;			//订单id
	private String description;		//套餐描述
	private String paytime;			//付款时间
	private String submittime;		//提交审核材料通过时间
	private String signtime;		//报名完成时间
	private String gettime;			//得到材料时间
	private String refundtime;			//退款时间
	private String imageurl;			//退款时间
	private int originalprice;			//退款时间
	
	public int getOriginalprice() {
		return originalprice;
	}
	public void setOriginalprice(int originalprice) {
		this.originalprice = originalprice;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	public String getSigntime() {
		return signtime;
	}
	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public int getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(int orderprice) {
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
