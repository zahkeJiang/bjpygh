package com.wxgzpt.bjpygh.entity;
/**
 * 驾校套餐信息类
 * @author 蒋圆
 *
 */
public class DsPackage {

	private int packageid;	//套餐id
	private String dsname;		//驾校名称
	private String dstype;		//班型
	private String models;		//车型
	private String traintime;	//训练时间
	private int price;			//套餐价格
	private String description;	//套餐描述
	
	public int getPackageid() {
		return packageid;
	}
	public void setPackageid(int packageid) {
		this.packageid = packageid;
	}
	public String getDsname() {
		return dsname;
	}
	public void setDsname(String dsname) {
		this.dsname = dsname;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getTraintime() {
		return traintime;
	}
	public void setTraintime(String traintime) {
		this.traintime = traintime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
