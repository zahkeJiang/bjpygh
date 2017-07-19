package com.wxgzpt.bjpygh.entity;
/**
 * 用户优惠券信息
 * @author 蒋圆
 *
 */
public class UserCoupon {

	private long userid;			//用户唯一标识
	private String phonenumber;	//手机号
	private int couponstatus;		//优惠券使用状态
	private int couponprice;		//优惠价格
	private int coupontype;		//优惠类型
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
	public int getCouponstatus() {
		return couponstatus;
	}
	public void setCouponstatus(int couponstatus) {
		this.couponstatus = couponstatus;
	}
	public int getCouponprice() {
		return couponprice;
	}
	public void setCouponprice(int couponprice) {
		this.couponprice = couponprice;
	}
	public int getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(int coupontype) {
		this.coupontype = coupontype;
	}
	
}
