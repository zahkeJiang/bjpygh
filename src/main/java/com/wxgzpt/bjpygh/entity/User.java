package com.wxgzpt.bjpygh.entity;
/**
 * 用户个人信息类
 * @author 蒋圆
 *
 */
public class User {

	private long userid;			//用户唯一标识
	private String phonenumber;	//手机号
	private String realname;		//真实姓名
	private String school;			//用户所在学校
	private String college;			//用户所在学院
	private String openid;			//公众号标识
	private String nickname;		//用户昵称
	private int sex;				//用户性别
	private String city;			//用户所在城市
	private String country;			//用户所在国家
	private String province;		//用户所在省份
	private String headimageurl;	//用户头像地址
//	private String language;		//用户所用语言
	private String reamark;			//公众号对粉丝的备注
	private String address;			//用户地址
	private int age;				//用户年龄
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHeadimageurl() {
		return headimageurl;
	}
	public void setHeadimageurl(String headimageurl) {
		this.headimageurl = headimageurl;
	}
//	public String getLanguage() {
//		return language;
//	}
//	public void setLanguage(String language) {
//		this.language = language;
//	}
	public String getReamark() {
		return reamark;
	}
	public void setReamark(String reamark) {
		this.reamark = reamark;
	}

}
