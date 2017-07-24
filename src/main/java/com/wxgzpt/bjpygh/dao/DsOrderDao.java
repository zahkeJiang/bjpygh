package com.wxgzpt.bjpygh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.mapper.DsOrderMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class DsOrderDao {

	SqlSession sqlSession = null;
	
	//鎻掑叆璁㈠崟
	public void insertOrder(DsOrder dsorder){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
			dsOrderMapper.insertOrder(dsorder);
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

	
	//鑾峰彇璁㈠崟淇℃伅
	public List<DsOrder> getOrderById(String userid){
		List<DsOrder> dsOrder = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
			dsOrder = dsOrderMapper.getOrderById(userid);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return dsOrder;
	}
	
	//鑾峰彇璁㈠崟淇℃伅
		public DsOrder getDsOrder(String userid){
			DsOrder dsOrder = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
				dsOrder = dsOrderMapper.getDsOrder(userid);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return dsOrder;
		}
		
	//鎻掑叆璁㈠崟
		public void updateOrder(DsOrder dsorder){
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
				dsOrderMapper.updateOrder(dsorder);
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
	
		//鎻掑叆璁㈠崟
		public void changeStatus(Map<String, String> map){
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
				dsOrderMapper.changeStatus(map);
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
		
		//鎻掑叆璁㈠崟
		public void changeStatusByNum(Map<String, String> map){
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
				dsOrderMapper.changeStatusByNum(map);
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
