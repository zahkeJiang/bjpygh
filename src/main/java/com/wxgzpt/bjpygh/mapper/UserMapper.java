package com.wxgzpt.bjpygh.mapper;

import java.util.Map;

import com.wxgzpt.bjpygh.entity.User;

public interface UserMapper {
	public String getPhoneNumber(String openid);
	public void insertUser(User user);
	public String getUserId(String openid);
	public String getUserIdByPN(String phonenumber);
	public void bondUser(Map<String,String> map);
	public void bondRealName(Map<String,String> map);
	public User getUserById(String userid);
	
	public void changeUserPoints(Map<String, String> map);
}
