package com.wxgzpt.bjpygh.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.User;
import com.wxgzpt.bjpygh.mapper.UserMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class UserDao {

	SqlSession sqlSession = null;
	
	//通过openid获取用户手机号信息
	public String getPhoneNumber(String openid){
		String phoneNumber = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			phoneNumber = userMapper.getPhoneNumber(openid);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
	return phoneNumber;
	}
	
	//插入从微信获取的用户数据
	public void InsertUserFromWx(User user){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(user);
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
	
	//通过openid获取用户user_id
	public String getUserIdByOpenid(String openid){
		String user_id = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			user_id = userMapper.getUserId(openid);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			System.out.println("DaoOpenid="+openid);
			System.out.println("DaoUserid="+user_id);
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return user_id;
	}
	
	//绑定用户，更新用户数据
	public void bondUser(Map<String,String> map){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.bondUser(map);
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
	
	//通过手机号获取用户信息
	public User getUserById(String userid){
		User user = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			user = userMapper.getUserById(userid);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return user;
	}
	
	//通过手机号获取用户信息
		public User getUserByOpenid(String openid){
			User user = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
				user = userMapper.getUserByOpenid(openid);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return user;
		}
	
	//通过手机号获取用户信息
	public String getUserIdByPN(String phonenumber){
		String userid = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userid = userMapper.getUserIdByPN(phonenumber);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return userid;
	}
	
	//绑定用户，更新用户数据
		public void bondRealName(Map<String,String> map){
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
				userMapper.bondRealName(map);
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
		
	//绑定用户，更新用户数据
	public void changeUserPoints(Map<String,String> map){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.changeUserPoints(map);
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
	//绑定用户，更新用户数据
		public void changeUserInfo(Map<String,String> map){
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
				userMapper.changeUserInfo(map);
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
	
}
