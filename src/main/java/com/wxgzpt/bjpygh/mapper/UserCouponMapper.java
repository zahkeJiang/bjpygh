package com.wxgzpt.bjpygh.mapper;

import java.util.Map;

import com.wxgzpt.bjpygh.entity.UserCoupon;

public interface UserCouponMapper {

	public void insertUserCoupon(UserCoupon userCoupon);
	public void updataCouponStatus(Map<String, String> statusMap);
	public void updataCouponPrice(Map<Integer, Integer> cpriceMap);
	public UserCoupon selectUserCoupon(String userid);
	
}
