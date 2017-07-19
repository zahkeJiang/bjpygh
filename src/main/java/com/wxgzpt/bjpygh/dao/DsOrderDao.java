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
		List<DsOrder> dsOrderlist = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsOrderMapper dsOrderMapper = sqlSession.getMapper(DsOrderMapper.class);
			dsOrderlist = dsOrderMapper.getOrderById(userid);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return dsOrderlist;
	}
		
	
}
