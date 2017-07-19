package com.wxgzpt.bjpygh.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.UserCoupon;
import com.wxgzpt.bjpygh.mapper.UserCouponMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class UserCouponDao {

	SqlSession sqlSession = null;
	
	//插入用户优惠券
	public void insertUserCoupon(UserCoupon userCoupon){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserCouponMapper userCouponMapper = sqlSession.getMapper(UserCouponMapper.class);
			userCouponMapper.insertUserCoupon(userCoupon);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	//更新优惠券状态
	public void updataCouponStatus(Map<Integer, Integer> statusMap){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserCouponMapper userCouponMapper = sqlSession.getMapper(UserCouponMapper.class);
			userCouponMapper.updataCouponStatus(statusMap);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	//更新优惠券价格
	public void updataCouponPrice(Map<Integer, Integer> priceMap){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserCouponMapper userCouponMapper = sqlSession.getMapper(UserCouponMapper.class);
			userCouponMapper.updataCouponPrice(priceMap);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	//更新优惠券价格
		public UserCoupon selectUserCoupon(String userid){
			UserCoupon userCoupon = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				UserCouponMapper userCouponMapper = sqlSession.getMapper(UserCouponMapper.class);
				userCoupon = userCouponMapper.selectUserCoupon(Integer.parseInt(userid));
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return userCoupon;
		}
	
}
