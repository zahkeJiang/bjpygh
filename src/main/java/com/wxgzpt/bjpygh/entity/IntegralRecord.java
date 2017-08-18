package com.wxgzpt.bjpygh.entity;
/**
 * 积分记录类
 */

public class IntegralRecord {

	private int recordid;  //唯一标识
	private long userid;	//用户id
	private int value;		//积分值
	private String time;		//时间记录
	private String note;	//积分说明
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
