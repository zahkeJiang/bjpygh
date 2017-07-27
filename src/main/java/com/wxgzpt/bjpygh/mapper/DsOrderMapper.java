package com.wxgzpt.bjpygh.mapper;

import java.util.List;
import java.util.Map;

import com.wxgzpt.bjpygh.entity.DsOrder;

public interface DsOrderMapper {

	public List<DsOrder> getOrderById(String userid);
	public DsOrder getDsOrder(String userid);
	public void insertOrder(DsOrder dsorder);
	public void updateOrder(DsOrder dsorder);
	public void changeStatus(Map<String, String> map);
	public void changeStatusByNum(Map<String, String> map);
	public String getUserIdByOrderNum(String ordernumber);
	
}
