package com.wxgzpt.bjpygh.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.UserCoupon;
import com.wxgzpt.bjpygh.mapper.UserCouponMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class UserCouponDao {

	SqlSession sqlSession = null;
	
	//鎻掑叆鐢ㄦ埛浼樻儬鍒�
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
	
	//鏇存柊浼樻儬鍒哥姸鎬�
	public void updataCouponStatus(Map<String, String> statusMap){
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
	
	//鏇存柊浼樻儬鍒镐环鏍�
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
	
	//鏇存柊浼樻儬鍒镐环鏍�
		public UserCoupon selectUserCoupon(String userid){
			UserCoupon userCoupon = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				UserCouponMapper userCouponMapper = sqlSession.getMapper(UserCouponMapper.class);
				userCoupon = userCouponMapper.selectUserCoupon(userid);
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
